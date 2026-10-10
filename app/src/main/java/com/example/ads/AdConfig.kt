package com.example.ads

/**
 * Centralized AdMob / Advertising Configuration
 * Google Mobile Ads SDK Configuration
 */
object AdConfig {
    /**
     * Master toggle for advertisements. Set to true to enable, false to disable.
     */
    const val ADS_ENABLED = true

    /**
     * Toggle between Production IDs and Google Official Test IDs.
     * Set USE_TEST_ADS = false for production release.
     */
    const val USE_TEST_ADS = false

    // Official Google Test IDs
    const val TEST_APP_ID = "ca-app-pub-3940256099942544~3347511713"
    const val TEST_BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"
    const val TEST_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

    // Production Daily Dish AdMob Credentials
    const val PRODUCTION_APP_ID = "ca-app-pub-5222053984568989~4936428567"
    const val PRODUCTION_BANNER_AD_UNIT_ID = "ca-app-pub-5222053984568989/4736198143"
    const val PRODUCTION_INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-5222053984568989/9796953135"

    /**
     * Active Application ID
     */
    val appId: String
        get() = if (USE_TEST_ADS) TEST_APP_ID else PRODUCTION_APP_ID

    /**
     * Active Banner Ad Unit ID
     */
    val bannerAdUnitId: String
        get() = if (USE_TEST_ADS) TEST_BANNER_AD_UNIT_ID else PRODUCTION_BANNER_AD_UNIT_ID

    /**
     * Active Interstitial Ad Unit ID
     */
    val interstitialAdUnitId: String
        get() = if (USE_TEST_ADS) TEST_INTERSTITIAL_AD_UNIT_ID else PRODUCTION_INTERSTITIAL_AD_UNIT_ID

    /**
     * Child-directed treatment flag (COPPA compliance).
     * Daily Dish is targeted at general audiences (home cooks, adults, families).
     */
    const val TAG_FOR_CHILD_DIRECTED_TREATMENT = false

    /**
     * Minimum time interval between interstitial ad impressions (in milliseconds)
     * AdMob policy requirement: do not overwhelm users with frequent interstitial ads.
     */
    const val INTERSTITIAL_COOLDOWN_MS = 60_000L // 60 seconds minimum cooldown
}
