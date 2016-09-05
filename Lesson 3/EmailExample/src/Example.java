import java.io.*;

public class Example {

    public static void main(String[] args) throws IOException {

        String pathToInputFile = "data.csv";

        // We modified the string because it's used in several places
        String reportFile = "AutomationReport.xlsx";

        ReportCreator reportCreator = new ReportCreator();
        reportCreator.createHeader();

        // Object for setting message parameters and eventually sending email
        GmailReportSender  gmailReport = new GmailReportSender();
        gmailReport.setReceiver("manager.mail@gmail.com");

        Employee employee = new Employee();

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

                reportCreator.writeTestCaseResult(testCaseCounter, year, expectedResult, actualResult, testResult);
                testCaseCounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        reportCreator.calculateSummary();
        reportCreator.saveReport(reportFile);

        // Setting email subject, content, attaching report file and sending it
        gmailReport.setTopicAndBody("Automation report", "Please see results in the attachment");
        gmailReport.addAttachment(reportFile);
        gmailReport.send();
    }
}

