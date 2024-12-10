import React, { Component } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Login from "./components/Login";
import ForgotPassword from "./forgotpassword/ForgotPassword";
import Dashboard from "./dashboard/Dashboard";
import Trains from "./trains/Trains";
import Reservations from "./reservations/Reservations";
import Tickets from "./tickets/Tickets";
import Profile from "./profile/Profile";







function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/trains" element={<Trains />} />
        <Route path="/reservations" element={<Reservations />} />
        <Route path="/tickets" element={<Tickets />} />
        <Route path="/profile" element={<Profile />} />

        
      </Routes>
    </Router>
  );
}

export default App;
