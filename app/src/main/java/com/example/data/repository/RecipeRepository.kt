package com.example.data.repository

import com.example.R
import com.example.data.model.CookingStep
import com.example.data.model.Difficulty
import com.example.data.model.Ingredient
import com.example.data.model.Language
import com.example.data.model.Recipe
import java.util.Calendar

object RecipeRepository {

    private val recipes: List<Recipe> = listOf(
        Recipe(
            id = "royal_chicken_biryani",
            titleEn = "Royal Fragrant Chicken Biryani",
            titleBn = "মুঘলাই জাফরানি চিকেন বিরিয়ানি",
            descriptionEn = "A legendary royal dish featuring aromatic long-grain basmati rice, tender chicken marinated in warm yogurt spices, caramelized onions, and saffron milk.",
            descriptionBn = "দীর্ঘ সুগন্ধি বাসমতী চাল, টকদই ও বিশেষ মসলায় ম্যারিনেট করা নরম মুরগির মাংস, বেরেস্তা এবং জাফরানি দুধের অপূর্ব রাজকীয় মিশ্রণ।",
            categoryEn = "Royal Mughlai & Festive",
            categoryBn = "রাজকীয় ও উৎসবের পদ",
            prepTimeMinutes = 30,
            cookTimeMinutes = 45,
            baseServings = 4,
            difficulty = Difficulty.GOURMET,
            imageResId = R.drawable.dish_biryani,
            ingredients = listOf(
                Ingredient(500.0, "g", "গ্রাম", "Chicken pieces (bone-in)", "মুরগির মাংস টুকরো"),
                Ingredient(400.0, "g", "গ্রাম", "Aged Basmati Rice", "বাসমতী চাল"),
                Ingredient(150.0, "g", "গ্রাম", "Thick Plain Yogurt", "টকদই"),
                Ingredient(2.0, "large", "টি বড়", "Onions, finely sliced for Beresta", "পেঁয়াজ স্লাইস (বেরেস্তা)"),
                Ingredient(2.0, "tbsp", "টেবিল চামচ", "Ginger-Garlic Paste", "আদা-রসুন বাটা"),
                Ingredient(1.0, "tbsp", "টেবিল চামচ", "Biryani Garam Masala", "বিরিয়ানি গরম মসলা"),
                Ingredient(0.5, "tsp", "চা চামচ", "Saffron strands soaked in warm milk", "জাফরান ভেজানো দুধ"),
                Ingredient(3.0, "tbsp", "টেবিল চামচ", "Pure Ghee", "খাঁটি ঘি"),
                Ingredient(1.0, "handful", "মুঠো", "Fresh Mint & Coriander leaves", "পুদিনা ও ধনেপাতা কুচি"),
                Ingredient(1.5, "tsp", "চা চামচ", "Salt (to taste)", "লবণ (স্বাদমতো)")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Marinate the chicken with yogurt, ginger-garlic paste, half the fried onions, garam masala, and salt for at least 30 minutes.",
                    "টকদই, আদা-রসুন বাটা, বেরেস্তার অর্ধেক, গরম মসলা এবং লবণ দিয়ে মুরগির মাংস অন্তত ৩০ মিনিট ম্যারিনেট করে রাখুন।",
                    "Overnight marination creates the most succulent texture.",
                    "আগের রাতে ম্যারিনেট করে রাখলে মাংস আরও নরম ও রসালো হয়।"
                ),
                CookingStep(
                    2,
                    "Wash basmati rice and parboil in rolling salted water with whole spices until 75% done. Drain thoroughly.",
                    "চাল ধুয়ে গোটা গরম মসলা ও লবণযুক্ত ফুটন্ত পানিতে ৭৫% সিদ্ধ করে পানি ভালো করে ঝরিয়ে নিন।",
                    "Do not overcook the rice in this stage; it finishes cooking on dum.",
                    "চাল বেশি সেদ্ধ করবেন না, দমে বাকিটা হবে।"
                ),
                CookingStep(
                    3,
                    "In a heavy-bottomed pot, sear the marinated chicken in ghee until lightly browned. Spread evenly at base.",
                    "ভারী তলযুক্ত পাত্রে ঘিয়ে ম্যারিনেট করা মুরগি কষিয়ে তলদেশে সমানভাবে ছড়িয়ে দিন।"
                ),
                CookingStep(
                    4,
                    "Layer parboiled rice on top. Sprinkle remaining fried onions, chopped mint, coriander, saffron milk, and dollops of ghee.",
                    "উপরে ভাতের স্তর দিন। বাকি বেরেস্তা, পুদিনা, ধনেপাতা, জাফরানি দুধ এবং ঘি ছড়িয়ে দিন।"
                ),
                CookingStep(
                    5,
                    "Seal the pot with tight foil or dough. Cook on low heat (Dum) for 25 minutes. Rest 10 minutes before opening.",
                    "পাত্রটি ফয়েল বা ঢাকনা দিয়ে শক্ত করে আটকে কম আঁচে ২৫ মিনিট 'দম' দিন। খোলার আগে ১০ মিনিট দমে রাখুন।"
                )
            ),
            chefTipEn = "Always gently fluff the rice with a flat spatula from the rim to avoid breaking long rice grains.",
            chefTipBn = "চাল যেন ভেঙে না যায় সেজন্য ফ্ল্যাট চামচ দিয়ে পাত্রের একপাশ থেকে আলতো করে পরিবেশন করুন।"
        ),
        Recipe(
            id = "creamy_tuscan_garlic_pasta",
            titleEn = "Creamy Tuscan Garlic Chicken Pasta",
            titleBn = "ক্রিমি তাসকান গার্লিক চিকেন পাস্তা",
            descriptionEn = "Tender pan-seared chicken breasts tossed in a velvety garlic parmesan cream sauce with baby spinach, sweet sun-dried tomatoes, and fettuccine.",
            descriptionBn = "রসুন, পারমেসান পনির, কচি পালং শাক এবং টমেটোর ক্রিমি সুস্বাদু সসে তৈরি দুর্দান্ত স্বাদের পাস্তা।",
            categoryEn = "Italian Comfort Food",
            categoryBn = "ইতালীয় সুস্বাদু পাস্তা",
            prepTimeMinutes = 15,
            cookTimeMinutes = 25,
            baseServings = 4,
            difficulty = Difficulty.EASY,
            imageResId = R.drawable.dish_pasta,
            ingredients = listOf(
                Ingredient(350.0, "g", "গ্রাম", "Fettuccine or Penne Pasta", "পাস্তা"),
                Ingredient(400.0, "g", "গ্রাম", "Chicken breast strips", "মুরগির বুকের মাংসের টুকরো"),
                Ingredient(4.0, "cloves", "কোয়া", "Fresh Garlic, minced", "রসুন কুচি"),
                Ingredient(1.0, "cup", "কাপ", "Heavy Cream or Cooking Cream", "হেভি ক্রিম"),
                Ingredient(0.5, "cup", "কাপ", "Grated Parmesan cheese", "পারমেসান চিজ"),
                Ingredient(2.0, "cups", "কাপ", "Fresh Baby Spinach", "কচি পালং শাক"),
                Ingredient(0.5, "cup", "কাপ", "Cherry tomatoes or sun-dried tomatoes", "চেরি টমেটো বা সান-ড্রাইড টমেটো"),
                Ingredient(2.0, "tbsp", "টেবিল চামচ", "Extra Virgin Olive Oil", "অলিভ অয়েল"),
                Ingredient(1.0, "tsp", "চা চামচ", "Italian Herb Seasoning", "ইতালীয় মিক্সড হার্বস"),
                Ingredient(1.0, "tsp", "চা চামচ", "Crushed Black Pepper & Salt", "কালো গোলমরিচ গুঁড়ো ও লবণ")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Cook pasta in salted boiling water until al dente. Reserve half a cup of pasta water before draining.",
                    "লবণাক্ত গরম পানিতে পাস্তা সিদ্ধ করে নিন। পানি ঝরানোর আগে আধা কাপ পাস্তার পানি সংরক্ষণ করুন।",
                    "Starchy pasta water creates an emulsified glossy sauce.",
                    "পাস্তা সেদ্ধ করা পানি সস ঘন ও মোলায়েম করতে দারুণ সাহায্য করে।"
                ),
                CookingStep(
                    2,
                    "Season chicken with salt, pepper, and Italian herbs. Heat olive oil in a skillet and sear chicken for 6-8 minutes until golden.",
                    "মুরগির মাংসে লবণ, গোলমরিচ ও হার্বস মিশিয়ে অলিভ অয়েলে সোনালী করে ভেজে তুলে রাখুন।"
                ),
                CookingStep(
                    3,
                    "In the same skillet, sauté minced garlic and tomatoes for 2 minutes until fragrant.",
                    "একই প্যানে রসুন কুচি ও টমেটো ২ মিনিট সুগন্ধ বের হওয়া পর্যন্ত ভাজুন।"
                ),
                CookingStep(
                    4,
                    "Pour in cream and bring to a gentle simmer. Whisk in grated parmesan until rich and melted.",
                    "ক্রিম ঢেলে হালকা ফুটিয়ে নিন এবং পারমেসান চিজ দিয়ে ভালো করে মিশিয়ে নিন।"
                ),
                CookingStep(
                    5,
                    "Add baby spinach and cooked chicken. Toss in warm pasta and reserved pasta water until evenly coated. Serve hot.",
                    "পালং শাক, মুরগির মাংস এবং পাস্তা দিয়ে ভালোভাবে মিশিয়ে গরম গরম পরিবেশন করুন।"
                )
            ),
            chefTipEn = "Do not boil heavy cream on high heat; keep it on medium-low so the dairy stays smooth without separating.",
            chefTipBn = "ক্রিম কখনোই অতিরিক্ত উচ্চ আঁচে ফোটাবেন না; মাঝারি আঁচে রাখলে সস মসৃণ থাকে।"
        ),
        Recipe(
            id = "pan_seared_lemon_herb_salmon",
            titleEn = "Pan-Seared Lemon Herb Salmon Bowl",
            titleBn = "লেমন হার্ব প্যান-সিয়ার্ড সালমন বাউল",
            descriptionEn = "Crispy skin pan-seared salmon fillet basted in lemon garlic butter, served alongside herb-roasted vegetables and fluffy quinoa.",
            descriptionBn = "মুচমুচে চামড়া ও রসালো সালমন মাছের ফিলে, লেবু ও রসুন-মাখনের সুবাসে ভাজা এবং পুষ্টিকর সবজির সাথে পরিবেশিত।",
            categoryEn = "Healthy & High Protein",
            categoryBn = "স্বাস্থ্যকর ও প্রোটিন সমৃদ্ধ",
            prepTimeMinutes = 15,
            cookTimeMinutes = 15,
            baseServings = 2,
            difficulty = Difficulty.MEDIUM,
            imageResId = R.drawable.dish_salmon,
            ingredients = listOf(
                Ingredient(2.0, "fillets", "টুকরো", "Fresh Salmon or Bhetki fillets (180g each)", "তাজা সালমন বা ভেটকি ফিলে"),
                Ingredient(2.0, "tbsp", "টেবিল চামচ", "Unsalted Butter", "মাখন"),
                Ingredient(1.0, "whole", "টি", "Fresh Lemon (juiced & sliced)", "তাজা পাতিলেবু (রস ও স্লাইস)"),
                Ingredient(3.0, "cloves", "কোয়া", "Garlic, crushed", "রসুন কুচি"),
                Ingredient(1.0, "tbsp", "টেবিল চামচ", "Fresh Dill or Parsley, chopped", "ধনেপাতা বা পার্সলে কুচি"),
                Ingredient(1.0, "tbsp", "টেবিল চামচ", "Olive Oil", "অলিভ অয়েল"),
                Ingredient(0.5, "tsp", "চা চামচ", "Smoked Paprika or Red Chili Flakes", "পাপরিকা বা চিলি ফ্লেক্স"),
                Ingredient(1.0, "tsp", "চা চামচ", "Coarse Sea Salt and Black Pepper", "লবণ ও গোলমরিচ গুঁড়ো")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Pat salmon fillets completely dry with paper towels. Season generously with sea salt, pepper, and paprika.",
                    "সালমন ফিলের পানি টিস্যু দিয়ে ভালোভাবে শুকিয়ে নিন। লবণ, গোলমরিচ ও পাপরিকা মেখে নিন।",
                    "A bone-dry fish surface is the secret to guaranteed crispy skin.",
                    "মাছের চামড়া সম্পূর্ণ শুকনো রাখলে চামড়া দারুণ মুচমুচে হয়।"
                ),
                CookingStep(
                    2,
                    "Heat olive oil in a stainless steel or cast-iron skillet over medium-high heat until shimmering.",
                    "প্যানে অলিভ অয়েল মাঝারি-উচ্চ তাপে গরম করুন।"
                ),
                CookingStep(
                    3,
                    "Place salmon skin-side down. Press gently for 10 seconds. Sear undisturbed for 4-5 minutes until skin is golden crisp.",
                    "মাছের চামড়ার অংশ নিচে দিয়ে আলতো চাপুন। ৪-৫ মিনিট সোনালী ও ক্রিস্পি হওয়া পর্যন্ত ভাজুন।"
                ),
                CookingStep(
                    4,
                    "Flip fillets over. Add butter, crushed garlic, lemon slices, and fresh herbs to the pan.",
                    "মাছ উল্টে দিন। প্যানে মাখন, রসুন, লেবুর টুকরো এবং হার্বস যোগ করুন।"
                ),
                CookingStep(
                    5,
                    "Tilt the pan and spoon foaming hot lemon-garlic butter over the fish for 2 minutes. Rest 3 minutes before serving.",
                    "প্যান একটু কাত করে চামচ দিয়ে গরম বাটার মাছের ওপর ঢালুন ২ মিনিট। পরিবেশনের আগে ৩ মিনিট বিশ্রাম দিন।"
                )
            ),
            chefTipEn = "Do not try to move the fish too early. When the skin is perfectly crisped, it naturally releases from the pan.",
            chefTipBn = "ভাজার সময় মাছ আগেভাগে নাড়াচাড়া করবেন না; চামড়া সঠিক ক্রিস্পি হলে নিজে থেকেই প্যান থেকে আলগা হয়ে আসবে।"
        ),
        Recipe(
            id = "shorshe_fish_curry",
            titleEn = "Authentic Mustard Fish Curry (Shorshe)",
            titleBn = "ঐতিহ্যবাহী খাঁটি সর্ষে ইলিশ / সর্ষে রুই",
            descriptionEn = "A heritage Bengali masterpiece of tender fish steaks gently simmered in a pungent stone-ground yellow and black mustard paste with pure mustard oil and slit green chillies.",
            descriptionBn = "ঘানিভাঙা খাঁটি সর্ষের তেল, কাঁচামরিচ এবং মিহি বাটা সর্ষের ঝাঁঝে তৈরি জিভে জল আনা খাঁটি বাঙালিয়ানা সর্ষে মাছের ঝোল।",
            categoryEn = "Traditional Bengali Heritage",
            categoryBn = "বাঙালি ঐতিহ্যবাহী পদ",
            prepTimeMinutes = 20,
            cookTimeMinutes = 20,
            baseServings = 4,
            difficulty = Difficulty.MEDIUM,
            imageResId = R.drawable.dish_curry,
            ingredients = listOf(
                Ingredient(4.0, "steaks", "টুকরো", "Fresh Hilsa (Ilish) or Rui fish steaks", "তাজা ইলিশ বা রুই মাছের টুকরো"),
                Ingredient(3.0, "tbsp", "টেবিল চামচ", "Yellow & Black Mustard seeds (soaked)", "সাদা ও কালো সর্ষে দানা"),
                Ingredient(4.0, "tbsp", "টেবিল চামচ", "Cold-Pressed Pure Mustard Oil", "খাঁটি সর্ষের তেল"),
                Ingredient(6.0, "whole", "টি", "Fresh Green Chillies, slit", "কাঁচামরিচ ফালি"),
                Ingredient(1.0, "tsp", "চা চামচ", "Turmeric Powder (Haldi)", "হলুদ গুঁড়ো"),
                Ingredient(0.5, "tsp", "চা চামচ", "Kalonji (Nigella seeds)", "কালোজিরা"),
                Ingredient(0.5, "tsp", "চা চামচ", "Kashmiri Red Chili Powder", "মৃদু লাল মরিচ গুঁড়ো"),
                Ingredient(1.5, "tsp", "চা চামচ", "Salt (to taste)", "লবণ (স্বাদমতো)"),
                Ingredient(1.0, "cup", "কাপ", "Warm Water", "হালকা গরম পানি")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Grind soaked mustard seeds with 2 green chillies and a pinch of salt with water until ultra-smooth. Strain through a sieve.",
                    "ভেজানো সর্ষে, ২টি কাঁচামরিচ এবং সামান্য লবণ দিয়ে মিহি পেস্ট তৈরি করে ছাঁকনি দিয়ে ছেঁকে নিন।",
                    "Grinding with green chillies and salt prevents mustard bitterness.",
                    "কাঁচামরিচ ও লবণ দিয়ে সর্ষে বাটলে তিতকুটে ভাব কেটে যায়।"
                ),
                CookingStep(
                    2,
                    "Marinate fish steaks with half turmeric powder and salt for 10 minutes.",
                    "মাছের টুকরোগুলোতে অর্ধেক হলুদ ও লবণ মাখিয়ে ১০ মিনিট রাখুন।"
                ),
                CookingStep(
                    3,
                    "Heat 3 tablespoons of pungent mustard oil in a pan until smoking gently. Add kalonji and slit chillies; let them crackle.",
                    "প্যানে ৩ টেবিল চামচ সর্ষের তেল ভালো করে গরম করে কালোজিরা ও কাঁচামরিচ ফোড়ন দিন।"
                ),
                CookingStep(
                    4,
                    "Mix strained mustard paste with warm water and remaining turmeric; pour into the pan and bring to a lively boil.",
                    "ছাঁকা সর্ষের পেস্ট, গরম পানি ও হলুদ মিশিয়ে ঝোল ফুটতে দিন।"
                ),
                CookingStep(
                    5,
                    "Slide in fish steaks. Cover and simmer on medium heat for 10-12 minutes until fish is cooked. Drizzle 1 tbsp raw mustard oil on top.",
                    "মাছের টুকরোগুলো সাবধানে দিন। ঢাকনা দিয়ে মাঝারি আঁচে ১০-১২ মিনিট রান্না করুন। নামানোর আগে উপর দিয়ে ১ চামচ কাঁচা সর্ষের তেল ছড়িয়ে দিন।"
                )
            ),
            chefTipEn = "The final drizzle of raw mustard oil right before taking off heat gives the authentic aromatic punch.",
            chefTipBn = "নামানোর আগমুহূর্তে এক চামচ কাঁচা সর্ষের তেল ছড়িয়ে দিলে খাঁটি সর্ষের অতুলনীয় সুবাস পাওয়া যায়।"
        ),
        Recipe(
            id = "bhuna_khichuri_dim_bhaji",
            titleEn = "Fragrant Bhuna Khichuri & Spiced Egg",
            titleBn = "শাহী ভুনা খিচুড়ি ও মুচমুচে ডিম ভাজি",
            descriptionEn = "Golden roasted moong dal cooked with aromatic chinigura rice, whole aromatic spices, and green chillies, paired with a crispy herb fried egg.",
            descriptionBn = "ঘিয়ে ভাজা সোনা মুগ ডাল, সুবাসিত চিনিগুঁড়া চাল, আদা-মসলার ফোড়ন এবং পেঁয়াজ-মরিচ দিয়ে মচমচে ডিম ভাজির মন মাতানো স্বাদ।",
            categoryEn = "Rainy Day & Comfort Food",
            categoryBn = "বৃষ্টির দিনের মুখরোচক খাবার",
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            baseServings = 4,
            difficulty = Difficulty.EASY,
            imageResId = R.drawable.dish_biryani,
            ingredients = listOf(
                Ingredient(300.0, "g", "গ্রাম", "Chinigura or Kalijeera Aromatic Rice", "চিনিগুঁড়া বা সুগন্ধি চাল"),
                Ingredient(200.0, "g", "গ্রাম", "Yellow Roasted Moong Dal", "ভাজা সোনা মুগ ডাল"),
                Ingredient(3.0, "tbsp", "টেবিল চামচ", "Pure Ghee & Mustard Oil blend", "ঘি ও সর্ষের তেল"),
                Ingredient(2.0, "medium", "টি", "Onions, sliced", "পেঁয়াজ কুচি"),
                Ingredient(1.5, "tbsp", "টেবিল চামচ", "Ginger & Garlic paste", "আদা-রসুন বাটা"),
                Ingredient(1.0, "tsp", "চা চামচ", "Cumin & Coriander powder", "জিরা ও ধনে গুঁড়ো"),
                Ingredient(1.0, "tsp", "চা চামচ", "Turmeric powder", "হলুদ গুঁড়ো"),
                Ingredient(4.0, "whole", "টি", "Eggs (for serving)", "ডিম (ভাজার জন্য)"),
                Ingredient(5.0, "cups", "কাপ", "Boiling Hot Water", "ফুটন্ত গরম পানি"),
                Ingredient(1.5, "tsp", "চা চামচ", "Salt & slit green chillies", "লবণ ও কাঁচামরিচ ফালি")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Dry roast yellow moong dal until fragrant and light golden. Wash thoroughly along with aromatic rice.",
                    "মুগ ডাল হালকা সোনালী করে ভেজে চালের সাথে ধুয়ে পানি ঝরিয়ে নিন।"
                ),
                CookingStep(
                    2,
                    "Heat ghee and oil in a deep pot. Sauté whole cardamom, cinnamon, and sliced onions until translucent golden.",
                    "পাত্রে তেল-ঘি গরম করে এলাচ, দারুচিনি এবং পেঁয়াজ সোনালী করে ভাজুন।"
                ),
                CookingStep(
                    3,
                    "Add ginger-garlic paste and ground spices; fry for 2 minutes. Add drained rice and dal, roasting for 5 minutes until crisp.",
                    "মসলা দিয়ে কষিয়ে চাল ও ডাল ৫ মিনিট ভালোভাবে ভেজে নিন।"
                ),
                CookingStep(
                    4,
                    "Pour in boiling hot water and salt. Stir once and cover. Cook on medium heat until water reduces.",
                    "ফুটন্ত গরম পানি ও লবণ দিন। মাঝারি আঁচে পানি শুকিয়ে যাওয়া পর্যন্ত রান্না করুন।"
                ),
                CookingStep(
                    5,
                    "Place on low heat (dum) with slit chillies and a dollop of ghee for 15 minutes. Serve with crispy fried egg.",
                    "কাঁচামরিচ ও এক চামচ ঘি দিয়ে ১৫ মিনিট দমে রাখুন। মুচমুচে ডিম ভাজির সাথে গরম গরম পরিবেশন করুন।"
                )
            ),
            chefTipEn = "Roasting the raw rice and dal in ghee until they hiss in the pot guarantees non-sticky, distinct khichuri grains.",
            chefTipBn = "চাল-ডাল ভালোভাবে ঘিয়ে ভেজে নিলে খিচুড়ি ঝরঝরে ও সুস্বাদু হয়।"
        ),
        Recipe(
            id = "creamy_butter_chicken",
            titleEn = "Silky Butter Chicken (Murgh Makhani)",
            titleBn = "রেস্তোরাঁ স্টাইল বাটার চিকেন মাখানি",
            descriptionEn = "Tender tandoori-style spiced chicken pieces simmered in a velvety smooth tomato, cashew nut, and butter gravy perfumed with crushed fenugreek leaves.",
            descriptionBn = "মাখন, কাজুবাদাম পেস্ট এবং তাজা টমেটোর স্মুথ গ্রেভিতে রান্না করা অত্যন্ত জনপ্রিয় রেস্তোরাঁ স্টাইল বাটার চিকেন।",
            categoryEn = "North Indian Classic",
            categoryBn = "নর্থ ইন্ডিয়ান স্পেশাল",
            prepTimeMinutes = 25,
            cookTimeMinutes = 35,
            baseServings = 4,
            difficulty = Difficulty.MEDIUM,
            imageResId = R.drawable.dish_curry,
            ingredients = listOf(
                Ingredient(500.0, "g", "গ্রাম", "Boneless Chicken Thighs, cubed", "মুরগির মাংস টুকরো"),
                Ingredient(500.0, "g", "গ্রাম", "Ripe Red Tomatoes, puréed", "পাকা টমেটো পিউরি"),
                Ingredient(50.0, "g", "গ্রাম", "Cashew Nuts, soaked & blended", "কাজুবাদাম পেস্ট"),
                Ingredient(50.0, "g", "গ্রাম", "Butter (divided)", "মাখন"),
                Ingredient(3.0, "tbsp", "টেবিল চামচ", "Fresh Heavy Cream", "ফ্রেশ ক্রিম"),
                Ingredient(1.5, "tbsp", "টেবিল চামচ", "Ginger-Garlic Paste", "আদা-রসুন বাটা"),
                Ingredient(1.0, "tbsp", "টেবিল চামচ", "Kashmiri Red Chilli Powder (mild color)", "কাশ্মীরি মরিচ গুঁড়ো"),
                Ingredient(1.0, "tbsp", "টেবিল চামচ", "Kasuri Methi (crushed fenugreek)", "কাসুরি মেথি"),
                Ingredient(1.0, "tsp", "চা চামচ", "Garam Masala & Honey/Sugar", "গরম মসলা ও মধু")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Marinate chicken with yogurt, ginger-garlic paste, chili powder, and salt. Pan-sear or grill until smoky and charred.",
                    "দই ও মসলায় মাংস ম্যারিনেট করে প্যানে হালকা পোড়া পোড়া ভাব এনে ভেজে নিন।"
                ),
                CookingStep(
                    2,
                    "Cook tomato purée with ginger, chili powder, and butter until oil separates.",
                    "মাখনে টমেটো পিউরি, কাশ্মীরি মরিচ ও আদা দিয়ে তেল ওঠা পর্যন্ত রান্না করুন।"
                ),
                CookingStep(
                    3,
                    "Stir in smooth cashew paste and simmer for 5 minutes until rich and thick.",
                    "কাজুবাদাম পেস্ট মিশিয়ে ৫ মিনিট ঘন গ্রেভি তৈরি করুন।"
                ),
                CookingStep(
                    4,
                    "Add cooked chicken pieces and simmer gently for 8-10 minutes.",
                    "ভাজা মাংসের টুকরোগুলো গ্রেভিতে দিয়ে ৮-১০ মিনিট রান্না করুন।"
                ),
                CookingStep(
                    5,
                    "Finish with crushed Kasuri Methi, butter, a drizzle of fresh cream, and a drop of honey.",
                    "কাসুরি মেথি, বাকি মাখন, ক্রিম ও সামান্য মধু মিশিয়ে নামিয়ে নিন।"
                )
            ),
            chefTipEn = "Crushing dry Kasuri Methi between your palms before adding unlocks its distinctive restaurant fragrance.",
            chefTipBn = "হাতের তালুতে কাসুরি মেথি ঘষে নিয়ে দিলে অসাধারণ রেস্তোরাঁর সুবাস বের হয়।"
        ),
        Recipe(
            id = "mediterranean_shakshuka",
            titleEn = "Skillet Mediterranean Shakshuka",
            titleBn = "মেডিটেরিনিয়ান শাকশুকা (পোচ ডিমের সুস্বাদু পদ)",
            descriptionEn = "Sunny whole eggs gently poached in a simmering chunky sauce of spiced tomatoes, sweet bell peppers, onions, cumin, and crumbled feta cheese.",
            descriptionBn = "টমেটো, ক্যাপসিকাম, জিরা ও অলিভ অয়েলের ঘন সসের মধ্যে আলতো করে পোচ করা ডিম ও ধনেপাতার সুস্বাদু স্বাস্থ্যকর ব্রেকফাস্ট বা ডিনার।",
            categoryEn = "Mediterranean & Healthy",
            categoryBn = "স্বাস্থ্যকর ও চটজলদি খাবার",
            prepTimeMinutes = 10,
            cookTimeMinutes = 15,
            baseServings = 3,
            difficulty = Difficulty.EASY,
            imageResId = R.drawable.dish_pasta,
            ingredients = listOf(
                Ingredient(4.0, "large", "টি", "Fresh Farm Eggs", "তাজা ডিম"),
                Ingredient(400.0, "g", "গ্রাম", "Crushed Ripe Tomatoes", "টমেটো কুচি বা পিউরি"),
                Ingredient(1.0, "large", "টি", "Red Bell Pepper, diced", "লাল ক্যাপসিকাম কুচি"),
                Ingredient(1.0, "medium", "টি", "Onion, chopped", "পেঁয়াজ কুচি"),
                Ingredient(3.0, "cloves", "কোয়া", "Garlic, minced", "রসুন কুচি"),
                Ingredient(2.0, "tbsp", "টেবিল চামচ", "Extra Virgin Olive Oil", "অলিভ অয়েল"),
                Ingredient(1.0, "tsp", "চা চামচ", "Ground Cumin & Smoked Paprika", "জিরা গুঁড়ো ও পাপরিকা"),
                Ingredient(50.0, "g", "গ্রাম", "Crumbled Feta or Goat Cheese", "ফেটা বা পনিরের টুকরো"),
                Ingredient(1.0, "handful", "মুঠো", "Fresh Parsley & Cilantro", "ধনেপাতা কুচি")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Heat olive oil in a wide skillet. Sauté chopped onions and bell peppers for 5 minutes until tender.",
                    "প্যানে অলিভ অয়েল গরম করে পেঁয়াজ ও ক্যাপসিকাম ৫ মিনিট নরম হওয়া পর্যন্ত ভাজুন।"
                ),
                CookingStep(
                    2,
                    "Add minced garlic, cumin, and paprika; cook for 1 minute until fragrant.",
                    "রসুন কুচি, জিরা ও পাপরিকা দিয়ে ১ মিনিট নাড়ুন।"
                ),
                CookingStep(
                    3,
                    "Pour in crushed tomatoes and season with salt and pepper. Simmer on medium-low for 10 minutes until sauce thickens.",
                    "টমেটো কুচি, লবণ ও গোলমরিচ দিয়ে ১০ মিনিট ঘন হওয়া পর্যন্ত ফোটান।"
                ),
                CookingStep(
                    4,
                    "Use a spoon to make small wells in the sauce. Gently crack an egg directly into each well.",
                    "চামচ দিয়ে সসের মাঝে ছোট গর্ত তৈরি করে সাবধানে প্রতিটি ডিম ভেঙে দিন।"
                ),
                CookingStep(
                    5,
                    "Cover the pan and cook on low heat for 5-7 minutes until whites are set and yolks remain runny. Garnish with herbs and cheese.",
                    "ঢাকনা দিয়ে কম আঁচে ৫-৭ মিনিট রাখুন যাতে ডিমের সাদা অংশ শক্ত হয় কিন্তু কুসুম নরম থাকে। পনির ও ধনেপাতা ছড়িয়ে পরিবেশন করুন।"
                )
            ),
            chefTipEn = "Serve immediately with crusty sourdough bread or warm garlic naan to dip into the rich molten yolks.",
            chefTipBn = "কুসুমে ডুবিয়ে খাওয়ার জন্য গরম গরম নানরুটি বা টোস্ট করা পাউরুটির সাথে পরিবেশন করুন।"
        ),
        Recipe(
            id = "lentil_tadka_dal",
            titleEn = "Golden Home-Style Tadka Dal",
            titleBn = "সুস্বাদু রসুন-জিরার তড়কা ডাল",
            descriptionEn = "Comforting golden lentils slow-cooked with turmeric, crowned with a sizzling ghee tempering of crisped garlic, cumin, whole red chillies, and fresh cilantro.",
            descriptionBn = "ঘিয়ে ভাজা মুচমুচে রসুন, গোটা জিরা ও শুকনা মরিচের সুবাসিত তড়কা দেওয়া মুখরোচক সোনালী মসুর-মুগ ডাল।",
            categoryEn = "Comfort Food & Vegan/Veg",
            categoryBn = "প্রতিদিনের আরামদায়ক খাবার",
            prepTimeMinutes = 10,
            cookTimeMinutes = 20,
            baseServings = 4,
            difficulty = Difficulty.EASY,
            imageResId = R.drawable.dish_salmon,
            ingredients = listOf(
                Ingredient(200.0, "g", "গ্রাম", "Red Lentils (Masoor) & Yellow Moong", "মসুর ও মুগ ডাল"),
                Ingredient(2.0, "tbsp", "টেবিল চামচ", "Pure Desi Ghee", "খাঁটি ঘি"),
                Ingredient(5.0, "cloves", "কোয়া", "Garlic, finely sliced", "রসুন কুচি"),
                Ingredient(1.0, "tsp", "চা চামচ", "Whole Cumin Seeds", "গোটা জিরা"),
                Ingredient(2.0, "whole", "টি", "Dry Red Chillies", "শুকনা মরিচ"),
                Ingredient(1.0, "medium", "টি", "Tomato, chopped", "টমেটো কুচি"),
                Ingredient(0.5, "tsp", "চা চামচ", "Turmeric & Kashmiri Chili powder", "হলুদ ও মরিচ গুঁড়ো"),
                Ingredient(1.0, "handful", "মুঠো", "Fresh Coriander leaves", "ধনেপাতা কুচি"),
                Ingredient(1.0, "tsp", "চা চামচ", "Salt (to taste)", "লবণ (স্বাদমতো)")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Rinse lentils well and boil with 3 cups of water, turmeric, half the salt, and chopped tomato until creamy soft (15 mins).",
                    "ডাল ধুয়ে পানি, হলুদ ও টমেটো দিয়ে ১৫ মিনিট নরম ও মসৃণ হওয়া পর্যন্ত সেদ্ধ করুন।"
                ),
                CookingStep(
                    2,
                    "Whisk lentils lightly with a wooden churner until velvety. Adjust water to desired consistency.",
                    "ঘুঁটনি দিয়ে ডাল একটু ঘুটে নিন এবং পছন্দমতো ঘনত্বের পানি দিন।"
                ),
                CookingStep(
                    3,
                    "For the Tadka: Heat ghee in a small pan until smoking lightly.",
                    "তড়কার জন্য ছোট প্যানে ঘি ভালো করে গরম করুন।"
                ),
                CookingStep(
                    4,
                    "Add cumin seeds, dry red chillies, and sliced garlic. Fry until garlic turns golden brown and fragrant.",
                    "জিরা, শুকনা মরিচ এবং রসুন কুচি দিয়ে বাদামী ও মুচমুচে হওয়া পর্যন্ত ভাজুন।"
                ),
                CookingStep(
                    5,
                    "Immediately pour sizzling tadka over the dal and cover with a lid for 2 minutes to trap the aroma. Stir in fresh coriander.",
                    "ফুটে ওঠা তড়কা ডালের ওপর ঢেলে সাথে সাথে ঢাকনা দিয়ে ২ মিনিট রাখুন। ধনেপাতা মিশিয়ে নিন।"
                )
            ),
            chefTipEn = "Cover the dal pot immediately for 2 minutes right after pouring the sizzling tadka to lock in the garlic aroma.",
            chefTipBn = "ঘিয়ের তড়কা ঢালার সাথে সাথে ঢাকনা দিয়ে দিলে রসুনের অতুলনীয় ঘ্রাণ ডালের ভেতরে আটকে যায়।"
        )
    )

    fun getAllRecipes(): List<Recipe> = recipes

    fun getRecipeById(id: String): Recipe? = recipes.find { it.id == id }

    /**
     * Deterministic daily recipe selection based on the day of the year and year.
     * Guarantees all users see the exact same recipe today, and a fresh recipe tomorrow!
     */
    fun getRecipeForDate(calendar: Calendar = Calendar.getInstance()): Recipe {
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)
        val combinedSeed = dayOfYear + (year * 365)
        val index = Math.floorMod(combinedSeed, recipes.size)
        return recipes[index]
    }

    /**
     * Retrieve recipes from the last few days.
     */
    fun getRecentRecipes(daysCount: Int = 5): List<Recipe> {
        val today = Calendar.getInstance()
        val list = mutableListOf<Recipe>()
        for (i in 1..daysCount) {
            val pastCal = Calendar.getInstance().apply {
                timeInMillis = today.timeInMillis
                add(Calendar.DAY_OF_YEAR, -i)
            }
            val recipe = getRecipeForDate(pastCal)
            if (!list.contains(recipe)) {
                list.add(recipe)
            }
        }
        return list
    }

    /**
     * Pick a random recipe for users who want immediate inspiration.
     */
    fun getSurpriseRecipe(currentId: String?): Recipe {
        val candidates = recipes.filterNot { it.id == currentId }
        return if (candidates.isNotEmpty()) candidates.random() else recipes.first()
    }

    /**
     * Format full recipe details for clipboard copying or social sharing.
     */
    fun formatRecipeForShare(recipe: Recipe, language: Language, servings: Int): String {
        val isBn = language == Language.BANGLA
        val title = recipe.getTitle(language)
        val desc = recipe.getDescription(language)
        val category = recipe.getCategory(language)
        val prepLabel = if (isBn) "প্রস্তুতির সময়" else "Prep Time"
        val cookLabel = if (isBn) "রান্নার সময়" else "Cook Time"
        val servingsLabel = if (isBn) "পরিবেশন" else "Servings"
        val difficultyLabel = if (isBn) "কঠিনতা" else "Difficulty"
        val ingredientsTitle = if (isBn) "🛒 প্রয়োজনীয় উপকরণ:" else "🛒 INGREDIENTS:"
        val stepsTitle = if (isBn) "👨‍🍳 রান্নার প্রণালী:" else "👨‍🍳 STEP-BY-STEP INSTRUCTIONS:"
        val tipTitle = if (isBn) "💡 শেফের টিপস:" else "💡 CHEF'S TIP:"

        val sb = StringBuilder()
        sb.append("🍽️ ").append(title).append("\n")
        sb.append("🏷️ ").append(category).append("\n\n")
        sb.append(desc).append("\n\n")
        sb.append("⏱️ ").append(prepLabel).append(": ").append(recipe.prepTimeMinutes).append(if (isBn) " মিনিট | " else " mins | ")
        sb.append(cookLabel).append(": ").append(recipe.cookTimeMinutes).append(if (isBn) " মিনিট\n" else " mins\n")
        sb.append("👥 ").append(servingsLabel).append(": ").append(servings).append(" | ")
        sb.append("⭐ ").append(difficultyLabel).append(": ").append(recipe.difficulty.getDisplayName(language)).append("\n\n")

        sb.append(ingredientsTitle).append("\n")
        recipe.ingredients.forEach { ing ->
            sb.append("• ").append(ing.getDisplayText(language, servings, recipe.baseServings)).append("\n")
        }
        sb.append("\n")

        sb.append(stepsTitle).append("\n")
        recipe.steps.forEach { step ->
            sb.append(step.stepNumber).append(". ").append(step.getInstruction(language)).append("\n")
        }
        sb.append("\n")

        sb.append(tipTitle).append("\n")
        sb.append(recipe.getChefTip(language)).append("\n\n")

        sb.append("—\n")
        sb.append("✨ ").append(if (isBn) "Daily Dish অ্যাপের সৌজন্যে — প্রতিদিনের নতুন রেসিপি" else "Discovered on Daily Dish — Fresh Daily Meal Inspiration")
        sb.append("\n© 2026 ViveScript Solutions LLC")

        return sb.toString()
    }
}
