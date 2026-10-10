# Google Play Data Safety Guidance — Daily Dish

**App Name:** Daily Dish  
**Package:** `com.vivescriptsolutions.dailydish`  
**Publisher:** ViveScript Solutions LLC  
**AdMob SDK Version:** `com.google.android.gms:play-services-ads:23.6.0`  

---

## 1. Data Safety Summary

Daily Dish core features are entirely local and offline. The application does not require a user account, does not collect personal names, email addresses, precise location, financial details, or contacts.

Network connectivity is utilized only for optional Google Mobile Ads SDK (AdMob) display when the device is online.

---

## 2. Google Play Console Form Declarations

### Section 1: Data Collection & Sharing Overview
* **Does your app collect or share any of the required user data types?**  
  👉 **Yes** (solely through third-party Google Mobile Ads SDK for advertising purposes).
* **Is all of the user data collected by your app encrypted in transit?**  
  👉 **Yes** (Google Mobile Ads SDK uses HTTPS/TLS).
* **Do you provide a way for users to request that their data be deleted?**  
  👉 **No / Not applicable** (no user accounts or personal profiles are stored on servers; all local data is cleared upon app uninstall).

---

### Section 2: Data Types Breakdown

#### Category: Device or other IDs
* **Data collected:** Device or other IDs (e.g. Google Advertising ID / GAID).
* **Is this data shared?** Yes, shared with Google AdMob.
* **Is this data processed ephemerally?** Yes.
* **Is this collection required or optional?** Required for ad network operation when connected.
* **Purposes:**
  * Advertising or marketing
  * Analytics / Fraud prevention

#### Categories NOT Collected:
* ❌ Location (Neither Precise nor Coarse)
* ❌ Personal info (No name, email, phone number, address)
* ❌ Financial info
* ❌ Health & fitness
* ❌ Messages / Emails
* ❌ Photos and videos
* ❌ Audio files
* ❌ Contacts
* ❌ Calendar

---

## 3. Advertising & Child-Directed Review

* **Ad Configuration:** `AdConfig.TAG_FOR_CHILD_DIRECTED_TREATMENT = false` (App targets general audiences and adults interested in home cooking).
* **Ads Declaration in Console:** In the App Content > Ads questionnaire, select **"Yes, my app contains ads"**.
* **Integrated Ad Units:**
  * **App ID:** `ca-app-pub-5222053984568989~4936428567`
  * **Banner 1:** `ca-app-pub-5222053984568989/4736198143`
  * **Interstitial 1:** `ca-app-pub-5222053984568989/9796953135`

