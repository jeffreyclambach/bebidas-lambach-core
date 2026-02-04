package com.bebidas.core;

import com.bebidas.model.Client;
import com.bebidas.service.ClientService;

public class MainTest {

    public static void main(String[] args) {

        try {
            ClientService service = new ClientService();

            Client c = new Client();
            c.setName("Cliente Teste Core");
            c.setPhone("999999");

            service.salvar(c);

            System.out.println("Cliente salvo com sucesso no CORE.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
