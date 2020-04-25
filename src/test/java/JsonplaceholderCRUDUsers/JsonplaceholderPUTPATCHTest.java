package JsonplaceholderCRUDUsers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPUTPATCHTest extends JsonplaceholderCommon {

    @Test
    public void jsonplaceholderUpdateUserPUTTest() {

        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(user.toString())
                .when()
                .put(BASE_URL + USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeName, json.get("name"));
        assertEquals(fakeUsername, json.get("username"));
        assertEquals(fakeEmail, json.get("email"));

        System.out.println(response.asString());

    }

    @Test
    public void jsonplaceholderUpdateUserPATCHTest() {

        JSONObject newEmail = new JSONObject();
        newEmail.put("email", fakeEmail);

        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(newEmail.toString())
                .when()
                .patch(BASE_URL + USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeEmail, json.get("email"));

        System.out.println(response.asString());

    }

}
