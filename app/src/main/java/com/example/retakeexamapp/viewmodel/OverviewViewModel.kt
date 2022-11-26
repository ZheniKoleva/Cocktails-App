package com.example.retakeexamapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.retakeexamapp.database.entity.Cocktail
import com.example.retakeexamapp.database.entity.CocktailGeneralData
import com.example.retakeexamapp.repository.CocktailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OverviewViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    private val cocktailsListStateFlow = MutableStateFlow<List<CocktailGeneralData>>(arrayListOf())
    val cocktailsList: StateFlow<List<CocktailGeneralData>> = cocktailsListStateFlow.asStateFlow()

    private val selectedCocktailStateFlow = MutableStateFlow<Cocktail?>(null)
    val selectedCocktail: StateFlow<Cocktail?> = selectedCocktailStateFlow.asStateFlow()

    suspend fun getCocktails() {
        var cocktails = cocktailRepository.getAllCocktails()
        cocktailsListStateFlow.value = cocktails
    }

    suspend fun getCocktailByName(id: String) {
        val cocktail = cocktailRepository.getCocktailById(id)
        selectedCocktailStateFlow.value = cocktail ?: return
    }

    suspend fun setAsFavorite(cocktail: Cocktail) {
        cocktailRepository.setFavorite(cocktail)
        selectedCocktailStateFlow.update {
            it?.copy(favorite = cocktail.favorite)
        }
    }
}