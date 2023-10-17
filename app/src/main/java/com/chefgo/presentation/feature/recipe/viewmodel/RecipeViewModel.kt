package com.chefgo.presentation.feature.recipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chefgo.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    val viewState: LiveData<RecipeViewState> get() = _viewState
    val loading: LiveData<Boolean> get() = _loading
    private val _viewState = MutableLiveData<RecipeViewState>()
    private val _loading = MutableLiveData<Boolean>()

    @OptIn(DelicateCoroutinesApi::class)
    fun getRecipes() {
        _loading.postValue(true)

        val handler = CoroutineExceptionHandler { _, _ ->
            _viewState.postValue(RecipeViewState.ShowError)
            _loading.postValue(false)
        }

        GlobalScope.launch(handler) {
            val recipes = getRecipesUseCase.invoke()
            _viewState.postValue(RecipeViewState.ShowList(recipes))
            _loading.postValue(false)
        }
    }

}