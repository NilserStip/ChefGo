package com.chefgo.domain.usecase

import com.chefgo.data.repository.RecipeRepositoryImp
import com.chefgo.domain.model.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class GetRecipesUseCaseTest {

    @Mock
    private lateinit var recipeRepositoryImp: RecipeRepositoryImp

    lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun onBefore() {
        MockitoAnnotations.openMocks(this)
        getRecipesUseCase = GetRecipesUseCase(recipeRepositoryImp)
    }

    @Test
    fun testGetRecipesEmpty() = runBlocking {
        val expectedRepositories = ArrayList<Recipe>()

        // Mock the API response
        Mockito.`when`(recipeRepositoryImp.getRecipes()).thenReturn(expectedRepositories)

        // Call the method under test
        val result = getRecipesUseCase()

        // Verify that the API method is called with the correct
        Mockito.verify(recipeRepositoryImp).getRecipes()

        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetRecipesNull() = runBlocking {

        // Mock the API response
        Mockito.`when`(recipeRepositoryImp.getRecipes()).thenReturn(null)

        // Call the method under test
        val result = getRecipesUseCase()

        // Verify that the API method is called with the correct
        Mockito.verify(recipeRepositoryImp).getRecipes()

        // Verify that the result matches the expected repositories
        assert(result == null)
    }

    @Test
    fun testGetExpectedRecipes() = runBlocking {
        val expectedRecipes = arrayListOf(
            Recipe("0001", "https://example.com/avatar1.jpg", "Receta 1", "descripción N. 1","ingredientes N. 1", "preparación N. 1", 4, "-1000, -1200"),
            Recipe("0002", "https://example.com/avatar2.jpg", "Receta 2", "descripción N. 2", "ingredientes N. 2", "preparación N. 2", 2,"-1200, -1600")
        )

        // Mock the API response
        Mockito.`when`(recipeRepositoryImp.getRecipes()).thenReturn(expectedRecipes)

        // Call the method under test
        val result = getRecipesUseCase()

        // Verify that the API method is called with the correct
        Mockito.verify(recipeRepositoryImp).getRecipes()

        // Verify that the result matches the expected repositories
        assert(result == expectedRecipes)
    }
}