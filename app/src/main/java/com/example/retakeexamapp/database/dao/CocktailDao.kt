package com.example.retakeexamapp.database.dao

import androidx.room.*
import com.example.retakeexamapp.database.entity.Cocktail
import com.example.retakeexamapp.database.entity.CocktailGeneralData

@Dao
interface CocktailDao {
    @Query("SELECT * FROM `cocktails` ORDER By favorite Desc, name")
    suspend fun getAllCocktailsGeneralData(): List<CocktailGeneralData>

    @Query("SELECT * FROM `cocktails_info` WHERE id=:id")
    suspend fun getCocktailByName(id: String): Cocktail

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktailsGeneralData(cocktails: List<CocktailGeneralData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktailData(cocktail: Cocktail)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGeneralData(cocktail: CocktailGeneralData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCocktailData(cocktail: Cocktail)
}