package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.data.model.Recipe
import com.example.data.preferences.UserPreferences
import com.example.data.repository.RecipeRepository
import com.example.ui.components.AboutDialog
import com.example.ui.components.AdBannerView
import com.example.ui.components.ServingsSelector
import com.example.ui.components.ShareSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import android.app.Activity
import com.example.ads.InterstitialAdManager

enum class AppTab {
    TODAYS_DISH,
    PAST_DAYS,
    FAVORITES
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyDishScreen(
    userPreferences: UserPreferences,
    interstitialAdManager: InterstitialAdManager? = null,
    activity: Activity? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language by userPreferences.language.collectAsState()
    val favoriteIds by userPreferences.favoriteIds.collectAsState()
    val checkedIngredients by userPreferences.checkedIngredients.collectAsState()
    val checkedSteps by userPreferences.checkedSteps.collectAsState()

    val isBn = language == Language.BANGLA
    val todayRecipe = remember { RecipeRepository.getRecipeForDate() }
    var currentRecipe by remember { mutableStateOf(todayRecipe) }
    var selectedServings by remember { mutableIntStateOf(4) }
    var currentTab by remember { mutableStateOf(AppTab.TODAYS_DISH) }
    var showShareSheet by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

    // Intercept back button if viewing a past/favorite recipe detail
    if (currentTab != AppTab.TODAYS_DISH || currentRecipe.id != todayRecipe.id) {
        BackHandler {
            if (currentRecipe.id != todayRecipe.id) {
                currentRecipe = todayRecipe
            } else {
                currentTab = AppTab.TODAYS_DISH
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Restaurant,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = "Daily Dish",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            val dateStr = remember(language) {
                                val sdf = SimpleDateFormat("EEEE, d MMMM", Locale.getDefault())
                                sdf.format(Date())
                            }
                            Text(
                                text = if (isBn) "আজকের স্পেশাল রেসিপি" else dateStr,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    // Surprise Random Dish button
                    IconButton(
                        onClick = {
                            val action = {
                                val surprise = RecipeRepository.getSurpriseRecipe(currentRecipe.id)
                                currentRecipe = surprise
                                Toast.makeText(
                                    context,
                                    if (isBn) "নতুন রেসিপি: ${surprise.titleBn}" else "Inspiration: ${surprise.titleEn}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            if (activity != null && interstitialAdManager != null) {
                                interstitialAdManager.showAdIfAvailable(activity, action)
                            } else {
                                action()
                            }
                        },
                        modifier = Modifier.testTag("surprise_recipe_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Casino,
                            contentDescription = if (isBn) "অন্য রেসিপি দেখুন" else "Surprise Recipe",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }

                    // Language toggle EN / বাংলা
                    Surface(
                        onClick = {
                            val newLang = if (language == Language.ENGLISH) Language.BANGLA else Language.ENGLISH
                            userPreferences.setLanguage(newLang)
                        },
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .testTag("language_toggle_button")
                    ) {
                        Text(
                            text = if (language == Language.ENGLISH) "বাংলা" else "EN",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // About dialog icon
                    IconButton(
                        onClick = { showAboutDialog = true },
                        modifier = Modifier.testTag("about_app_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "About App",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .navigationBarsPadding()
            ) {
                AdBannerView()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tab Selector Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = currentTab == AppTab.TODAYS_DISH,
                    onClick = {
                        currentTab = AppTab.TODAYS_DISH
                        currentRecipe = todayRecipe
                    },
                    label = { Text(if (isBn) "আজকের পদ" else "Today's Dish") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.testTag("tab_todays_dish")
                )

                FilterChip(
                    selected = currentTab == AppTab.PAST_DAYS,
                    onClick = { currentTab = AppTab.PAST_DAYS },
                    label = { Text(if (isBn) "আগের দিনের পদ" else "Recent Days") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.testTag("tab_past_days")
                )

                FilterChip(
                    selected = currentTab == AppTab.FAVORITES,
                    onClick = { currentTab = AppTab.FAVORITES },
                    label = {
                        Text(
                            text = if (isBn) "প্রিয় পদ (${favoriteIds.size})" else "Favorites (${favoriteIds.size})"
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.testTag("tab_favorites")
                )
            }

            // Main Content Area
            when (currentTab) {
                AppTab.TODAYS_DISH -> {
                    RecipeDetailView(
                        recipe = currentRecipe,
                        isToday = currentRecipe.id == todayRecipe.id,
                        servings = selectedServings,
                        language = language,
                        isFavorite = favoriteIds.contains(currentRecipe.id),
                        checkedIngredients = checkedIngredients,
                        checkedSteps = checkedSteps,
                        onServingsChanged = { selectedServings = it },
                        onToggleFavorite = { userPreferences.toggleFavorite(currentRecipe.id) },
                        onToggleIngredient = { key -> userPreferences.toggleIngredientChecked(key) },
                        onToggleStep = { key -> userPreferences.toggleStepChecked(key) },
                        onResetChecks = { userPreferences.clearCheckedForRecipe(currentRecipe.id) },
                        onShare = { showShareSheet = true }
                    )
                }

                AppTab.PAST_DAYS -> {
                    val recentRecipes = remember { RecipeRepository.getRecentRecipes(7) }
                    RecipeListView(
                        recipes = recentRecipes,
                        language = language,
                        emptyMessage = if (isBn) "কোনো অতীত রেসিপি পাওয়া যায়নি" else "No recent dishes yet",
                        onSelectRecipe = { selected ->
                            val action = {
                                currentRecipe = selected
                                currentTab = AppTab.TODAYS_DISH
                            }
                            if (activity != null && interstitialAdManager != null) {
                                interstitialAdManager.showAdIfAvailable(activity, action)
                            } else {
                                action()
                            }
                        }
                    )
                }

                AppTab.FAVORITES -> {
                    val allRecipes = remember { RecipeRepository.getAllRecipes() }
                    val favoriteRecipes = allRecipes.filter { favoriteIds.contains(it.id) }

                    if (favoriteRecipes.isEmpty()) {
                        EmptyFavoritesView(language = language)
                    } else {
                        RecipeListView(
                            recipes = favoriteRecipes,
                            language = language,
                            emptyMessage = if (isBn) "আপনার প্রিয় তালিকায় কোনো রেসিপি নেই" else "No favorite recipes yet",
                            onSelectRecipe = { selected ->
                                val action = {
                                    currentRecipe = selected
                                    currentTab = AppTab.TODAYS_DISH
                                }
                                if (activity != null && interstitialAdManager != null) {
                                    interstitialAdManager.showAdIfAvailable(activity, action)
                                } else {
                                    action()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    if (showShareSheet) {
        ShareSheetDialog(
            recipe = currentRecipe,
            servings = selectedServings,
            language = language,
            onDismiss = { showShareSheet = false }
        )
    }

    if (showAboutDialog) {
        AboutDialog(
            language = language,
            onDismiss = { showAboutDialog = false }
        )
    }
}

@Composable
fun RecipeDetailView(
    recipe: Recipe,
    isToday: Boolean,
    servings: Int,
    language: Language,
    isFavorite: Boolean,
    checkedIngredients: Set<String>,
    checkedSteps: Set<String>,
    onServingsChanged: (Int) -> Unit,
    onToggleFavorite: () -> Unit,
    onToggleIngredient: (String) -> Unit,
    onToggleStep: (String) -> Unit,
    onResetChecks: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isBn = language == Language.BANGLA

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Food Image Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.imageResId)
                                .crossfade(true)
                                .build(),
                            contentDescription = recipe.getTitle(language),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Subtle bottom gradient for image readability
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                                        startY = 200f
                                    )
                                )
                        )

                        // Category Badge
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f),
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = recipe.getCategory(language),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        // Favorite Bookmark Button
                        IconButton(
                            onClick = onToggleFavorite,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Black.copy(alpha = 0.5f)
                            ),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .testTag("favorite_toggle_button")
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                                tint = if (isFavorite) Color(0xFFFF5252) else Color.White
                            )
                        }

                        // Difficulty & Total Time Badge on Image Bottom
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Black.copy(alpha = 0.6f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "${recipe.totalTimeMinutes} ${if (isBn) "মিনিট" else "mins"}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Black.copy(alpha = 0.6f)
                            ) {
                                Text(
                                    text = recipe.difficulty.getDisplayName(language),
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    // Recipe Title and Narrative
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = recipe.getTitle(language),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = recipe.getDescription(language),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 22.sp
                        )

                        // Meta Row (Prep, Cook, Servings, Difficulty)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            MetaInfoChip(
                                label = if (isBn) "প্রস্তুতি" else "Prep",
                                value = "${recipe.prepTimeMinutes} ${if (isBn) "মি." else "m"}"
                            )
                            MetaInfoChip(
                                label = if (isBn) "রান্না" else "Cook",
                                value = "${recipe.cookTimeMinutes} ${if (isBn) "মি." else "m"}"
                            )
                            MetaInfoChip(
                                label = if (isBn) "মোট সময়" else "Total",
                                value = "${recipe.totalTimeMinutes} ${if (isBn) "মি." else "m"}"
                            )
                            MetaInfoChip(
                                label = if (isBn) "লেভেল" else "Level",
                                value = recipe.difficulty.getDisplayName(language)
                            )
                        }
                    }
                }
            }
        }

        // Action Buttons Row: [Share Recipe] & [Copy Recipe]
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Primary Share Recipe Button
                Button(
                    onClick = onShare,
                    modifier = Modifier
                        .weight(1.2f)
                        .testTag("share_recipe_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isBn) "রেসিপি শেয়ার" else "Share Recipe",
                        fontWeight = FontWeight.Bold
                    )
                }

                // Copy Recipe to Clipboard
                FilledTonalButton(
                    onClick = {
                        val text = RecipeRepository.formatRecipeForShare(recipe, language, servings)
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Recipe", text))
                        Toast.makeText(
                            context,
                            if (isBn) "রেসিপি ক্লিপবোর্ডে কপি করা হয়েছে!" else "Recipe copied to clipboard!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("copy_recipe_button"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isBn) "কপি করুন" else "Copy",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Interactive Servings Controller
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                ServingsSelector(
                    selectedServings = servings,
                    language = language,
                    onServingsSelected = onServingsChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp)
                )
            }
        }

        // Ingredients Checklist Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isBn) "প্রয়োজনীয় উপকরণ (${recipe.ingredients.size})" else "Ingredients (${recipe.ingredients.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        TextButton(
                            onClick = onResetChecks,
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isBn) "রিসেট" else "Reset",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))

                    recipe.ingredients.forEachIndexed { index, ingredient ->
                        val checkKey = "${recipe.id}_ing_$index"
                        val isChecked = checkedIngredients.contains(checkKey)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onToggleIngredient(checkKey) }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { onToggleIngredient(checkKey) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = ingredient.getDisplayText(language, servings, recipe.baseServings),
                                style = MaterialTheme.typography.bodyMedium,
                                textDecoration = if (isChecked) TextDecoration.LineThrough else null,
                                color = if (isChecked) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Cooking Instructions Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    val completedStepsCount = recipe.steps.count { step ->
                        checkedSteps.contains("${recipe.id}_step_${step.stepNumber}")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isBn) "রান্নার প্রণালী (${recipe.steps.size} ধাপ)" else "Step-by-Step Instructions",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "$completedStepsCount / ${recipe.steps.size} ${if (isBn) "সম্পন্ন" else "done"}",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))

                    recipe.steps.forEach { step ->
                        val stepKey = "${recipe.id}_step_${step.stepNumber}"
                        val isDone = checkedSteps.contains(stepKey)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isDone) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                                    else Color.Transparent
                                )
                                .clickable { onToggleStep(stepKey) }
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = if (isDone) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    if (isDone) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Text(
                                            text = step.stepNumber.toString(),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = step.getInstruction(language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    textDecoration = if (isDone) TextDecoration.LineThrough else null,
                                    color = if (isDone) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 22.sp
                                )

                                step.getTip(language)?.let { tip ->
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = "💡 $tip",
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Chef's Pro Tip Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = if (isBn) "শেফের গোপন টিপস" else "Chef's Secret Tip",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = recipe.getChefTip(language),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MetaInfoChip(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun RecipeListView(
    recipes: List<Recipe>,
    language: Language,
    emptyMessage: String,
    onSelectRecipe: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    val isBn = language == Language.BANGLA

    if (recipes.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emptyMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(recipes) { itemRecipe ->
            Card(
                onClick = { onSelectRecipe(itemRecipe) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(itemRecipe.imageResId)
                            .crossfade(true)
                            .build(),
                        contentDescription = itemRecipe.getTitle(language),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = itemRecipe.getCategory(language),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = itemRecipe.getTitle(language),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = itemRecipe.getDescription(language),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 16.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⏱️ ${itemRecipe.totalTimeMinutes} ${if (isBn) "মিনিট" else "mins"}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = "•",
                                color = MaterialTheme.colorScheme.outline
                            )
                            Text(
                                text = itemRecipe.difficulty.getDisplayName(language),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesView(
    language: Language,
    modifier: Modifier = Modifier
) {
    val isBn = language == Language.BANGLA
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(72.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (isBn) "এখনও কোনো প্রিয় রেসিপি নেই" else "No Favorite Recipes Yet",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (isBn)
                "যেকোনো রেসিপির ওপরের হার্ট আইকনে ট্যাপ করে তা আপনার প্রিয় তালিকায় সংরক্ষণ করুন।"
            else
                "Tap the heart icon on any recipe to save it here for quick access anytime.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}
