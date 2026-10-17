# Preserve line numbers and source files for crash reports on Google Play Console
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Preserve data models for serialization and state handling
-keep class com.example.data.model.** { *; }

# Google Mobile Ads (AdMob)
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep public class com.google.ads.** {
   public *;
}

# Moshi JSON adapters
-keepattributes *Annotation*
-keepclassmembers class * {
    @com.squareup.moshi.* <fields>;
    @com.squareup.moshi.* <methods>;
}
