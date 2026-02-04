package com.bebidas.model;


public class Client extends User {
private String phone;


public Client() {}
public Client(int id, String name, String email, String phone) {
super(id, name, email);
this.phone = phone;
}


public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }


@Override
public String toString() {
return String.format("Cliente[%d] %s <%s> - %s", id, name, email, phone);
}
}