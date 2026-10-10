package com.example

import com.example.data.model.Difficulty
import com.example.data.model.Ingredient
import com.example.data.model.Language
import com.example.data.repository.RecipeRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class DailyDishUnitTest {

    @Test
    fun `recipe repository contains recipes`() {
        val all = RecipeRepository.getAllRecipes()
        assertTrue("Repository should have recipes", all.isNotEmpty())
        assertTrue("Repository should have at least 5 recipes", all.size >= 5)
    }

    @Test
    fun `recipe for date is deterministic for same date`() {
        val cal1 = Calendar.getInstance().apply {
            set(2026, Calendar.OCTOBER, 10, 10, 0, 0)
        }
        val cal2 = Calendar.getInstance().apply {
            set(2026, Calendar.OCTOBER, 10, 18, 30, 0)
        }

        val recipe1 = RecipeRepository.getRecipeForDate(cal1)
        val recipe2 = RecipeRepository.getRecipeForDate(cal2)

        assertEquals("Same day must yield identical recipe", recipe1.id, recipe2.id)
    }

    @Test
    fun `recipe for consecutive dates changes`() {
        val day1 = Calendar.getInstance().apply {
            set(2026, Calendar.OCTOBER, 10)
        }
        val day2 = Calendar.getInstance().apply {
            set(2026, Calendar.OCTOBER, 11)
        }

        val recipeDay1 = RecipeRepository.getRecipeForDate(day1)
        val recipeDay2 = RecipeRepository.getRecipeForDate(day2)

        assertFalse("Different days should cycle through dishes", recipeDay1.id == recipeDay2.id)
    }

    @Test
    fun `ingredient scaling works accurately for different servings`() {
        val ingredient = Ingredient(
            baseQuantity = 400.0,
            unitEn = "g",
            unitBn = "গ্রাম",
            nameEn = "Basmati Rice",
            nameBn = "বাসমতী চাল"
        )

        // Base servings is 4
        assertEquals("400", ingredient.getFormattedQuantity(servings = 4, baseServings = 4))
        assertEquals("200", ingredient.getFormattedQuantity(servings = 2, baseServings = 4))
        assertEquals("800", ingredient.getFormattedQuantity(servings = 8, baseServings = 4))

        val displayEn = ingredient.getDisplayText(Language.ENGLISH, servings = 2, baseServings = 4)
        assertEquals("200 g Basmati Rice", displayEn)

        val displayBn = ingredient.getDisplayText(Language.BANGLA, servings = 2, baseServings = 4)
        assertEquals("২০০ গ্রাম বাসমতী চাল", displayBn)
    }

    @Test
    fun `format recipe for share generates rich content with branding`() {
        val recipe = RecipeRepository.getAllRecipes().first()
        val formattedEn = RecipeRepository.formatRecipeForShare(recipe, Language.ENGLISH, servings = 4)
        val formattedBn = RecipeRepository.formatRecipeForShare(recipe, Language.BANGLA, servings = 4)

        assertTrue(formattedEn.contains(recipe.titleEn))
        assertTrue(formattedEn.contains("INGREDIENTS"))
        assertTrue(formattedEn.contains("STEP-BY-STEP INSTRUCTIONS"))
        assertTrue(formattedEn.contains("ViveScript Solutions LLC"))

        assertTrue(formattedBn.contains(recipe.titleBn))
        assertTrue(formattedBn.contains("প্রয়োজনীয় উপকরণ"))
        assertTrue(formattedBn.contains("রান্নার প্রণালী"))
        assertTrue(formattedBn.contains("Daily Dish"))
    }
}
