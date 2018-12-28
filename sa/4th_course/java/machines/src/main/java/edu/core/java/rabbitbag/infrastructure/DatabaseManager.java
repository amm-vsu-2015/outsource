package edu.core.java.rabbitbag.infrastructure;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// to make singleton
public class DatabaseManager {

    private HikariDataSource ds = new HikariDataSource();

    public void setupConnection() {

        ds.setJdbcUrl("jdbc:mysql://localhost:3306/rabs");
        ds.setUsername("rab");
        ds.setPassword("skad1011");

    }

//    DatabaseManager d = new DatabaseManager();
//        d.setupConnection();
//    Connection c = d.getConnection();
//
//        c.nativeSQL("CREATE TABLE brand (id UNIQUE PRIMARY KEY, text VARCHAR(150) NOT NULL)");
//        c.commit();
//        c.nativeSQL("INSERT INTO brand (`name`) VALUES (`bobby`)");
//        c.commit();
//        System.out.println(c.nativeSQL("SELECT * FROM brand"));

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(ds.getJdbcUrl());
    }

}
