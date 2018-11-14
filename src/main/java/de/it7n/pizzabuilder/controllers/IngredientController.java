package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.entities.Ingredient;
import de.it7n.pizzabuilder.factories.JsonFactory;
import de.it7n.pizzabuilder.services.IngredientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Collin Alpert
 */
@RestController
public class IngredientController {

	@GetMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAll() {
		return JsonFactory.entityToJson(IoC.resolveService(IngredientService.class).getAll(Ingredient::getName));
	}
}
