# fraud-detection-service
Basic Spring Boot service

## 📖 Overview
This service provides a REST API process payment transactions and to detect possible fraud.

---

Technologies Used

- Spring Boot 3.x
- Spring Boot Actuator (for metrics & health checks)
- Micrometer (for monitoring)
- JUnit 5 (for unit testing)

---

## 🚀 Endpoints
### **1️⃣ Transaction Evaluation**
**Endpoint:** `POST /api/verify`
- **Description:** Evaluates if a transaction violates fraud detection rules.
- **Request Body:**
  ```json
  {
    "cardNumber": "94837564923782",
    "amount": 500.00,
    "currencyCode": "DKK",
    "terminal": {
        "id": 5,
        "threatScore": 10
    }   
  }
  
- **Response:**
  ```json
    {
    "status": "OK",
    "message": "No fraud detected",
    "fraudScore": 0
  }
