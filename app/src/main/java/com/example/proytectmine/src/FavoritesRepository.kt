package com.example.proytectmine.src

import android.content.Context
import com.example.proytectmine.data.Drink
import com.example.proytectmine.data.FavoritesDAO

object FavoritesRepository {

    // Función para obtener la lista de cocteles favoritos desde la base de datos
    fun getFavoriteDrinks(context: Context): List<Drink> {
        return FavoritesDAO(context).findAll()
    }

    // Función para obtener solo los IDs de los cocteles favoritos
    fun getFavoriteDrinkIds(context: Context): MutableList<String> {
        return getFavoriteDrinks(context).map { it.idDrink }.toMutableList()
    }

}