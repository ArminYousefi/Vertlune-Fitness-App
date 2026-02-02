# 🏋️ Vertlune Fitness App

---

## 🇮🇷 معرفی پروژه (فارسی)

**Vertlune Fitness App** یک اپلیکیشن اندرویدی مدرن در حوزه فیتنس و فروش محصولات ورزشی است که با استفاده از **Kotlin** و **Jetpack Compose** توسعه داده شده است. این پروژه با معماری تمیز (Clean Architecture) و الگوی **MVVM** پیاده‌سازی شده و تمرکز اصلی آن روی خوانایی، مقیاس‌پذیری و تجربه کاربری روان است.

### ✨ امکانات اصلی
- نمایش محصولات ورزشی و فیتنس
- دسته‌بندی و فیلتر محصولات
- بخش محصولات پرفروش (Best Sellers)
- افزودن و مدیریت سبد خرید
- صفحه Discover برای پیشنهادات ویژه
- جستجوی محصولات
- طراحی کاملاً مدرن با Jetpack Compose
- مدیریت وضعیت‌ها با ViewModel و State
- دیتابیس محلی با Room

### 🧱 معماری پروژه
این پروژه بر اساس **Clean Architecture** طراحی شده و شامل لایه‌های زیر است:

- **UI Layer**: شامل Screenها، Componentها و Themeها (Jetpack Compose)
- **Domain Layer**: شامل Modelها، Repository Interfaceها و UseCaseها
- **Data Layer**: شامل Repository Implementation، Room Database، DAOها و Mapperها

### 🛠 تکنولوژی‌ها و ابزارها
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Clean Architecture
- Room Database
- Hilt (Dependency Injection)
- Coroutines & Flow
- Material 3

### 📁 ساختار پوشه‌ها (خلاصه)
```
app/
├── data/
│   ├── local/
│   ├── repository/
│   └── mapper/
├── domain/
│   ├── model/
│   ├── repository/
│   └── use_case/
├── ui/
│   ├── screens/
│   ├── components/
│   ├── navigation/
│   └── theme/
└── di/
```

### ▶️ نحوه اجرا
1. پروژه را کلون کنید
2. در Android Studio باز کنید
3. Gradle Sync انجام شود
4. اپلیکیشن را اجرا کنید 🚀

---

## 🇬🇧 Project Overview (English)

**Vertlune Fitness App** is a modern Android fitness & shopping application built using **Kotlin** and **Jetpack Compose**. The project follows **Clean Architecture** principles and the **MVVM** pattern, focusing on scalability, maintainability, and a smooth user experience.

### ✨ Key Features
- Fitness & sports product listing
- Product categories and filters
- Best seller section
- Shopping cart management
- Discover section for featured items
- Product search functionality
- Fully modern UI with Jetpack Compose
- State management with ViewModel
- Local database using Room

### 🧱 Architecture
The project is structured based on **Clean Architecture**, consisting of:

- **UI Layer**: Screens, UI components, navigation, and theming
- **Domain Layer**: Business logic, models, repositories, and use cases
- **Data Layer**: Room database, DAO interfaces, mappers, and repository implementations

### 🛠 Tech Stack
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Clean Architecture
- Room Database
- Hilt for Dependency Injection
- Kotlin Coroutines & Flow
- Material 3

### 📁 Project Structure (Summary)
```
app/
├── data/
├── domain/
├── ui/
├── di/
```

### ▶️ How to Run
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app on an emulator or physical device 🚀

---

## 👤 Developer

Developed by **Armin Yousefi**

---

⭐ اگر این پروژه را دوست داشتید، خوشحال می‌شوم به آن ستاره بدهید!

