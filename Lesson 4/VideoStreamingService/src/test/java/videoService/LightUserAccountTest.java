package videoService;

import org.joda.time.LocalTime;
import org.junit.*;
import videoService.userProfile.*;
import videoService.videoData.*;

import static org.junit.Assert.assertEquals;

public class LightUserAccountTest {

    private User lightUser;
    private Movie movie;

    @BeforeClass
    public static void start()
    {
        System.out.println("Test plan for light user account has started at " + new LocalTime());
    }

    @AfterClass
    public static void finish()
    {
        System.out.println("Test plan for light user account has been finished at " + new LocalTime());
    }

    @Before
    public void setUp()
    {
        lightUser = UserCreator.createNewUser(UserType.LIGHT, 44);
        movie = new Movie("Avatar", MPAARatings.PG13, Categories.ACTION, true);
        VideoRepository.uploadToServer(movie);
    }

    @Test
    public void testThatLightAccountCannotAddBookmark()
    {
        assertEquals(ServiceResponseCodes.CODE4, lightUser.addToBookmarks(movie));
    }

    @Test
    public void testThatLightAccountCannotPlayHD()
    {
        assertEquals(ServiceResponseCodes.CODE3, lightUser.play(movie, true));
    }
}
