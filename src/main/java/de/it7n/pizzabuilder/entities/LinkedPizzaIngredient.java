package de.it7n.pizzabuilder.entities;

import com.github.collinalpert.java2db.annotations.ForeignKeyEntity;
import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;

/**
 * @author Collin Alpert
 */
@TableName("linkedpizzaingredient")
public class LinkedPizzaIngredient extends BaseEntity {

	private int pizzaId;

	@ForeignKeyEntity("pizzaId")
	private Pizza pizza;

	private int ingredientId;

	@ForeignKeyEntity("ingredientId")
	private Ingredient ingredient;

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}
}
