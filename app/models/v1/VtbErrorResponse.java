package models.v1;

public class VtbErrorResponse {

    //
    // Тип ошибки :
    // invalid_scope - В запросе содержатся недоступные для клиента scope значения.
    // invalid_grant - предоставленный код авторизации (или рефреш токен) некорректен, просрочен, отозван,  или выдан для другого клиентского приложения
    // invalid_request - В запросе отсутствуют обязательные параметры, или имеют некорректное значение 
    // unauthorized_client - Клиент с указанным clientId не зарегистрирован в системе, либо не удалось его авторизовать
    // access_denied - Пользователь не завершил процесс аутентификации, тем самым прервал процесс авторизации 
    // unsupported_response_type - Сервер авторизации не поддерживает получение кода авторизации с использованием данного метода
    // agreement_not_signed - Пользователь прошел аутентификацию, но не дал согласие на передачу персональных данных 
    // server_error - Произошла внутренняя ошибка сервера авторизации(условие возникновение ошибки не попадает под другие типы)
    // temporarily_unavailable - Сервер временно не может обработать запрос (сервер перегружен запросами) 
    // invalid_token - предоставленный токен доступа просрочен, отозван, или нераспознан.
    //
    private String error;

    /* Краткое описание ошибки */
    private String error_message;


    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_message() {
        return this.error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

}
