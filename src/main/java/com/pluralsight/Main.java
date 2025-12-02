package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static Connection connection = null;
    private static final Scanner keyboard = new Scanner(System.in);
    private static String userInput = "";

    public static void main(String[] args) {
        System.out.println("Welcome to the Sakila Movie Database");

        loadConnection(args[0], args[1]);

        System.out.print("Enter the last name of an actor/actress: ");
        userInput = keyboard.nextLine();
        lastNameSearch(userInput);

        userInput = "";

        System.out.println("Enter the first and last name of an actor/actress you want to see.");
        System.out.println("All of the movies they star in will be listed.");
        System.out.print("First Name: ");
        userInput = keyboard.nextLine();
        System.out.print("Last Name: ");
        String userInput2 = keyboard.nextLine();

        firstLastNameSearch(userInput, userInput2);
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

    public static void lastNameSearch(String lastName) {
        String query = "SELECT First_Name, Last_Name FROM Actor WHERE Last_Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            try (ResultSet results = statement.executeQuery()) {
                System.out.println("\nListing Actors with the Last Name " + lastName);
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+");
                while (results.next()) {
                    String fName = results.getString("first_name");
                    String lName = results.getString("last_name");
                    System.out.println(fName + " " + lName);
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void firstLastNameSearch(String firstName, String lastName) {
        String query = "SELECT F.Title, F.Release_Year, F.Length, F.Rating FROM Film AS F " +
                "JOIN Film_Actor AS FA ON (FA.Film_ID = F.Film_ID)" +
                "JOIN Actor AS A ON (A.Actor_ID = FA.Actor_ID)" +
                "WHERE A.First_Name = ? AND A.Last_Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet results = statement.executeQuery()) {
                System.out.println("\nMovie Title \t Release Year \t Film Length \t Rating");
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                while (results.next()) {
                    String movieTitle = results.getString("F.Title");
                    String movieYear = results.getString("F.Release_Year");
                    String movieLength = results.getString("F.Length");
                    String movieRating = results.getString("F.Rating");
                    System.out.printf("%s \t %s \t %s \t %s\n", movieTitle, movieYear, movieLength, movieRating);
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
