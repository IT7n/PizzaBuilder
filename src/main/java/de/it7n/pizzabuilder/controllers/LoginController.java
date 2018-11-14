package de.it7n.pizzabuilder.controllers;

import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.factories.JsonFactory;
import de.it7n.pizzabuilder.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Collin Alpert
 */
@RestController
public class LoginController {

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

	@GetMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password) {
		var customerService = IoC.resolveService(CustomerService.class);
		var customerOptional = customerService.findUser(userName, hash(password));
		if (customerOptional.isPresent()) {
			return JsonFactory.entityToJson(customerOptional.get());
		}
		return JsonFactory.generateFailure(404, "Kunde konnte nicht gefunden werden.");
	}
}
