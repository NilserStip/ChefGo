package com.chefgo.presentation.feature.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chefgo.App
import com.chefgo.R
import com.chefgo.databinding.FragmentDetailBinding
import com.chefgo.domain.model.Recipe
import com.chefgo.presentation.base.BaseFragment
import com.chefgo.presentation.feature.main.AppConstants
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
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
        logEventViewItem()

        return binding.root
    }

    private fun getParams() {
        _data = Gson().fromJson(
            arguments?.getString(AppConstants.ARG_ITEM_RECIPE),
            object : TypeToken<Recipe>() {}.type
        )
    }

    private fun logEventViewItem() {
        App.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_LIST_ID, _data.id)
            param(FirebaseAnalytics.Param.ITEM_LIST_NAME, _data.name)
        }
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