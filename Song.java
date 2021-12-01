import java.util.List;

public class Song {
    protected String songId;
    protected int duration;
    protected List<Components> components;
    
    public Song(String songId, int duration, List<Components> components) {
        this.songId = songId;
        this.duration = duration;
        this.components = components;
    }
}
