package com.pluralsight.exercise3;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private DataSource dataSource;
    private static Connection connection = null;
    private static final Scanner keyboard = new Scanner(System.in);

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actors> getActorsByName() {
        List<Actors> actors = new ArrayList<>();

        System.out.print("Enter the last name of an actor/actress: ");
        String lastName = keyboard.nextLine();

        String query = "SELECT Actor_ID, First_Name, Last_Name FROM Actor WHERE Last_Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            try (ResultSet results = statement.executeQuery()) {
                System.out.println("\nListing Actors with the Last Name " + lastName);
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+");
                while (results.next()) {
                    int actorID = results.getInt("actor_id");
                    String fName = results.getString("first_name");
                    String lName = results.getString("last_name");
                    System.out.println(fName + " " + lName);

                    Actors actor = new Actors(actorID, fName, lName);
                    Actors.add(actor);
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Movies> getMoviesByActor(){
        List<Movies> movies = new ArrayList<>();

        System.out.println("Enter the first and last name of an actor/actress you want to see.");
        System.out.println("All of the movies they star in will be listed.");
        System.out.print("First Name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Last Name: ");
        String lastName = keyboard.nextLine();


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
                    int movieYear = results.getInt("F.Release_Year");
                    int movieLength = results.getInt("F.Length");
                    String movieRating = results.getString("F.Rating");
                    System.out.printf("%s \t %s \t %s \t %s\n", movieTitle, movieYear, movieLength, movieRating);

                    Movies movie = new Movies(title, year, length, rating);
                    movies.add(movie);
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

}
