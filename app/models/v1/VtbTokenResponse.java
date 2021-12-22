package models.v1;

public class VtbTokenResponse {

    /*
     * Список разрешений, выданных для текущего access токена. приvер :
     * "patronymic gender openid surname name mainMobilePhone email"
     */
    private String scope;

    /*
     * Токен доступа
     */
    private String access_token;

    /*
     * Токен для обновления access_token и id_token
     */
    private String refresh_token;

    /*
     */
    private String id_token;


    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getId_token() {
        return this.id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
    
}
