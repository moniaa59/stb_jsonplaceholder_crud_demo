package JsonplaceholderCRUDPhotos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderDELETETestPhotos extends JsonplaceholderCommonPhotos{

    @Test
    public void JsonplaceholderDeletePhotos(){

        Response response = given()
                .when()
                .delete(BASE_URL + PHOTOS + "/" + photoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertNull(json.get("name"));

    }

}
