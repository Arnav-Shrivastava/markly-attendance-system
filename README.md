# Markly

### Smart QR-Based Attendance Management System

Markly is a web-based attendance management system designed to eliminate proxy attendance using **time-bound QR codes** and **role-based authentication**.

It provides a secure and efficient way for institutions to manage attendance digitally.

---

## 🚀 Features

* **QR-based attendance marking**
* **Time-limited QR codes** to prevent misuse
* **Role-based access control** (Student / Faculty / Admin)
* **Secure authentication** using JWT
* **Duplicate attendance prevention**
* **Attendance reports and analytics**
* **Clean and scalable** backend architecture

---

## 🧠 How It Works

1. **Faculty** generates a QR code for a specific class session.
2. The QR code is **valid only for a limited time**.
3. **Students** scan the QR using their mobile browser.
4. The **Backend** validates the QR token, user role, and timestamp.
5. Attendance is recorded securely in the database.

---

## 🛠 Tech Stack

**Frontend**

* React
* HTML5 QR Scanner
* Axios

**Backend**

* Spring Boot
* Spring Security
* JWT Authentication
* REST APIs

**Database**

* MySQL
* MongoDB

---

## 🏗 Project Structure

```text
markly-qr-attendance/
├── backend/    # Spring Boot backend
├── frontend/   # React frontend
├── docs/       # Architecture diagrams & screenshots
└── README.md

```

---

## ⚙️ Getting Started

### Backend

1. Navigate to the `backend` folder.
2. Configure database credentials in `application.yml`.
3. Run the application using:
`mvn spring-boot:run`

### Frontend

1. Navigate to the `frontend` folder.
2. Install dependencies:
`npm install`
3. Start the development server:
`npm run dev`

---

## 🔒 Security Highlights

* **JWT-based authentication** for secure sessions.
* **Role-based endpoint access** (RBAC).
* **Server-side validation** for all attendance requests.
* **Strict entry rules:** One attendance entry per student per class per day.

---

## 📌 Future Enhancements

* Progressive Web App (PWA) support.
* Face verification for added security.
* Admin analytics dashboard.
* Automated email notifications.

---

## 👤 Author

Developed by **Arnav Shrivastava** 
---