package com.bebidas.dao;

import com.bebidas.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private final Connection conn;

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

public void insert(Client c) throws SQLException {

    String sql = "INSERT INTO clients (name, email, phone) VALUES (?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, c.getName());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getPhone());
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                c.setId(rs.getInt(1));
            }
        }
    }
}


    public List<Client> listAll() throws SQLException {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT id, name, email, phone FROM clients ORDER BY name";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                list.add(c);
            }
        }
        return list;
    }
}
