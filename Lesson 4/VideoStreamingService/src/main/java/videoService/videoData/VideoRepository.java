package videoService.videoData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VideoRepository
{
    private static List<Movie> library = new ArrayList<>();

    public static boolean uploadToServer(Movie movie)
    {
        if(!library.contains(movie))
        {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 499 + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            library.add(movie);
            return true;
        }
        return false;
    }

    public static boolean deleteFromServer(Movie movie)
    {
        if(library.contains(movie) && !movie.getStreamingStatus())
        {
            library.remove(movie);
            return true;
        }
        return false;
    }

    public static boolean checkAvailability(Movie movie) {
        return library.contains(movie);
    }
}
