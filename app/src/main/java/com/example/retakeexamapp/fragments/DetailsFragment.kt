package com.example.retakeexamapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.retakeexamapp.R
import com.example.retakeexamapp.activity.MainActivity
import com.example.retakeexamapp.databinding.FragmentDetailsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedCocktail = arguments?.getString("id", null)

        GlobalScope.launch {
            (activity as? MainActivity)?.overviewViewModel?.getCocktailByName(
                selectedCocktail ?: return@launch
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(LayoutInflater.from(context))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        GlobalScope.launch {
            (activity as? MainActivity)?.overviewViewModel?.selectedCocktail?.collect {
                binding.cocktail = it ?: return@collect

                (activity as? MainActivity)?.runOnUiThread {
                    setDataBinding()
                    Glide
                        .with(context ?: return@runOnUiThread)
                        .load(it.image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivImage)
                }
            }
        }
    }

    private fun setDataBinding() {
        binding.cocktail ?: return

        if (binding.cocktail?.favorite == true) {
            binding.btnLike.setImageResource(android.R.drawable.star_big_on)
        } else {
            binding.btnLike.setImageResource(android.R.drawable.star_big_off)
        }

        setVisibilityAndTextField(binding.tvIngredient1, binding.cocktail?.ingredient1)
        setVisibilityAndTextField(binding.tvIngredient2, binding.cocktail?.ingredient2)
        setVisibilityAndTextField(binding.tvIngredient3, binding.cocktail?.ingredient3)
        setVisibilityAndTextField(binding.tvIngredient4, binding.cocktail?.ingredient4)
        setVisibilityAndTextField(binding.tvIngredient5, binding.cocktail?.ingredient5)
        setVisibilityAndTextField(binding.tvIngredient6, binding.cocktail?.ingredient6)
        setVisibilityAndTextField(binding.tvIngredient7, binding.cocktail?.ingredient7)
        setVisibilityAndTextField(binding.tvIngredient8, binding.cocktail?.ingredient8)
        setVisibilityAndTextField(binding.tvIngredient9, binding.cocktail?.ingredient9)
        setVisibilityAndTextField(binding.tvIngredient10, binding.cocktail?.ingredient10)
        setVisibilityAndTextField(binding.tvIngredient11, binding.cocktail?.ingredient11)
        setVisibilityAndTextField(binding.tvIngredient12, binding.cocktail?.ingredient12)
        setVisibilityAndTextField(binding.tvIngredient13, binding.cocktail?.ingredient13)
        setVisibilityAndTextField(binding.tvIngredient14, binding.cocktail?.ingredient14)
        setVisibilityAndTextField(binding.tvIngredient15, binding.cocktail?.ingredient15)

        binding.btnLike.setOnClickListener {
            val cocktail = binding.cocktail

            cocktail?.favorite = cocktail?.favorite != true

            if (cocktail?.favorite == true) {
                binding.btnLike.setImageResource(android.R.drawable.star_big_on)
            } else {
                binding.btnLike.setImageResource(android.R.drawable.star_big_off)
            }

            GlobalScope.launch {
                (activity as? MainActivity)?.overviewViewModel?.setAsFavorite(
                    binding.cocktail ?: return@launch
                )
            }
        }
    }

    private fun setVisibilityAndTextField(textView: TextView, ingredient: String?) {

        if (ingredient.isNullOrBlank()) {
            textView.visibility = View.GONE
        } else {
            textView.text = ingredient
        }
    }
}
