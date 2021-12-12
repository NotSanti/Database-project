package JDBC;

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
            String componentNum, int offsetSong, int durationSong,List<Song> songs) {
        this.componentId = componentId;
        this.songId = songId;
        this.offsetComponent = offsetComponent;
        this.durationComponent = durationComponent;
        this.componentNum = componentNum;
        this.offsetSong = offsetSong;
        this.durationSong = durationSong;
        this.songs = songs;
    }

    @Override
    public String toString(){
        
        return ("componentID: "+this.componentId+" || songID: "+songId+" || offSetComponent: "+this.offsetComponent+
        " || durationComponent: "+this.durationComponent+" || componentNum: "+this.componentNum+"|| durationSong: "
        + this.durationSong+" ||"+"\n");
    }

    public String getComponentId() {
        return componentId;
    }

    public String getSongId() {
        return songId;
    }

    public int getOffsetComponent() {
        return offsetComponent;
    }

    public int getDurationComponent() {
        return durationComponent;
    }

    public String getComponentNum() {
        return componentNum;
    }

    public int getOffsetSong() {
        return offsetSong;
    }

    public int getDurationSong() {
        return durationSong;
    }
    
}
