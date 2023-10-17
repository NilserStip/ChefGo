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
import com.chefgo.R
import com.chefgo.databinding.FragmentRecipeBinding
import com.chefgo.domain.model.Recipe
import com.chefgo.presentation.base.BaseFragment
import com.chefgo.presentation.feature.main.AppConstants
import com.chefgo.presentation.feature.recipe.viewmodel.RecipeViewModel
import com.chefgo.presentation.feature.recipe.viewmodel.RecipeViewState
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class RecipeFragment : BaseFragment(), RecipeAdapter.OnClickListener {

    private var _binding: FragmentRecipeBinding? = null
    private lateinit var _viewModel: RecipeViewModel

    @set:Inject
    var adapter: RecipeAdapter? = null
    private var _data = ArrayList<Recipe>()
    var isReturned = false

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
                is RecipeViewState.ShowError -> {
                    getRecipesFailure()
                }
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
        val bundle = Bundle()
        bundle.putString(AppConstants.ARG_ITEM_RECIPE, Gson().toJson(item))
        findNavController().navigate(R.id.action_RecipeFragment_to_DetailFragment, bundle)
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
        _data = ArrayList(data)
        adapter?.setList(data)
        updateSateRecycler(binding.recyclerRecipes, _data, binding.iListEmpty.root)
    }

    private fun getRecipesFailure() {
        val constraints = binding.iListEmpty
        constraints.tViewTitle.text = getString(R.string.error_service)
        updateSateRecycler(binding.recyclerRecipes, constraints.root)
    }


    override fun onStop() {
        super.onStop()
        isReturned = true
    }

}