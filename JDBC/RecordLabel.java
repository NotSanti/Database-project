package JDBC;

import java.util.List;

public class RecordLabel {
    protected String recordLabelId;
    protected String name;
    protected List<Distribution> distributions;
    
    public RecordLabel(String recordLabelId, String name, List<Distribution> distributions) {
        this.recordLabelId = recordLabelId;
        this.name = name;
        this.distributions = distributions;
    }

    @Override
    public String toString(){
        
        return ("|| RecordLabelID: "+ this.recordLabelId+" || "+
        "Name: "+this.name+" || " + "\n");
    }

    public String getRecordLabelId() {
        return recordLabelId;
    }

    public String getName() {
        return name;
    }

    public List<Distribution> getDistributions() {
        return distributions;
    }
    
}
