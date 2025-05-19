package com.xc.utils.ip.juhe;


public class AddressResult {
    private String area;
    private String location;

    public void setArea(String area) {
        this.area = area;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AddressResult)) return false;
        AddressResult other = (AddressResult) o;
        if (!other.canEqual(this)) return false;
        Object this$area = getArea(), other$area = other.getArea();
        if ((this$area == null) ? (other$area != null) : !this$area.equals(other$area)) return false;
        Object this$location = getLocation(), other$location = other.getLocation();
        return !((this$location == null) ? (other$location != null) : !this$location.equals(other$location));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AddressResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $area = getArea();
        result = result * 59 + (($area == null) ? 43 : $area.hashCode());
        Object $location = getLocation();
        return result * 59 + (($location == null) ? 43 : $location.hashCode());
    }

    public String toString() {
        return "AddressResult(area=" + getArea() + ", location=" + getLocation() + ")";
    }


    public String getArea() {
        return this.area;
    }

    public String getLocation() {
        return this.location;
    }
}

