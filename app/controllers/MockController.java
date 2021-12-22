package controllers;

import javax.inject.Inject;

import com.typesafe.config.Config;

import models.v1.EkdLicenseRequest;
import models.v1.EkdLicenseResponse;
import models.v1.VtbTokenResponse;
import play.libs.Json;
import play.mvc.*;

/**
 * This controller contains an actions to handle HTTP requests to the fake mock
 * controller.
 */
public class MockController extends Controller {

    private final Config config;

    @Inject
    public MockController(Config config) {
        this.config = config;
    }

    /* An action that mocks token validaion with ekd-license. */
    public Result ekdLicense(Http.Request request) {
        if (!request.header(config.getString("ekd.key")).isPresent()) {
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

    /* An action that mocks vtb auth. */
    public Result vtbAuth(Http.Request request) {
        var result = new VtbTokenResponse();
        result.setScope("patronymic gender openid surname name mainMobilePhone email");
        result.setAccess_token("7b1808f4-9e71-4312-8a4b-dfe1b15881bb");
        result.setRefresh_token("7b1808f4-9e71-4312-8a4b-dfe1b15881bb");
        result.setId_token(
                "eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJDMlZZdjNiNlJIRWlnMm5fNTZiZm5uM0dmSTRhIiwiYXpwIjoiQzJWWXYzYjZSSEVpZzJuXzU2YmZubjNHZkk0YSIsInNwX25hbWUiOiJtb2JpbGUtYmFuay1wYXJ0bmVyIiwiaXNzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6OTQ0My9vYXV0aDIvdG9rZW4iLCJuYmYiOjE2MTkxNTY2MzksImlhdCI6MTYxOTE1NjYzOSwiZXhwIjoxNjE5MTU2OTk5LCJhbXIiOlsiY29kZSJdLCJzdWIiOiIyMDAxNDM0MyIsImF1dGhfc2Vzc2lvbl9pZCI6IjIyMTMwOTIwLWQ5OGUtNDgwNS05ZDA1LTI0ZjFmMDg0YzY1NSIsImRvbWFpbiI6Im1hc3RlciIsImFjciI6Im1zYT1leUpZTFZWelpYSXRVMlZ6YzJsdmJpMUpSQ0k2SWpoaU1UTXlNREkzTFdZMVpUZ3ROR05sTlMxaFpqZzFMVFF6WW1SaU9XTXpOR1JqTkNJc0luSnZkWFJsVG1GdFpTSTZJa1JKVTBGVFZFVlNJaXdpYzJoaGNtUk9ZVzFsSWpvaVJFbFRRVk5VUlZJaUxDSllMVkpQVlZSRkxVNUJUVVVpT2lKRVNWTkJVMVJGVWlJc0lsZ3RVMGhCVWtRdFRrRk5SU0k2SWtSSlUwRlRWRVZTSWl3aVdDMUVaV0oxWnlJNkltWmhiSE5sSWl3aVdDMUpibWwwYVdGMGIzSXRTRzl6ZENJNklrNHZRU0lzSWxndFEyaGhibTVsYkNJNklsZFhWeklpTENKWUxWQnNZWFJtYjNKdElqb2lkMlZpSWl3aVdDMU1iMmRwYmkxTmIyUmxJanB1ZFd4c0xDSllMVlpsY25OcGIyNGlPbTUxYkd3c0lsZ3RVbTlzWlhNaU9tNTFiR3dzSWtoRlFVUkZVakVpT2lKMGNuVmxJaXdpU0VWQlJFVlNNaUk2SW5SbGMzUnpkSEpwYm1jaWZRPT0iLCJtc2Ffc2Vzc2lvbl9pZCI6IjhiMTMyMDI3LWY1ZTgtNGNlNS1hZjg1LTQzYmRiOWMzNGRjNCJ9.Zgq3bUPlxnXKUixCh9NMDA1F3-shwtQ8btARjOtp9TMv_NEzSByR6CWgy6G0lMOCtguCvO4zAs5xd_aSWjOpRHWQP8J81yOU_DsWb6qgjCKZ6KMvzCXkpHLnXbznOxoI4cbGbgxLc6jqYe9yNwbJQapS1clf2LHwtqaGkqtYFJL4pCCrEsSvcn0gFfwJQeLF2xHGhkW3j5SLOexqrUTO70vC-BiSEbx83CzvMyNNsprfei8MOo_kbIh9OnbcoobPREI8Of1XBO0t5khE8EetsO1iptJe8ib_5GkW__QUNUWtrB-LlrFJcZb5LUEcGysczABvV3Huyf6NFrTw_qGt0w");
        return ok(Json.toJson(result));
    }

}
