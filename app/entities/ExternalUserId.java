package entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExternalUserId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "user_id")
    private UUID id;
    
    @Column(name = "system_type")
    private String systemType;
    
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSystemType() {
        return this.systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof ExternalUserId))
            return false;
            ExternalUserId castOther = (ExternalUserId) other;
        return id.equals(castOther.id) && systemType.equals(castOther.systemType);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.id.hashCode();
        hash = hash * prime + this.systemType.hashCode();
        return hash;
    }
}
