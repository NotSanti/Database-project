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

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }
    
}
