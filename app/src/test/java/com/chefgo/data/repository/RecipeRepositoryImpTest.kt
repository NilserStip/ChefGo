package com.chefgo.data.repository

import com.chefgo.data.ChefGoClient
import com.chefgo.data.datasource.remote.mapper.RecipeMapper
import com.chefgo.data.datasource.remote.source.RemoteRecipeDataSource
import com.chefgo.domain.model.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class RecipeRepositoryImpTest {

    @Mock
    private lateinit var chefGoClient: ChefGoClient

    private lateinit var recipeRepository: RecipeRepositoryImp

    private val client = ChefGoClient()
    private val remoteRecipeDataSource = RemoteRecipeDataSource(client)
    private val recipeMapper = RecipeMapper()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        recipeRepository = RecipeRepositoryImp(remoteRecipeDataSource, recipeMapper)
    }

    @Test
    fun testGetEmptyRecipes() = runBlocking {
        val expectedRepositories = ArrayList<Recipe>()

        // Mock the API response
        Mockito.`when`(recipeRepository.getRecipes()).thenReturn(expectedRepositories)

        // Call the method under test
        val result = recipeRepository.getRecipes()

        // Verify that the API method is called with the correct username
        Mockito.verify(recipeRepository).getRecipes()

        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetExpectedRepositories() = runBlocking {
        val expectedRepositories = arrayListOf(
            Recipe("0001", "https://example.com/avatar1.jpg", "Receta 1", "descripción N. 1", "-1000, -1200"),
            Recipe("0002", "https://example.com/avatar2.jpg", "Receta 2", "descripción N. 1", "-1000, -1200")
        )

        // Mock the API response
        Mockito.`when`(recipeRepository.getRecipes()).thenReturn(expectedRepositories)

        // Call the method under test
        val result = recipeRepository.getRecipes()

        // Verify that the API method is called with the correct username
        Mockito.verify(recipeRepository).getRecipes()

        // Verify that the result matches the expected repositories
        assert(2 == result.size)
    }

}