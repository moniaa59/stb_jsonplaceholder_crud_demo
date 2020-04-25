package JsonplaceholderCRUDPhotos;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class JsonplaceholderCommonPhotos {

    protected final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    protected final String PHOTOS = "photos";
    protected final String CONTENT_TYPE = "application/json";

    Integer photoId = Integer.valueOf(5);
    Integer albumId = Integer.valueOf(1);

    public static JSONObject photo;

    public static Faker faker;
    protected Double fakeAlbumId;
    protected String fakeTitle;
    protected String fakeUrl;
    protected String fakeThumbnailUrl;

    @BeforeAll
    public static void BeforeAll() {

        faker = new Faker();

        photo = new JSONObject();

    }

    @BeforeEach
    public void BeforeEach() {

        fakeAlbumId = faker.number().randomDouble(0, 1, 15);
        fakeTitle = faker.lorem().sentence();
        fakeUrl = faker.internet().url();
        fakeThumbnailUrl = faker.internet().url();


        photo.put("albumId", fakeAlbumId);
        photo.put("title", fakeTitle);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);


    }

}
