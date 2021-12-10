package JDBC;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

public class Application {
    
    static Connection con;
    static String username;

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Username: ");
        username = s.nextLine();

        Console console = System.console();
        String password = new String(console.readPassword("Enter Password: "));
        getConnection(username, password);

        updateAuditLog();

    }

    public static void getConnection(String username, String password) throws SQLException{
        System.out.println("connecting");
        con = DriverManager.getConnection("jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca", username, password);
        if(con.isValid(1000)){
        System.out.println("connected");
        }
    }
    public static void updateAuditLog() throws SQLException{
        Date date = new Date(System.currentTimeMillis());
        

        String query = "INSERT INTO AUDITLOG(username, dateinfo) VALUES(?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setDate(2, date);
        //needs to give an explanation too
        stmt.executeUpdate();
    }

}