public class MyExceptions {

    public static void main(String[] args) {

        String [] testResults = {"FAILED", "PASSED", "PASSED", "BLOCKED", "INCONCLUSIVE"};
        getTestResultFromArray(testResults, 7);
    }

    static String getTestResultFromArray(String[] results, int position)
    {
        String testCaseResult = null;
        try{
            testCaseResult = results[position];
            System.out.println("The result is " + testCaseResult);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(String.format("There is no test case with the index %s", position));
        } finally {
            System.out.println("Thank you for using our tool");
        }
        return testCaseResult;
    }



}


