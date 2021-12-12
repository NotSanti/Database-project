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
   
    public Distribution(String songId, String recordLabelId, String marketId, Date releaseDate, String songTitle, List<Song> songs) {
        this.songId = songId;
        this.recordLabelId = recordLabelId;
        this.marketId = marketId;
        this.releaseDate = releaseDate;
        this.songTitle = songTitle;
        this.songs = songs;
    }

    @Override
    public String toString(){
        
        return ("|| songID: "+ this.songId+" || "+
        "recordLabelID: "+this.recordLabelId+" || "
        + "releaseDate: "+ this.releaseDate+ " || " 
        + "marketID: "+this.marketId+ "|| " 
        + "songTitle: "+ this.songTitle+ "||"+ "\n");
    }
}