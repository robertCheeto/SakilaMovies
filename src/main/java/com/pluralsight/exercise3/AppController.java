package com.pluralsight.exercise3;

import com.pluralsight.exercise3.models.*;
import java.util.List;
import java.util.Scanner;

public class AppController {
    private static DataManager dataManager;
    private static boolean isRunning = true;
    private static final Scanner keyboard = new Scanner(System.in);

    public static void runProgram(DataManager dm){
        dataManager = dm;

        while(isRunning) {
            UI.displayMainMenu();
            int userInput = keyboard.nextInt();
            keyboard.nextLine();

            switch (userInput) {
                case 1 -> listActors();
                case 2 -> manageActors();
                case 3 -> listMovies();
                case 4 -> manageMovies();
                case 99 -> isRunning = false;
                default -> System.out.println("Enter a valid input");
            }
        }
        System.out.println("Program shutting down...");
        keyboard.close();
        System.exit(0);
    }

    private static void listActors(){
        List<Actors> actors = dataManager.getActors();
        UI.displayActors(actors);
    }

    private static void manageActors(){
        List<Actors> actors = dataManager.getActorsByName();
        UI.displayActors(actors);
    }

    private static void listMovies(){
        List<Movies> movies = dataManager.getMovies();
        UI.displayMovies(movies);
    }

    private static void manageMovies(){
        List<Movies> movies = dataManager.getMoviesByActor();
        UI.displayMovies(movies);
    }
}