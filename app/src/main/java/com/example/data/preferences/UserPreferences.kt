package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("daily_dish_prefs", Context.MODE_PRIVATE)

    private val _language = MutableStateFlow(loadLanguage())
    val language: StateFlow<Language> = _language.asStateFlow()

    private val _favoriteIds = MutableStateFlow(loadFavorites())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds.asStateFlow()

    private val _checkedIngredients = MutableStateFlow(loadCheckedIngredients())
    val checkedIngredients: StateFlow<Set<String>> = _checkedIngredients.asStateFlow()

    private val _checkedSteps = MutableStateFlow(loadCheckedSteps())
    val checkedSteps: StateFlow<Set<String>> = _checkedSteps.asStateFlow()

    private fun loadLanguage(): Language {
        val code = prefs.getString(KEY_LANGUAGE, Language.ENGLISH.name) ?: Language.ENGLISH.name
        return runCatching { Language.valueOf(code) }.getOrDefault(Language.ENGLISH)
    }

    fun setLanguage(newLanguage: Language) {
        prefs.edit().putString(KEY_LANGUAGE, newLanguage.name).apply()
        _language.value = newLanguage
    }

    private fun loadFavorites(): Set<String> {
        return prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    }

    fun toggleFavorite(recipeId: String) {
        val current = _favoriteIds.value.toMutableSet()
        if (current.contains(recipeId)) {
            current.remove(recipeId)
        } else {
            current.add(recipeId)
        }
        prefs.edit().putStringSet(KEY_FAVORITES, current).apply()
        _favoriteIds.value = current
    }

    fun isFavorite(recipeId: String): Boolean {
        return _favoriteIds.value.contains(recipeId)
    }

    private fun loadCheckedIngredients(): Set<String> {
        return prefs.getStringSet(KEY_CHECKED_INGREDIENTS, emptySet()) ?: emptySet()
    }

    fun toggleIngredientChecked(key: String) {
        val current = _checkedIngredients.value.toMutableSet()
        if (current.contains(key)) {
            current.remove(key)
        } else {
            current.add(key)
        }
        prefs.edit().putStringSet(KEY_CHECKED_INGREDIENTS, current).apply()
        _checkedIngredients.value = current
    }

    fun clearCheckedForRecipe(recipeId: String) {
        val currentIng = _checkedIngredients.value.filterNot { it.startsWith("${recipeId}_") }.toSet()
        val currentSteps = _checkedSteps.value.filterNot { it.startsWith("${recipeId}_") }.toSet()
        prefs.edit()
            .putStringSet(KEY_CHECKED_INGREDIENTS, currentIng)
            .putStringSet(KEY_CHECKED_STEPS, currentSteps)
            .apply()
        _checkedIngredients.value = currentIng
        _checkedSteps.value = currentSteps
    }

    private fun loadCheckedSteps(): Set<String> {
        return prefs.getStringSet(KEY_CHECKED_STEPS, emptySet()) ?: emptySet()
    }

    fun toggleStepChecked(key: String) {
        val current = _checkedSteps.value.toMutableSet()
        if (current.contains(key)) {
            current.remove(key)
        } else {
            current.add(key)
        }
        prefs.edit().putStringSet(KEY_CHECKED_STEPS, current).apply()
        _checkedSteps.value = current
    }

    companion object {
        private const val KEY_LANGUAGE = "pref_language"
        private const val KEY_FAVORITES = "pref_favorites"
        private const val KEY_CHECKED_INGREDIENTS = "pref_checked_ingredients"
        private const val KEY_CHECKED_STEPS = "pref_checked_steps"
    }
}
