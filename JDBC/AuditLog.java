package JDBC;

import java.util.Date;

public class AuditLog {
    protected String username;
    protected Date date;
    protected String info;
    
    public AuditLog(String username, Date date, String info) {
        this.username = username;
        this.date = date;
        this.info = info;
    }
}
