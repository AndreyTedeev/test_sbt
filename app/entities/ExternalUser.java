package entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_external_id")
public class ExternalUser {

    @EmbeddedId
    private ExternalUserId externalUserId;
    
    @Column(name = "value")
    private String value;
}
