package com.example.retakeexamapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retakeexamapp.adapter.CocktailAdapter
import com.example.retakeexamapp.database.CocktailDatabase
import com.example.retakeexamapp.databinding.ActivityMainBinding
import com.example.retakeexamapp.factory.OverviewViewModelFactory
import com.example.retakeexamapp.repository.CocktailRepository
import com.example.retakeexamapp.service.CocktailService
import com.example.retakeexamapp.utility.Network
import com.example.retakeexamapp.viewmodel.OverviewViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var cocktailService: CocktailService

    private lateinit var cocktailRepository: CocktailRepository

    lateinit var overviewViewModel: OverviewViewModel

    lateinit var db: RoomDatabase

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        init()
        observeData()

        if (!Network.isInternetAvailable(this)) {
            Snackbar.make(
                binding.root,
                "No internet connection, information could be outdated",
                Snackbar.LENGTH_LONG
            ).show()
        }

        GlobalScope.launch {
            overviewViewModel.getCocktails()
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            CocktailDatabase::class.java,
            "cocktails_database"
        ).build()

        val currencyDao = (db as CocktailDatabase).cocktailDao()
        cocktailService = retrofit.create(CocktailService::class.java)
        cocktailRepository = CocktailRepository(this, cocktailService, currencyDao)
        val currencyViewModelFactory = OverviewViewModelFactory(cocktailRepository)
        overviewViewModel =
            ViewModelProvider(this, currencyViewModelFactory)[OverviewViewModel::class.java]
    }

    private fun observeData() {
        GlobalScope.launch {
            overviewViewModel.cocktailsList.collect {
                runOnUiThread {
                    var cocktails = it
                    cocktails = cocktails.sortedByDescending { it.favorite }
                    val adapter = CocktailAdapter(cocktails)
                    binding.cocktailsList.adapter = adapter
                }
            }
        }
    }
}