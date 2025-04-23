package com.example.proytectmine.data

import android.content.Context

class SessionManager (context: Context) {
    //creamos variable para acceder a los metodos de preferencias para compartir
    private val sharedPref = context.getSharedPreferences("cocktail_session", Context.MODE_PRIVATE)

    // recibimos el nombre del cocktail favorito y lo guardamos en las preferencias
    fun setFavorite(id: String, favorite: Boolean) {
        // asignamos clave-valor para el signo favorito
        val editor = sharedPref.edit()
        editor.putBoolean("${id}_favorite", favorite)
        // guardamos los cambios : OBLIGATORIO O SINO NO SE GUARDAN
        editor.apply()
    }

    // retornamos el signo favorito y le damos un valor por defecto en caso de que no exista
    fun getFavorite(): String {
        return sharedPref.getString("FAVORITE_COCKTAIL", "")!!
    }

    //aqui validaremos si es favorito o no y retornaremos un booleano
    fun isFavorite(id: String): Boolean {
        // hacemos in IF si el id recibido es igual al signo guardado en getFavorite
        return sharedPref.getBoolean("${id}_favorite", false)
    }
}
