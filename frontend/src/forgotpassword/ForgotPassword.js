import React, { useState } from "react";
import "./ForgotPassword.css";

function ForgotPassword() {
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`A reset code has been sent to ${email}`);
  };

  return (
    <div className="forgot-password-container">
      <div className="forgot-password-card">
        <h2 className="forgot-password-title">Forgot Password</h2>
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="forgot-password-button">
            Send Code
          </button>
        </form>
        <p className="back-to-login">
          Remember your password?{" "}
          <a href="/" className="back-to-login-link">
            Back to Login
          </a>
        </p>
      </div>
    </div>
  );
}

export default ForgotPassword;
