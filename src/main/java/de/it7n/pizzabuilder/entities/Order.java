package de.it7n.pizzabuilder.entities;

import com.github.collinalpert.java2db.annotations.ForeignKeyEntity;
import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;

import java.time.LocalDateTime;

@TableName("order")
public class Order extends BaseEntity {

	private double totalPrice;

	private long customerId;

	@ForeignKeyEntity("customerId")
	private Customer customer;

	private LocalDateTime time;

	public Order(double totalPrice, long customerId) {
		this();
		this.totalPrice = totalPrice;
		this.customerId = customerId;
	}

	public Order() {
		this.time = LocalDateTime.now();
	}
}
