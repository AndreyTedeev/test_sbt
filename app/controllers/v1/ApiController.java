package controllers.v1;

import javax.inject.Inject;

import models.v1.AddUsersRequest;
import models.v1.AddUsersResponse;
import play.libs.Json;
import play.mvc.*;
import services.v1.ApiService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's API v1.
 */
public class ApiController extends Controller {

    private final ApiService api;

    @Inject
    public ApiController(ApiService api) {
        this.api = api;
    }

    /**
     * Эндпоинт добавления и обновления пользователей в БД ЕСА
     * 
     * Описание: Метод предназначен для добавления и\или обновления записей данных
     * пользователей в БД ЕСА.
     * 
     * Требования к реализации:
     * 
     * Метод должен поддерживать пакетную обработку, т.е. может принимать сразу
     * множество сотрудников
     * Запрос должен быть аутентифицирован токеном в заголовке. Проверка валидности
     * токена осуществляется запросом к сервису ekd-license.
     * Метод не принимает пользовательские данные, принимает только хэш от
     * нормализованного значения ФИОДУЛДР
     * Метод: POST
     * 
     * URL: https://{ECA_URL}/api/v1/users
     * 
     * Авторизация Токен авторизации в заголовке Api-Token
     */
    public Result addUsers(Http.Request request) {
        var result = new AddUsersResponse();
        var tokenParam = request.header("Api-Token");
        result.setResult(tokenParam.isPresent()
                && api.isValidToken(tokenParam.get()));
        if (result.getResult()) {
            try {
                var data = Json.fromJson(request.body().asJson(), AddUsersRequest.class);
                result.setCount(api.addUsers(data));
            } catch (Exception e) {
                return badRequest(e.getMessage());
            }
        }

        return ok(Json.toJson(result));
    }

}
