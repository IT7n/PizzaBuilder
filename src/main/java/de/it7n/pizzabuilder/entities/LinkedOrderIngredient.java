package de.it7n.pizzabuilder.entities;

import com.github.collinalpert.java2db.annotations.ForeignKeyEntity;
import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;

@TableName("linkedorderingredient")
public class LinkedOrderIngredient extends BaseEntity {

	private long orderId;

	@ForeignKeyEntity("orderId")
	private Order order;

	private boolean hasMozzarella;

	private boolean hasOlive;
	private boolean hasMushroom;
	private boolean hasPepperoni;
	private boolean hasRedPepper;
	private boolean hasGreenPepper;
	private boolean hasTomato;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Order getOrder() {
		return order;
	}

	public LinkedOrderIngredient(long orderId) {
		this.orderId = orderId;
	}

	public LinkedOrderIngredient() {
	}

	public void hasMozzarella(boolean hasMozzarella) {
		this.hasMozzarella = hasMozzarella;
	}

	public void hasOlive(boolean hasOlive) {
		this.hasOlive = hasOlive;
	}

	public void hasMushroom(boolean hasMushroom) {
		this.hasMushroom = hasMushroom;
	}

	public void hasPepperoni(boolean hasPepperoni) {
		this.hasPepperoni = hasPepperoni;
	}

	public void hasRedPepper(boolean hasRedPepper) {
		this.hasRedPepper = hasRedPepper;
	}

	public void hasGreenPepper(boolean hasGreenPepper) {
		this.hasGreenPepper = hasGreenPepper;
	}

	public void hasTomato(boolean hasTomato) {
		this.hasTomato = hasTomato;
	}
}
