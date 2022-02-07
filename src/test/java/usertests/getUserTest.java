package usertests;

import io.restassured.response.Response;
import model.UserBody;
import org.testng.annotations.Test;
import services.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;
import static util.Helpers.generateString;

public class getUserTest extends BaseTest {

    @Test
    public void shouldUserFoundWithValidUserName() {
        Response response = UserService.getUser(username);

        while (response.statusCode() == 404) {
            response = UserService.getUser(username);
        }

        assertTrue(response.statusCode() == 200);

        UserBody getUserBody = response.getBody().as(UserBody.class);

        assertThat(getUserBody.getUsername(), is(equalTo(userBody.getUsername())));
        assertThat(getUserBody.getPassword(), is(equalTo(userBody.getPassword())));
        assertThat(getUserBody.getEmail(), is(equalTo(userBody.getEmail())));
        assertThat(getUserBody.getUserStatus(), is(equalTo(userBody.getUserStatus())));
        assertThat(getUserBody.getFirstName(), is(equalTo(userBody.getFirstName())));
        assertThat(getUserBody.getLastName(), is(equalTo(userBody.getLastName())));
        assertThat(getUserBody.getPhone(), is(equalTo(userBody.getPhone())));
    }

    @Test
    public void shouldUserNotFoundWithInvalidUserName() {
        Response response = UserService.getUser(username + generateString(5));

        assertTrue(response.statusCode() == 404);
        assertThat(response.jsonPath().getString("message"), equalTo("User not found"));
        assertThat(response.jsonPath().getString("type"), equalTo("error"));
    }
}
