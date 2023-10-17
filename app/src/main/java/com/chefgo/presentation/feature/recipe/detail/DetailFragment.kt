package com.chefgo.presentation.feature.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chefgo.R
import com.chefgo.presentation.base.BaseFragment
import com.chefgo.databinding.FragmentDetailBinding
import com.chefgo.presentation.feature.main.AppConstants
import com.chefgo.domain.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private lateinit var _data: Recipe

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        getParams()

        return binding.root
    }

    private fun getParams() {
        _data = Gson().fromJson(
            arguments?.getString(AppConstants.ARG_ITEM_RECIPE),
            object : TypeToken<Recipe>() {}.type
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(mContext!!).load(_data.logo.trim { it <= ' ' }).centerCrop()
                .placeholder(R.drawable.loading).into(imageRecipe)
            textViewPeople.text = _data.people.toString()
            textViewName.text = capitalize(_data.name)
            textviewDescription.text = capitalize(_data.description)
            textviewIngredients.text = _data.ingredients
            textviewPreparation.text = _data.preparation

            buttonMap.setOnClickListener {
                actionMap()
            }
        }
    }

    private fun actionMap() {
        val bundle = Bundle()
        bundle.putString(AppConstants.ARG_DATA_LOCATION, _data.location)
        findNavController().navigate(R.id.action_DetailFragment_to_MapsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}