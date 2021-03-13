package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter,
                                 IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryCommandConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setNotes(this.notesConverter.convert(source.getNotes()));
        recipeCommand.setImage(source.getImage());
        recipeCommand.setDifficulty(source.getDifficulty());

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(
                    category -> recipeCommand.getCategories().add(this.categoryConverter.convert(category))
            );
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(
                    ingredient -> recipeCommand.getIngredients().add(this.ingredientConverter.convert(ingredient))
            );
        }

        return recipeCommand;
    }
}
