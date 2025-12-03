package com.pluralsight.exercise3;

public class MainEx3 {

    public static void main(String[] args) {
        System.out.println("Welcome to the Sakila Movie Database v3");

        //String username = System.getenv("DB_USERNAME");
        String username = "root";
        //String password = System.getenv("DB_PASSWORD");
        String password = "yearup";

        if (username == null || password == null) {
            System.out.println("Username AND/OR Password Not set/correct.");
            System.exit(1);
        }

        DataManager dataManager = new DataManager(username, password);

        AppController.runProgram(dataManager);
    }
}