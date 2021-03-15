import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Test09876543212345";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                System.out.println(courseName);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}