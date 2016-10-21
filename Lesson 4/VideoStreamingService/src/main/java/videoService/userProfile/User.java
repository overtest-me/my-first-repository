package videoService.userProfile;

import videoService.ServiceResponseCodes;
import videoService.videoData.Movie;
import videoService.videoData.VideoRepository;
import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private int age;
    boolean hasAccessToBookmarks;
    boolean canWatchHD;

    private List<Movie> bookmarks = new ArrayList<>();

    User(int age) {
        this.createUser();
        this.age = age;
    }

    public abstract void createUser();

    public ServiceResponseCodes addToBookmarks(Movie movie) {
        if (!VideoRepository.checkAvailability(movie)) {
            return ServiceResponseCodes.CODE6;
        } else if (!hasAccessToBookmarks) {
            return ServiceResponseCodes.CODE4;
        } else if (bookmarks.contains(movie)) return ServiceResponseCodes.CODE99;
        else {
            bookmarks.add(movie);
            return ServiceResponseCodes.CODE0;
        }
    }

    public ServiceResponseCodes removeFromBookmarks(Movie movie) {
        if (!hasAccessToBookmarks) {
            return ServiceResponseCodes.CODE4;
        } else if (!bookmarks.contains(movie)) return ServiceResponseCodes.CODE99;
        else {
            bookmarks.remove(movie);
            return ServiceResponseCodes.CODE0;
        }
    }

    public boolean removeLastBookmark()
    {
        bookmarks.remove(bookmarks.size()-1);
        return true;
    }

    public ServiceResponseCodes play(Movie movie, boolean inHDQuality){
        if(!VideoRepository.checkAvailability(movie))
        {
            return ServiceResponseCodes.CODE1;
        }
        else if(!canWatchHD)
        {
            return ServiceResponseCodes.CODE3;
        }
        else if(inHDQuality && !movie.isHighQuality())
        {
            return ServiceResponseCodes.CODE2;
        }
        else if(movie.getRating().getAge() > age)
        {
            return ServiceResponseCodes.CODE5;
        }
        else
        {
            movie.setStreamingStatus(true);
            return ServiceResponseCodes.CODE0;
        }
    }

    public void stop(Movie movie)
    {
        if(movie.getStreamingStatus()) movie.setStreamingStatus(false);
    }
}
