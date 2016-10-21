package videoService;

public enum ServiceResponseCodes {

        CODE0("Success!"),
        CODE1("ContentAvailabilityException: File doesn't exist on server or it's still being uploaded"),
        CODE2("ContentAvailabilityException: This movie doesn't have an HD version!"),
        CODE3("UserAccessException: HD quality isn't available for your account type. Please upgrade your account"),
        CODE4("UserAccessException: Bookmarks feature isn't available for your account type. Please upgrade your account"),
        CODE5("AgeRestrictionException: You are not allowed to play this movie due to age restrictions. Please talk to your parents!"),
        CODE6("ContentAvailabilityException: Can't add to bookmarks movie which doesn't exist on server or it's still being uploaded"),
        CODE99("Fail");


    private String value;
    ServiceResponseCodes(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return value;
    }
}
