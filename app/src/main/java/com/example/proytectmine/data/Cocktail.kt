
package com.example.proytectmine.data

//import com.google.gson.annotations.SerializedName// SerializedName sirve para que el nombre de la variable sea el mismo que el de la API

// en esta clase creamos el modelo de datos de la respuesta de la API
data class CocktailResponse (
    val ingredients: List<Ingredients>
) {

}
// en esta clase creamos el modelo de datos de un super heroe
data class Cocktail (
    val id: String,
    val nameCocktail: String
){
}
data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String,
    val strType: String,
    val strAlcohol: String,
    val strABV: String?// esto puede ser null , toca manejar eso
)
/*
* "idIngredient": "552",
      "strIngredient": "Elderflower cordial",
      "strDescription": "Elderflower cordial is a soft drink
      * made largely from a refined sugar and water solution
      * and uses the flowers of the European elderberry. Historically
      * the cordial has been popular in North Western Europe where it
      *  has a strong Victorian heritage.",
      "strType": "Cordial",
      "strAlcohol": "No",
      "strABV": null*/