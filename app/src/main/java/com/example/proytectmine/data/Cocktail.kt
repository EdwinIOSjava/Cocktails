
package com.example.proytectmine.data

import com.google.gson.annotations.SerializedName

//import com.google.gson.annotations.SerializedName// SerializedName sirve para que el nombre de la variable sea el mismo que el de la API

// en esta clase creamos el modelo de datos de la respuesta de la API
data class CocktailResponse (
    val drinks: List<Drink>,// search coctail by name
    //val ingredients: List<Ingredients>,// search ingredient by name
)
/*data class Cocktail(
    val id: String,
    val nameCocktail: String
)*/

data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String,
    val strType: String,
    val strAlcohol: String,
    val strABV: String?// esto puede ser null , toca manejar eso
)

data class Drink(
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
}
/*
* {
  "drinks": [
    {
      "idDrink": "11004",
      "strDrink": "Whiskey Sour",
      "strDrinkAlternate": null,
      "strTags": "IBA,Classic,Alcoholic,ContemporaryClassic",
      "strVideo": "https://www.youtube.com/watch?v=hFKZPzfngcU",
      "strCategory": "Ordinary Drink",
      "strIBA": "Unforgettables",
      "strAlcoholic": "Alcoholic",
      "strGlass": "Old-fashioned glass",
      "strInstructions": "Shake with ice. Strain into chilled glass, garnish and serve. If served 'On the rocks', strain ingredients into old-fashioned glass filled with ice.",
      "strInstructionsES": "Agitar con hielo. Colar en un vaso frío, decorar y servir. Si se sirve con hielo, colar los ingredientes en un vaso old-fashioned lleno de hielo.",
      "strInstructionsDE": "Mit Eis schütteln. In ein gekühltes Glas abseihen, garnieren und servieren. Wenn Sie \"On the rocks\" servieren, die Zutaten in ein old-fashioned, mit Eis gefülltes Glas geben.",
      "strInstructionsFR": "Agiter avec de la glace. Filtrer dans un verre réfrigéré, garnir et servir. Pour un service \"on the rocks\", filtrer les ingrédients dans un verre à l'ancienne rempli de glace.",
      "strInstructionsIT": "Shakerare con ghiaccio.Filtrare in un bicchiere freddo, guarnire e servire.Se servito \"On the rocks\", filtra gli ingredienti in un bicchiere vecchio stile pieno di ghiaccio.",
      "strInstructionsZH-HANS": null,
      "strInstructionsZH-HANT": null,
      "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/hbkfsh1589574990.jpg",
      "strIngredient1": "Blended whiskey",
      "strIngredient2": "Lemon",
      "strIngredient3": "Powdered sugar",
      "strIngredient4": "Cherry",
      "strIngredient5": "Lemon",
      "strIngredient6": null,
      "strIngredient7": null,
      "strIngredient8": null,
      "strIngredient9": null,
      "strIngredient10": null,
      "strIngredient11": null,
      "strIngredient12": null,
      "strIngredient13": null,
      "strIngredient14": null,
      "strIngredient15": null,
      "strMeasure1": "2 oz ",
      "strMeasure2": "Juice of 1/2 ",
      "strMeasure3": "1/2 tsp ",
      "strMeasure4": "1 ",
      "strMeasure5": "1/2 slice ",
      "strMeasure6": null,
      "strMeasure7": null,
      "strMeasure8": null,
      "strMeasure9": null,
      "strMeasure10": null,
      "strMeasure11": null,
      "strMeasure12": null,
      "strMeasure13": null,
      "strMeasure14": null,
      "strMeasure15": null,
      "strImageSource": "https://commons.wikimedia.org/wiki/File:15-09-26-RalfR-WLC-0191.jpg",
      "strImageAttribution": "Ralf Roletschek https://www.wikidata.org/wiki/Q15080600",
      "strCreativeCommonsConfirmed": "Yes",
      "dateModified": "2017-09-02 12:45:25"
    }
  ]
}*/