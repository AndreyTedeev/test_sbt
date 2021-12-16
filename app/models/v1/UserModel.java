package models.v1;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserModel implements Serializable {
    
    /* UUID пользователя HR-Link */
    private UUID id;
    
    /* Хэш SHA256 ФИОДУЛДР пользователя */
    private String fioduldrHash;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFioduldrHash() {
        return this.fioduldrHash;
    }

    public void setFioduldrHash(String fioduldrHash) {
        this.fioduldrHash = fioduldrHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserModel)) {
            return false;
        }
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(fioduldrHash, userModel.fioduldrHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fioduldrHash);
    }

}
