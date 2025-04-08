package com.example.proytectmine.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {
//    @GET("search/{name}")
//    suspend fun findSuperheroesByName(@Path("name") query: String): CocktailResponse
//
//    @GET("lookup.php?i={cocktail-id}")
//    suspend fun findCocktailById(@Path("cocktail-id") id: String): CocktailResponse
    @GET("lookup.php")
    suspend fun findCocktailIngredientsById(@Query("iid") id: String): CocktailResponse
    @GET("search.php")
    suspend fun  findCocktailByName(@Query("s") name: String): CocktailResponse
}
//www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552
//lookup.php?iid=552

//www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
//search.php?s=margarita