package org.techtown.festival;

public class RecyclerItem {
    private String nameStr ;
    private String locationStr ;
    private String periodStr ;

    public void setName(String name) {
        nameStr = name ;
    }
    public void setLocation(String location) {
        locationStr = location ;
    }
    public void setPeriod(String period) {
        periodStr = period ;
    }

    public String getName() {
        return this.nameStr ;
    }
    public String getLocation() {
        return this.locationStr ;
    }
    public String getPeriod() {
        return this.periodStr ;
    }
}

