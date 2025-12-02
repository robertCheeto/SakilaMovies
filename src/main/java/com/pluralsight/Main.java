package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static Connection connection = null;
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Sakila Movie Database");

        loadConnection(args[0], args[1]);

        System.out.print("Enter the last name of an actor/actress: ");
        String userInput = keyboard.nextLine();
    }

    public static void loadConnection(String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error when loading connection. Exiting application.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void lastNameSearch() {

    }

    public static void firstLastNameSearch() {

    }


    public static void actorFilmSearch() {

    }
}
