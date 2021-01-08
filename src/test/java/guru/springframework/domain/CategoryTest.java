package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before // before each test
    public void setUp() {
        this.category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;
        this.category.setId(idValue);

        assertEquals(idValue, this.category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}