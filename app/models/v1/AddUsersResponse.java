package models.v1;

import java.io.Serializable;
import java.util.Objects;

public class AddUsersResponse implements Serializable {
    
    /* Количество обработанных записей */
    private Integer count;

    /* статус */
    private Boolean result;

    public AddUsersResponse() {
    }

    public AddUsersResponse(Integer count, Boolean result) {
        this.count = count;
        this.result = result;
    }
    
    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AddUsersResponse)) {
            return false;
        }
        var other = (AddUsersResponse) o;
        return Objects.equals(count, other.count) && Objects.equals(result, other.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, result);
    }
}
