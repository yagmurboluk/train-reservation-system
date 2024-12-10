import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Trains.css";

const Trains = () => {
  const [trains, setTrains] = useState([]);
  const [selectedTrain, setSelectedTrain] = useState(null);
  const [seats, setSeats] = useState([]);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [formData, setFormData] = useState({
    travelDate: "",
    ticketType: "ONE_WAY",
    ticketClass: "ECONOMY",
  });
  const [reservationSuccess, setReservationSuccess] = useState(false);
  const [reservationError, setReservationError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    // Tren bilgilerini API'den al
    axios
      .get("http://localhost:8080/api/trains")
      .then((response) => {
        setTrains(response.data);
      })
      .catch((error) => {
        console.error("Trains API Error:", error);
        setReservationError("Failed to fetch train data.");
      });
  }, []);

  const handleSelectTrain = (train) => {
    setSelectedTrain(train);
    setSelectedSeat(null);
    setSeats([]);
    setReservationSuccess(false);
    setReservationError("");

    // Tren için koltuk bilgilerini al
    axios
      .get(`http://localhost:8080/api/seats/train/${train.trainId}`)
      .then((response) => {
        setSeats(response.data);
      })
      .catch((error) => {
        console.error("Seats API Error:", error);
        setReservationError("Failed to fetch seat data.");
      });
  };

  const handleSeatSelection = (seat) => {
    if (!seat.reserved) {
      setSelectedSeat(seat);
      setFormData({ ...formData, seatNumber: seat.seatNumber });
    } else {
      alert("This seat is already reserved. Please choose another one.");
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!selectedTrain || !selectedSeat) {
      alert("Please select a train and a seat before making a reservation.");
      return;
    }

    // Rezervasyon verilerini gönder
    axios
      .post("http://localhost:8080/api/reservations", {
        ...formData,
        trainId: selectedTrain.trainId,
        seatNumber: selectedSeat.seatNumber,
        userId: 1, // Sabit bir kullanıcı ID'si
      })
      .then(() => {
        setReservationSuccess(true);
        alert("Reservation Successful!");
      })
      .catch((error) => {
        console.error("Reservation Error:", error);
        setReservationError("Failed to make reservation. Please try again.");
      });
  };

  const goToReservations = () => {
    navigate("/reservations");
  };

  return (
    <div className="trains-container">
      <h1>Mevcut Trenler</h1>
      {reservationError && <p className="error-message">{reservationError}</p>}

      <div className="train-list">
        {trains.map((train) => (
          <div
            key={train.trainId}
            className={`train-card ${
              selectedTrain?.trainId === train.trainId ? "selected" : ""
            }`}
            onClick={() => handleSelectTrain(train)}
          >
            <h3>{train.trainName}</h3>
            <p>
              {train.departureStation} → {train.arrivalStation}
            </p>
            <p>Departure: {new Date(train.departureTime).toLocaleString()}</p>
            <p>Arrival: {new Date(train.arrivalTime).toLocaleString()}</p>
            <p>Capacity: {train.capacity}</p>
          </div>
        ))}
      </div>

      {selectedTrain && (
        <div className="reservation-section">
          <h2>Koltuğunuzu Seçin {selectedTrain.trainName}</h2>
          <div className="seat-map">
            {seats.map((seat, index) => (
              <button
                key={seat.seatNumber}
                className={`seat ${
                  seat.reserved ? "reserved" : ""
                } ${selectedSeat?.seatNumber === seat.seatNumber ? "selected" : ""}`}
                onClick={() => handleSeatSelection(seat)}
                disabled={seat.reserved}
              >
                {seat.seatNumber}
              </button>
            ))}
          </div>

          {selectedSeat && (
            <div className="reservation-form">
              <h3>Rezervasyon Bilgilerim</h3>
              {reservationSuccess && (
                <div>
                  <p className="success-message">
                    Rezervasyon Başarıyla Tamamlandı!
                  </p>
                  <button
                    className="reservation-success-button"
                    onClick={goToReservations}
                  >
                    Rezervasyonlarıma Git
                  </button>
                </div>
              )}
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <label>Yolculuk Tarihi:</label>
                  <input
                    type="date"
                    name="travelDate"
                    value={formData.travelDate}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Bilet Türü:</label>
                  <select
                    name="ticketType"
                    value={formData.ticketType}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="ONE_WAY">Tek Yön</option>
                    <option value="ROUND_TRIP">Gidiş-Dönüş</option>
                  </select>
                </div>
                <div className="form-group">
                  <label>Sınıf Seçimi:</label>
                  <select
                    name="ticketClass"
                    value={formData.ticketClass}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="ECONOMY">Ekonomi</option>
                    <option value="FIRST_CLASS">Birinci Sınıf</option>
                  </select>
                </div>
                <button type="submit" className="reservation-button">
                  Şimdi Reserve Edin
                </button>
              </form>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default Trains;
