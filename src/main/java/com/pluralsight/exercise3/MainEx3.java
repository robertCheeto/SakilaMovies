package com.pluralsight.exercise3;

import static com.pluralsight.exercise3.DataManager.loadConnection;
import com.pluralsight.exercise3.DataManager.

public class MainEx3 {
    public static void main(String[] args) {
        loadConnection(args[0], args[1]);

        SakilaDataManager dataManager = new SakilaDataManager(dataSource);

        List<Actors> actors = dataManager.getActorsByName();

    }
}
