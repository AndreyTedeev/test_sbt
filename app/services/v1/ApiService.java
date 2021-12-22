package services.v1;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.typesafe.config.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.ExternalUser;
import entities.ExternalUserId;
import entities.ExternalUserSystemType;
import entities.Tenant;
import entities.User;
import io.ebean.DB;
import models.v1.AddUsersRequest;
import models.v1.EkdLicenseRequest;
import models.v1.EkdLicenseResponse;
import models.v1.TenantModel;
import models.v1.UserModel;
import play.libs.Json;
import play.libs.ws.WSClient;

@Singleton
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private final Config config;
    private final WSClient ws;

    @Inject
    public ApiService(Config config, WSClient ws) {
        this.config = config;
        this.ws = ws;
    }

    /*
     * Проверка валидности токена, полученного от HR-Link в запросе на
     * создание\обновление данных пользователей
     * 
     * реализовать файл конфигурации для хранения настроек запроса (URL, Header Key
     * для License-Manager-Token, token value)
     * 
     * Аутентификация: Заголовок запроса должен содержать: License-Manager-Token
     * (Сервису ЕСА будет выдан токен, с которым он будет делать запросы к
     * ekd-license, передавая его в заголовке) Токен должен задаваться через
     * конфигурацию. Имя HTTP-заголовка должно настраиваться через конфигурацию.
     * 
     * URL эндпоинта берем из конфига Название Header Key для License-Manager-Token
     * берем из конфига Значение License-Manager-Token берем из конфига Значение
     * токена для отправки на валидацию берем из Header запроса от HR-Link в
     * EndPoint Users Create Тело запроса: В теле запроса должен передаваться токен,
     * который пришёл от экземпляра HRL в заголовке Api-Token запроса Users Create.
     */
    public Boolean isValidToken(String token) {
        logger.info("called isValidToken");
        var url = config.getString("ekd.url");
        var key = config.getString("ekd.key");
        var tkn = config.getString("ekd.token");
        var data = new EkdLicenseRequest();
        data.setToken(token);

        var result = false;
        var req = ws.url(url).addHeader(key, tkn);
        try {
            var rsp = req.post(Json.toJson(data)).toCompletableFuture().get();
            if (rsp.getStatus() == 200) {
                result = Json.fromJson(rsp.asJson(), EkdLicenseResponse.class).isExists();
            }
            else {
                logger.error("Response status code is : %d", rsp.getStatus());
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    public int addUsers(AddUsersRequest data) {
        var tenant = getEntity(data.getTenant());

        for (UserModel model : data.getUsers()) {
            var user = getEntity(model);
            DB.save(user);
            if (!tenant.getUsers().contains(user)) {
                tenant.getUsers().add(user);
            }
        }

        DB.save(tenant);
        return data.getUsers().size();
    }

    private Tenant getEntity(TenantModel model) {
        var tenantId = model.getId();
        var tenant = DB.find(Tenant.class, tenantId);
        if (tenant == null) {
            tenant = new Tenant();
            tenant.setId(tenantId);
        }

        tenant.setHost(model.getHost());
        return tenant;
    }

    private User getEntity(UserModel model) {
        var id = model.getId();
        var extId = new ExternalUserId();
        extId.setId(id);
        extId.setSystemType(ExternalUserSystemType.FIODULDR);

        var extUser = new ExternalUser();
        extUser.setExternalUserId(extId);
        extUser.setValue(model.getFioduldrHash());

        var result = DB.find(User.class, id);

        if (result == null) {
            result = new User();
            result.setId(id);
            result.setExternalUsers(new ArrayList<ExternalUser>());
            result.getExternalUsers().add(extUser);
        }
        else if (!result.getExternalUsers().contains(extUser)) {
            result.getExternalUsers().add(extUser);
        }

        return result;
    }

}
