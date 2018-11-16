package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.entities.Customer;
import de.it7n.pizzabuilder.factories.JsonFactory;
import de.it7n.pizzabuilder.services.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer login(@RequestParam String email, @RequestParam String password) {
		var customerService = IoC.resolveService(CustomerService.class);
		var customerOptional = customerService.findUser(email, hash(password));
		return customerOptional.orElse(null);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public String signup(@RequestBody Customer customer) {
		customer.setPassword(hash(customer.getPassword()));
		try {
			var service = IoC.resolveService(CustomerService.class);
			var email = customer.getEmail();
			if (!service.any(c -> c.getEmail() == email)) {
				service.create(customer);
				return JsonFactory.generateSuccess(201, "Kunde wurde erfolgreich erstellt.");
			}
			return JsonFactory.generateFailure(206, "Kunde existiert schon.");
		} catch (SQLException e) {
			e.printStackTrace();
			return JsonFactory.generateFailure(400, "SQLException");
		}
	}
}
