import React, { useState } from "react";
import axios from "axios"; // Axios doğrudan kullanılıyor
import { useNavigate } from "react-router-dom";
import "./Login.css";

const Login = () => {
  const [credentials, setCredentials] = useState({ email: "", password: "" });
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCredentials({ ...credentials, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/users/login", {
        email: credentials.email,
        password: credentials.password,
      });
      console.log("Login Successful:", response.data);
      alert("Login successful!");
      navigate("/dashboard");
    } catch (error) {
      console.error("Login Error:", error);
      setErrorMessage("Invalid email or password.");
    }
  };

  const handleForgotPassword = () => {
    navigate("/forgot-password"); // Forgot Password sayfasına yönlendir
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h2 className="login-title">Login</h2>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={credentials.email}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="input-group">
            <input
              type="password"
              name="password"
              placeholder="Password"
              value={credentials.password}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="options">
            <label>
              <input type="checkbox" /> Remember me
            </label>
            <button
              type="button"
              className="link-button"
              onClick={handleForgotPassword}
            >
              Forgot password?
            </button>
          </div>
          <button type="submit" className="login-button">
            Login
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;
