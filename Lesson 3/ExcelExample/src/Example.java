import java.io.*;

public class Example {

    public static void main(String[] args) throws IOException {

        String pathToInputFile = "data.csv";

        // This string represents the name of our future report
        String reportName = "AutomationReport";

        Employee employee = new Employee();

        /* Create an object of our auxiliary class and create
        and create title for our report table */
        ReportCreator reportCreator = new ReportCreator();
        reportCreator.createHeader();

        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToInputFile)))))
        {
            String singleLine;
            int testCaseCounter = 1;
            while ((singleLine = br.readLine()) != null) {

                int year = Integer.parseInt(singleLine.split(",")[0]);
                double expectedResult = Double.parseDouble(singleLine.split(",")[1]);
                double actualResult = employee.calculateBonus(year);

                String testResult;
                if (actualResult == expectedResult) testResult = "PASSED";
                else testResult = "FAILED";

                // Writing results of single test to the report
                reportCreator.writeTestCaseResult(testCaseCounter, year, expectedResult, actualResult, testResult);
                testCaseCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculating success coverage and saving report
        reportCreator.calculateSummary();
        reportCreator.saveReport(reportName);
    }
}

