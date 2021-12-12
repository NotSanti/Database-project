package JDBC;

import java.util.List;

public class Markets {
    protected String marketId;
    protected String area;
    protected List<Distribution> distributions;
    
    public Markets(String marketId, String area) {
        this.marketId = marketId;
        this.area = area;
        //this.distributions = distributions;
    }

    @Override
    public String toString(){
        
        return ("|| marketID: "+ this.marketId+" || "
        +"Area: "+ this.area+ "||"+"\n");
    }

    public String getMarketId() {
        return marketId;
    }

    public String getArea() {
        return area;
    }

    public List<Distribution> getDistributions() {
        return distributions;
    }
 
}
