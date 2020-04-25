package JsonplaceholderCRUDUsers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

//GIVEN - konfiguracja
//WHEN - wysyłanie requestu

public class JsonplaceholderGETTest extends JsonplaceholderCommon {

    @Test
    public void jsonplaceholderReadAllUsers() {

        Response response = given()
                .when()
                .get(BASE_URL + USERS);

//        System.out.println(response.asString());

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath json = response.jsonPath();

        List<String> names = json.getList("name");

        assertEquals(10, names.size());

//        names.stream()
//                .forEach(System.out::println);
    }

    @Test
    public void jsonplaceholderReadOneUser() {
        Response response = given()
                .when()
                .get(BASE_URL + USERS + "/1");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));


        System.out.println(response.asString());
    }

    @Test
    public void jsonplaceholderReadOneUserWithPathVariable() {

        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(BASE_URL + USERS + "/" + "{userId}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    public void jsonplaceholderReadAllUsersWithQueryParams() {
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(BASE_URL + USERS);

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
        assertEquals("Kulas Light", json.getList("address.street").get(0));
    }
}
