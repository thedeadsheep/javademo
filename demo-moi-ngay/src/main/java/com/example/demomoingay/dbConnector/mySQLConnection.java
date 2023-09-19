package com.example.demomoingay.dbConnector;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class mySQLConnection {

    private String hostName = "localhost:3306";
    private String dbName = "sakila";
    private String username ="root";
    private String password = "phuonganh";
    private  String connectionURL = "jdbc:mysql://"+hostName+"/"+dbName;
    public Connection connect(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(connectionURL,username,password);
        }catch  (SQLException e){
            e.printStackTrace();
        }


        return conn;
    }
}
