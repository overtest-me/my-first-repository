package videoService;

import org.joda.time.LocalTime;
import org.junit.*;
import videoService.userProfile.*;
import videoService.videoData.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdvancedUserAccountTest {

    private User advancedUser;
    private Movie movie;

    @BeforeClass
    public static void start()
    {
        System.out.println("Test plan for advanced user account has started at " + new LocalTime());
    }

    @AfterClass
    public static void finish()
    {
        System.out.println("Test plan for advanced user account has been finished at " + new LocalTime());
    }

    @Before
    public void setUp()
    {
        advancedUser = UserCreator.createNewUser(UserType.ADVANCED, 25);
        movie = new Movie("Green mile", MPAARatings.R, Categories.THRILLER, true);
        VideoRepository.uploadToServer(movie);
    }

    @Test
    public void testThatAdvancedAccountCanAddBookmark()
    {
        assertEquals(ServiceResponseCodes.CODE0, advancedUser.addToBookmarks(movie));
    }

    @Test
    public void testThatAdvancedAccountCanRemoveBookmark()
    {
        Movie newMovie = new Movie("Hobbit", MPAARatings.G, Categories.FANTASY, false);
        VideoRepository.uploadToServer(newMovie);
        advancedUser.addToBookmarks(newMovie);
        assertEquals(ServiceResponseCodes.CODE0, advancedUser.removeFromBookmarks(newMovie));
    }

    @Test
    public void testThatAdvancedAccountCannotPlayHD()
    {
        assertEquals(ServiceResponseCodes.CODE3, advancedUser.play(movie, true));
    }

    @Ignore
    @Test
    public void testThatLastExistingBookmarkCanBeRemoved()
    {
        //Movie newMovie = new Movie("No movie", MPAARatings.G, Categories.FANTASY, false);
        //VideoRepository.uploadToServer(newMovie);
        //advancedUser.addToBookmarks(newMovie);
        assertTrue(advancedUser.removeLastBookmark());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testThatNonExistingBookmarkCannotBeRemoved()
    {
        advancedUser.removeLastBookmark();
    }


}
