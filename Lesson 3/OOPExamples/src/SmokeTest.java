class SmokeTest extends BaseTest {

    boolean isRunEveryBuild;
    int maxDurationInSec;

    public String getFullDescription()
    {
        return super.getFullDescription()
                + String.format("Should I run every build: %s I should pass in %s seconds max",
                isRunEveryBuild, maxDurationInSec);
    }
}


