import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    static Connection con;

    public static void main(String[] args) throws SQLException{
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = s.nextLine();

        Console console = System.console();
        String password = new String(console.readPassword("Enter Password: "));
        getConnection(username, password);
    }

    public static void getConnection(String username, String password) throws SQLException{
        con = DriverManager.getConnection("jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca", username, password);
    }
}