package com.example.proytectmine.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {

    @GET("filter.php")
    suspend fun findCocktailByIngredients(@Query("i") i: String): CocktailResponseReduced

    //www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin
    @GET("lookup.php")
    suspend fun  findCocktailById(@Query("i") id: String): CocktailResponseFull
    @GET("search.php")
    suspend fun  findAllsCocktailsByFirstLetter(@Query("f") letter: String): CocktailResponseReduced
    @GET("search.php")
    suspend fun  findAllsCocktailsByName(@Query("s") name: String): CocktailResponseReduced?
    @GET("random.php")
    suspend fun findRandomCocktail(): CocktailResponseReduced
}
//www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552
//lookup.php?iid=552

//www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
//search.php?s=margarita
// buscar por nombre
//www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita