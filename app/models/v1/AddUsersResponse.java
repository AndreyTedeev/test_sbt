package models.v1;

import java.io.Serializable;
import java.util.Objects;

public class AddUsersResponse implements Serializable {
    
    /* Количество обработанных записей */
    private Integer count;

    /* статус */
    private Boolean status;

    public AddUsersResponse() {
    }

    public AddUsersResponse(Integer count, Boolean status) {
        this.count = count;
        this.status = status;
    }
    
    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AddUsersResponse)) {
            return false;
        }
        var other = (AddUsersResponse) o;
        return Objects.equals(count, other.count) && Objects.equals(status, other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, status);
    }
}
