package com.example.proytectmine.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.proytectmine.managers.DatabaseManager

class FavoritesDAO(context: Context) {
    private val databaseManager = DatabaseManager(context)

    fun insert(drink: DrinkFull) {
        //insertar en la base de datos
        val db = databaseManager.writableDatabase

        //crear un mapa de valores, donde los nombres de las columnas son las claves
        val values = ContentValues().apply {
            put(DrinkFull.COLUMN_ID, drink.idDrink)
            put(DrinkFull.COLUMN_NAME, drink.strDrink)
            put(DrinkFull.COLUMN_ALTERNATE, drink.strDrinkAlternate)
            put(DrinkFull.COLUMN_TAGS, drink.strTags)
            put(DrinkFull.COLUMN_VIDEO, drink.strVideo)
            put(DrinkFull.COLUMN_CATEGORY, drink.strCategory)
            put(DrinkFull.COLUMN_IBA, drink.strIBA)
            put(DrinkFull.COLUMN_ALCOHOLIC, drink.strAlcoholic)
            put(DrinkFull.COLUMN_GLASS, drink.strGlass)
            put(DrinkFull.COLUMN_INSTRUCTIONS, drink.strInstructions)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ES, drink.strInstructionsES)
            put(DrinkFull.COLUMN_INSTRUCTIONS_DE, drink.strInstructionsDE)
            put(DrinkFull.COLUMN_INSTRUCTIONS_FR, drink.strInstructionsFR)
            put(DrinkFull.COLUMN_INSTRUCTIONS_IT, drink.strInstructionsIT)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS, drink.strInstructionsZH_HANS)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT, drink.strInstructionsZH_HANT)
            put(DrinkFull.COLUMN_IMAGE, drink.strDrinkThumb)
            put(DrinkFull.COLUMN_INGREDIENT1, drink.strIngredient1)
            put(DrinkFull.COLUMN_INGREDIENT2, drink.strIngredient2)
            put(DrinkFull.COLUMN_INGREDIENT3, drink.strIngredient3)
            put(DrinkFull.COLUMN_INGREDIENT4, drink.strIngredient4)
            put(DrinkFull.COLUMN_INGREDIENT5, drink.strIngredient5)
            put(DrinkFull.COLUMN_INGREDIENT6, drink.strIngredient6)
            put(DrinkFull.COLUMN_INGREDIENT7, drink.strIngredient7)
            put(DrinkFull.COLUMN_INGREDIENT8, drink.strIngredient8)
            put(DrinkFull.COLUMN_INGREDIENT9, drink.strIngredient9)
            put(DrinkFull.COLUMN_INGREDIENT10, drink.strIngredient10)
            put(DrinkFull.COLUMN_INGREDIENT11, drink.strIngredient11)
            put(DrinkFull.COLUMN_INGREDIENT12, drink.strIngredient12)
            put(DrinkFull.COLUMN_INGREDIENT13, drink.strIngredient13)
            put(DrinkFull.COLUMN_INGREDIENT14, drink.strIngredient14)
            put(DrinkFull.COLUMN_INGREDIENT15, drink.strIngredient15)
            put(DrinkFull.COLUMN_MEASURE1, drink.strMeasure1)
            put(DrinkFull.COLUMN_MEASURE2, drink.strMeasure2)
            put(DrinkFull.COLUMN_MEASURE3, drink.strMeasure3)
            put(DrinkFull.COLUMN_MEASURE4, drink.strMeasure4)
            put(DrinkFull.COLUMN_MEASURE5, drink.strMeasure5)
            put(DrinkFull.COLUMN_MEASURE6, drink.strMeasure6)
            put(DrinkFull.COLUMN_MEASURE7, drink.strMeasure7)
            put(DrinkFull.COLUMN_MEASURE8, drink.strMeasure8)
            put(DrinkFull.COLUMN_MEASURE9, drink.strMeasure9)
            put(DrinkFull.COLUMN_MEASURE10, drink.strMeasure10)
            put(DrinkFull.COLUMN_MEASURE11, drink.strMeasure11)
            put(DrinkFull.COLUMN_MEASURE12, drink.strMeasure12)
            put(DrinkFull.COLUMN_MEASURE13, drink.strMeasure13)
            put(DrinkFull.COLUMN_MEASURE14, drink.strMeasure14)
            put(DrinkFull.COLUMN_MEASURE15, drink.strMeasure15)
            put(DrinkFull.COLUMN_IMAGE_SOURCE, drink.strImageSource)
            put(DrinkFull.COLUMN_IMAGE_ATTRIBUTION, drink.strImageAttribution)
            put(DrinkFull.COLUMN_CC_CONFIRMED, drink.strCreativeCommonsConfirmed)
            put(DrinkFull.COLUMN_DATE_MODIFIED, drink.dateModified)
        }

        try {
            //insertar el nuevo registro, devolviendo el id del nuevo registro
            val newRowId = db.insert(DrinkFull.TABLE_NAME, null, values)
            Log.i("DATABASE", "Inserted favorite with id: $newRowId")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error insert ocktail: ${e.message}")

        } finally {
            db.close()// cerrar la base de datos
        }
    }

    fun update(drink: DrinkFull) {
        // obtenemos la base de datos en modo escritura
        val db = databaseManager.writableDatabase
        // creamos un mapa de valores, donde los nombres de las columnas son las claves

        val values = ContentValues().apply {
            put(DrinkFull.COLUMN_ID, drink.idDrink)
            put(DrinkFull.COLUMN_NAME, drink.strDrink)
            put(DrinkFull.COLUMN_ALTERNATE, drink.strDrinkAlternate)
            put(DrinkFull.COLUMN_TAGS, drink.strTags)
            put(DrinkFull.COLUMN_VIDEO, drink.strVideo)
            put(DrinkFull.COLUMN_CATEGORY, drink.strCategory)
            put(DrinkFull.COLUMN_IBA, drink.strIBA)
            put(DrinkFull.COLUMN_ALCOHOLIC, drink.strAlcoholic)
            put(DrinkFull.COLUMN_GLASS, drink.strGlass)
            put(DrinkFull.COLUMN_INSTRUCTIONS, drink.strInstructions)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ES, drink.strInstructionsES)
            put(DrinkFull.COLUMN_INSTRUCTIONS_DE, drink.strInstructionsDE)
            put(DrinkFull.COLUMN_INSTRUCTIONS_FR, drink.strInstructionsFR)
            put(DrinkFull.COLUMN_INSTRUCTIONS_IT, drink.strInstructionsIT)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS, drink.strInstructionsZH_HANS)
            put(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT, drink.strInstructionsZH_HANT)
            put(DrinkFull.COLUMN_IMAGE, drink.strDrinkThumb)
            put(DrinkFull.COLUMN_INGREDIENT1, drink.strIngredient1)
            put(DrinkFull.COLUMN_INGREDIENT2, drink.strIngredient2)
            put(DrinkFull.COLUMN_INGREDIENT3, drink.strIngredient3)
            put(DrinkFull.COLUMN_INGREDIENT4, drink.strIngredient4)
            put(DrinkFull.COLUMN_INGREDIENT5, drink.strIngredient5)
            put(DrinkFull.COLUMN_INGREDIENT6, drink.strIngredient6)
            put(DrinkFull.COLUMN_INGREDIENT7, drink.strIngredient7)
            put(DrinkFull.COLUMN_INGREDIENT8, drink.strIngredient8)
            put(DrinkFull.COLUMN_INGREDIENT9, drink.strIngredient9)
            put(DrinkFull.COLUMN_INGREDIENT10, drink.strIngredient10)
            put(DrinkFull.COLUMN_INGREDIENT11, drink.strIngredient11)
            put(DrinkFull.COLUMN_INGREDIENT12, drink.strIngredient12)
            put(DrinkFull.COLUMN_INGREDIENT13, drink.strIngredient13)
            put(DrinkFull.COLUMN_INGREDIENT14, drink.strIngredient14)
            put(DrinkFull.COLUMN_INGREDIENT15, drink.strIngredient15)
            put(DrinkFull.COLUMN_MEASURE1, drink.strMeasure1)
            put(DrinkFull.COLUMN_MEASURE2, drink.strMeasure2)
            put(DrinkFull.COLUMN_MEASURE3, drink.strMeasure3)
            put(DrinkFull.COLUMN_MEASURE4, drink.strMeasure4)
            put(DrinkFull.COLUMN_MEASURE5, drink.strMeasure5)
            put(DrinkFull.COLUMN_MEASURE6, drink.strMeasure6)
            put(DrinkFull.COLUMN_MEASURE7, drink.strMeasure7)
            put(DrinkFull.COLUMN_MEASURE8, drink.strMeasure8)
            put(DrinkFull.COLUMN_MEASURE9, drink.strMeasure9)
            put(DrinkFull.COLUMN_MEASURE10, drink.strMeasure10)
            put(DrinkFull.COLUMN_MEASURE11, drink.strMeasure11)
            put(DrinkFull.COLUMN_MEASURE12, drink.strMeasure12)
            put(DrinkFull.COLUMN_MEASURE13, drink.strMeasure13)
            put(DrinkFull.COLUMN_MEASURE14, drink.strMeasure14)
            put(DrinkFull.COLUMN_MEASURE15, drink.strMeasure15)
            put(DrinkFull.COLUMN_IMAGE_SOURCE, drink.strImageSource)
            put(DrinkFull.COLUMN_IMAGE_ATTRIBUTION, drink.strImageAttribution)
            put(DrinkFull.COLUMN_CC_CONFIRMED, drink.strCreativeCommonsConfirmed)
            put(DrinkFull.COLUMN_DATE_MODIFIED, drink.dateModified)
        }


        try {
            val updatedRows = db.update(
                DrinkFull.TABLE_NAME,
                values,
                "${DrinkFull.COLUMN_ID} = ${drink.idDrink}",
                null
            )
            Log.i("DATABASE", "Updated task with id: ${drink.idDrink}")
        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("DATABASE", "Error updating ocktail: ${e.message}")
        } finally {
            db.close()
        }
    }

    fun delete(drink: DrinkFull) {
        val db = databaseManager.writableDatabase

        try {
            val deletedRows =
                db.delete(DrinkFull.TABLE_NAME, "${DrinkFull.COLUMN_ID} = '${drink.idDrink}'", null)

            Log.i("DATABASE", "Deleted drink with id: ${drink.idDrink}")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun findById(id: String): DrinkFull? {
        // obtenemos la base de datos en modo lectura
        val db = databaseManager.readableDatabase

        val projection = arrayOf(// aqui ponemos las columnas que queremos obtener
            DrinkFull.COLUMN_ID,
            DrinkFull.COLUMN_NAME,
            DrinkFull.COLUMN_ALTERNATE,
            DrinkFull.COLUMN_TAGS,
            DrinkFull.COLUMN_VIDEO,
            DrinkFull.COLUMN_CATEGORY,
            DrinkFull.COLUMN_IBA,
            DrinkFull.COLUMN_ALCOHOLIC,
            DrinkFull.COLUMN_GLASS,
            DrinkFull.COLUMN_INSTRUCTIONS,
            DrinkFull.COLUMN_INSTRUCTIONS_ES,
            DrinkFull.COLUMN_INSTRUCTIONS_DE,
            DrinkFull.COLUMN_INSTRUCTIONS_FR,
            DrinkFull.COLUMN_INSTRUCTIONS_IT,
            DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS,
            DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT,
            DrinkFull.COLUMN_IMAGE,
            DrinkFull.COLUMN_INGREDIENT1,
            DrinkFull.COLUMN_INGREDIENT2,
            DrinkFull.COLUMN_INGREDIENT3,
            DrinkFull.COLUMN_INGREDIENT4,
            DrinkFull.COLUMN_INGREDIENT5,
            DrinkFull.COLUMN_INGREDIENT6,
            DrinkFull.COLUMN_INGREDIENT7,
            DrinkFull.COLUMN_INGREDIENT8,
            DrinkFull.COLUMN_INGREDIENT9,
            DrinkFull.COLUMN_INGREDIENT10,
            DrinkFull.COLUMN_INGREDIENT11,
            DrinkFull.COLUMN_INGREDIENT12,
            DrinkFull.COLUMN_INGREDIENT13,
            DrinkFull.COLUMN_INGREDIENT14,
            DrinkFull.COLUMN_INGREDIENT15,
            DrinkFull.COLUMN_MEASURE1,
            DrinkFull.COLUMN_MEASURE2,
            DrinkFull.COLUMN_MEASURE3,
            DrinkFull.COLUMN_MEASURE4,
            DrinkFull.COLUMN_MEASURE5,
            DrinkFull.COLUMN_MEASURE6,
            DrinkFull.COLUMN_MEASURE7,
            DrinkFull.COLUMN_MEASURE8,
            DrinkFull.COLUMN_MEASURE9,
            DrinkFull.COLUMN_MEASURE10,
            DrinkFull.COLUMN_MEASURE11,
            DrinkFull.COLUMN_MEASURE12,
            DrinkFull.COLUMN_MEASURE13,
            DrinkFull.COLUMN_MEASURE14,
            DrinkFull.COLUMN_MEASURE15,
            DrinkFull.COLUMN_IMAGE_SOURCE,
            DrinkFull.COLUMN_IMAGE_ATTRIBUTION,
            DrinkFull.COLUMN_CC_CONFIRMED,
            DrinkFull.COLUMN_DATE_MODIFIED
        )
        val selection = "${DrinkFull.COLUMN_ID} = '${id}'"
        /* val selection = "${DrinkFull.COLUMN_ID} = ?"
            val selectionArgs = arrayOf(id)*/

        var drink: DrinkFull? = null

        try {
            val cursor = db.query(
                DrinkFull.TABLE_NAME, // nombre de la tabla
                projection,// aqui ponemos las columnas que queremos obtener
                selection,// aqui ponemos la clausula where
                null,// aqui ponemos los argumentos de la clausula where
                null,// no agrupamos
                null,// no filtramos por grupos
                null// no ordenamos
            )
            if (cursor.moveToFirst()) {
                drink = DrinkFull(
                    idDrink = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ID)),
                    strDrink = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_NAME)),
                    strDrinkAlternate = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ALTERNATE)),
                    strTags = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_TAGS)),
                    strVideo = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_VIDEO)),
                    strCategory = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_CATEGORY)),
                    strIBA = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IBA)),
                    strAlcoholic = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ALCOHOLIC)),
                    strGlass = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_GLASS)),
                    strInstructions = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS)),
                    strInstructionsES = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ES)),
                    strInstructionsDE = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_DE)),
                    strInstructionsFR = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_FR)),
                    strInstructionsIT = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_IT)),
                    strInstructionsZH_HANS = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS)),
                    strInstructionsZH_HANT = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT)),
                    strDrinkThumb = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE)),
                    strIngredient1 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT1)),
                    strIngredient2 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT2)),
                    strIngredient3 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT3)),
                    strIngredient4 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT4)),
                    strIngredient5 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT5)),
                    strIngredient6 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT6)),
                    strIngredient7 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT7)),
                    strIngredient8 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT8)),
                    strIngredient9 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT9)),
                    strIngredient10 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT10)),
                    strIngredient11 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT11)),
                    strIngredient12 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT12)),
                    strIngredient13 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT13)),
                    strIngredient14 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT14)),
                    strIngredient15 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT15)),
                    strMeasure1 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE1)),
                    strMeasure2 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE2)),
                    strMeasure3 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE3)),
                    strMeasure4 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE4)),
                    strMeasure5 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE5)),
                    strMeasure6 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE6)),
                    strMeasure7 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE7)),
                    strMeasure8 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE8)),
                    strMeasure9 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE9)),
                    strMeasure10 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE10)),
                    strMeasure11 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE11)),
                    strMeasure12 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE12)),
                    strMeasure13 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE13)),
                    strMeasure14 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE14)),
                    strMeasure15 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE15)),
                    strImageSource = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE_SOURCE)),
                    strImageAttribution = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE_ATTRIBUTION)),
                    strCreativeCommonsConfirmed = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_CC_CONFIRMED)),
                    dateModified = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_DATE_MODIFIED))
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error getting-findbyId ocktail: ${e.message}")
        } finally {
            db.close()
        }
        return drink
    }

    fun findAll(): List<DrinkFull> {
        val drinks = mutableListOf<DrinkFull>()
        val db = databaseManager.readableDatabase

        val cursor = db.query(
            DrinkFull.TABLE_NAME,
            null, // null para obtener todas las columnas
            null,
            null,
            null,
            null,
            null
        )

        try {
            if (cursor.moveToFirst()) {
                do {
                    val drink = DrinkFull(
                        idDrink = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ID)),
                        strDrink = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_NAME)),
                        strDrinkAlternate = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ALTERNATE)),
                        strTags = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_TAGS)),
                        strVideo = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_VIDEO)),
                        strCategory = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_CATEGORY)),
                        strIBA = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IBA)),
                        strAlcoholic = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_ALCOHOLIC)),
                        strGlass = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_GLASS)),
                        strInstructions = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS)),
                        strInstructionsES = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ES)),
                        strInstructionsDE = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_DE)),
                        strInstructionsFR = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_FR)),
                        strInstructionsIT = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_IT)),
                        strInstructionsZH_HANS = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS)),
                        strInstructionsZH_HANT = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT)),
                        strDrinkThumb = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE)),
                        strIngredient1 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT1)),
                        strIngredient2 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT2)),
                        strIngredient3 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT3)),
                        strIngredient4 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT4)),
                        strIngredient5 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT5)),
                        strIngredient6 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT6)),
                        strIngredient7 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT7)),
                        strIngredient8 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT8)),
                        strIngredient9 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT9)),
                        strIngredient10 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT10)),
                        strIngredient11 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT11)),
                        strIngredient12 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT12)),
                        strIngredient13 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT13)),
                        strIngredient14 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT14)),
                        strIngredient15 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_INGREDIENT15)),
                        strMeasure1 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE1)),
                        strMeasure2 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE2)),
                        strMeasure3 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE3)),
                        strMeasure4 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE4)),
                        strMeasure5 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE5)),
                        strMeasure6 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE6)),
                        strMeasure7 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE7)),
                        strMeasure8 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE8)),
                        strMeasure9 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE9)),
                        strMeasure10 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE10)),
                        strMeasure11 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE11)),
                        strMeasure12 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE12)),
                        strMeasure13 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE13)),
                        strMeasure14 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE14)),
                        strMeasure15 = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_MEASURE15)),
                        strImageSource = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE_SOURCE)),
                        strImageAttribution = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_IMAGE_ATTRIBUTION)),
                        strCreativeCommonsConfirmed = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_CC_CONFIRMED)),
                        dateModified = cursor.getString(cursor.getColumnIndexOrThrow(DrinkFull.COLUMN_DATE_MODIFIED))
                    )
                    drinks.add(drink)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("DATABASE", "Error fetching all cocktails: ${e.message}")
        } finally {
            cursor.close()
            db.close()
        }

        return drinks
    }



}
