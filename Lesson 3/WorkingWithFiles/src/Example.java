import java.io.*;

public class Example {

    public static void main(String[] args) throws IOException {

        // Create an Employee object for calculating bonuses
        Employee employee = new Employee();

        // Define paths to our file with test data and to the final report file
        String pathToInputFile = "data.csv";
        String pathToOutputFile = "report.txt";

        // Initializing buffer reader/writer with appropriate files and streams inside try-with-resources block
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToInputFile))));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(pathToOutputFile))));)
        {

            // Walk through the each line of CSV file in a cycle and read it
            String singleLine;
            while ((singleLine = br.readLine()) != null) {

                // Parse the first column in the CSV file - years of experience
                int year = Integer.parseInt(singleLine.split(",")[0]);

                // Parse the second column in the CSV file - expected bonus for employee
                double expectedResult = Double.parseDouble(singleLine.split(",")[1]);

                // Calculating the actual bonus for employee
                double actualResult = employee.calculateBonus(year);

                // Comparing actual and expected bonuses and defining the test result
                String testResult;
                if (actualResult == expectedResult) testResult = "PASSED";
                else testResult = "FAILED";

                // Writing the test result to our report file
                bw.write(testResult + " for " + year + " year (years): " + actualResult);
                bw.newLine();
            }

        // Catching possible exceptions
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

