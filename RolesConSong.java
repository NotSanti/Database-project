import java.util.List;

public class RolesConSong {
    protected String contributorId;
    protected String roleId;
    protected String songId;
    protected List<Roles> roles;
    protected List<Contributors> contributors;
    protected List<Song> songs;
    
    public RolesConSong(String contributorId, String roleId, String songId, List<Roles> roles,
            List<Contributors> contributors, List<Song> songs) {
        this.contributorId = contributorId;
        this.roleId = roleId;
        this.songId = songId;
        this.roles = roles;
        this.contributors = contributors;
        this.songs = songs;
    }
}
