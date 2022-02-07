package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.UserBody;
import util.ApiPath;

public class UserRequests extends BaseRequests {
    public Response getUserRequest(String username){
        return RestAssured
                .given()
                .spec(requestSpec.getRequestSpec())
                .pathParam("username",username)
                .get(ApiPath.CRUD_PATH)
                .then()
                .extract()
                .response();
    }
    public Response postUserRequest(UserBody body){
        return RestAssured
                .given()
                .spec(requestSpec.getRequestSpec())
                .body(body)
                .post(ApiPath.USER_PATH)
                .then()
                .extract()
                .response();
    }
    public Response putUserRequest(String username, UserBody body){
        return RestAssured
                .given()
                .spec(requestSpec.getRequestSpec())
                .pathParam("username",username)
                .body(body)
                .put(ApiPath.CRUD_PATH)
                .then()
                .extract()
                .response();
    }
    public Response deleteUserRequest(String username){
        return RestAssured
                .given()
                .spec(requestSpec.getRequestSpec())
                .pathParam("username",username)
                .delete(ApiPath.CRUD_PATH)
                .then()
                .extract()
                .response();
    }
}
