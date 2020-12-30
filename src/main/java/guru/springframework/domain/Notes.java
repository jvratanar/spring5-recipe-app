package guru.springframework.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Not specifying cascade as the Recipe owns the Notes entity. We do not
    // want the changes to Notes to affect Recipe
    @OneToOne
    private Recipe recipe;

    @Lob // to store larger (> 255) strings to db
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
