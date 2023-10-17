package com.chefgo.presentation.feature.recipe.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chefgo.domain.model.Recipe
import com.chefgo.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class RecipeViewModelTest {

    @Mock
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    private lateinit var recipeViewModel: RecipeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        recipeViewModel = RecipeViewModel(getRecipesUseCase)
    }

    @Test
    fun testGetRecipesEmpty() = runTest {
        val expectedRepositories = ArrayList<Recipe>()

        // Mock the API response
        Mockito.`when`(getRecipesUseCase.invoke()).thenReturn(expectedRepositories)

        // Call the method under test
        recipeViewModel.getRecipes()

        // Verify that the API method is called with the correct
        Mockito.verify(getRecipesUseCase).invoke()

        // Verify that the result matches the expected repositories
        assert(recipeViewModel.viewState.value == expectedRepositories)
    }

    @Test
    fun testGetRecipesNullReturnEmpty() = runTest {
        val expectedRepositories = ArrayList<Recipe>()


        // Mock the API response
        Mockito.`when`(getRecipesUseCase()).thenReturn(null)

        // Call the method under test
        recipeViewModel.getRecipes()

        // Verify that the API method is called with the correct
        Mockito.verify(getRecipesUseCase).invoke()

        // Verify that the result matches the expected repositories
        assert(recipeViewModel.viewState.value == expectedRepositories)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }
}