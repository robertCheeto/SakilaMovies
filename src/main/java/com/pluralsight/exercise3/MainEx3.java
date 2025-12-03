package com.pluralsight.exercise3;

import java.util.Scanner;

public class MainEx3 {
    private static final Scanner keyboard = new Scanner(System.in);
    private static boolean isRunning = true;
    public static void main(String[] args) {
        System.out.println("Welcome to the Sakila Movie Database");

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (username == null || password == null) {
            System.out.println("Username AND/OR Password Not set/correct.");
            System.exit(1);
        }

        DataManager dataManager = new DataManager(username, password);

        while(isRunning) {
            UI.displayMainMenu();
            int userInput = keyboard.nextInt();
            keyboard.nextLine();

            switch (userInput) {
                case 1 -> displayActors();
                case 2 -> displayMovies();
                case 99 -> isRunning = false;
                default -> System.out.println("Enter a valid input");
            }
            System.out.println("Program shutting down...");
            keyboard.close();
            System.exit(0);
        }
    }
}
