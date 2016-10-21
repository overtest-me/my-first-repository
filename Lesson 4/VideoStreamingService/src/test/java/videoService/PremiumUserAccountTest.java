package videoService;

import org.joda.time.LocalTime;
import org.junit.*;
import static org.junit.Assert.*;
import videoService.userProfile.*;
import videoService.videoData.*;

public class PremiumUserAccountTest {

    private User premiumUser;
    private Movie movie;

    @BeforeClass
    public static void start()
    {
        System.out.println("Test plan for premium user account has started at " + new LocalTime());
    }

    @AfterClass
    public static void finish()
    {
        System.out.println("Test plan for premium user account has been finished at " + new LocalTime());
    }

    @Before
    public void setUp()
    {
        premiumUser = UserCreator.createNewUser(UserType.PREMIUM, 15);
        movie = new Movie("Gladiator", MPAARatings.PG13, Categories.HISTORICAL, true);
        VideoRepository.uploadToServer(movie);
    }

    @Test
    public void testThatUserCanPlayMovie()
    {
        assertEquals("Video isn't played", ServiceResponseCodes.CODE0, premiumUser.play(movie, false));
    }

    @Test
    public void testThatUserCanStopMovie()
    {
        premiumUser.play(movie, false);
        premiumUser.stop(movie);
        assertFalse("Video wasn't stopped", movie.getStreamingStatus());
    }

    @Test
    public void testThatNotUploadedMovieCannotBePlayed()
    {
        VideoRepository.deleteFromServer(movie);
        assertEquals(ServiceResponseCodes.CODE1, premiumUser.play(movie, false));
    }

    @Test
    public void testThatUserCannotPlayMovieWithAgeRestriction()
    {
        Movie newMovie = new Movie("A Nightmare on Elm Street", MPAARatings.R, Categories.HORROR, false);
        VideoRepository.uploadToServer(newMovie);
        assertEquals(ServiceResponseCodes.CODE5, premiumUser.play(newMovie, false));
    }

    @Test
    public void testThatPremiumAccountCanAddBookmark()
    {
        assertEquals(ServiceResponseCodes.CODE0, premiumUser.addToBookmarks(movie));
    }

    @Test
    public void testThatPremiumAccountCanRemoveBookmark()
    {
        Movie newMovie = new Movie("The Matrix", MPAARatings.R, Categories.FANTASY, false);
        VideoRepository.uploadToServer(newMovie);
        premiumUser.addToBookmarks(newMovie);
        assertEquals(ServiceResponseCodes.CODE0, premiumUser.removeFromBookmarks(newMovie));
    }

    @Test
    public void testThatPremiumAccountCanPlayHD()
    {
        assertEquals(ServiceResponseCodes.CODE0, premiumUser.play(movie, true));
    }
}