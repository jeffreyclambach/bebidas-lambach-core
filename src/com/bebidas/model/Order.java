package com.bebidas.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {
public enum Status {CRIADO, AS_SEPARAR, EM_ENTREGA, ENTREGUE, CANCELADO}


private int id;
private Client client;
private List<OrderItem> items = new ArrayList<>();
private LocalDateTime createdAt = LocalDateTime.now();
private Status status = Status.CRIADO;


public Order() {}
public Order(int id, Client client) { this.id = id; this.client = client; }


public void addItem(OrderItem item) { items.add(item); }
public double calculateTotal() {
return items.stream().mapToDouble(i -> i.getDrink().getPrice() * i.getQuantity()).sum();
}


// getters e setters
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public Client getClient() { return client; }
public void setClient(Client client) { this.client = client; }
public List<OrderItem> getItems() { return items; }
public LocalDateTime getCreatedAt() { return createdAt; }
public Status getStatus() { return status; }
public void setStatus(Status status) { this.status = status; }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(String.format("Pedido[%d] - Cliente: %s - Criado: %s\n", id, client.getName(), createdAt));
for (OrderItem i : items) {
sb.append(String.format(" - %s x %d = R$ %.2f\n", i.getDrink().getName(), i.getQuantity(), i.getDrink().getPrice()*i.getQuantity()));
}
return sb.toString();
}
}