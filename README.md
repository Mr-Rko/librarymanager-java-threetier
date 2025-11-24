# 📚 Library Manager — Spring Boot + Thymeleaf + MySQL

A complete **Library Management System** built with **Spring Boot**, **Thymeleaf**, and **MySQL**.  
This web application includes full book management, borrower tracking, a clean UI, and Docker support for fast deployment.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Java](https://img.shields.io/badge/Java-17-orange)

---

## ✨ Features

### 📖 Book Management
- Add new books with validation  
- Edit and delete books  
- Search by title, author, or genre  
- Filter available books  
- Prevent duplicate ISBN entries  

### 🔄 Borrowing System
- Borrow and return books  
- Due-date tracking  
- View borrow history  
- Overdue detection  
- View currently borrowed items  

### 🎨 User Interface
- Fully responsive design  
- Built with **Bootstrap 5**  
- Clean and modern UI  
- Dashboard with statistics  
- Frontend + backend validation  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot 3.2 (Java 17) |
| Frontend | Thymeleaf + Bootstrap 5 |
| Database | MySQL 8 |
| Build Tool | Maven |
| Deployment | Docker & Docker Compose |

---

## 🚀 Getting Started

### **Prerequisites**
- Docker & Docker Compose  
- Git  
- Java 17 (if running without Docker)

---

## 📦 Installation & Run (Docker Recommended)

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Mr-Rko/librarymanager-java-threetier.git
cd librarymanager-java-threetier
```

### 2️⃣ Run with deployment script
```bash
chmod +x setup.sh
./setup.sh
```
### 3️⃣ OR run manually
```bash
docker compose down
docker compose up --build -d
```
### 🌐 Access the App

Application: http://localhost:8080

MySQL: localhost:3306

## 🖼️ Screenshots

### 🏠 Homepage
![Homepage](https://raw.githubusercontent.com/Mr-Rko/librarymanager-java-threetier/main/screenshot/homepage.png)

### 📚 All Books
![All Books](https://raw.githubusercontent.com/Mr-Rko/librarymanager-java-threetier/main/screenshot/allbooks.png)

### ➕ Add New Book
![Add New Book](https://raw.githubusercontent.com/Mr-Rko/librarymanager-java-threetier/main/screenshot/newbookadd.png)

### 📖 Borrow Book
![Borrow Book](https://raw.githubusercontent.com/Mr-Rko/librarymanager-java-threetier/main/screenshot/borrowbook.png)

### 🕒 Borrow History
![Borrow History](https://raw.githubusercontent.com/Mr-Rko/librarymanager-java-threetier/main/screenshot/borrowhistory.png)


🧑‍💻 Author

Omar Faruk
📧 of4689469@gmail.com

🔗 GitHub: https://github.com/Mr-Rko

### ⭐ Contributions

Pull requests are welcome!

For major changes, please open an issue first to discuss what you would like to modify.
