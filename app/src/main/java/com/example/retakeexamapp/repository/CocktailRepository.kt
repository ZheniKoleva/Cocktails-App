package com.example.retakeexamapp.repository

import android.content.Context
import com.example.retakeexamapp.database.dao.CocktailDao
import com.example.retakeexamapp.database.entity.Cocktail
import com.example.retakeexamapp.database.entity.CocktailGeneralData
import com.example.retakeexamapp.database.entity.asCocktailGeneralData
import com.example.retakeexamapp.models.asCocktail
import com.example.retakeexamapp.models.asCocktailGeneralData
import com.example.retakeexamapp.service.CocktailService
import com.example.retakeexamapp.utility.Network

class CocktailRepository constructor(
    private val context: Context,
    private val cocktailService: CocktailService,
    private val cocktailDao: CocktailDao
) {

    suspend fun getAllCocktails(): List<CocktailGeneralData> {
        return try {
            if (Network.isInternetAvailable(context)) {
                val cocktails = cocktailService.getCocktailsList().execute().body()
                val mappedCocktails = cocktails?.drinks?.map { it.asCocktailGeneralData() }
                cocktailDao.insertCocktailsGeneralData(mappedCocktails ?: return arrayListOf())
            }

            cocktailDao.getAllCocktailsGeneralData()
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    suspend fun getCocktailById(id: String): Cocktail? {
        return try {
            if (Network.isInternetAvailable(context)) {
                val cocktail = cocktailService.getCocktailDetails(id).execute().body()
                val mappedCocktail = cocktail?.drinks?.map { it.asCocktail() }?.first()
                cocktailDao.insertCocktailData(mappedCocktail ?: return null)
            }
            cocktailDao.getCocktailByName(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun setFavorite(cocktail: Cocktail) {
        cocktailDao.updateCocktailData(cocktail)
        cocktailDao.updateGeneralData(cocktail.asCocktailGeneralData())
    }
}