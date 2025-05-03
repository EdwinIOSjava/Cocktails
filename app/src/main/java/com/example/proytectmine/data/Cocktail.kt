
package com.example.proytectmine.data

import com.google.gson.annotations.SerializedName

//import com.google.gson.annotations.SerializedName// SerializedName sirve para que el nombre de la variable sea el mismo que el de la API

// en esta clase creamos el modelo de datos de la respuesta de la API
data class CocktailResponseReduced (
    val drinks: List<Drink>?,
    val ingredients: List<IngredientsResponse>
)
data class CocktailResponseFull(
    val drinks: List<DrinkFull>
)

data class Ingredients(
    val idIngredient: String?,
    val strIngredient: String?,
    val strDescription: String?,
    val strType: String?,
    val strAlcohol: String?,
    val strABV: String?// esto puede ser null , toca manejar eso
)
data class IngredientsResponse(
    val strDrink: String?,
    val strDrinkThumb: String?,
    val idDrink: String?
)
data class Drink(
    val idDrink: String,
    val strDrink: String?,
    val strDrinkThumb: String?
)

data class DrinkFull(
    val idDrink: String?,
    val strDrink: String?,
    val strDrinkAlternate: String?,
    val strTags: String?,
    val strVideo: String?,
    val strCategory: String?,
    val strIBA: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strInstructionsES: String?,
    val strInstructionsDE: String?,
    val strInstructionsFR: String?,
    val strInstructionsIT: String?,
    @SerializedName("strInstructionsZH-HANS") val strInstructionsZH_HANS: String?,
    @SerializedName("strInstructionsZH-HANT") val strInstructionsZH_HANT: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strImageSource: String?,
    val strImageAttribution: String?,
    val strCreativeCommonsConfirmed: String?,
    val dateModified: String?
) {
    fun getIngredients(): List<String> {
        return listOf(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13,
            strIngredient14,
            strIngredient15
        ).filterNotNull()
    }

    fun getMeasures(): List<String?> {
        return listOf(
            strMeasure1,
            strMeasure2,
            strMeasure3,
            strMeasure4,
            strMeasure5,
            strMeasure6,
            strMeasure7,
            strMeasure8,
            strMeasure9,
            strMeasure10,
            strMeasure11,
            strMeasure12,
            strMeasure13,
            strMeasure14,
            strMeasure15
        )
    }

    fun getIngredientsWithMeasures(): List<String> {
        return getIngredients().mapIndexed { i, item ->
            " ${getMeasures()[i] ?: "Al gusto"} - $item "
        }
    }
    fun isFavorite(id: String): Boolean {
        val favorite = true
        return favorite
    }

    companion object {
        const val TABLE_NAME = "favorites"

        // Columnas básicas
        const val COLUMN_ID = "idDrink"
        const val COLUMN_NAME = "strDrink"
        const val COLUMN_ALTERNATE = "strDrinkAlternate"
        const val COLUMN_TAGS = "strTags"
        const val COLUMN_VIDEO = "strVideo"
        const val COLUMN_CATEGORY = "strCategory"
        const val COLUMN_IBA = "strIBA"
        const val COLUMN_ALCOHOLIC = "strAlcoholic"
        const val COLUMN_GLASS = "strGlass"
        const val COLUMN_INSTRUCTIONS = "strInstructions"
        const val COLUMN_INSTRUCTIONS_ES = "strInstructionsES"
        const val COLUMN_INSTRUCTIONS_DE = "strInstructionsDE"
        const val COLUMN_INSTRUCTIONS_FR = "strInstructionsFR"
        const val COLUMN_INSTRUCTIONS_IT = "strInstructionsIT"
        const val COLUMN_INSTRUCTIONS_ZH_HANS = "strInstructionsZH_HANS"
        const val COLUMN_INSTRUCTIONS_ZH_HANT = "strInstructionsZH_HANT"
        const val COLUMN_IMAGE = "strDrinkThumb"
        const val COLUMN_IMAGE_SOURCE = "strImageSource"
        const val COLUMN_IMAGE_ATTRIBUTION = "strImageAttribution"
        const val COLUMN_CC_CONFIRMED = "strCreativeCommonsConfirmed"
        const val COLUMN_DATE_MODIFIED = "dateModified"

        // Ingredientes y medidas
        const val COLUMN_INGREDIENT1 = "strIngredient1"
        const val COLUMN_MEASURE1 = "strMeasure1"
        const val COLUMN_INGREDIENT2 = "strIngredient2"
        const val COLUMN_MEASURE2 = "strMeasure2"
        const val COLUMN_INGREDIENT3 = "strIngredient3"
        const val COLUMN_MEASURE3 = "strMeasure3"
        const val COLUMN_INGREDIENT4 = "strIngredient4"
        const val COLUMN_MEASURE4 = "strMeasure4"
        const val COLUMN_INGREDIENT5 = "strIngredient5"
        const val COLUMN_MEASURE5 = "strMeasure5"
        const val COLUMN_INGREDIENT6 = "strIngredient6"
        const val COLUMN_MEASURE6 = "strMeasure6"
        const val COLUMN_INGREDIENT7 = "strIngredient7"
        const val COLUMN_MEASURE7 = "strMeasure7"
        const val COLUMN_INGREDIENT8 = "strIngredient8"
        const val COLUMN_MEASURE8 = "strMeasure8"
        const val COLUMN_INGREDIENT9 = "strIngredient9"
        const val COLUMN_MEASURE9 = "strMeasure9"
        const val COLUMN_INGREDIENT10 = "strIngredient10"
        const val COLUMN_MEASURE10 = "strMeasure10"
        const val COLUMN_INGREDIENT11 = "strIngredient11"
        const val COLUMN_MEASURE11 = "strMeasure11"
        const val COLUMN_INGREDIENT12 = "strIngredient12"
        const val COLUMN_MEASURE12 = "strMeasure12"
        const val COLUMN_INGREDIENT13 = "strIngredient13"
        const val COLUMN_MEASURE13 = "strMeasure13"
        const val COLUMN_INGREDIENT14 = "strIngredient14"
        const val COLUMN_MEASURE14 = "strMeasure14"
        const val COLUMN_INGREDIENT15 = "strIngredient15"
        const val COLUMN_MEASURE15 = "strMeasure15"
    }
}
