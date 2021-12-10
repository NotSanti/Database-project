import java.util.Date;
import java.util.List;

public class Distribution{
    protected String songId;
    protected String recordLabelId;
    protected Date releaseDate;
    protected String marketId;
    protected List<Song> songs;
   
    public Distribution(String songId, String recordLabelId, Date releaseDate, String marketId, List<Song> songs) {
        this.songId = songId;
        this.recordLabelId = recordLabelId;
        this.releaseDate = releaseDate;
        this.marketId = marketId;
        this.songs = songs;
    }
}