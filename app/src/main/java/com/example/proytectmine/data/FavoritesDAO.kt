package com.example.proytectmine.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.proytectmine.managers.DatabaseManager

class FavoritesDAO(context: Context) {
    val databaseManager= DatabaseManager(context)

    fun insert(drink: Drink){
        //insertar en la base de datos
        val db = databaseManager.writableDatabase

        //crear un mapa de valores, donde los nombres de las columnas son las claves
        val values = ContentValues().apply {
            put(Drink.COLUMN_NAME, drink.strDrink)
            put(Drink.COLUMN_IMAGE, drink.strDrinkThumb)
            put(Drink.COLUMN_ID, drink.idDrink)
        }
        try {
            //insertar el nuevo registro, devolviendo el id del nuevo registro
            val newRowId = db.insert(Drink.TABLE_NAME, null, values)
            Log.i("DATABASE", "Inserted favorite with id: $newRowId")
        }catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error insert ocktail: ${e.message}")

        } finally {
            db.close()// cerrar la base de datos
        }
    }
    fun update(drink: Drink){
        // obtenemos la base de datos en modo escritura
        val db = databaseManager.writableDatabase
        // creamos un mapa de valores, donde los nombres de las columnas son las claves

        val values = ContentValues().apply {
            put(Drink.COLUMN_NAME, drink.strDrink)
            put(Drink.COLUMN_IMAGE, drink.strDrinkThumb)
            put(Drink.COLUMN_ID, drink.idDrink)
        }

        try{
            val updatedRows = db.update(Drink.TABLE_NAME, values, "${Drink.COLUMN_ID} = ${drink.idDrink}", null)
            Log.i("DATABASE", "Updated task with id: ${drink.idDrink}")
        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("DATABASE", "Error updating ocktail: ${e.message}")
        } finally {
            db.close()
        }
    }

    fun delete(drink: Drink) {
        val db = databaseManager.writableDatabase

        try {
            val deletedRows = db.delete(Drink.TABLE_NAME, "${Drink.COLUMN_ID} = '${drink.idDrink}'", null)

            Log.i("DATABASE", "Deleted drink with id: ${drink.idDrink}")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun findById(id: String): Drink? {
        // obtenemos la base de datos en modo lectura
        val db = databaseManager.readableDatabase

        val projection = arrayOf(// aqui ponemos las columnas que queremos obtener
            Drink.COLUMN_ID,
            Drink.COLUMN_NAME,
            Drink.COLUMN_IMAGE
        )
        val selection = "${Drink.COLUMN_ID} = '${id}'"
        
        var drink: Drink? = null

        try {
            val cursor = db.query(
                Drink.TABLE_NAME, // nombre de la tabla
                projection,// aqui ponemos las columnas que queremos obtener
                selection,// aqui ponemos la clausula where
                null,// aqui ponemos los argumentos de la clausula where
                null,// no agrupamos
                null,// no filtramos por grupos
                null// no ordenamos
            )
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Drink.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(Drink.COLUMN_NAME))
                val image = cursor.getString(cursor.getColumnIndexOrThrow(Drink.COLUMN_IMAGE))

                drink = Drink(id.toString(), name, image)// he creado una data class solo para probar esto !!!!


            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error getting-findbyId ocktail: ${e.message}")
        } finally {
            db.close()
        }
        return drink

    }
    fun findAll(): List<Drink> {
        // obtenemos la base de datos en modo lectura
        val db = databaseManager.readableDatabase
        val projection = arrayOf(// aqui ponemos las columnas que queremos obtener
            Drink.COLUMN_ID,
            Drink.COLUMN_NAME,
            Drink.COLUMN_IMAGE
        )
        var favoritesList: MutableList<Drink> = mutableListOf() // ESTO ES MUTABLE ???   si

        try {
            val cursor = db.query(
                Drink.TABLE_NAME, // nombre de la tabla
                projection,// aqui ponemos las columnas que queremos obtener
                null,// aqui ponemos la clausula where
                null,// aqui ponemos los argumentos de la clausula where
                null,// no agrupamos
                null,// no filtramos por grupos
                null// no ordenamos
            )
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Drink.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(Drink.COLUMN_NAME))
                val image = cursor.getString(cursor.getColumnIndexOrThrow(Drink.COLUMN_IMAGE))

                val drink = Drink(id.toString(), name, image)
                favoritesList.add(drink)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error getting-findAll ocktail: ${e.message}")
            }
        finally {
            db.close()
        }


        return favoritesList

    }


}
