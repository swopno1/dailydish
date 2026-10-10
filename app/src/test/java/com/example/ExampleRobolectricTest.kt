package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.Language
import com.example.data.preferences.UserPreferences
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    private lateinit var context: Context
    private lateinit var userPreferences: UserPreferences

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        userPreferences = UserPreferences(context)
    }

    @Test
    fun `read string from context`() {
        val appName = context.getString(R.string.app_name)
        assertEquals("Daily Dish", appName)
    }

    @Test
    fun `toggle favorite persists in preferences`() {
        val recipeId = "royal_chicken_biryani"
        assertFalse(userPreferences.isFavorite(recipeId))

        userPreferences.toggleFavorite(recipeId)
        assertTrue(userPreferences.isFavorite(recipeId))

        userPreferences.toggleFavorite(recipeId)
        assertFalse(userPreferences.isFavorite(recipeId))
    }

    @Test
    fun `language preference updates correctly`() {
        userPreferences.setLanguage(Language.BANGLA)
        assertEquals(Language.BANGLA, userPreferences.language.value)

        userPreferences.setLanguage(Language.ENGLISH)
        assertEquals(Language.ENGLISH, userPreferences.language.value)
    }
}
