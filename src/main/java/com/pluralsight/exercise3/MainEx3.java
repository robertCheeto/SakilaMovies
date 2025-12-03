package com.pluralsight.exercise3;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class MainEx3 {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sakila";

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);

        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error when loading connection. Exiting application.");
            e.printStackTrace();
            System.exit(1);
        }

        DataManager dataManager = new DataManager(dataSource);

        List<Actors> actors = dataManager.getActorsByName();

    }
}
