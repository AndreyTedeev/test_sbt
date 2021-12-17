package models.v1;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TenantModel implements Serializable {

    /* UUID тенанта */
    private UUID id;

    /* URL тенанта */
    private String host;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TenantModel)) {
            return false;
        }
        var other = (TenantModel) o;
        return Objects.equals(id, other.id) && Objects.equals(host, other.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, host);
    }

}
