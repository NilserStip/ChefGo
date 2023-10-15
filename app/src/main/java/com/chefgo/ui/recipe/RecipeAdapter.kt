package com.chefgo.ui.recipe

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chefgo.R
import com.chefgo.ui.model.Recipe
import com.chefgo.base.BaseAdapter
import com.chefgo.databinding.ItemRecipeBinding

class RecipeAdapter(private val listener: OnClickListener) :
    BaseAdapter<RecipeAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var data = ArrayList<Recipe>()

    class ViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemRecipeBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = data[position]

        holder.binding.apply {
            Glide.with(context).load(recipe.logo.trim { it <= ' ' }).centerCrop()
                .placeholder(R.drawable.loading).into(imageRecipe)
            textViewName.text = capitalize(recipe.name)

            root.setOnClickListener {
                listener.onClick(recipe)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(array: ArrayList<Recipe>) {
        data = ArrayList(array)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(text: String, allRecipes: ArrayList<Recipe>) {
        val optionsFilter: ArrayList<Recipe> = ArrayList()

        allRecipes.forEach {
            if (it.name.lowercase().contains(text.lowercase()) ||
                it.description.contains(text.lowercase())
            )
                optionsFilter.add(it)
        }

        data = ArrayList(optionsFilter)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(item: Recipe)
    }

    override fun getItemCount() = data.size
}