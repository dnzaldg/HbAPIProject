package services;

import io.restassured.response.Response;
import model.UserBody;

public class UserService extends BaseService {
    public static Response createNewUser(UserBody body) {
        return userRequests.postUserRequest(body);
    }

    public static Response getUser(String username) {
        return userRequests.getUserRequest(username);
    }

    public static Response deleteUser(String username) {
        return userRequests.deleteUserRequest(username);
    }
    public static Response updateUser(String username, UserBody body) {
        return userRequests.putUserRequest(username,body);
    }
}
