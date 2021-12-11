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
}
