package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    // Recipe is owner - cascade, Ingredient entity must have property recipe
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<>();

    @Lob // Large OBject field - blob
    private Byte[] image;

    // It is desirable to set the enum values to String. The value
    // is then stored in the db. If we insert some new enum between
    // the two, the enums after if will not have the same values but
    // incremented by one. This leaves us with wrong values stored in
    // the db for enums as there are old ones, not the one incremented.
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // cascade definition specifies the Recipe to own the Notes e.g. if Recipe is
    // deleted then delete the Notes entity as well.
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // Encapsulates setting of bidirectional relation
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }
}
