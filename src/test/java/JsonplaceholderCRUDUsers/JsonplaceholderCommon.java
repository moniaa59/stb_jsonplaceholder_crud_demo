package JsonplaceholderCRUDUsers;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class JsonplaceholderCommon {

    protected final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    protected final String USERS = "users";
    protected final String CONTENT_TYPE = "application/json";

    public static JSONObject user;
    public static JSONObject geo;
    public static JSONObject address;
    public static JSONObject company;

    public static Faker faker;
    protected String fakeName;
    protected String fakeUsername;
    protected String fakeEmail;
    protected String fakePhone;
    protected String fakeWWW;
    protected Double fakeLat;
    protected Double fakeLng;
    protected String fakeStreet;
    protected String fakeSuite;
    protected String fakeCity;
    protected String fakeZipCode;
    protected String fakeCompanyName;
    protected String fakeCompanyCatchPhrase;
    protected String fakeCompanyBs;

    @BeforeAll
    public static void beforeAll() {

        faker = new Faker();

        user = new JSONObject();
        geo = new JSONObject();
        address = new JSONObject();
        company = new JSONObject();

    }

    @BeforeEach
    public void beforeEach() {
        fakeName = faker.name().fullName();
        fakeUsername = faker.name().firstName();
        fakeEmail = faker.internet().emailAddress();
        fakePhone = faker.phoneNumber().phoneNumber();
        fakeWWW = faker.internet().url();
        fakeLat = faker.number().randomDouble(4, -90, 90);
        fakeLng = faker.number().randomDouble(4, 0, 90);
        fakeStreet = faker.address().streetName();
        fakeSuite = "Apt. " + faker.number().randomNumber();
        fakeCity = faker.address().city();
        fakeZipCode = faker.address().zipCode();
        fakeCompanyName = faker.company().name();
        fakeCompanyCatchPhrase = faker.company().catchPhrase();
        fakeCompanyBs = faker.company().bs();

        //JSONObject
        user.put("name", fakeName);
        user.put("username", fakeUsername);
        user.put("email", fakeEmail);
        user.put("phone", fakePhone);
        user.put("website", fakeWWW);

        geo.put("lat", fakeLat);
        geo.put("lng", fakeLng);

        address.put("street", fakeStreet);
        address.put("suite", fakeSuite);
        address.put("city", fakeCity);
        address.put("zipcode", fakeZipCode);
        address.put("geo", geo);

        user.put("address", address);

        company.put("name", fakeCompanyName);
        company.put("catchPhrase", fakeCompanyCatchPhrase);
        company.put("bs", fakeCompanyBs);

        user.put("company", company);

    }
}
