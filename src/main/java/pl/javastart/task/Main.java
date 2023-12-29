package pl.javastart.task;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        StationManager stationManager = new StationManager();
        String fileName = "vehicles.txt";
        try {
            stationManager.run(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
