package com.example.proytectmine.managers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proytectmine.data.DrinkFull

class DatabaseManager(context: Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "favorites.db"
        const val DATABASE_VERSION = 2// seguramente tengo que cambiar la version de la tabla al poner todos los datos


    //crear la tabla favorites
    private const val SQL_CREATE_TABLE_FAVORITES =
        "CREATE TABLE ${DrinkFull.TABLE_NAME}(" +
                "${DrinkFull.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DrinkFull.COLUMN_NAME} TEXT, " +
                "${DrinkFull.COLUMN_ALTERNATE} TEXT, " +
                "${DrinkFull.COLUMN_TAGS} TEXT, " +
                "${DrinkFull.COLUMN_VIDEO} TEXT, " +
                "${DrinkFull.COLUMN_CATEGORY} TEXT, " +
                "${DrinkFull.COLUMN_IBA} TEXT, " +
                "${DrinkFull.COLUMN_ALCOHOLIC} TEXT, " +
                "${DrinkFull.COLUMN_GLASS} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_ES} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_DE} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_FR} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_IT} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANS} TEXT, " +
                "${DrinkFull.COLUMN_INSTRUCTIONS_ZH_HANT} TEXT, " +
                "${DrinkFull.COLUMN_IMAGE} TEXT, " +
                "${DrinkFull.COLUMN_IMAGE_SOURCE} TEXT, " +
                "${DrinkFull.COLUMN_IMAGE_ATTRIBUTION} TEXT, " +
                "${DrinkFull.COLUMN_CC_CONFIRMED} TEXT, " +
                "${DrinkFull.COLUMN_DATE_MODIFIED} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT1} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE1} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT2} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE2} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT3} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE3} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT4} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE4} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT5} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE5} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT6} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE6} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT7} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE7} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT8} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE8} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT9} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE9} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT10} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE10} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT11} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE11} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT12} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE12} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT13} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE13} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT14} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE14} TEXT, " +
                "${DrinkFull.COLUMN_INGREDIENT15} TEXT, " +
                "${DrinkFull.COLUMN_MEASURE15} TEXT)"

        private const val SQL_DROP_TABLE_FAVORITES = "DROP TABLE IF EXISTS ${DrinkFull.TABLE_NAME}"
}


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            onDestroy(db)
            onCreate(db) // hacer esto elimina por copleto la tabla y la vuelve a crear
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITES)
        Log.i("DATABASE", "Created table FAVORITES")
    }

     fun onDestroy(db: SQLiteDatabase) {
        db.execSQL(SQL_DROP_TABLE_FAVORITES)
        Log.i("DATABASE", "Deleted table FAVORITES")

    }
}