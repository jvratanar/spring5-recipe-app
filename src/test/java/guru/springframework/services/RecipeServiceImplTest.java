package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    private RecipeServiceImpl recipeService;

    @Mock   // set this field as a mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        // mock the recipeRepository
        MockitoAnnotations.initMocks(this);

        this.recipeService = new RecipeServiceImpl(this.recipeRepository);
    }

    @Test
    public void getRecipes() {
        // mock
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        when(this.recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = this.recipeService.getRecipes();

        assertEquals(1, recipes.size());
        // verify that findAll method has been called once
        verify(this.recipeRepository, times(1)).findAll();
    }
}