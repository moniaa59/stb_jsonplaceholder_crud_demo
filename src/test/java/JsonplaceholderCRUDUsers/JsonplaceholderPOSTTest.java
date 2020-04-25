package JsonplaceholderCRUDUsers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTest extends JsonplaceholderCommon {

    @Test
    public void jsonplaceholderCreateNewUser() {

        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(user.toString())
                .when()
                .post(BASE_URL + USERS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeUsername, json.get("username"));
        assertEquals(fakeEmail, json.get("email"));

        System.out.println(response.asString());

    }
}
