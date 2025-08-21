# 🎓 CBCT Subject Allocation System

A Spring Boot project that automatically allocates **CBCT subjects** to students based on their preferences (1st, 2nd, 3rd choice) and seat availability. Allocation is done on a **first-come, first-served** basis.

---

## 🚀 Features
- Import student and subject data from CSV/Excel
- Allocate subjects automatically based on preferences
- Export final allocation results to Excel
- REST API support for allocation
- Built using **Spring Boot + JPA + Lombok**

---

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **H2 Database (or MySQL)**
- **Apache POI (Excel Export)**
- **Lombok**
- **Maven**

---

## 📂 Project Structure
src/main/java/com/guru/cbct/cbct_allocation

├── student # Student entity + repository

├── subject # Subject entity + repository

├── allocation # Allocation logic service + controller

├── DataLoader # Loads sample data

└── CbctAllocationApplication.java


Access API

Health check → http://localhost:8080/api/allocation/ping

Trigger allocation → POST http://localhost:8080/api/allocation/run

Download results (Excel) → GET http://localhost:8080/api/allocation/export

📊 Sample Workflow

Students submit choices (CSV/Excel imported)
Allocation runs and assigns subjects
Final results exported to Excel

🖼️ Screenshots
Screenshot 2025-08-21 233659.png

Screenshot 2025-08-21 233448.png

Screenshot 2025-08-21 233329.png

✨ Future Improvements

User login (student/teacher admin portal)
Upload Google Form responses directly
Web UI with subject selection dashboard

👨‍💻 Author

Gurupada Nayak

📧 gurupadpnayak@gmail.com

🌐 LinkedIn - https://www.linkedin.com/in/gurupada-nayak-71724b25b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app
