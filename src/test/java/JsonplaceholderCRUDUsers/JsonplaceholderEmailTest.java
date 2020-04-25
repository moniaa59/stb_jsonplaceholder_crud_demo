package JsonplaceholderCRUDUsers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonplaceholderEmailTest extends JsonplaceholderCommon {

    @Test
    public void jsonplaceholderReadAllUsersAndCheckEmail() {
        Response response = given()
                .when()
                .get(BASE_URL + USERS);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();

        List<String> userEmails = json.getList("email");
//
//        for (String email : userEmails) {
//            if(email.endsWith(".biz")){
//                System.out.println(email);
//            }
//
//        userEmails.stream()
//                .filter(email2 -> email2.endsWith(".biz"))
//                .forEach(System.out::println);

        boolean anyMatchEndsWithPl = userEmails.stream()
                .anyMatch(email -> email.endsWith(".pl"));

        assertFalse(anyMatchEndsWithPl);

    }


}
