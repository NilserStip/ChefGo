package com.chefgo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.chefgo.base.BaseFragment
import com.chefgo.databinding.FragmentRecipeBinding
import com.chefgo.ui.model.Recipe

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : BaseFragment(), RecipeAdapter.OnClickListener, RecipeView {

    private var _binding: FragmentRecipeBinding? = null
    private var _presenter = RecipePresenter()
    private var _adapter = RecipeAdapter(this)
    private var _data = ArrayList<Recipe>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)

        initParams()
        initUI()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            srlContent.setOnRefreshListener {
                getRecipes
                binding.srlContent.isRefreshing = false
            }
            root.setOnClickListener {
                hideKeyboard()
            }

            tilSearch.editText!!.addTextChangedListener {
                if (it != null)
                    _adapter.filter(it.toString(), _data)
            }
        }
    }

    private val getRecipes: Unit
        get() {
            _presenter.getRecipes()
        }

    private fun initParams() {
        _presenter.addView(this)
        getRecipes
    }

    private fun initUI() {
        loadGridLayout(adapter = _adapter, recyclerView = binding.recyclerRecipes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(item: Recipe) {
    }

    override fun showLoading() {
        binding.apply {
            recyclerRecipes.visibility = View.GONE
            shimmerRecipes.startShimmer()
            shimmerRecipes.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        binding.apply {
            recyclerRecipes.visibility = View.VISIBLE
            shimmerRecipes.stopShimmer()
            shimmerRecipes.visibility = View.GONE
        }
    }

    override fun getRecipesSuccess(data: ArrayList<Recipe>) {
        _data = ArrayList(data)
        _adapter.setList(data)
        updateSateRecycler(binding.recyclerRecipes, data, binding.iListEmpty.root)
    }

    override fun getRecipesFailure() {
        updateSateRecycler(binding.recyclerRecipes, binding.iListEmpty.root)
    }

}