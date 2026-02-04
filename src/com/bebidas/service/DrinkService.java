package com.bebidas.service;

import com.bebidas.dao.DrinkDAO;
import com.bebidas.model.Drink;
import com.bebidas.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class DrinkService {

    public List<Drink> listar() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            DrinkDAO dao = new DrinkDAO(conn);
            return dao.listAll();
        }
    }
}
