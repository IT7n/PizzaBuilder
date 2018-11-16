package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.requests.OrderRequest;
import de.it7n.pizzabuilder.services.CustomerService;
import de.it7n.pizzabuilder.services.OrderService;
import de.it7n.pizzabuilder.services.PizzaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		var pizzaService = IoC.resolveService(PizzaService.class);
		var customer = customerService.getByEmail(request.getCustomerEmail());
		return null;
	}
}
