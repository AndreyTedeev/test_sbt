package entities;

import java.util.UUID;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tenant {
    
    @Id
    private UUID id; 

    private String host;
    
    @ManyToMany(mappedBy = "tenants")
    private List<User> users;

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
    
}
