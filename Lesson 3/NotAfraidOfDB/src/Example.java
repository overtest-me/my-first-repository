import java.sql.*;

public class Example {

    // 01. Define constants for the database URL and user's credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        // 02. Open a connection to the local DB with provided credentials
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();) {

            // 03. Executing query and saving the result in the ResultSet object
            ResultSet result = statement.executeQuery("SELECT * FROM test.company");

            // 04. Iterating through all DB rows we have in the ResultSet object
            while (result.next()) {

                /* 05. Obtaining value of each column for a single row:
                ID, name, number of employees, is company profitable*/
                int id = result.getInt("id");
                String name = result.getString("name");
                int numOfEmpl = result.getInt("numOfEmployee");
                boolean isProfitable = result.getBoolean("isProfitable");

                // Output to the console to make sure that we got some data
                System.out.println(String.format("ID: %s, Name: %s," +
                        "Number of employees: %s, Is profitable: %s",
                        id, name, numOfEmpl, isProfitable));
            }

            // 05. Closing the ResultSet object
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
