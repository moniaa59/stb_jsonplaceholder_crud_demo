package JsonplaceholderCRUDUsers;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderDELETETest extends JsonplaceholderCommon {

    @Test

    public void jsonplaceholderDeleteUser() {
        Response response = given()
                .when()
                .delete(BASE_URL + USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

}
