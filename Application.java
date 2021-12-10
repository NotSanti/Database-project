import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
    static Connection con;

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = s.nextLine();

        Console console = System.console();
        String password = new String(console.readPassword("Enter Password: "));
        getConnection(username = "A2032367", password = "SQL2021");


        // Statement stmt = con.createStatement();
        // String query = "SELECT title FROM books";
        // ResultSet rs = stmt.executeQuery(query);

        // while(rs.next()){
        //     String str = rs.getString("title");
        //     System.out.println(str);
        // }
        //     if(stmt != null){
        //         stmt.close();
        //     }

        testQuery();

    }

    public static void getConnection(String username, String password) throws SQLException{
        con = DriverManager.getConnection("jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca", username, password);
    }

        // public static Connection getConnection(String username, String password) throws SQLException {
        //     Connection conn;
            
        //     conn = DriverManager.getConnection("jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca", username, password);
        //     System.out.println("connceted to the database");
        //     return conn;
        // }

    public static void testQuery() throws SQLException {
        Statement stmt = con.createStatement();
        String query = "SELECT title FROM books";
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()){
            String s = rs.getString("title");
            System.out.println(s);
        }
            if(stmt != null){
                stmt.close();
            }
        
    }
}