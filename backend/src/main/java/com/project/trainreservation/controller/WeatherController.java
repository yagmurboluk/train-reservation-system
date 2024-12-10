package com.project.trainreservation.controller;

import com.project.trainreservation.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WeatherController, hava durumu bilgilerini almak için kullanılan bir REST API sağlar.
 */
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * Belirtilen şehir için hava durumu bilgilerini döner.
     *
     * @param city Şehir adı
     * @return Hava durumu bilgisi JSON formatında String olarak döner
     */
    @GetMapping("/api/weather")
    public String getWeather(@RequestParam String city) {
        // Şehir adı boş veya geçersiz ise bir hata mesajı döndür
        if (city == null || city.trim().isEmpty()) {
            return "{\"error\": \"Geçerli bir şehir adı giriniz.\"}";
        }

        try {
            // WeatherService kullanarak hava durumu bilgisini al
            return weatherService.getWeather(city.trim());
        } catch (Exception e) {
            // Hata durumunda JSON formatında bir hata mesajı döndür
            return "{\"error\": \"Hava durumu bilgisi alınamadı: " + e.getMessage() + "\"}";
        }
    }
}
