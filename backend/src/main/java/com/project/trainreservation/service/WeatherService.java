package com.project.trainreservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * WeatherService, OpenWeatherMap API'si aracılığıyla hava durumu bilgisini alır.
 */
@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    /**
     * Belirtilen şehir için OpenWeatherMap API'den hava durumu bilgisi alır.
     *
     * @param city Şehir adı
     * @return Hava durumu bilgisi JSON formatında String olarak döner
     */
    public String getWeather(String city) {
        // API URL'sini oluştur
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, apiKey);

        // RestTemplate ile API'ye istek yap
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            // Hata durumunda kullanıcıya anlamlı bir JSON mesajı döndür
            return String.format("{\"error\": \"Hava durumu bilgisi alınamadı: %s\"}", e.getMessage());
        }
    }
}
