package JsonplaceholderCRUDPhotos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderGETTestPhotos extends JsonplaceholderCommonPhotos {

    @Test
    public void jsonplaceholderReadAllPhotos() {

        Response response = given()
                .when()
                .get(BASE_URL + PHOTOS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        List<String> titles = json.getList("title");
        assertEquals(5000, titles.size());
    }

    @Test
    public void jsonplaceholderReadAllPhotosWithQueryParams(){

        Response response = given()
                .queryParam("albumId", albumId)
                .when()
                .get(BASE_URL + PHOTOS)
                .then()
                .extract()
                .response();

//        System.out.println(response.asString());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();

        List<String> url = json.getList("url");

        System.out.println("URL QUANTITY WITH 'albumId': " + albumId + " IS " + url.size());

        assertEquals(50, url.size());

    }

    @Test
    public void jsonplaceholderReadOnePhoto() {
        Response response = given()
                .when()
                .get(BASE_URL + PHOTOS + "/" + photoId);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();

        assertEquals(albumId, json.get("albumId"));
        assertEquals("natus nisi omnis corporis facere molestiae rerum in", json.get("title"));
        assertEquals("https://via.placeholder.com/600/f66b97", json.get("url"));
        assertEquals("https://via.placeholder.com/150/f66b97", json.get("thumbnailUrl"));
    }

    @Test
    public void jsonplaceholderReadOnePhotoWithPathVariable() {
        Response response = given()
                .pathParam("id", photoId)
                .when()
                .get(BASE_URL + PHOTOS + "/" + "{id}");

        System.out.println(response.asString());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();

        assertEquals(albumId, json.get("albumId"));
        assertEquals("natus nisi omnis corporis facere molestiae rerum in", json.get("title"));
        assertEquals("https://via.placeholder.com/600/f66b97", json.get("url"));
        assertEquals("https://via.placeholder.com/150/f66b97", json.get("thumbnailUrl"));
    }


}
