package com.example.retakeexamapp.service

import com.example.retakeexamapp.models.AllDrinksNetworkResponse
import com.example.retakeexamapp.models.CocktailNetworkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailService {
    @GET("filter.php?c=Cocktail")
   fun getCocktailsList(): Call<AllDrinksNetworkResponse>

    @GET("lookup.php?")
    fun getCocktailDetails(@Query("i") id: String) : Call<CocktailNetworkResponse>
}