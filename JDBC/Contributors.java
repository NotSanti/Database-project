package JDBC;

public class Contributors {
    protected String contributorId;
    protected String fullname;
   

    public Contributors(String contributorId, String fullname) {
        this.contributorId = contributorId;
        this.fullname = fullname;
        
    }

    @Override
    public String toString(){
        return ("|| contributorId: "+this.contributorId+
                " || fullName: "+this.fullname +" ||"+ "\n");
    }
}
