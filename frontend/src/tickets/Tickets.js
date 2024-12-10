import React, { useState, useEffect } from "react";
import axios from "axios";
import { QRCodeCanvas } from "qrcode.react"; // QR Kod Oluşturucu
import "./Tickets.css";

const Tickets = () => {
  const [tickets, setTickets] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  const [eTicket, setETicket] = useState(null);
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  useEffect(() => {
    fetchTickets();
  }, []);

  const fetchTickets = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/tickets");
      setTickets(response.data);
      setIsLoading(false);
    } catch (error) {
      console.error("Error fetching tickets:", error);
      setError(
        "Biletler yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyin."
      );
      setIsLoading(false);
    }
  };

  const handleCreateETicket = async (ticketId) => {
    try {
      const response = await axios.post("http://localhost:8080/api/etickets", {
        ticketId,
      });
      setETicket(response.data);
      setIsDialogOpen(true);
      fetchTickets(); // Listeyi güncelle
    } catch (error) {
      console.error("Error creating e-ticket:", error);
      alert("E-Bilet oluşturulurken bir hata oluştu.");
    }
  };

  const handleShowETicket = async (ticketId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/etickets/ticket/${ticketId}`
      );
      setETicket(response.data);
      setIsDialogOpen(true);
    } catch (error) {
      console.error("Error fetching e-ticket:", error);
      alert("E-Bilet bulunamadı.");
    }
  };

  const handleDeleteTicket = async (ticketId) => {
    try {
      await axios.delete(`http://localhost:8080/api/tickets/${ticketId}`);
      alert("Bilet başarıyla iptal edildi.");
      fetchTickets(); 
    } catch (error) {
      console.error("Error deleting ticket:", error);
      alert("Bilet iptal edilirken bir hata oluştu.");
    }
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
    setETicket(null);
  };

  return (
    <div className="tickets-container">
      <h1>Biletlerim</h1>
      {isLoading ? (
        <p>Biletleriniz Yükleniyor...</p>
      ) : error ? (
        <div className="error-message">
          <i className="fas fa-exclamation-triangle"></i>
          <p>{error}</p>
        </div>
      ) : (
        <>
          <table className="tickets-table">
            <thead>
              <tr>
                <th>Ticket ID</th>
                <th>Nereden</th>
                <th>Nereye</th>
                <th>Yolculuk Tarihi</th>
                <th>Durum</th>
                <th>Hareketler</th>
              </tr>
            </thead>
            <tbody>
              {tickets.map((ticket) => (
                <tr key={ticket.ticketId}>
                  <td>{ticket.ticketId}</td>
                  <td>{ticket.from || "Belirtilmemiş"}</td>
                  <td>{ticket.to || "Belirtilmemiş"}</td>
                  <td>{ticket.travelDate || "Belirtilmemiş"}</td>
                  <td>{ticket.status}</td>
                  <td>
                    {ticket.eTicketExists ? (
                      <button
                        onClick={() => handleShowETicket(ticket.ticketId)}
                        className="show-eticket-button"
                      >
                        Show E-Ticket
                      </button>
                    ) : (
                      <button
                        onClick={() => handleCreateETicket(ticket.ticketId)}
                        className="create-eticket-button"
                      >
                        E-Bilet Oluştur
                      </button>
                    )}
                    <button
                      onClick={() => handleDeleteTicket(ticket.ticketId)}
                      className="cancel-ticket-button"
                    >
                      Bileti Sil
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* E-Ticket Dialog */}
          {isDialogOpen && eTicket && (
            <div className="dialog-overlay">
              <div className="dialog">
                <h2>E-Ticket</h2>
                <p>
                  <strong>E-Ticket ID :</strong> {eTicket.eTicketId}
                </p>
                <p>
                  <strong>Nereden:</strong> {eTicket.from}
                </p>
                <p>
                  <strong>Nereye:</strong> {eTicket.to}
                </p>
                <p>
                  <strong>Veriliş Tarihi:</strong> {eTicket.issueDate}
                </p>
                <div className="qr-code">
                  <QRCodeCanvas value={eTicket.qrCode} size={150} />
                </div>
                <p>
                  Bu QR kodu cep telefonunuzdan taratarak bileti
                  görüntüleyebilirsiniz.
                </p>
                <button onClick={closeDialog} className="close-dialog-button">
                  Close
                </button>
              </div>
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default Tickets;
