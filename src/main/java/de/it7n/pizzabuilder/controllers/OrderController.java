package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.entities.LinkedOrderIngredient;
import de.it7n.pizzabuilder.entities.Order;
import de.it7n.pizzabuilder.factories.JsonFactory;
import de.it7n.pizzabuilder.requests.OrderRequest;
import de.it7n.pizzabuilder.services.CustomerService;
import de.it7n.pizzabuilder.services.LinkedOrderIngredientService;
import de.it7n.pizzabuilder.services.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author Collin Alpert
 */
@RestController
public class OrderController {

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/order")
	public String order(@RequestBody OrderRequest request) {
		var customerService = IoC.resolveService(CustomerService.class);
		var orderService = IoC.resolveService(OrderService.class);
		var linkedOrderIngredientService = IoC.resolveService(LinkedOrderIngredientService.class);
		try {
			var customer = customerService.getByEmail(request.getCustomerEmail());
			if (!customer.isPresent()) {
				return JsonFactory.generateFailure(404, "Kunde konnte nicht gefunden werden");
			}
			var orderId = orderService.create(new Order(request.getTotalPrice(), customer.get().getId()));
			var linkedOrder = new LinkedOrderIngredient(orderId);
			var ingredients = request.getIngs();
			linkedOrder.hasMozzarella(ingredients.getMozzarella() == 1);
			linkedOrder.hasOlive(ingredients.getOlive() == 1);
			linkedOrder.hasMushroom(ingredients.getMushroom() == 1);
			linkedOrder.hasPepperoni(ingredients.getPepperoni() == 1);
			linkedOrder.hasRedPepper(ingredients.getRedPepper() == 1);
			linkedOrder.hasGreenPepper(ingredients.getGreenPepper() == 1);
			linkedOrder.hasTomato(ingredients.getTomato() == 1);
			linkedOrderIngredientService.create(linkedOrder);
			return JsonFactory.generateSuccess(201, "Bestellung erfolgreich erstellt.");
		} catch (SQLException e) {
			e.printStackTrace();
			return JsonFactory.generateFailure(400, "SQLException");
		}
	}
}
