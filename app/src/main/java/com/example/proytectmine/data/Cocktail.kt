
package com.example.proytectmine.data

import com.google.gson.annotations.SerializedName

//import com.google.gson.annotations.SerializedName// SerializedName sirve para que el nombre de la variable sea el mismo que el de la API

// en esta clase creamos el modelo de datos de la respuesta de la API
data class CocktailResponse (
    val drinks: List<Drink>?,
    val ingredients: List<IngredientsResponse>
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
){companion object {
    const val TABLE_NAME = "favorites"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_IMAGE = "image"
}
}

data class DrinkOriginal(
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

    companion object{
        const val  TABLE_NAME = "favorites"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_IMAGE = "image"
    }
}
