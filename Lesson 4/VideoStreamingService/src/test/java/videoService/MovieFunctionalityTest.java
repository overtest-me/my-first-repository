package videoService;

import org.joda.time.LocalTime;
import org.junit.*;
import static org.junit.Assert.*;
import videoService.userProfile.*;
import videoService.videoData.*;

public class MovieFunctionalityTest {

    private User newUser;
    private Movie myMovie;

    @BeforeClass
    public static void start()
    {
        System.out.println("Test plan for general functionality has started at " + new LocalTime());
    }

    @AfterClass
    public static void finish()
    {
        System.out.println("Test plan for general functionality has been finished at " + new LocalTime());
    }

    @Before
    public void setUp()
    {
        newUser = UserCreator.createNewUser(UserType.PREMIUM, 25);
    }

    @After
    public void tearDown()
    {
        newUser = null;
    }


    @Test(timeout=250)
    public void checkThatMovieCanBeAddedToServer()
    {
        myMovie = new Movie("Back to the future", MPAARatings.G, Categories.FANTASY, true);
        assertTrue("Video can't be uploaded to server", VideoRepository.uploadToServer(myMovie));
    }

    @Test
    public void testThatMovieCanBeDeletedFromServer()
    {
        myMovie = new Movie("Back to the future II", MPAARatings.G, Categories.FANTASY, true);
        VideoRepository.uploadToServer(myMovie);
        assertTrue("Video can't be deleted from server", VideoRepository.deleteFromServer(myMovie));
    }

    @Test
    public void testThatMovieCantBeDeletedFromServerIfPlayed(){
        myMovie = new Movie("Back to the future III", MPAARatings.G, Categories.FANTASY, true);
        VideoRepository.uploadToServer(myMovie);
        newUser.play(myMovie, false);
        assertFalse("Video can't be deleted from server", VideoRepository.deleteFromServer(myMovie));
    }
}