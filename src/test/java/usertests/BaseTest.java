package usertests;

import io.restassured.response.Response;
import model.UserBody;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import services.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;
import static util.Helpers.generateString;

public class BaseTest {
    public static Response response;
    public static String username;
    public static UserBody userBody;
    public UserBody createUserBodyModel(String username) {
        return UserBody.builder()
                .username(username)
                .firstName("Deniz")
                .lastName("aladağ")
                .email("test@test.com")
                .password("123qwe")
                .phone("0555555555")
                .userStatus(0)
                .build();
    }
    public UserBody updateUserBodyModel(String username) {
        return UserBody.builder()
                .username(username+generateString(3))
                .firstName("Deniz")
                .lastName("aladağ")
                .email("test@test.com")
                .password("123qwe")
                .phone("0555555555")
                .userStatus(0)
                .build();
    }
    @BeforeMethod
    public void createNewUser(){
        username = generateString(8);

        userBody = createUserBodyModel(username);

        response = UserService.createNewUser(userBody);
    }
    @AfterMethod
    public void deleteUser(){
        response = UserService.deleteUser(username);


        while (response.statusCode() == 404) {
            response = UserService.deleteUser(username);
        }
        assertTrue(response.statusCode() == 200);

        Response getResponse = UserService.getUser(username);
        assertTrue(getResponse.statusCode() == 404);
        assertThat(getResponse.jsonPath().getString("message"),equalTo("User not found"));
        assertThat(getResponse.jsonPath().getString("type"),equalTo("error"));
    }
}
