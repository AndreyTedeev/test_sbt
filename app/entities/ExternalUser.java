package entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_external_id")
public class ExternalUser {

    @EmbeddedId
    private ExternalUserId externalUserId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User user;
    

    @Column(name = "value")
    private String value;


    public ExternalUserId getExternalUserId() {
        return this.externalUserId;
    }

    public void setExternalUserId(ExternalUserId externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
