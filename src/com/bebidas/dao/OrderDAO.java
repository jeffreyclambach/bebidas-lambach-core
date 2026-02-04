package com.bebidas.dao; //pacote certo3

import com.bebidas.model.Drink;
import com.bebidas.model.Order;
import com.bebidas.model.OrderItem;

import java.sql.*;

public class OrderDAO {

    private final Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Cria um pedido em orders + seus itens em order_items
     * e atualiza o estoque em drinks.
     */
    public int createOrder(Order order) throws SQLException {
        String sqlOrder = "INSERT INTO orders (client_id, status, created_at) " +
                          "VALUES (?, ?, NOW())";

        String sqlItem = "INSERT INTO order_items (order_id, drink_id, quantity, unit_price) " +
                         "VALUES (?, ?, ?, ?)";

        String sqlUpdateStock = "UPDATE drinks SET stock = stock - ? WHERE id = ?";

        int orderId;

        try {
            conn.setAutoCommit(false);

            // INSERT na tabela orders
            try (PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, order.getClient().getId());
                psOrder.setString(2, order.getStatus().name()); // CRIADO, etc.
                psOrder.executeUpdate();

                try (ResultSet rs = psOrder.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                        order.setId(orderId);
                    } else {
                        throw new SQLException("Falha ao obter ID do pedido.");
                    }
                }
            }

            // INSERT em order_items + atualização de estoque
            try (PreparedStatement psItem = conn.prepareStatement(sqlItem);
                 PreparedStatement psStock = conn.prepareStatement(sqlUpdateStock)) {

                for (OrderItem item : order.getItems()) {
                    Drink d = item.getDrink();

                    psItem.setInt(1, orderId);
                    psItem.setInt(2, d.getId());
                    psItem.setInt(3, item.getQuantity());
                    psItem.setDouble(4, d.getPrice());
                    psItem.addBatch();

                    psStock.setInt(1, item.getQuantity());
                    psStock.setInt(2, d.getId());
                    psStock.addBatch();
                }

                psItem.executeBatch();
                psStock.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

        return orderId;
    }

    public void insert(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
