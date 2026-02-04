package com.bebidas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

private static final String URL =
    "jdbc:mysql://127.0.0.1:3306/bebidas?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";

    private static final String USER = "root";          
    private static final String PASSWORD = "Q1w@e3r4t5"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}