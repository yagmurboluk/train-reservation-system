package com.project.trainreservation.dto;

public class WeatherDTO {
    private String city;
    private String description;
    private double temperature;

    // Getter ve Setter metotları
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
