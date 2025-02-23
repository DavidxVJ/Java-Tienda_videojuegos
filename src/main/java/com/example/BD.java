package com.example;

import java.sql.*;

public class BD {
    
    public static Connection Conectar() throws ClassNotFoundException{
        //Conexion a la base de datos local SQLite
        
        Connection conexion = null;

        try{
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:src/main/resources/Database/Catalogo_videojuegos.db");
            System.out.println("Conexion a la base de datos establecida");
            return conexion;
        }
        catch(SQLException e){
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
