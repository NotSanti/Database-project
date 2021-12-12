package JDBC;

import java.util.List;

public class Markets {
    protected String marketId;
    protected String area;
    protected List<Distribution> distributions;
    
    public Markets(String marketId, String area, List<Distribution> distributions) {
        this.marketId = marketId;
        this.area = area;
        this.distributions = distributions;
    }

    @Override
    public String toString(){
        
        return ("|| marketID: "+ this.marketId+" || "
        +"Area: "+ this.area+ "||"+"\n");
    }
}
