package com.example.retakeexamapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retakeexamapp.R
import com.example.retakeexamapp.activity.MainActivity
import com.example.retakeexamapp.database.entity.CocktailGeneralData
import com.example.retakeexamapp.databinding.OverviewListItemBinding
import com.example.retakeexamapp.fragments.DetailsFragment

class CocktailAdapter(private val cocktails: List<CocktailGeneralData>) :
    RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {

    class CocktailViewHolder(val binding: OverviewListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OverviewListItemBinding.inflate(layoutInflater, parent, false)

        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktails[position]
        holder.binding.apply {
            this.cocktail = currentCocktail

            ivLiked.visibility = if (currentCocktail.favorite) View.VISIBLE else View.GONE

            Glide
                .with(root.context)
                .load(currentCocktail.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivImage)

            holder.binding.root.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.commit {
                    val bundle = Bundle()
                    bundle.putString("id", currentCocktail.id)
                    replace(R.id.fragment_view, DetailsFragment::class.java, bundle)
                    addToBackStack("cocktails_list_to_details")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }
}