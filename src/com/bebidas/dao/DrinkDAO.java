package com.bebidas.dao; //pacote certo


import com.bebidas.model.Drink;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DrinkDAO {
private final Connection conn;


public DrinkDAO(Connection conn) { this.conn = conn; }


public void createTableIfNotExists() throws SQLException {
String sql = "CREATE TABLE IF NOT EXISTS drinks ("+
"id INT AUTO_INCREMENT PRIMARY KEY, "+
"name VARCHAR(255), brand VARCHAR(255), type VARCHAR(100), price DOUBLE, stock INT)";
try (Statement st = conn.createStatement()) {
st.execute(sql);
}
}


public Drink insert(Drink d) throws SQLException {
String sql = "INSERT INTO drinks (name, brand, type, price, stock) VALUES (?, ?, ?, ?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, d.getName());
ps.setString(2, d.getBrand());
ps.setString(3, d.getType());
ps.setDouble(4, d.getPrice());
ps.setInt(5, d.getStock());
ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
if (rs.next()) d.setId(rs.getInt(1));
}
}
return d;
}


public List<Drink> listAll() throws SQLException {
List<Drink> list = new ArrayList<>();
String sql = "SELECT id, name, brand, type, price, stock FROM drinks";
try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
while (rs.next()) {
Drink d = new Drink(rs.getInt("id"), rs.getString("name"), rs.getString("brand"), rs.getString("type"), rs.getDouble("price"), rs.getInt("stock"));
list.add(d);
}
}
return list;
}
}