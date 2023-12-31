package com.chefgo.presentation.feature.recipe

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chefgo.R
import com.chefgo.domain.model.Recipe
import com.chefgo.presentation.base.BaseAdapter
import com.chefgo.databinding.ItemRecipeBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeAdapter @Inject constructor() :
    BaseAdapter<RecipeAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var listener: OnActionsListener? = null
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
                listener?.onClick(recipe)
            }
        }
    }

    fun setListener(listener: OnActionsListener) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(array: List<Recipe>) {
        data = ArrayList(array)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(text: String, allRecipes: ArrayList<Recipe>) {
        val optionsFilter: ArrayList<Recipe> = ArrayList()

        allRecipes.forEach {
            if (it.name.lowercase().contains(text.lowercase()) ||
                it.ingredients.contains(text.lowercase())
            )
                optionsFilter.add(it)
        }

        data = ArrayList(optionsFilter)
        notifyDataSetChanged()

        if (optionsFilter.isEmpty())
            listener?.showEmptyFilterList()
    }

    interface OnActionsListener {
        fun onClick(item: Recipe)
        fun showEmptyFilterList()
    }

    override fun getItemCount() = data.size
}