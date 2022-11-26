package com.example.retakeexamapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retakeexamapp.repository.CocktailRepository
import com.example.retakeexamapp.viewmodel.OverviewViewModel

class OverviewViewModelFactory(
    private val cocktailRepository: CocktailRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OverviewViewModel(cocktailRepository) as T
    }
}
