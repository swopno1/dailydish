package com.example.data.model

import androidx.annotation.DrawableRes

enum class Language {
    ENGLISH,
    BANGLA
}

enum class Difficulty {
    EASY,
    MEDIUM,
    GOURMET;

    fun getDisplayName(language: Language): String {
        return when (this) {
            EASY -> if (language == Language.BANGLA) "সহজ" else "Easy"
            MEDIUM -> if (language == Language.BANGLA) "মাঝারি" else "Medium"
            GOURMET -> if (language == Language.BANGLA) "গুরমে / স্পেশাল" else "Gourmet"
        }
    }
}

data class Ingredient(
    val baseQuantity: Double,
    val unitEn: String,
    val unitBn: String,
    val nameEn: String,
    val nameBn: String
) {
    fun getFormattedQuantity(servings: Int, baseServings: Int): String {
        val scaled = (baseQuantity * servings) / baseServings
        return if (scaled % 1.0 == 0.0) {
            scaled.toInt().toString()
        } else if ((scaled * 2) % 1.0 == 0.0) {
            val whole = scaled.toInt()
            if (whole == 0) "1/2" else "$whole 1/2"
        } else {
            String.format("%.1f", scaled)
        }
    }

    fun getDisplayText(language: Language, servings: Int, baseServings: Int): String {
        val qty = getFormattedQuantity(servings, baseServings)
        return if (language == Language.BANGLA) {
            val bnQty = convertToBanglaDigits(qty)
            "$bnQty $unitBn $nameBn".trim()
        } else {
            "$qty $unitEn $nameEn".trim()
        }
    }

    private fun convertToBanglaDigits(input: String): String {
        val digitsMap = mapOf(
            '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
            '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯',
            '.' to '.'
        )
        return input.map { digitsMap[it] ?: it }.joinToString("")
    }
}

data class CookingStep(
    val stepNumber: Int,
    val instructionEn: String,
    val instructionBn: String,
    val tipEn: String? = null,
    val tipBn: String? = null
) {
    fun getInstruction(language: Language): String =
        if (language == Language.BANGLA) instructionBn else instructionEn

    fun getTip(language: Language): String? =
        if (language == Language.BANGLA) tipBn else tipEn
}

data class Recipe(
    val id: String,
    val titleEn: String,
    val titleBn: String,
    val descriptionEn: String,
    val descriptionBn: String,
    val categoryEn: String,
    val categoryBn: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val baseServings: Int = 4,
    val difficulty: Difficulty,
    @get:DrawableRes val imageResId: Int,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>,
    val chefTipEn: String,
    val chefTipBn: String
) {
    val totalTimeMinutes: Int
        get() = prepTimeMinutes + cookTimeMinutes

    fun getTitle(language: Language): String =
        if (language == Language.BANGLA) titleBn else titleEn

    fun getDescription(language: Language): String =
        if (language == Language.BANGLA) descriptionBn else descriptionEn

    fun getCategory(language: Language): String =
        if (language == Language.BANGLA) categoryBn else categoryEn

    fun getChefTip(language: Language): String =
        if (language == Language.BANGLA) chefTipBn else chefTipEn
}
