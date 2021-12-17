package models.v1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AddUsersRequest implements Serializable {

    /* объект с параметрами тенанта ФЛ */
    private TenantModel tenant;

    /* массив данных пользователей */
    private List<UserModel> users;

    public TenantModel getTenant() {
        return this.tenant;
    }

    public void setTenant(TenantModel tenant) {
        this.tenant = tenant;
    }

    public List<UserModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AddUsersRequest)) {
            return false;
        }
        var other = (AddUsersRequest) o;
        return Objects.equals(tenant, other.tenant) && Objects.equals(users, other.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenant, users);
    }
}
