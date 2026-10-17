package com.example

import android.content.ComponentCallbacks2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
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

        // Configure memory-efficient Coil ImageLoader with hardware bitmaps and low memory bounds
        val imageLoader = ImageLoader.Builder(applicationContext)
            .memoryCache {
                MemoryCache.Builder(applicationContext)
                    .maxSizePercent(0.15)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(20L * 1024 * 1024)
                    .build()
            }
            .allowHardware(true)
            .build()
        Coil.setImageLoader(imageLoader)

        userPreferences = UserPreferences(applicationContext)

        // Initialize Google Mobile Ads SDK
        if (AdConfig.ADS_ENABLED) {
            try {
                val requestConfiguration = com.google.android.gms.ads.RequestConfiguration.Builder()
                    .setTestDeviceIds(listOf(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR))
                    .build()
                MobileAds.setRequestConfiguration(requestConfiguration)
                MobileAds.initialize(this) {}
            } catch (e: Exception) {
                android.util.Log.w("MainActivity", "AdMob initialization caught: ${e.message}")
            }
        }

        interstitialAdManager = InterstitialAdManager(applicationContext)

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

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // Release image memory caches when app is hidden or device has memory pressure
        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Coil.imageLoader(this).memoryCache?.clear()
        }
    }
}
