package JDBC;

public class Contributors {
    protected String contributorId;
    protected String name;
   

    public Contributors(String contributorId, String name) {
        this.contributorId = contributorId;
        this.name = name;
        
    }

    @Override
    public String toString(){
        
        return ("|| contributorId: "+this.contributorId+
                " || fullName: "+this.name +" ||"+ "\n");
    }
}
