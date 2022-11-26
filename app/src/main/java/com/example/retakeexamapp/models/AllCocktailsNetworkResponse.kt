package com.example.retakeexamapp.models

import com.example.retakeexamapp.database.entity.CocktailGeneralData

data class AllDrinksNetworkResponse(
    val drinks: List<DrinkResponse>
)

data class DrinkResponse(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)

fun DrinkResponse.asCocktailGeneralData(): CocktailGeneralData {
    return CocktailGeneralData(
        id = this.idDrink,
        name = this.strDrink,
        image = this.strDrinkThumb,
        favorite = false
    )
}