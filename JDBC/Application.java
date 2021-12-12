package JDBC;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {
    
    static Connection con;
    static String username;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Username: ");
        username = s.nextLine();

        Console console = System.console();
        String password = new String(console.readPassword("Enter Password: "));
        getConnection(username = "A2032367", password ="SQL2021");

        System.out.println("Type the number of the choice");
        
        System.out.println("1 -- UPDATE a table");
        System.out.println("2 -- INSERT a table");
        System.out.println("3 -- DELETE a table");
        System.out.println("4 -- Prepared statements");

        String choice = s.nextLine();
        if(choice.equals("1") || choice.equals("2") || choice.equals("3")){
            showTables();
            String table = s.nextLine();
            if(table.equals("1")){
                System.out.println(" --- COMPONENTS --- ");
                initComponents();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER COMPONENT ID --- ");
                    String comId = s.nextLine();
                    updateComponents(comId);
                } else if(choice.equals("2")){
                    insertComponents();
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER COMPONENT ID --- ");
                    String comId = s.nextLine();
                    deleteComponent(comId);
                }
            } else if(table.equals("2")){
                System.out.println(" --- CONTRIBUTORS --- ");
                initContributors();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER CONTRIBUTOR ID --- ");
                    String conID = s.nextLine();
                    updateContributors(conID);
                    updateAuditLog("Updating contributor table");
                }else if(choice.equals("2")){
                    insertContributors();
                    updateAuditLog("Inserting into contributor table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER CONTRIBUTOR ID --- ");
                    String conID = s.nextLine();
                    deleteContributors(conID);
                    updateAuditLog("Deleting from contributor table");
                }
            } else if(table.equals("3")){
                System.out.println(" --- DISTRIBUTIONS --- ");
                initDistribution();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER SONG ID --- ");
                    String songid = s.nextLine();
                    updateDistribution(songid);
                    updateAuditLog("Updating Distributions table");
                } else if(choice.equals("2")){
                    insertDistribution();
                    updateAuditLog("Inserting into Distributions table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER SONG ID --- ");
                    String songID = s.nextLine();
                    deleteDistribution(songID);
                    updateAuditLog("Deleting from Distributions table");
                }
            } else if(table.equals("4")){
                System.out.println(" --- MARKETS --- ");
                initMarkets();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER MARKET ID --- ");
                    String mid = s.nextLine();
                    updateMarkets(mid);
                    updateAuditLog("Updating Markets table");
                } else if(choice.equals("2")){
                    insertMarkets();
                    updateAuditLog("Inserting into Markets table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER MARKET ID --- ");
                    String mID = s.nextLine();
                    deleteMarket(mID);
                    updateAuditLog("Deleting from Markets table");
                }
            } else if(table.equals("5")){
                System.out.println(" --- RECORD LABELS --- ");
                initRecordLabel();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER RECORD LABEL ID --- ");
                    String rid = s.nextLine();
                    updateRecordLabel(rid);
                    updateAuditLog("Updating RecordLabel table");
                } else if(choice.equals("2")){
                    insertRecordLabel();
                    updateAuditLog("Inserting into RecordLabel table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER RECORD LABEL ID --- ");
                    String riID = s.nextLine();
                    deleteRecordLabel(riID);
                    updateAuditLog("Deleting from RecordLabel table");
                }
            } else if(table.equals("6")){
                System.out.println(" --- ROLES --- ");
                initRoles();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER ROLE ID --- ");
                    String rid = s.nextLine();
                    updateRoles(rid);
                    updateAuditLog("Updating Roles table");
                } else if(choice.equals("2")){
                    insertRoles();
                    updateAuditLog("Inserting into Roles table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER ROLE ID --- ");
                    String roleID = s.nextLine();
                    deleteRole(roleID);
                    updateAuditLog("Deleting from Roles table");
                }
            } else if(table.equals("7")){
                System.out.println(" --- SONG --- ");
                initSong();
                if(choice.equals("1")){
                    System.out.println(" --- ENTER SONG ID --- ");
                    String sid = s.nextLine();
                    updateSong(sid);
                    updateAuditLog("Updating Song table");
                } else if(choice.equals("2")){
                    insertSong();
                    updateAuditLog("Inserting into Song table");
                } else if(choice.equals("3")){
                    System.out.println(" --- ENTER SONG ID --- ");
                    String sID = s.nextLine();
                    deleteSongs(sID);
                    updateAuditLog("Deleting from Song table");
                }
            } 
        } else if(choice.equals("4")){
            System.out.println(" --- PREPARED STATMENTS --- ");
            System.out.println("1 -- search contributors and roles to a song");
            System.out.println("2 -- get a breakdown of a compilation");
            System.out.println("3 -- update fields of a song");
            System.out.println("4 -- search contributions and roles that an artist has had");
            System.out.println("5 -- show roles of an artist");
        }
        //updateAuditLog();

    }

    public static void getConnection(String username, String password) throws SQLException{
        System.out.println("connecting");
        con = DriverManager.getConnection("jdbc:oracle:thin:@pdbora19c.dawsoncollege.qc.ca:1521/pdbora19c.dawsoncollege.qc.ca", username = "A2032367", password = "SQL2021");
       
        if(con.isValid(1000)){
        System.out.println("Connected");
        }
    }

    public static void updateAuditLog(String info) throws SQLException{
        Date date = new Date(System.currentTimeMillis());
        
        String query = "INSERT INTO AUDITLOG(username, information, dateinfo) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, info);
        stmt.setDate(3, date);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateComponents(String comId) throws SQLException{
        String query = "SELECT * FROM components WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String componentId = rs.getString("componentid");
        String songId = rs.getString("songid");
        int offsetComponent = rs.getInt("offsetcomponent");
        int durationComponent = rs.getInt("durationcomponent");
        String songUsed = rs.getString("songused");
        int offsetSong = rs.getInt("offsetsong");
        int durationSong = rs.getInt("durationsong");
        Components components = new Components(componentId, songId, offsetComponent, durationComponent, songUsed, offsetSong, durationSong, getSongsComponent(comId));
        System.out.println(1+" -- "+components);
        stmt.close(); 
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER songId: ");
        String sId = s.nextLine();
        System.out.println("ENTER offsetComponent:");
        int osc = s.nextInt();
        System.out.println("ENTER durationComponent:");
        int dc = s.nextInt();
        System.out.println("ENTER songUsed:");
        String su = s.nextLine();
        System.out.println("ENTER offsetSong:");
        int os = s.nextInt();        
        System.out.println("ENTER durationSong:");
        int ds = s.nextInt();
        String q2 = "UPDATE components SET songId = ?, offsetcomponent = ?, durationcomponent = ?, songused = ?, offsetsong = ?, durationsong = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, sId);
        stmt.setInt(2, osc);
        stmt.setInt(3, dc);
        stmt.setString(4, su);
        stmt.setInt(5, os);
        stmt.setInt(6, ds);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    } 

    public static void updateContributors(String conID) throws SQLException{
        String query = "SELECT * FROM contributors WHERE contributorid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, conID);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String contributorId = rs.getString("contributorId");
        String name = rs.getString("fullname");
        Contributors contributors = new Contributors(contributorId, name);
        System.out.println(1+" -- "+contributors);
        stmt.close();
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER fullName:");
        String fullname = s.nextLine();
        String q2 = "UPDATE contributors SET contributorid = ?, fullname = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, conID);
        stmt.setString(2, fullname);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateDistribution(String songid) throws SQLException, ParseException{
        String query = "SELECT * FROM distribution WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String songID = rs.getString("songid");
        String recordLabelID = rs.getString("recordlabelid");
        String marketid = rs.getString("marketid");
        Date distDate = rs.getDate("distributiondate");
        String songTitle = rs.getString("songtitle");
        Distribution d = new Distribution(songID, recordLabelID, marketid, distDate, songTitle, getSongsSong(songid));
        System.out.println(1+" -- "+d);
        stmt.close();
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER recordLabelID:");
        String rid = s.nextLine();
        System.out.println("ENTER marketID:");
        String mid = s.nextLine();
        System.out.println("ENTER releaseDate:");
        String dateTemp = s.nextLine();
        Date date = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(dateTemp);
        
        System.out.println("ENTER song title:");
        String title = s.nextLine();
        String q2 = "UPDATE distribution SET songid = ?, recordlabelid = ?, marketid = ?, distributiondate = ?, songtitle = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, songid);
        stmt.setString(2, rid);
        stmt.setString(3, mid);
        stmt.setDate(4, date);
        stmt.setString(5, title);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateMarkets(String marketid) throws SQLException{
        String query = "SELECT * FROM markets WHERE marketid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, marketid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String marketId = rs.getString("marketid");
        String area = rs.getString("area");
        Markets m = new Markets(marketId, area);
        System.out.println(1+" -- "+m);
        stmt.close();
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER area:");
        String area1 = s.nextLine();
        
        String q2 = "UPDATE markets SET area = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, area1);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateRecordLabel(String rid) throws SQLException{
        String query = "SELECT * FROM recordlabel WHERE recordlabelid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, rid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String recordlabelID = rs.getString("recordlabelid");
        String rlname = rs.getString("name");
        RecordLabel rl = new RecordLabel(recordlabelID, rlname, getDistributionsRecordLabel(rid));
        System.out.println(1+" -- "+rl);
        stmt.close();
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER Name:");
        String name = s.nextLine();
        
        String q2 = "UPDATE recordlabel SET recordlabelid = ?, name = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, rid);
        stmt.setString(2, name);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateRoles(String roid) throws SQLException{
        String query = "SELECT * FROM roles WHERE roleid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, roid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String roleID = rs.getString("roleid");
        String name = rs.getString("rolename");
        Roles r = new Roles(roleID, name);
        System.out.println(1+" -- "+r);
        stmt.close();
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER RoleName:");
        String name1 = s.nextLine();
        
        String q2 = "UPDATE roles SET roleid = ?, rolename = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, roid);
        stmt.setString(2, name1);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void updateSong(String sid) throws SQLException, ParseException{
        String query = "SELECT * FROM song WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, sid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String songID = rs.getString("songid");
        Date rdate = rs.getDate("releasedate");
        int sduration = rs.getInt("duration");
        Song song = new Song(songID, rdate, sduration, getContributorsSong(sid), getRolesSong(sid), getComponentsSong(sid));
        System.out.println(1+" -- "+song);
        stmt.close();
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER ReleaseDate:");
        String dateTemp = s.nextLine();
        Date date = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(dateTemp);
        System.out.println("ENTER Duration:");
        int duration = s.nextInt();
        
        String q2 = "UPDATE song SET songid = ?, releasedate = ?, duration = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, sid);
        stmt.setDate(2, date);
        stmt.setInt(3,duration);
        stmt.executeUpdate();
        System.out.println("UPDATE successfully completed");
    }

    public static void initComponents() throws SQLException{
        String query = "SELECT * FROM components";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        //List<Components> comp = new ArrayList<>();
        int i=0;
        while(rs.next()){
            i++;
            String componentId = rs.getString("componentid");
            String songId = rs.getString("songid");
            int offsetComponent = rs.getInt("offsetcomponent");
            int durationComponent = rs.getInt("durationcomponent");
            String songUsed = rs.getString("songused");
            int offsetSong = rs.getInt("offsetsong");
            int durationSong = rs.getInt("durationsong");
            Components components = new Components(componentId, songId, offsetComponent, durationComponent, songUsed, offsetSong, durationSong, getSongsComponent(rs.getString("componentid")));
            //comp.add(components);
            System.out.println(i+" -- "+components);
        }
        stmt.close();
        //return comp;
    }

    public static void initContributors() throws SQLException{
        String query = "SELECT * FROM contributors";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String contributorId = rs.getString("contributorId");
            String name = rs.getString("fullname");
            Contributors contributors = new Contributors(contributorId, name);
            System.out.println(i+" -- "+contributors);
        }
        stmt.close();
    }

    public static void initDistribution() throws SQLException{
        String query = "SELECT * FROM distribution";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String songID = rs.getString("songid");
            String recordLabelID = rs.getString("recordlabelid");
            String marketID = rs.getString("marketid");
            Date releaseDate = rs.getDate("distributiondate");
            String title = rs.getString("songtitle");
            Distribution dist = new Distribution(songID, recordLabelID, marketID, releaseDate,title, getSongsSong(rs.getString("songid")));
            System.out.println(i+" -- "+dist);
        }
        stmt.close();
    }

    public static void initMarkets() throws SQLException{
        System.out.println("working");
        String query = "SELECT * FROM markets";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String marketId = rs.getString("marketid");
            String area = rs.getString("area");
            Markets m = new Markets(marketId, area);
            System.out.println(i+" -- "+m);
        }
        rs.close();

        stmt.close();
    }

    public static void initRecordLabel() throws SQLException{
        String query = "SELECT * FROM recordlabel";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String recordlabelId = rs.getString("recordlabelid");
            String name = rs.getString("name");
            RecordLabel r = new RecordLabel(recordlabelId, name, getDistributionsRecordLabel(rs.getString("recordlabelid")));
            System.out.println(i+" -- "+r);
        }
        stmt.close();
    }

    public static void initRoles() throws SQLException{
        String query = "SELECT * FROM roles";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String roleID = rs.getString("roleid");
            String name = rs.getString("rolename");
            Roles r = new Roles(roleID, name);
            System.out.println(i+" -- "+r);
        }
        stmt.close();
    }

    public static void initSong() throws SQLException{
        String query = "SELECT * FROM song";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
            String songID = rs.getString("songid");
            Date date = rs.getDate("releaseDate");
            int duration = rs.getInt("duration");
            Song s = new Song(songID, date, duration, getContributorsSong(rs.getString("songid")), getRolesSong(rs.getString("songid")), getComponentsSong(rs.getString("songid")));
            System.out.println(i+" -- "+s);
        }
        stmt.close();
    }


    public static void getConRoles(String song) throws SQLException{
        String query = "SELECT fullname,rolename FROM contributors"
        +" JOIN rolesconsong  rcs USING(contributorid)"
        +" JOIN roles USING(roleid)"
        +" JOIN song USING(songid)"
        +" JOIN distribution USING(songid)"
        +" WHERE songtitle LIKE ? ";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, song);
        ResultSet rs = stmt.executeQuery();
        //List<String> results = new ArrayList<String>();
        while(rs.next()){
            System.out.println("name: "+rs.getString("fullname") + "|| role: "+ rs.getString("rolname") );
        }

    }

    public static void showTables(){
        System.out.println(" --- TABLES --- ");
        System.out.println("1 -- COMPONENTS");
        System.out.println("2 -- CONTRIBUTORS");
        System.out.println("3 -- DISTRIBUTION");
        System.out.println("4 -- MARKETS");
        System.out.println("5 -- RECORDLABEL");
        System.out.println("6 -- ROLES");
        System.out.println("7 -- SONG");
        System.out.println(" --- SELECT WHICH TABLE TO MODIFY BY TYPING THE NUMBER --- ");


    }

    public static void insertComponents() throws SQLException{
        String query = "INSERT INTO components(componentid,songid,offsetcomponent,durationcomponent,songused,offsetsong,durationsong) VALUES('COM' || componentSeq.nextval,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER songId: ");
        String songId = s.nextLine();
        System.out.println("ENTER offsetComponent:");
        int osc = s.nextInt();
        System.out.println("ENTER durationComponent:");
        int dc = s.nextInt();
        System.out.println("ENTER songUsed:");
        String su = s.nextLine();
        System.out.println("ENTER offsetSong:");
        int os = s.nextInt();        
        System.out.println("ENTER durationSong:");
        int ds = s.nextInt();
        stmt.setString(1, songId);
        stmt.setInt(2, osc);
        stmt.setInt(3, dc);
        stmt.setString(4, su);
        stmt.setInt(5, os);
        stmt.setInt(6, ds);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void insertContributors() throws SQLException{
        String query = "INSERT INTO contributors(contributorid,fullname) VALUES('CO' || contributorSeq.nextval,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER fullName:");
        String name = s.nextLine();
        stmt.setString(1, name);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void insertDistribution() throws SQLException, ParseException{
        String query = "INSERT INTO distribution(songid,recordlabelid, marketid, distributiondate, songtitle) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER SongID:");
        String sid = s.nextLine();
        System.out.println("ENTER recordLabelID:");
        String rid = s.nextLine();
        System.out.println("ENTER marketID:");
        String mid = s.nextLine();
        System.out.println("ENTER releaseDate:");
        String dateTemp = s.nextLine();
        Date date = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(dateTemp);
        System.out.println("ENTER song title:");
        String title = s.nextLine();
       
        stmt.setString(1, sid);
        stmt.setString(2, rid);
        stmt.setString(3, mid);
        stmt.setDate(4, date);
        stmt.setString(5, title);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void insertMarkets() throws SQLException{
        String query = "INSERT INTO markets(marketid, area) VALUES('M' || marketSeq.nextval,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER area:");
        String area = s.nextLine();
            
        stmt.setString(1, area);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
        updateAuditLog("INSERTED into markets table");
    }

    public static void insertRecordLabel() throws SQLException{
        String query = "INSERT INTO recordlabel(recordlabelid, name) VALUES('RL' || recordlabelSeq.nextval,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER Name:");
        String name = s.nextLine();
        
        stmt.setString(1, name);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void insertRoles() throws SQLException{
        String query = "INSERT INTO roles(roleid,rolename) VALUES('RO' || rolesSeq.nextval,?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER RoleName:");
        String name = s.nextLine();
          
        stmt.setString(1, name);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void insertSong() throws SQLException, ParseException{
        String query = "INSERT INTO song(songid, releasedate, duration) VALUES('S' || songSeq.nextval,?, ?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        Scanner s = new Scanner(System.in);
        System.out.println("ENTER ReleaseDate:");
        String dateTemp = s.nextLine();
        Date date = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(dateTemp);
        System.out.println("ENTER Duration:");
        int duration = s.nextInt();
          
        stmt.setDate(1, date);
        stmt.setInt(2, duration);
        stmt.executeUpdate();
        System.out.println("INSERT successfully completed");
        stmt.close();
    }

    public static void deleteComponent(String comId) throws SQLException{
        String query = "SELECT * FROM components WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comId);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String componentId = rs.getString("componentid");
        String songId = rs.getString("songid");
        int offsetComponent = rs.getInt("offsetcomponent");
        int durationComponent = rs.getInt("durationcomponent");
        String songUsed = rs.getString("songused");
        int offsetSong = rs.getInt("offsetsong");
        int durationSong = rs.getInt("durationsong");
        Components components = new Components(componentId, songId, offsetComponent, durationComponent, songUsed, offsetSong, durationSong, getSongsComponent(comId));
        System.out.println(1+" -- "+components);
        stmt.close(); 
        String q2 = "DELETE FROM components WHERE componentId = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, comId);
        stmt.executeUpdate();
        System.out.println("DELETE successfully completed");
    } 

    public static void deleteContributors(String conID) throws SQLException{
        String query = "SELECT * FROM contributors WHERE contributorid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, conID);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String contributorId = rs.getString("contributorId");
        String name = rs.getString("fullname");
        Contributors contributors = new Contributors(contributorId, name);
        System.out.println(1+" -- "+contributors);
        stmt.close();
        
        String q2 = "DELETE FROM contributors WHERE contributorid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, conID);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void deleteDistribution(String songid) throws SQLException{
        String query = "SELECT * FROM distribution WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String songID = rs.getString("songid");
        String recordLabelID = rs.getString("recordlabelid");
        String marketid = rs.getString("marketid");
        Date distDate = rs.getDate("distributiondate");
        String songTitle = rs.getString("songtitle");
        Distribution d = new Distribution(songID, recordLabelID, marketid, distDate,songTitle, getSongsSong(songid));
        System.out.println(1+" -- "+d);
        stmt.close();
        
        String q2 = "DELETE FROM distribution WHERE songid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, songid);
        stmt.executeUpdate();
        System.out.println("DELETE successfully completed");
        stmt.close();
    }

    public static void deleteMarket(String mID) throws SQLException{
        String query = "SELECT * FROM markets WHERE marketid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, mID);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String marketid = rs.getString("marketid");
        String area = rs.getString("area");
        Markets market = new Markets(marketid, area);
        System.out.println(1+" -- "+market);
        stmt.close();
        
        String q2 = "DELETE FROM markets WHERE marketid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, mID);
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Delete successfully completed");
    }

    public static void deleteRecordLabel(String rid) throws SQLException{
        String query = "SELECT * FROM recordlabel WHERE recordlabelid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, rid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String rlID = rs.getString("recordlabelid");
        String name = rs.getString("name");
        RecordLabel rl = new RecordLabel(rlID, name, getDistributionsRecordLabel(rid));
        System.out.println(1+" -- "+rl);
        stmt.close();
        
        String q2 = "DELETE FROM recordlabel WHERE recordlabelid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, rid);
        stmt.executeUpdate();
        System.out.println("Delete successfully completed");
        stmt.close();
    }

    public static void deleteRole(String roid) throws SQLException{
        String query = "SELECT * FROM roles WHERE roleid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, roid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String roleID = rs.getString("roleid");
        String name = rs.getString("rolename");
        Roles r = new Roles(roleID, name);
        System.out.println(1+" -- "+r);
        stmt.close();
        
        String q2 = "DELETE FROM roles WHERE roleid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, roid);
        stmt.executeUpdate();
        System.out.println("DELETE successfully completed");
        stmt.close();
    }

    public static void deleteSongs(String sid) throws SQLException{
        String query = "SELECT * FROM song WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, sid);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        String songID = rs.getString("songid");
        Date rdate = rs.getDate("releasedate");
        int sduration = rs.getInt("duration");
        Song s = new Song(songID, rdate, sduration, getContributorsSong(sid), getRolesSong(sid), getComponentsSong(sid));
        System.out.println(1+" -- "+s);
        stmt.close();
        
        String q2 = "DELETE FROM SONG WHERE songid = ?";
        stmt = con.prepareStatement(q2);
        stmt.setString(1, sid);
        stmt.executeUpdate();
        System.out.println("DELETE successfully completed");
        stmt.close();
    }

    public static List<Song> getSongsComponent(String comID) throws SQLException{
        String query = "SELECT * FROM song INNER JOIN components USING (songid) WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comID);
        ResultSet rs = stmt.executeQuery();
        List<Song> songs = new ArrayList<>();
        while(rs.next()){
            Song song = new Song(rs.getString("songid"), rs.getDate("releasedate"), rs.getInt("duration"), getContributorsComponent(comID), getRolesComponent(comID), getComponentsComponent(comID));
            songs.add(song);
        }
        rs.close();

        stmt.close();
        return songs;
    }

    public static List<Contributors> getContributorsComponent(String comID) throws SQLException{
        String query = "SELECT * FROM contributors INNER JOIN rolesconsong USING (contributorid) INNER JOIN song USING (songid) INNER JOIN components USING (songid) WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comID);
        ResultSet rs = stmt.executeQuery();
        List<Contributors> contributors = new ArrayList<>();
        while(rs.next()){
            Contributors contributor = new Contributors(rs.getString("contributorid"), rs.getString("fullname"));
            contributors.add(contributor);
        }
        rs.close();

        stmt.close();
        return contributors;
    }

    public static List<Roles> getRolesComponent(String comID) throws SQLException{
        String query = "SELECT * FROM roles INNER JOIN rolesconsong USING (roleid) INNER JOIN song USING (songid) INNER JOIN components USING (songid) WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comID);
        ResultSet rs = stmt.executeQuery();
        List<Roles> roles = new ArrayList<>();
        while(rs.next()){
            Roles role = new Roles(rs.getString("roleid"), rs.getString("rolename"));
            roles.add(role);
        }
        rs.close();

        stmt.close();
        return roles;
    }

    public static List<Components> getComponentsComponent(String comID) throws SQLException{
        String query = "SELECT * FROM components WHERE componentid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, comID);
        ResultSet rs = stmt.executeQuery();
        List<Components> components = new ArrayList<>();
        while(rs.next()){
            Components component = new Components(rs.getString("componentid"), rs.getString("songid"), rs.getInt("offsetcomponent"), rs.getInt("durationcomponent"), rs.getString("songused"), rs.getInt("offsetsong"), rs.getInt("durationsong"), getSongsSong(rs.getString("songused")));
            components.add(component);
        }
        rs.close();

        stmt.close();
        return components;
    }

    public static List<Song> getSongsSong(String songID) throws SQLException{
        String query = "SELECT * FROM song WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songID);
        ResultSet rs = stmt.executeQuery();
        List<Song> songs = new ArrayList<>();
        while(rs.next()){
            Song song = new Song(rs.getString("songid"), rs.getDate("releasedate"), rs.getInt("duration"), getContributorsSong(songID), getRolesSong(songID), getComponentsSong(songID));
            songs.add(song);
        }
        rs.close();

        stmt.close();
        return songs;
    }

    public static List<Contributors> getContributorsSong(String songID) throws SQLException{
        String query = "SELECT * FROM contributors INNER JOIN rolesconsong USING (contributorid) WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songID);
        ResultSet rs = stmt.executeQuery();
        List<Contributors> contributors = new ArrayList<>();
        while(rs.next()){
            Contributors contributor = new Contributors(rs.getString("contributorid"), rs.getString("fullname"));
            contributors.add(contributor);
        }
        rs.close();

        stmt.close();
        return contributors;
    }

    public static List<Roles> getRolesSong(String songID) throws SQLException{
        String query = "SELECT * FROM roles INNER JOIN rolesconsong USING(roleid) WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songID);
        ResultSet rs = stmt.executeQuery();
        List<Roles> roles = new ArrayList<>();
        while(rs.next()){
            Roles role = new Roles(rs.getString("roleid"), rs.getString("rolename"));
            roles.add(role);
        }
        rs.close();

        stmt.close();
        return roles;
    }

    public static List<Components> getComponentsSong(String songID) throws SQLException{
        String query = "SELECT * FROM components INNER JOIN song USING (songid) WHERE songid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, songID);
        ResultSet rs = stmt.executeQuery();
        List<Components> components = new ArrayList<>();
        while(rs.next()){
            Components component = new Components(rs.getString("componentid"), rs.getString("songid"), rs.getInt("offsetcomponent"), rs.getInt("durationcomponent"), rs.getString("songused"), rs.getInt("offsetsong"), rs.getInt("durationsong"), getSongsSong(rs.getString("songused")));
            components.add(component);
        }
        rs.close();
        stmt.close();
        return components;
    }

    public static List<Distribution> getDistributionsRecordLabel(String rlID) throws SQLException{
        String query = "SELECT * FROM distribution INNER JOIN recordlabel USING (recordlabelid) WHERE recordlabelid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, rlID);
        ResultSet rs = stmt.executeQuery();
        List<Distribution> distributions = new ArrayList<>();
        while(rs.next()){
            Distribution distribution = new Distribution(rs.getString("songid"), rs.getString("recordlabelid"), rs.getString("marketid"), rs.getDate("distributiondate"), rs.getString("songtitle"), getSongsSong(rs.getString("songid")));
            distributions.add(distribution);
        }
        rs.close();
        stmt.close();
        return distributions;
    }

    public static List<Distribution> getDistributionsMarket(String mID) throws SQLException{
        String query = "SELECT * FROM distribution INNER JOIN markets USING (marketid) WHERE marketid = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, mID);
        ResultSet rs = stmt.executeQuery();
        List<Distribution> distributions = new ArrayList<>();
        while(rs.next()){
            Distribution distribution = new Distribution(rs.getString("songid"), rs.getString("recordlabelid"), rs.getString("marketid"), rs.getDate("distributiondate"), rs.getString("songtitle"), getSongsSong(rs.getString("songid")));
            distributions.add(distribution);
        }
        rs.close();
        stmt.close();
        return distributions;
    }
}