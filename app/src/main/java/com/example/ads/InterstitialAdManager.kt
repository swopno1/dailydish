package com.example.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdManager(private val context: Context) {

    private var interstitialAd: InterstitialAd? = null
    private var isLoading = false
    private var lastAdShowTime: Long = 0L

    init {
        loadAd()
    }

    /**
     * Pre-loads an interstitial ad for smooth instant presentation when needed.
     */
    fun loadAd() {
        if (!AdConfig.ADS_ENABLED || isLoading || interstitialAd != null) return

        isLoading = true
        try {
            val adRequest = AdRequest.Builder().build()

            InterstitialAd.load(
                context,
                AdConfig.interstitialAdUnitId,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(ad: InterstitialAd) {
                        interstitialAd = ad
                        isLoading = false
                        Log.d(TAG, "Interstitial ad successfully loaded.")
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        interstitialAd = null
                        isLoading = false
                        Log.d(TAG, "Interstitial ad failed to load: ${error.message} (Code: ${error.code})")
                    }
                }
            )
        } catch (e: Exception) {
            isLoading = false
            Log.w(TAG, "Failed to load interstitial ad: ${e.message}")
        }
    }

    /**
     * Shows the interstitial ad if available and respect AdMob policy cooldown.
     * Always calls [onAdDismissed] so user experience is never blocked.
     */
    fun showAdIfAvailable(activity: Activity, onAdDismissed: () -> Unit) {
        val now = System.currentTimeMillis()
        val timeSinceLastAd = now - lastAdShowTime

        // If ads are disabled or ad is not loaded or cooldown has not elapsed, proceed directly
        if (!AdConfig.ADS_ENABLED || interstitialAd == null || timeSinceLastAd < AdConfig.INTERSTITIAL_COOLDOWN_MS) {
            onAdDismissed()
            if (interstitialAd == null) {
                loadAd()
            }
            return
        }

        val ad = interstitialAd
        ad?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Interstitial ad dismissed.")
                interstitialAd = null
                lastAdShowTime = System.currentTimeMillis()
                loadAd()
                onAdDismissed()
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                Log.w(TAG, "Interstitial ad failed to show: ${error.message}")
                interstitialAd = null
                loadAd()
                onAdDismissed()
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Interstitial ad showed fullscreen content.")
                lastAdShowTime = System.currentTimeMillis()
            }
        }

        ad?.show(activity)
    }

    companion object {
        private const val TAG = "InterstitialAdManager"
    }
}
