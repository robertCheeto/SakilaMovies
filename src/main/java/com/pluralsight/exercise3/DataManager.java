package com.pluralsight.exercise3;

import com.pluralsight.exercise3.models.*;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sakila";
    private static Connection connection = null;
    private static final Scanner keyboard = new Scanner(System.in);

    public DataManager(String username, String password) {
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

    public List<Actors> getActors(){
        List<Actors> actors = new ArrayList<>();

        String query = "SELECT Actor_ID, First_Name, Last_Name FROM Actor";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    int actorID = results.getInt("actor_id");
                    String fName = results.getString("first_name");
                    String lName = results.getString("last_name");
                    Actors actor = new Actors(actorID, fName, lName);
                    actors.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Actors> getActorsByName(){
        List<Actors> actors = new ArrayList<>();

        System.out.print("Enter the last name of an actor/actress: ");
        String lastName = keyboard.nextLine();

        String query = "SELECT Actor_ID, First_Name, Last_Name FROM Actor WHERE Last_Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    int actorID = results.getInt("actor_id");
                    String fName = results.getString("first_name");
                    String lName = results.getString("last_name");
                    Actors actor = new Actors(actorID, fName, lName);
                    actors.add(actor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Movies> getMovies(){
        List<Movies> movies = new ArrayList<>();

        String query = "SELECT Film_ID, Title, Release_Year, Length, Rating FROM Film";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    int movieID = results.getInt("Film_ID");
                    String movieTitle = results.getString("Title");
                    int movieYear = results.getInt("Release_Year");
                    int movieLength = results.getInt("Length");
                    String movieRating = results.getString("Rating");

                    Movies movie = new Movies(movieID, movieTitle, movieYear, movieLength, movieRating);
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Movies> getMoviesByActor(){
        List<Movies> movies = new ArrayList<>();

        System.out.println("Enter the first and last name of an actor/actress you want to see.");
        System.out.println("All of the movies they star in will be listed.");
        System.out.print("First Name: ");
        String firstName = keyboard.nextLine();
        System.out.print("Last Name: ");
        String lastName = keyboard.nextLine();


        String query = "SELECT F.Film_ID, F.Title, F.Release_Year, F.Length, F.Rating FROM Film AS F " +
                "JOIN Film_Actor AS FA ON (FA.Film_ID = F.Film_ID)" +
                "JOIN Actor AS A ON (A.Actor_ID = FA.Actor_ID)" +
                "WHERE A.First_Name = ? AND A.Last_Name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    int movieID = results.getInt("F.Film_ID");
                    String movieTitle = results.getString("F.Title");
                    int movieYear = results.getInt("F.Release_Year");
                    int movieLength = results.getInt("F.Length");
                    String movieRating = results.getString("F.Rating");

                    Movies movie = new Movies(movieID, movieTitle, movieYear, movieLength, movieRating);
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}