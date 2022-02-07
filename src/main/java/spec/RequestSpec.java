package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import util.ApiPath;

@Getter
public class RequestSpec {
    RequestSpecification requestSpec;

    public RequestSpec() {
        this.requestSpec = new RequestSpecBuilder().setBaseUri(ApiPath.BASEURL).setContentType("application/json").build();
    }
}
