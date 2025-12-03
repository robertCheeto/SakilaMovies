package com.pluralsight.exercise3;

import com.pluralsight.exercise3.models.*;
import java.util.List;
import java.util.Scanner;

public class UI {


    public static void displayMainMenu(){
        System.out.println("1) Search for Actors (Last Name)");
        System.out.println("2) Search for Films (Actor Starred)");
        System.out.println("99) Exit Program");
        System.out.print("Enter choice here: ");
    }

    public static void displayActors(List<Actors> actors){
        if (actors.isEmpty()) {
            System.out.println("No actors/actresses!");
            return;
        }
        System.out.println("\nActor ID \t First Name \t Last Name");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
        for (Actors actor : actors) {
            System.out.printf("%d \t %s \t %s\n", actor.getActorID(), actor.getFirstName(), actor.getLastName());
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public static void displayMovies(List<Movies> movies){
        if (movies.isEmpty()) {
            System.out.println("No movies");
            return;
        }

        System.out.println("\nFilm ID \t Movie Title \t Release Year \t Duration \t Rating");
        System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+");
        for (Movies movie : movies) {
            System.out.printf("%d \t %s \t %d \t %d \t %s\n", movie.getFilmID(), movie.getTitle(), movie.getReleaseYear(),
                    movie.getLength(), movie.getRating());
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
    }
}
