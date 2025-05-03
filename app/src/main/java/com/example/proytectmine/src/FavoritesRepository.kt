package com.example.proytectmine.src

import android.content.Context
import com.example.proytectmine.data.DrinkFull
import com.example.proytectmine.data.FavoritesDAO

object FavoritesRepository {

    // Función para obtener la lista de cocteles favoritos desde la base de datos
    fun getFavoriteDrinks(context: Context): MutableList<DrinkFull> {
        return FavoritesDAO(context)
            .findAll()
            .mapNotNull { // Filtra solo los elementos con idDrink no nulo y no vacío
                val idDrink = it.idDrink?.takeIf { id -> id.isNotEmpty() }
                if (idDrink != null) it else null // Solo devuelve el DrinkFull si idDrink es válido
            }
            .toMutableList()
    }

    // Función para obtener solo los IDs de los cocteles favoritos
    fun getFavoriteDrinkIds(context: Context): MutableList<String> {
        return getFavoriteDrinks(context)
            .mapNotNull { it.idDrink?.takeIf { id -> id.isNotEmpty() } } // Filtra los IDs no nulos y no vacíos
            .toMutableList()
    }

}