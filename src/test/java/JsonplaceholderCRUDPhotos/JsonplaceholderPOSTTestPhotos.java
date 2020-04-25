package JsonplaceholderCRUDPhotos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderPOSTTestPhotos extends JsonplaceholderCommonPhotos {

    @Test
    public void jsonplaceholderCreateNewPhoto() {
        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(photo.toString())
                .when()
                .post(BASE_URL + PHOTOS);

        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals(fakeAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
    }
}
