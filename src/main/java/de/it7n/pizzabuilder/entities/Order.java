package de.it7n.pizzabuilder.entities;

import com.github.collinalpert.java2db.annotations.TableName;
import com.github.collinalpert.java2db.entities.BaseEntity;

@TableName("order")
public class Order extends BaseEntity {
    private double totalPrice;
    private int customerId;

}
