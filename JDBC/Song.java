package JDBC;

import java.sql.Date;
import java.util.List;

public class Song {
    protected String songId;
    protected Date releaseDate;
    protected int duration;
    protected List<Contributors> contributors;
    protected List<Roles> roles;
    protected List<Components> components;
    
    public Song(String songId, Date releaseDate, int duration, List<Contributors> contributors, List<Roles> roles, List<Components> components) {
        this.songId = songId;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.contributors = contributors;
        this.roles = roles;
        this.components = components;
    }

    public String getSongId() {
        return songId;
    }

    public int getDuration() {
        return duration;
    }

    public List<Contributors> getContributors() {
        return contributors;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public List<Components> getComponents() {
        return components;
    }
    
}
