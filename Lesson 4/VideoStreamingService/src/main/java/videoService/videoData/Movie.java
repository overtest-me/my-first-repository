package videoService.videoData;

public class Movie {

    private String title;
    private MPAARatings rating;
    private Categories category;
    private boolean hasHdQuality;

    private boolean isStreaming = false;

    public Movie(String title, MPAARatings rating, Categories category, boolean hasHdQuality)
    {
        this.title = title;
        this.rating = rating;
        this.category = category;
        this.hasHdQuality = hasHdQuality;
    }

    public boolean getStreamingStatus()
    {
        return isStreaming;
    }

    public void setStreamingStatus(boolean status)
    {
        isStreaming = status;
    }

    public MPAARatings getRating()
    {
        return rating;
    }

    public boolean isHighQuality()
        {
            return hasHdQuality;
        }

}
