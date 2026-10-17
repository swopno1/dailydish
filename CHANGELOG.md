# Changelog

All notable changes to **Daily Dish** will be documented in this file.

## [1.0.1] - 2026-10-16

### Improvements & Google Play Updates
- **Version Code:** Incremented `versionCode` to `2` (version `1.0.1`) for Google Play update.
- **SDK Modernization:** Upgraded `androidx.fragment:fragment` to `1.8.6` resolving outdated library notices.
- **Memory Optimization:** Optimized and migrated all dish image assets into `drawable-nodpi/` to prevent device density upscaling and comply with Android Vitals 2027 memory standards.
- **Efficient Image Loading:** Integrated Coil `AsyncImage` with hardware bitmap acceleration, downsampling, and background memory trimming (`onTrimMemory`).
- **Release Diagnostics:** Enabled R8 deobfuscation mapping packaging and full native debug symbols (`debugSymbolLevel = "FULL"`).
- **AdServices Configuration:** Customized `gma_ad_services_config.xml` override to eliminate emulator service binding warnings.

## [1.0.0] - 2026-10-10

### Initial Release
- **Recipe of the Day Engine:** Deterministic daily algorithm cycling through curated culinary dishes each day.
- **Bilingual Support:** Full English and Bangla (বাংলা) localization for all recipes, ingredients, instructions, and tips.
- **Interactive Kitchen Checklist:** Interactive checkboxes for ingredients and step-by-step progress tracking.
- **Servings Scaler:** Dynamic ingredient scaling for 2, 4, 6, and 8 servings.
- **1-Tap Recipe Sharing:** Direct sharing to WhatsApp, social media, and clipboard copy.
- **Favorites & Past Recipes:** Local bookmarking and 7-day past recipes archive.
- **Random Recipe Discovery:** "Surprise Me" instant recipe discovery feature.
- **Monetization Architecture:** Clean `AdConfig` integration supporting Google Mobile Ads SDK (v23.6.0).
- **Google Play Store Package:** Complete metadata, store assets, screenshots, privacy policy, and compliance documentation.
