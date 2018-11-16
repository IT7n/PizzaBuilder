package de.it7n.pizzabuilder;

import com.github.collinalpert.java2db.database.DBConnection;
import com.github.collinalpert.java2db.utilities.IoC;
import de.it7n.pizzabuilder.entities.Customer;
import de.it7n.pizzabuilder.entities.LinkedOrderIngredient;
import de.it7n.pizzabuilder.entities.Order;
import de.it7n.pizzabuilder.services.CustomerService;
import de.it7n.pizzabuilder.services.LinkedOrderIngredientService;
import de.it7n.pizzabuilder.services.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaBuilderApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(PizzaBuilderApplication.class, args);
		context.getBean(Instance.class).initialize();
	}

	@Bean
	Instance getInstance() {
		return new Instance();
	}

	private static class Instance {

		@Value("${database.host}")
		private String host;

		@Value("${database.name}")
		private String database;

		@Value("${database.user}")
		private String userName;

		@Value("${database.password}")
		private String password;

		public void initialize() {
			DBConnection.HOST = host;
			DBConnection.DATABASE = database;
			DBConnection.USERNAME = userName;
			DBConnection.PASSWORD = password;

			IoC.registerService(Customer.class, new CustomerService());
			IoC.registerService(LinkedOrderIngredient.class, new LinkedOrderIngredientService());
			IoC.registerService(Order.class, new OrderService());
		}
	}
}
