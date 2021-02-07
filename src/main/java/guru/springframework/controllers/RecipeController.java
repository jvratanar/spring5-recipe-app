package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", this.recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String showById(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping()
    @RequestMapping("recipe") //or with @PostMapping
    // Bind the form post parameters to the RecipeCommand,
    // hence @ModelAttribute
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = this.recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }
}
