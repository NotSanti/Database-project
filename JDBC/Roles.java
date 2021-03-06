package JDBC;

public class Roles {
    protected String roleId;
    protected String roleName;

    public Roles(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString(){
        
        return ("|| roleID: "+ this.roleId+" || "+
        "roleName: "+this.roleName+" || " + "\n");
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
 
}
