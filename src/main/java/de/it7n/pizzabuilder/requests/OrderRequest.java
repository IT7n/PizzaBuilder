package de.it7n.pizzabuilder.requests;

/**
 * @author Collin Alpert
 */
public class OrderRequest {
	private String customerEmail;
	private IngredientRequest ingredients;
	private double totalPrice;

	public String getCustomerEmail() {
		return customerEmail;
	}

	public IngredientRequest getIngredients() {
		return ingredients;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

}
