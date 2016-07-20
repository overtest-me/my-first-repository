public class MyTests {

    public static void main(String[] args) {
        overridingExamples();
        overloadingExample();
    }

    public static void overridingExamples()
    {
        BaseTest bt = new BaseTest();
        bt.name = "Test #1";
        bt.description = "I'm just a test";
        bt.category = "DemoTests";
        System.out.println(bt.getFullDescription());

        RegressionTest rt = new RegressionTest();
        rt.name = "Test #2";
        rt.description = "I'm a regression test";
        rt.category = "DemoTests";
        rt.priority = Priority.MEDIUM;
        rt.numberOfRuns = 3;
        System.out.println(rt.getFullDescription());

        SmokeTest st = new SmokeTest();
        st.name = "Test #3";
        st.description = "I'm a smoke test";
        st.category = "AffectOnRelease";
        st.isRunEveryBuild = true;
        st.maxDurationInSec = 30;
        System.out.println(st.getFullDescription());
    }

    public static void overloadingExample()
    {
        BaseTest bt = new BaseTest();
        String[] steps = {"Start application", "Press login button 1000 times"};

        bt.reportBug("I don't like the font on web-site");
        bt.reportBug("Something strange", steps);
        bt.reportBug("Application has crashed during demo", Priority.HIGH);
    }
}

