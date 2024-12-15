package com.project.trainreservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


 
@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    
    public String getWeather(String city) {
        
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, apiKey);

        
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            
            return String.format("{\"error\": \"Hava durumu bilgisi alınamadı: %s\"}", e.getMessage());
        }
    }
}
