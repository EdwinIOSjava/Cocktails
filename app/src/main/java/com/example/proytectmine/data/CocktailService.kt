package com.example.proytectmine.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailService {
//    @GET("search/{name}")
//    suspend fun findSuperheroesByName(@Path("name") query: String): CocktailResponse
//
//    @GET("lookup.php?i={cocktail-id}")
//    suspend fun findCocktailById(@Path("cocktail-id") id: String): CocktailResponse
    @GET("lookup.php?iid={ingredient-id}")
    suspend fun findCocktailById(@Path("ingredient-id") id: String): CocktailResponse
}
//www.thecocktaildb.com/api/json/v1/1/lookup.php?iid=552
//lookup.php?iid=552
