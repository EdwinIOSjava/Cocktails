
package com.example.proytectmine.data

//import com.google.gson.annotations.SerializedName// SerializedName sirve para que el nombre de la variable sea el mismo que el de la API

// en esta clase creamos el modelo de datos de la respuesta de la API
class CocktailResponse (
    val response: String
) {

}
// en esta clase creamos el modelo de datos de un super heroe
class Cocktail (
    val id: String,
    val nameCocktail: String
){
}