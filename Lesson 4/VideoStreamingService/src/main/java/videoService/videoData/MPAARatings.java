package videoService.videoData;

public enum MPAARatings {
    G(0),
    PG(13),
    PG13(13),
    R(18);

    private int value;
    MPAARatings(int value)
    {
        this.value = value;
    }

    public int getAge()
    {
        return value;
    }
}
