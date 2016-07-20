class RegressionTest extends BaseTest {

    Priority priority;
    int numberOfRuns = 0;

    public String getFullDescription()
    {
        return super.getFullDescription()
                + String.format("Priority: %s I have been run %s times",
                priority, numberOfRuns);
    }
}


