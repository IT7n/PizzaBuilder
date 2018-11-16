package de.it7n.pizzabuilder.services;

import com.github.collinalpert.java2db.services.BaseService;
import de.it7n.pizzabuilder.entities.Customer;

import java.util.Optional;

/**
 * @author Collin Alpert
 */
public class CustomerService extends BaseService<Customer> {

	public Optional<Customer> findUser(String email, String password) {
		return getSingle(customer -> customer.getEmail() == email && customer.getPassword() == password);
	}

	public Optional<Customer> getByEmail(String email) {
		return getSingle(customer -> customer.getEmail() == email);
	}
}
