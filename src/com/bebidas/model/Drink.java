package com.bebidas.model;


public class Drink {
private int id;
private String name;
private String brand;
private String type; // e.g., Cerveja, Refrigerante
private double price;
private int stock;


public Drink() {}


public Drink(int id, String name, String brand, String type, double price, int stock) {
this.id = id;
this.name = name;
this.brand = brand;
this.type = type;
this.price = price;
this.stock = stock;
}


public Drink(String name, String brand, String type, double price, int stock) {
this(0, name, brand, type, price, stock);
}


// getters e setters
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getBrand() { return brand; }
public void setBrand(String brand) { this.brand = brand; }
public String getType() { return type; }
public void setType(String type) { this.type = type; }
public double getPrice() { return price; }
public void setPrice(double price) { this.price = price; }
public int getStock() { return stock; }
public void setStock(int stock) { this.stock = stock; }


@Override
public String toString() {
return String.format("[%d] %s - %s (%s) R$ %.2f - estoque: %d", id, name, brand, type, price, stock);
}
}