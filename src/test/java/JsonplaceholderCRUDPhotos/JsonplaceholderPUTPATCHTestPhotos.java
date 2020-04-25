package JsonplaceholderCRUDPhotos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPUTPATCHTestPhotos extends JsonplaceholderCommonPhotos {

    @Test
    public void jsonplaceholderUpdatePhotoPUTTest() {
        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(photo.toString())
                .when()
                .put(BASE_URL + PHOTOS + "/" + photoId);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();

        assertEquals(fakeAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));

    }

    @Test
    public void jsonplaceholderUpdatePhotoPATCHTest() {
        Response response = given()
                .contentType(CONTENT_TYPE)
                .body(photo.toString())
                .when()
                .patch(BASE_URL + PHOTOS + "/" + photoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeTitle, json.get("title"));
    }

}
