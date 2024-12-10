import React, { useState } from "react";
import "./Weather.css"; // CSS dosyası

const Weather = () => {
  const [city, setCity] = useState("");
  const [weatherData, setWeatherData] = useState(null);
  const [error, setError] = useState("");
  const [isHovered, setIsHovered] = useState(false);

  const fetchWeather = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/weather?city=${city}`
      );
      if (!response.ok) {
        throw new Error("Hava durumu bilgisi alınamadı.");
      }
      const data = await response.json();
      setWeatherData(data);
      setError("");
    } catch (err) {
      setError(err.message);
      setWeatherData(null);
    }
  };

  return (
    <div
      className="weather-sidebar"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div className="weather-icon-wrapper">
        <img
          src="https://cdn-icons-png.flaticon.com/512/869/869869.png"
          alt="Hava Durumu"
          className="weather-icon"
        />
        {isHovered && (
          <div className="weather-popup">
            <input
              type="text"
              placeholder="Şehir adı"
              value={city}
              onChange={(e) => setCity(e.target.value)}
              className="weather-input"
            />
            <button onClick={fetchWeather} className="weather-button">
              Getir
            </button>
            {weatherData && (
              <div className="weather-info">
                <p>
                  <b>{weatherData.name}</b>
                </p>
                <p>{weatherData.weather[0].description}</p>
                <p>{weatherData.main.temp}°C</p>
              </div>
            )}
            {error && <p className="error-message">{error}</p>}
          </div>
        )}
      </div>
    </div>
  );
};

export default Weather;
