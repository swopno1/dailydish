package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ads.AdConfig
import com.example.ads.InterstitialAdManager
import com.example.data.preferences.UserPreferences
import com.example.ui.screens.DailyDishScreen
import com.example.ui.theme.MyApplicationTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {

    private lateinit var userPreferences: UserPreferences
    private lateinit var interstitialAdManager: InterstitialAdManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userPreferences = UserPreferences(applicationContext)

        // Initialize Google Mobile Ads SDK
        if (AdConfig.ADS_ENABLED) {
            try {
                MobileAds.initialize(this) {}
            } catch (e: Exception) {
                android.util.Log.w("MainActivity", "AdMob initialization caught: ${e.message}")
            }
        }

        interstitialAdManager = InterstitialAdManager(this)

        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DailyDishScreen(
                        userPreferences = userPreferences,
                        interstitialAdManager = interstitialAdManager,
                        activity = this
                    )
                }
            }
        }
    }
}
