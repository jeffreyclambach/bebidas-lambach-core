package com.bebidas.service;

import com.bebidas.dao.ClientDAO;
import com.bebidas.model.Client;
import com.bebidas.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class ClientService {

    public void salvar(Client client) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            ClientDAO dao = new ClientDAO(conn);
            dao.insert(client);
        }
    }

    public List<Client> listar() throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            ClientDAO dao = new ClientDAO(conn);
            return dao.listAll();
        }
    }
}
