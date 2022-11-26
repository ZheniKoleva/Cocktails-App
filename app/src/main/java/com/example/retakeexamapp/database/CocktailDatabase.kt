package com.example.retakeexamapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retakeexamapp.database.dao.CocktailDao
import com.example.retakeexamapp.database.entity.Cocktail
import com.example.retakeexamapp.database.entity.CocktailGeneralData

@Database(entities = [CocktailGeneralData::class, Cocktail::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
}