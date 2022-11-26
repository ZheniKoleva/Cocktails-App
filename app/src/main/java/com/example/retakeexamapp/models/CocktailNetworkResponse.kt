package com.example.retakeexamapp.models

import com.example.retakeexamapp.database.entity.Cocktail

data class CocktailNetworkResponse(
    val drinks: List<CocktailResponse>
)

data class CocktailResponse(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strGlass: String?,
    val strInstructions: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
)

fun CocktailResponse.asCocktail(): Cocktail {
    return Cocktail(
        id = this.idDrink,
        name = this.strDrink,
        image = this.strDrinkThumb,
        glass = this.strGlass,
        instructions = this.strInstructions,
        ingredient1 = if (this.strIngredient1 != null && this.strMeasure1 != null)
            "${this.strMeasure1} ${this.strIngredient1}" else null,
        ingredient2 = if (this.strIngredient2 != null && this.strMeasure2 != null)
            "${this.strMeasure2} ${this.strIngredient2}" else null,
        ingredient3 = if (this.strIngredient3 != null && this.strMeasure3 != null)
            "${this.strMeasure3} ${this.strIngredient3}" else null,
        ingredient4 = if (this.strIngredient4 != null && this.strMeasure4 != null)
            "${this.strMeasure4} ${this.strIngredient4}" else null,
        ingredient5 = if (this.strIngredient5 != null && this.strMeasure5 != null)
            "${this.strMeasure5} ${this.strIngredient5}" else null,
        ingredient6 = if (this.strIngredient6 != null && this.strMeasure6 != null)
            "${this.strMeasure6} ${this.strIngredient6}" else null,
        ingredient7 = if (this.strIngredient7 != null && this.strMeasure7 != null)
            "${this.strMeasure7} ${this.strIngredient7}" else null,
        ingredient8 = if (this.strIngredient8 != null && this.strMeasure8 != null)
            "${this.strMeasure8} ${this.strIngredient8}" else null,
        ingredient9 = if (this.strIngredient9 != null && this.strMeasure9 != null)
            "${this.strMeasure9} ${this.strIngredient9}" else null,
        ingredient10 = if (this.strIngredient10 != null && this.strMeasure10 != null)
            "${this.strMeasure10} ${this.strIngredient10}" else null,
        ingredient11 = if (this.strIngredient11 != null && this.strMeasure11 != null)
            "${this.strMeasure11} ${this.strIngredient11}" else null,
        ingredient12 = if (this.strIngredient12 != null && this.strMeasure12 != null)
            "${this.strMeasure12} ${this.strIngredient12}" else null,
        ingredient13 = if (this.strIngredient13 != null && this.strMeasure13 != null)
            "${this.strMeasure13} ${this.strIngredient13}" else null,
        ingredient14 = if (this.strIngredient14 != null && this.strMeasure14 != null)
            "${this.strMeasure14} ${this.strIngredient14}" else null,
        ingredient15 = if (this.strIngredient15 != null && this.strMeasure15 != null)
            "${this.strMeasure15} ${this.strIngredient15}" else null,
        favorite = false
    )
}
