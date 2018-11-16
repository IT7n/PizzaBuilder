package de.it7n.pizzabuilder.requests;

/**
 * @author Collin Alpert
 */
public class OrderRequest {
	private String customerEmail;
	private IngredientRequest ings;
	private double totalPrice;

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public IngredientRequest getIngs() {
		return ings;
	}

	public void setIngs(IngredientRequest ings) {
		this.ings = ings;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
