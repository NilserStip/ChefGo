package com.chefgo.presentation.feature.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chefgo.App
import com.chefgo.R
import com.chefgo.databinding.FragmentRecipeBinding
import com.chefgo.domain.model.Recipe
import com.chefgo.presentation.base.BaseFragment
import com.chefgo.presentation.feature.main.AppConstants
import com.chefgo.presentation.feature.recipe.viewmodel.RecipeViewModel
import com.chefgo.presentation.feature.recipe.viewmodel.RecipeViewState
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class RecipeFragment : BaseFragment(), RecipeAdapter.OnActionsListener {

    private var _binding: FragmentRecipeBinding? = null
    private lateinit var _viewModel: RecipeViewModel

    @set:Inject
    var adapter: RecipeAdapter? = null
    private var _data = ArrayList<Recipe>()
    private var isReturned = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)

        initParams()

        return binding.root
    }

    private fun initParams() {
        _viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        loadGridLayout()
        getRecipes
    }

    private fun loadGridLayout() {
        adapter?.setListener(this)
        binding.recyclerRecipes.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(mContext, 3)
        }
        binding.recyclerRecipes.adapter = adapter
        updateSateRecycler(binding.recyclerRecipes, view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.viewState.observe(this) { value ->
            when (value) {
                is RecipeViewState.ShowList -> showList(value.data)
                is RecipeViewState.ShowError -> showError()
                is RecipeViewState.ShowLoading -> showLoading()
                is RecipeViewState.HideLoading -> hideLoading()
            }
        }

        _viewModel.loading.observe(this) { value ->
            if (value)
                showLoading()
            else hideLoading()
        }

        binding.apply {
            srlContent.setOnRefreshListener {
                isReturned = false
                getRecipes
                binding.srlContent.isRefreshing = false
            }
            root.setOnClickListener {
                hideKeyboard()
            }

            tilSearch.editText!!.addTextChangedListener {
                if (it != null)
                    adapter?.filter(it.toString(), _data)
            }
        }
    }

    private val getRecipes: Unit
        get() {
            if (!isReturned)
                _viewModel.getRecipes()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(item: Recipe) {
        logEventSelectItem(item)

        val bundle = Bundle()
        bundle.putString(AppConstants.ARG_ITEM_RECIPE, Gson().toJson(item))
        findNavController().navigate(R.id.action_RecipeFragment_to_DetailFragment, bundle)
    }

    private fun logEventSelectItem(item: Recipe) {
        App.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_LIST_ID, item.id)
            param(FirebaseAnalytics.Param.ITEM_LIST_NAME, item.name)
        }
    }

    private fun logEventViewItemList(data: List<Recipe>) {
        val recipes: ArrayList<Bundle> = ArrayList()
        data.forEach {
            val recipe = Bundle()
            recipe.putString(FirebaseAnalytics.Param.ITEM_ID, it.id)
            recipe.putString(FirebaseAnalytics.Param.ITEM_NAME, it.name)
            recipe.putString(FirebaseAnalytics.Param.QUANTITY, it.people.toString())
            recipe.putString(
                FirebaseAnalytics.Param.VALUE,
                getString(if (it.active) R.string.fragment_recipe_visible else R.string.fragment_recipe_no_visible)
            )
        }
        val bundle = Bundle()
        bundle.putParcelableArrayList(FirebaseAnalytics.Param.ITEMS, recipes)
        App.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle)
    }

    private fun showLoading() {
        binding.apply {
            recyclerRecipes.visibility = View.GONE
            iListEmpty.root.visibility = View.GONE
            shimmerRecipes.startShimmer()
            shimmerRecipes.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        binding.apply {
            recyclerRecipes.visibility = View.VISIBLE
            shimmerRecipes.stopShimmer()
            shimmerRecipes.visibility = View.GONE
        }
    }

    private fun showList(data: List<Recipe>) {
        logEventViewItemList(data)

        _data = ArrayList(getActiveRecipes(data))
        adapter?.setList(_data)
        updateSateRecycler(binding.recyclerRecipes, _data, binding.iListEmpty.root)
    }

    private fun getActiveRecipes(data: List<Recipe>): List<Recipe> {
        return data.filter { it.active }
    }

    private fun showError() {
        val constraints = binding.iListEmpty
        constraints.tViewTitle.text = getString(R.string.error_service)
        updateSateRecycler(binding.recyclerRecipes, constraints.root)
    }

    override fun showEmptyFilterList() {
        val constraints = binding.iListEmpty
        constraints.tViewDescription.text = getString(R.string.view_list_empty_filter)
        updateSateRecycler(binding.recyclerRecipes, constraints.root)
    }


    override fun onStop() {
        super.onStop()
        isReturned = true
    }

}