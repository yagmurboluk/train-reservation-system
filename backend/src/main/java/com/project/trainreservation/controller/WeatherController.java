package com.project.trainreservation.controller;

import com.project.trainreservation.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

   
    @GetMapping("/api/weather")
    public String getWeather(@RequestParam String city) {
        
        if (city == null || city.trim().isEmpty()) {
            return "{\"error\": \"Geçerli bir şehir adı giriniz.\"}";
        }

        try {
            
            return weatherService.getWeather(city.trim());
        } catch (Exception e) {
            
            return "{\"error\": \"Hava durumu bilgisi alınamadı: " + e.getMessage() + "\"}";
        }
    }
}
