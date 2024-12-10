import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Reservations.css";

const Reservations = () => {
  const [reservations, setReservations] = useState([]);
  const [ticketInfo, setTicketInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Tüm rezervasyonları çek
    fetchReservations();
  }, []);

  const fetchReservations = () => {
    axios
      .get("http://localhost:8080/api/reservations")
      .then((response) => {
        setReservations(response.data);
      })
      .catch((error) => {
        console.error("Error fetching reservations:", error);
      });
  };

  const handleCreateTicket= (reservation) => {
    // Seçili rezervasyon için bilet oluştur
    axios
      .post("http://localhost:8080/api/tickets", {
        userId: reservation.userId,
        trainId: reservation.trainId,
        bookingDate: reservation.bookingDate,
        travelDate: reservation.travelDate,
        seatNumber: reservation.seatNumber,
        status: "BOOKED",
      })
      .then((response) => {
        setTicketInfo(response.data);
        navigate("/tickets", { state: { ticketInfo: response.data } }); // Kullanıcıyı bilet oluşturma sayfasına yönlendir
        fetchReservations(); // Rezervasyon listesini güncellemek için yeniden çek
      })
      .catch((error) => {
        console.error("Error creating ticket:", error);
      });
  };

  const handleCancelReservation = (reservationId) => {
    // Rezervasyonu iptal et
    axios
      .delete(`http://localhost:8080/api/reservations/${reservationId}`)
      .then(() => {
        alert("Rezervasyon başarıyla iptal edildi.");
        fetchReservations(); // Listeyi güncelle
      })
      .catch((error) => {
        console.error("Error cancelling reservation:", error);
        alert("Rezervasyon iptal edilirken bir hata oluştu.");
      });
  };

  return (
    <div className="reservations-container">
      <h1 className="reservations-title">Tren Rezervasyonları</h1>
      <div className="reservations-list">
        {reservations.map((reservation) => (
          <div key={reservation.reservationId} className="reservation-card">
            <p>
              <span className="label">Kullanıcı ID:</span> {reservation.userId}
            </p>
            <p>
              <span className="label">Tren ID:</span> {reservation.trainId}
            </p>
            <p>
              <span className="label">Koltuk Numarası:</span> {reservation.seatNumber}
            </p>
            <button
              className="create-ticket-button"
              onClick={() => handleCreateTicket(reservation)}
            >
              Bilet Oluştur
            </button>
            <button
              className="cancel-reservation-button"
              onClick={() => handleCancelReservation(reservation.reservationId)}
            >
              Rezervasyon İptal Et
            </button>
          </div>
        ))}
      </div>
      {ticketInfo && (
        <div className="ticket-info">
          <h2>Ticket Details</h2>
          <p>
            <span className="label">Bilet ID:</span> {ticketInfo.ticketId}
          </p>
          <p>
            <span className="label">Rezervasyon ID:</span> {ticketInfo.reservationId}
          </p>
          <p>
            <span className="label">Veriliş Tarihi:</span> {ticketInfo.issueDate}
          </p>
          <p>
            <span className="label">Yolcu Adı:</span> {ticketInfo.passengerName}
          </p>
        </div>
      )}
    </div>
  );
};

export default Reservations;
