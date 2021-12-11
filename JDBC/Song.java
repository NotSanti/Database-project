package JDBC;

import java.util.List;

public class Song {
    protected String songId;
    protected int duration;
    protected List<Contributors> contributors;
    protected List<Roles> roles;
    protected List<Components> components;
    
    public Song(String songId, int duration, List<Contributors> contributors, List<Roles> roles, List<Components> components) {
        this.songId = songId;
        this.duration = duration;
        this.contributors = contributors;
        this.roles = roles;
        this.components = components;
    }
}
