package JDBC;

import java.util.Date;
import java.util.List;

public class Distribution{
    protected String songId;
    protected String recordLabelId;
    protected String marketId;
    protected Date releaseDate;
    protected String songTitle;
    protected List<Song> songs;
   
    public Distribution(String songId, String recordLabelId, Date releaseDate, String marketId, String songTitle) {
        this.songId = songId;
        this.recordLabelId = recordLabelId;
        this.releaseDate = releaseDate;
        this.marketId = marketId;
        this.songTitle = songTitle;
    }

    @Override
    public String toString(){
        
        return ("|| songID: "+ this.songId+" || "+
        "recordLabelID: "+this.recordLabelId+" || "
        + "releaseDate: "+ this.releaseDate+ " || " 
        + "marketID: "+this.marketId+ "|| " 
        + "songTitle: "+ this.songTitle+ "||"+ "\n");
    }

    public String getSongId() {
        return songId;
    }

    public String getRecordLabelId() {
        return recordLabelId;
    }

    public String getMarketId() {
        return marketId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public List<Song> getSongs() {
        return songs;
    }
    
}