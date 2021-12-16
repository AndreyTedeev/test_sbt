package controllers.v1;

import com.google.gson.Gson;

import models.v1.AddUsersRequest;
import models.v1.AddUsersResponse;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's API v1.
 */
public class ApiController extends Controller {

    private final Gson gson = new Gson();

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
        try {
            var data = gson.fromJson(request.body().asText(), AddUsersRequest.class);
            // TODO : process data
            var result = new AddUsersResponse(data.getUsers().size(), true);
            return ok( gson.toJson(result));
        } catch (Exception e) {
            return badRequest(e.getMessage());
        }
    }

}
