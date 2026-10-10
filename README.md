# Daily Dish 🍳

> **Fresh daily meal inspiration without browsing complicated recipe websites.**

An offline-first, beautifully crafted Android cooking app that delivers a featured **Recipe of the Day** with rich food photography, precise ingredients, preparation & cooking times, interactive checklists, dynamic servings scaling, and 1-tap sharing to WhatsApp and social media.

Published by **ViveScript Solutions LLC**.

---

## Overview

* **App Name:** Daily Dish
* **Package ID:** `com.vivescriptsolutions.dailydish`
* **Version:** 1.0.0
* **Target Audience:** Home cooks, food lovers, and families seeking quick, delightful cooking ideas.
* **Languages:** English & Bangla (বাংলা)

---

## Key Features

1. **Daily Recipe Discovery:** A deterministic date-based algorithm delivers a unique featured recipe every day, working completely offline.
2. **Bilingual Localization:** Seamless 1-tap language toggle between English and Bengali (বাংলা) for all titles, stories, ingredients, and instructions.
3. **Interactive Ingredients Checklist:** Check off pantry staples as you prepare ingredients on your kitchen counter.
4. **Dynamic Servings Scaler:** Adjust between 2, 4, 6, and 8 servings—quantities update automatically in real-time.
5. **Step-by-Step Cooking Steps:** Numbered instructions with interactive completion tracking so you never lose your place.
6. **Chef's Pro Tips:** Valuable kitchen secrets included with every recipe to ensure foolproof cooking results.
7. **1-Tap Social Sharing:** Pre-formatted rich recipe cards ready to share instantly to WhatsApp, Messenger, Facebook, or your device clipboard.
8. **Favorites & Past Recipes:** Bookmark your most-loved dishes and revisit meals from previous days.
9. **Surprise Inspiration:** Tap the surprise die to discover an immediate recipe idea anytime.

---

## Technology Stack

* **Language:** 100% Kotlin
* **UI Toolkit:** Jetpack Compose (Material Design 3)
* **Architecture:** MVVM + Clean State Management
* **Image Loading:** Coil Compose
* **Local Persistence:** Android Preferences (zero database bloat, lightweight & offline)
* **Monetization Architecture:** Google Mobile Ads SDK (`play-services-ads:23.6.0`) via centralized `AdConfig`
* **Testing:** JUnit 4 & Robolectric

---

## Technical Specifications

| Parameter | Value |
| :--- | :--- |
| **Minimum SDK** | API 24 (Android 7.0 Nougat) |
| **Target SDK** | API 36 (Android 15+) |
| **Compile SDK** | API 36 |
| **JDK Version** | Java 11 / Java 21 |
| **Build System** | Gradle (Kotlin DSL) |

---

## Project Structure

```text
Daily Dish
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── ads/
│   │   │   │   │   └── AdConfig.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/Recipe.kt
│   │   │   │   │   ├── preferences/UserPreferences.kt
│   │   │   │   │   └── repository/RecipeRepository.kt
│   │   │   │   └── ui/
│   │   │   │       ├── components/
│   │   │   │       │   ├── AboutDialog.kt
│   │   │   │       │   ├── AdBannerView.kt
│   │   │   │       │   ├── ServingsSelector.kt
│   │   │   │       │   └── ShareSheetDialog.kt
│   │   │   │       ├── screens/DailyDishScreen.kt
│   │   │   │       └── theme/
│   │   └── test/java/com/example/
│   │       ├── DailyDishUnitTest.kt
│   │       └── ExampleRobolectricTest.kt
├── play_store_assets/
│   ├── app-icon/
│   ├── feature-graphic/
│   ├── phone/
│   └── tablet/
├── production_build/
│   └── dailydish-release.aab
├── LICENSE
├── PRIVACY_POLICY.md
├── TERMS_OF_SERVICE.md
├── PLAY_STORE_METADATA.md
├── PLAY_STORE_DATA_SAFETY.md
└── DESIGN_ASSETS.md
```

---

## Building and Running

### 1. Prerequisites
* Android Studio (Ladybug or newer)
* Android SDK 36 installed

### 2. Run Locally
```bash
gradle :app:installDebug
```

### 3. Run Unit and Robolectric Tests
```bash
gradle :app:testDebugUnitTest
```

### 4. Build Production Bundle
```bash
gradle :app:bundleRelease
```
The validated release artifact is placed in `/production_build/dailydish-release.aab`.

---

## Advertising Configuration

Advertising is centralized in `com.example.ads.AdConfig`:
* **App ID:** `ca-app-pub-5222053984568989~4936428567`
* **Banner 1 Ad Unit ID:** `ca-app-pub-5222053984568989/4736198143` (Non-intrusive bottom banner)
* **Interstitial 1 Ad Unit ID:** `ca-app-pub-5222053984568989/9796953135` (Natural recipe transition & inspiration break)
* **Toggle:** Set `AdConfig.ADS_ENABLED = false` to disable ads entirely.
* **Test Mode Toggle:** Set `AdConfig.USE_TEST_ADS = true` to test with Google's official test ad units during development.

---

## Privacy

Daily Dish operates completely offline. No accounts, sign-ins, personal information, locations, contacts, or photos are ever accessed or collected. All recipe data is bundled locally within the app.

---

## Licensing & Proprietary Rights

### MIT License
The application's source code is licensed under the **MIT License**. See the `LICENSE` file for details.

### Proprietary Branding Notice
© 2026 ViveScript Solutions LLC. The app name ("Daily Dish"), logo, icons, screenshots, trademarks, marketing materials, and associated branding are proprietary property of **ViveScript Solutions LLC** and are not licensed under the MIT License.
