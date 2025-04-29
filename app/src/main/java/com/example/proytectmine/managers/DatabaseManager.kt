package com.example.proytectmine.managers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proytectmine.data.Drink

class DatabaseManager(context: Context): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object{
        const val DATABASE_NAME = "favorites.db"
        const val DATABASE_VERSION = 1
    }

    //crear la tabla favorites
    private const val SQL_CREATE_TABLE_FAVORITES =
        "CREATE TABLE ${Drink.TABLE_NAME}("+
                "${Drink.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "${Drink.COLUMN_NAME} TEXT, "+
                "${Drink.COLUMN_IMAGE} TEXT)"
    private const val SQL_DROP_TABLE_FAVORITES ="DROP TABLE IF EXISTS ${Drink.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITES)
        Log.i("DATABASE", "Created table FAVORITES")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       onDestroy(db)
        onCreate(db) // hacer esto elimina por copleto la tabla y la vuelve a crear
    }

    override fun onDestroy(db: SQLiteDatabase) {
        db.execSQL(SQL_DROP_TABLE_FAVORITES)
        Log.i("DATABASE", "Deleted table FAVORITES")

    }
}