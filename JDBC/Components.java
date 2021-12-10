import java.util.List;

public class Components {
    protected String componentId;
    protected String songId;
    protected int offsetComponent;
    protected int durationComponent;
    protected String componentNum;
    protected int offsetSong;
    protected int durationSong;
    protected List<Song> songs;

    public Components(String componentId, String songId, int offsetComponent, int durationComponent,
            String componentNum, int offsetSong, int durationSong, List<Song> songs) {
        this.componentId = componentId;
        this.songId = songId;
        this.offsetComponent = offsetComponent;
        this.durationComponent = durationComponent;
        this.componentNum = componentNum;
        this.offsetSong = offsetSong;
        this.durationSong = durationSong;
        this.songs = songs;
    }
}
