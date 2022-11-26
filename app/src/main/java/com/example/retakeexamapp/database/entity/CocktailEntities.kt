package com.example.retakeexamapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailGeneralData(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean
)

@Entity(tableName = "cocktails_info")
data class Cocktail(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "glass") var glass: String?,
    @ColumnInfo(name = "instructions") var instructions: String?,
    @ColumnInfo(name = "ingredient1") var ingredient1: String?,
    @ColumnInfo(name = "ingredient2") var ingredient2: String?,
    @ColumnInfo(name = "ingredient3") var ingredient3: String?,
    @ColumnInfo(name = "ingredient4") var ingredient4: String?,
    @ColumnInfo(name = "ingredient5") var ingredient5: String?,
    @ColumnInfo(name = "ingredient6") var ingredient6: String?,
    @ColumnInfo(name = "ingredient7") var ingredient7: String?,
    @ColumnInfo(name = "ingredient8") var ingredient8: String?,
    @ColumnInfo(name = "ingredient9") var ingredient9: String?,
    @ColumnInfo(name = "ingredient10") var ingredient10: String?,
    @ColumnInfo(name = "ingredient11") var ingredient11: String?,
    @ColumnInfo(name = "ingredient12") var ingredient12: String?,
    @ColumnInfo(name = "ingredient13") var ingredient13: String?,
    @ColumnInfo(name = "ingredient14") var ingredient14: String?,
    @ColumnInfo(name = "ingredient15") var ingredient15: String?,
    @ColumnInfo(name = "favorite") var favorite: Boolean
)

fun Cocktail.asCocktailGeneralData(): CocktailGeneralData {
    return CocktailGeneralData(
        id = this.id,
        name = this.name,
        image = this.image,
        favorite = this.favorite
    )
}