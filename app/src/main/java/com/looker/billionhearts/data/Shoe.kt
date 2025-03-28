package com.looker.billionhearts.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.looker.billionhearts.R
import kotlin.random.Random

@Immutable
data class Shoe(
    val id: Int,
    val price: Double,
    val name: String,
    val description: String,
    val availableSizes: List<Int>,
    val variants: List<Variant>,
)

@Immutable
data class Variant(
    val id: Int,
    val color: Color,
    @DrawableRes
    val image: Int,
)

private val BLUE = Color(0xFF709BFD)
private val RED = Color(0xFFF17475)
private val GREEN = Color(0xFF6FB4B1)
private val YELLOW = Color(0xFFFDBA62)

@Stable
val mockCategories = listOf(
    "All",
    "Air Max",
    "Strider",
    "Street",
    "Casual",
    "Joggers",
    "Runners"
)

@Stable
val mockShoeData: List<Shoe> = listOf(
    Shoe(
        id = 1,
        price = 99.99,
        name = "Cloud Strider",
        description = "The perfect blend of comfort and style, these shoes will take you from the gym to the office with ease. With a breathable mesh upper and a cushioned sole, you'll never want to take them off.",
        availableSizes = randomSizes(),
        variants = listOf(
            Variant(1, RED, R.drawable.red_shoe),
            Variant(2, BLUE, R.drawable.blue_shoe),
            Variant(3, GREEN, R.drawable.green_shoe),
        ),
    ),
    Shoe(
        id = 2,
        price = 149.50,
        name = "Trail Blazer",
        description = "These rugged shoes are built to withstand the elements. Featuring a waterproof membrane and a durable outsole, you can explore any terrain with confidence.",
        availableSizes = randomSizes(),
        variants = listOf(
            Variant(1, BLUE, R.drawable.blue_shoe),
            Variant(2, RED, R.drawable.red_shoe),
        ),
    ),
    Shoe(
        id = 3,
        price = 79.00,
        name = "Street Style",
        description = "Make a statement with these eye-catching shoes. The unique design and bold colors are sure to turn heads wherever you go.",
        availableSizes = randomSizes(),
        variants = listOf(
            Variant(1, YELLOW, R.drawable.yellow_shoe),
            Variant(2, RED, R.drawable.red_shoe),
            Variant(3, GREEN, R.drawable.green_shoe),
        ),
    ),
    Shoe(
        id = 4,
        price = 120.00,
        name = "Classic Walker",
        description = "These classic shoes are a timeless addition to any wardrobe. The simple design and versatile color make them perfect for any occasion.",
        availableSizes = randomSizes(),
        variants = listOf(
            Variant(1, GREEN, R.drawable.green_shoe),
            Variant(2, YELLOW, R.drawable.yellow_shoe),
            Variant(3, BLUE, R.drawable.blue_shoe),
        ),
    ),
    Shoe(
        id = 5,
        price = 199.99,
        name = "Luxury Pacer",
        description = "Experience the ultimate in comfort with these luxurious shoes. The soft leather and cushioned insole will make you feel like you're walking on clouds.",
        availableSizes = randomSizes(),
        variants = listOf(
            Variant(1, YELLOW, R.drawable.yellow_shoe),
            Variant(2, RED, R.drawable.red_shoe),
        ),
    ),
)

private fun randomSizes(): List<Int> = List(Random.nextInt(2, 9)) {
    Random.nextInt(6, 14)
}.sorted()
