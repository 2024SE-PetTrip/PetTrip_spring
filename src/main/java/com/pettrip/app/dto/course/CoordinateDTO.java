package com.pettrip.app.dto.course;
import java.util.List;
public class CoordinateDTO {
    private int order;
    private double latitude;
    private double longitude;

    // Getters and Setters
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}

