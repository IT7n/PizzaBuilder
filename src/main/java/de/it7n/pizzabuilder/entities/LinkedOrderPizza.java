package de.it7n.pizzabuilder.entities;

import com.github.collinalpert.java2db.annotations.ForeignKeyEntity;
import com.github.collinalpert.java2db.annotations.TableName;

@TableName("linkedorderpizza")
public class LinkedOrderPizza {
    private int orderId;
    @ForeignKeyEntity("orderId")
    private Order order;
    private int pizzaId;
    @ForeignKeyEntity("pizzaId")
    private Pizza pizza;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
