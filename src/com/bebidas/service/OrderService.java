package com.bebidas.service;

import com.bebidas.dao.OrderDAO;
import com.bebidas.model.Order;
import com.bebidas.util.DBConnection;

import java.sql.Connection;

public class OrderService {

    public void salvar(Order order) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            dao.insert(order);
        }
    }
}
