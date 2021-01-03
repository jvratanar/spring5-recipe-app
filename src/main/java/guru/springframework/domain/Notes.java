package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
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
}
