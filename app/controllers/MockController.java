package controllers;

import javax.inject.Inject;

import com.typesafe.config.Config;

import models.v1.EkdLicenseRequest;
import models.v1.EkdLicenseResponse;
import play.libs.Json;
import play.mvc.*;

/**
 * This controller contains an actions to handle HTTP requests to the
 * fake mock controller.
 */
public class MockController extends Controller {
    
    private final Config config;

    @Inject
    public MockController(Config config) {
        this.config = config;
    }

    /* An action that mocks token validaion with ekd-license. */
    public Result ekdLicense(Http.Request request) { 
        if (!request.header(config.getString("ekd.key")).isPresent()) 
        {
            return badRequest("headers not found");
        }
        
        try {
            var token = Json.fromJson(request.body().asJson(), EkdLicenseRequest.class);
            if (token.getToken() == null) 
                throw new Exception("token is null");
        } 
        catch (Exception e) {
            return badRequest(e.getMessage());
        }

        var result = new EkdLicenseResponse();
        result.setExists(true);
        return ok(Json.toJson(result));
    }

}
