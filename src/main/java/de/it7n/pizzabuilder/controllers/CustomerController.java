package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.entities.Customer;
import de.it7n.pizzabuilder.factories.JsonFactory;
import de.it7n.pizzabuilder.services.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * @author Collin Alpert
 */
@RestController
public class CustomerController {

	private static String hash(String password) {
		try {
			var md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			var sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer login(@RequestParam String userName, @RequestParam String password) {
		var customerService = IoC.resolveService(CustomerService.class);
		var customerOptional = customerService.findUser(userName, hash(password));
		return customerOptional.orElse(null);
	}

	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody Customer customer) {
		customer.setPassword(hash(customer.getPassword()));
		try {
			IoC.resolveService(CustomerService.class).create(customer);
			return JsonFactory.generateSuccess(201, "Kunde wurde erfolgreich erstellt.");
		} catch (SQLException e) {
			e.printStackTrace();
			return JsonFactory.generateFailure(400, "SQLException");
		}
	}
}
