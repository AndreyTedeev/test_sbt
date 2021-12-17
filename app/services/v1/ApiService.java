package services.v1;

import java.util.ArrayList;

import javax.inject.Singleton;

import entities.ExternalUser;
import entities.ExternalUserId;
import entities.ExternalUserSystemType;
import entities.Tenant;
import entities.User;
import io.ebean.DB;
import models.v1.AddUsersRequest;
import models.v1.TenantModel;
import models.v1.UserModel;

@Singleton
public class ApiService {

    public int addUsers(AddUsersRequest data) {
        var tenant = getEntity(data.getTenant());

        for (UserModel model : data.getUsers()) {
            var user = getEntity(model);
            DB.save(user);
            if (!tenant.getUsers().contains(user)) {
                tenant.getUsers().add(user);
            }
        }

        DB.save(tenant);
        return data.getUsers().size();
    }

    private Tenant getEntity(TenantModel model) {
        var tenantId = model.getId();
        var tenant = DB.find(Tenant.class, tenantId);
        if (tenant == null) {
            tenant = new Tenant();
            tenant.setId(tenantId);
        }

        tenant.setHost(model.getHost());
        return tenant;
    }

    private User getEntity(UserModel model) {
        var id = model.getId();
        var extId = new ExternalUserId();
        extId.setId(id);
        extId.setSystemType(ExternalUserSystemType.FIODULDR);

        var extUser = new ExternalUser();
        extUser.setExternalUserId(extId);
        extUser.setValue(model.getFioduldrHash());

        var result = DB.find(User.class, id);
        
        if (result == null) {
            result = new User();
            result.setId(id);
            result.setExternalUsers(new ArrayList<ExternalUser>());
            result.getExternalUsers().add(extUser);
        } 
        else if (!result.getExternalUsers().contains(extUser)) {
            result.getExternalUsers().add(extUser);
        }

        
        return result;
    }

}
