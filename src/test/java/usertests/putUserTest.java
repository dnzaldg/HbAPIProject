package usertests;

import io.restassured.response.Response;
import model.UserBody;
import org.testng.annotations.Test;
import services.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;

public class putUserTest extends BaseTest {

    @Test(invocationCount = 10)
    public void shouldUpdateMyUser() {
        UserBody updateBody = updateUserBodyModel(username);

        Response updateResponse = UserService.updateUser(username, updateBody);

        assertTrue(updateResponse.getStatusCode() == 200);

        Response getResponse = UserService.getUser(username);

        assertTrue(getResponse.statusCode() == 404);
        assertThat(getResponse.jsonPath().getString("message"), equalTo("User not found"));
        assertThat(getResponse.jsonPath().getString("type"), equalTo("error"));

        username = updateBody.getUsername();
        Response response2 = UserService.getUser(username);
        while (response2.getStatusCode() == 404) {
            response2 = UserService.getUser(username);
        }

        assertTrue(response2.getStatusCode() == 200);
        UserBody getUserBody = response2.getBody().as(UserBody.class);

        assertThat(getUserBody.getUsername(), is(equalTo(updateBody.getUsername())));
        assertThat(getUserBody.getPassword(), is(equalTo(updateBody.getPassword())));
        assertThat(getUserBody.getEmail(), is(equalTo(updateBody.getEmail())));
        assertThat(getUserBody.getUserStatus(), is(equalTo(updateBody.getUserStatus())));
        assertThat(getUserBody.getFirstName(), is(equalTo(updateBody.getFirstName())));
        assertThat(getUserBody.getLastName(), is(equalTo(updateBody.getLastName())));
        assertThat(getUserBody.getPhone(), is(equalTo(updateBody.getPhone())));
    }


}
