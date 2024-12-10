import React, { useState } from "react";
import { Link } from "react-router-dom";
import Weather from "../weather/Weather"; // Weather bileşeni dahil edildi
import "./Dashboard.css";

const Dashboard = () => {
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    date: "",
    passengers: 1,
  });

  const [tickets, setTickets] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `http://localhost:8080/api/tickets/filter?from=${formData.from}&to=${formData.to}&date=${formData.date}&passengers=${formData.passengers}`
      );
      const data = await response.json();

      const ticketsWithPrice = data.map((ticket) => ({
        ...ticket,
        price: Math.floor(Math.random() * (700 - 300 + 1)) + 300,
      }));

      setTickets(ticketsWithPrice);
      setIsModalOpen(true);
    } catch (error) {
      console.error("Error fetching tickets:", error);
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>Türkiye Tren Rezervasyon Hattı</h1>
        <nav className="dashboard-nav">
          <a href="/trains">Trenler</a>
          <a href="/reservations">Rezervasyonlarım</a>
          <a href="/tickets">Biletlerim</a>
          <Link to="/profile">Profilim</Link>
        </nav>
      </header>
      <main className="dashboard-main">
        <div className="reservation-box">
          <h2>Yolculuğunuzu Planlayın</h2>
          <form className="reservation-form" onSubmit={handleSearch}>
            <div className="form-group">
              <label htmlFor="from">Nereden:</label>
              <input
                type="text"
                id="from"
                name="from"
                placeholder="Enter departure station"
                value={formData.from}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="to">Nereye:</label>
              <input
                type="text"
                id="to"
                name="to"
                placeholder="Enter destination station"
                value={formData.to}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="date">Tarih:</label>
              <input
                type="date"
                id="date"
                name="date"
                value={formData.date}
                onChange={handleChange}
              />
            </div>
            <div className="form-group">
              <label htmlFor="passengers">Yolcu Sayısı:</label>
              <input
                type="number"
                id="passengers"
                name="passengers"
                min="1"
                max="10"
                value={formData.passengers}
                onChange={handleChange}
              />
            </div>
            <button type="submit" className="reservation-button">
              Bilet Bulun
            </button>
          </form>
        </div>

        {isModalOpen && (
          <div className="modal">
            <div className="modal-content">
              <span className="close-button" onClick={closeModal}>
                &times;
              </span>
              <h2>Bulunan Biletler</h2>
              {tickets.length > 0 ? (
                <ul>
                  {tickets.map((ticket, index) => (
                    <li key={index}>
                      <p>
                        <b>Kalkış:</b> {ticket.from || "Belirtilmemiş"}
                      </p>
                      <p>
                        <b>Varış:</b> {ticket.to || "Belirtilmemiş"}
                      </p>
                      <p>
                        <b>Tarih:</b> {ticket.travelDate || "Belirtilmemiş"}
                      </p>
                      <p>
                        <b>Fiyat:</b> {ticket.price}₺
                      </p>
                    </li>
                  ))}
                </ul>
              ) : (
                <p>Bilet bulunamadı.</p>
              )}
            </div>
          </div>
        )}

        <div className="popular-routes">
          <h2>Popüler Güzergahlar</h2>
          <div className="routes-grid">
            <div className="route-card">
              <img
                src="https://demkagayrimenkul.com/wp-content/uploads/2020/11/ara-sokaklar-ve-renkli-cicekler-alacati-izmir-1024x576.png"
                alt="Ankara - İzmir"
              />
              <h3>Ankara - İzmir</h3>
              <p>500₺</p>
            </div>
            <div className="route-card">
              <img
                src="https://d25tea7qfcsjlw.cloudfront.net/1202/kicerik/92092/b-0220.jpg"
                alt="Ankara - Antalya"
              />
              <h3>Ankara - Antalya</h3>
              <p>450₺ </p>
            </div>
            <div className="route-card">
              <img
                src="https://thetourguy.com/wp-content/uploads/2022/01/Istanbul-things-to-do-feature-900x420.jpg"
                alt="Ankara - İstanbul"
              />
              <h3>Ankara - İstanbul</h3>
              <p>650₺</p>
            </div>
          </div>
        </div>

        {/* Hava Durumu Paneli */}
        <div className="weather-section">
          <Weather />
        </div>
      </main>
      <footer className="dashboard-footer">
        <p>© 2024 Türkiye Tren Rezervasyon Hattı. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Dashboard;
