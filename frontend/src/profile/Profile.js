import React, { useState } from "react";
import "./Profile.css";

const Profile = () => {
  const [user, setUser] = useState({
    name: "Doğa",
    email: "dogatuzun@example.com",
    birthDate: "2000-01-01",
  });

  const [isEditMode, setIsEditMode] = useState(false);
  const [editedUser, setEditedUser] = useState(user);

  const handleEdit = () => {
    setIsEditMode(true);
  };

  const handleSave = () => {
    setUser(editedUser);
    setIsEditMode(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditedUser({
      ...editedUser,
      [name]: value,
    });
  };

  return (
    <div className="profile-container">
      <h1>Profilim</h1>
      <div className="profile-card">
        <img
          src="https://img.freepik.com/free-vector/flat-design-flower-pixel-art-illustration_23-2149292280.jpg?w=360"
          alt="Profil Fotoğrafı"
          className="profile-photo"
        />
        {!isEditMode ? (
          <>
            <p><b>Ad:</b> {user.name}</p>
            <p><b>E-posta:</b> {user.email}</p>
            <p><b>Doğum Tarihi:</b> {user.birthDate}</p>
            <button className="edit-button" onClick={handleEdit}>
              Düzenle
            </button>
          </>
        ) : (
          <div className="edit-form">
            <label>
              Ad:
              <input
                type="text"
                name="name"
                value={editedUser.name}
                onChange={handleChange}
              />
            </label>
            <label>
              E-posta:
              <input
                type="email"
                name="email"
                value={editedUser.email}
                onChange={handleChange}
              />
            </label>
            <label>
              Doğum Tarihi:
              <input
                type="date"
                name="birthDate"
                value={editedUser.birthDate}
                onChange={handleChange}
              />
            </label>
            <button className="save-button" onClick={handleSave}>
              Kaydet
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Profile;
