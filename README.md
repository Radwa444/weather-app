# 🌦️ Weather App

A simple weather application built using MVVM architecture and Clean Architecture principles. The app displays real-time weather data from an external API (such as OpenWeather) with a modern and user-friendly interface.

---

## 🚀 Features

- Fetch real-time weather data by city or location
- Display temperature, humidity, pressure, wind speed, and cloud status
- Modern MVVM architecture with clear separation of concerns
- Dependency injection using Hilt
- API communication via Retrofit
- XML-based responsive UI
- Single Activity + Fragments architecture for easy scalability
- Smooth navigation between screens using Navigation Component

---

## 🏗️ Project Structure

```
📂 app/
 ┣ 📂 data/       → Local data sources (Retrofit), Repository interfaces
 ┣ 📂 domain/     → Entities + Repository implementations
 ┣ 📂 ui/         → Presentation layer (Single Activity, Fragments, ViewModels)
 ┣ 📂 di/         → Dependency Injection modules (Hilt)
 ┣ 📂 utils/      → Helper utilities, constants, and extensions
```

---

## 🔧 Tech Stack

- **Kotlin**
- **MVVM + Clean Architecture**
- **Hilt** (Dependency Injection)
- **Retrofit** (Networking)
- **Coroutines**
- **XML Layouts** (UI design)
- **Single Activity + Fragments**
- **Navigation Component** (Screen navigation)

---

## ⚙️ Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/weather-app.git
   ```

2. **Add your API key**  
   In the constants file or inside your Retrofit setup.

3. **Build and run the app**  
   Using Android Studio or your preferred build tool.

---

## 📌 Future Improvements

- Add a UseCases layer for improved Clean Architecture implementation
- Implement offline caching with Room Database
- Further UI enhancements (animations, dark mode, etc.)
