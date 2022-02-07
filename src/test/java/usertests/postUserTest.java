package usertests;

import io.restassured.response.Response;
import model.UserBody;
import org.testng.annotations.Test;
import services.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;
import static util.Helpers.generateString;

public class postUserTest extends BaseTest {

    @Test
    public void shouldCreateNewUserWithValidParameters() {

        assertTrue(response.statusCode() == 200);

    }

}
