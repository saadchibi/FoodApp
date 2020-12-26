package application01.ensa.uit.ac.foodapp;

import java.io.Serializable;

public class Restaurant implements Serializable {
    String name;
    String status;
    Double latitude;
    Double longitude;
    Float distance;
    String tel;

    public Restaurant(String name, String status, Double latitude, Double longitude, Float distance, String tel) {
        this.name = name;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
