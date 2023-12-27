package pl.javastart.task;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StationManager {

    private static final int EXIT = 0;
    private static final int ADD_VEHICLE = 1;
    private static final int PERFORM_VEHICLE_INSPECTION = 2;
    private Scanner scanner = new Scanner(System.in);
    private Queue<Vehicle> vehicles = new LinkedList<>();

    public void run(String fileName) throws IOException {

        if (!fileName.isEmpty()) {
            readQueueFromFile(fileName);
        }

        boolean shouldContinue = true;
        while (shouldContinue) {
            printOptions();
            int userChoice = readUserChoice();
            switch (userChoice) {
                case EXIT -> {
                    if (vehicles.isEmpty()) {
                        shouldContinue = false;
                        clearFile(fileName);
                    } else {
                        writeQueueToFile("vehicles.txt");
                        shouldContinue = false;
                    }
                }
                case ADD_VEHICLE -> vehicles.offer(readAndCreateVehicle());
                case PERFORM_VEHICLE_INSPECTION -> {
                    if (!vehicles.isEmpty()) {
                        performVehicleInspection(fileName);
                    } else {
                        System.out.println("Brak pojazdu w kolejce do przeglądu");
                    }
                }
                default -> System.out.println("Wybrałeś nieprawidłową opcją");
            }
        }
    }

    public void writeQueueToFile(String fileName) throws IOException {
        String line = null;
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.getProducer() + "," + vehicle.getModel() + "," + vehicle.getYearProduction() + ","
                        + vehicle.getVehicleMileage() + "," + vehicle.getVinNumber() + "," + vehicle.getType());
                writer.newLine();
            }
        }
    }

    public void clearFile(String fileName) throws IOException {

        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.write("");
        }
    }

    public void readQueueFromFile(String fileName) throws IOException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader);
        ) {
            String fileLine = null;
            while ((fileLine = reader.readLine()) != null) {
                String[] fileLineItems = fileLine.split(",");
                String producer = fileLineItems[0];
                String model = fileLineItems[1];
                int yearProduction = Integer.parseInt(fileLineItems[2]);
                long vehicleMileage = Long.parseLong(fileLineItems[3]);
                String vinNumber = fileLineItems[4];
                Vehicle.Type type = Vehicle.Type.valueOf(fileLineItems[5]);
                Vehicle vehicle = new Vehicle(producer, model, yearProduction, vehicleMileage, vinNumber, type);
                vehicles.offer(vehicle);
            }

        }
    }

    public void performVehicleInspection(String fileName) throws IOException {
        if (!vehicles.isEmpty()) {
            Vehicle vehicleToInspection = vehicles.poll();
            System.out.println("Przegląd pojazdu: " + vehicleToInspection);
        } else {
            clearFile(fileName);
        }
    }

    public Vehicle readAndCreateVehicle() {
        System.out.println("Podaj markę pojazdu");
        String producer = scanner.nextLine();
        System.out.println("Podaj model pojazdu");
        String model = scanner.nextLine();
        System.out.println("Podaj rocznik pojazdu");
        int productionYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj przebieg pojazdu");
        long vehicleMileage = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Podaj numer VIN pojazdu");
        String vinNumber = scanner.nextLine();
        System.out.println("Podaj typ pojazdu: ");
        for (Vehicle.Type type : Vehicle.Type.values()) {
            System.out.print(type + " ");
        }
        System.out.println();
        Vehicle.Type type = Vehicle.Type.valueOf(scanner.nextLine().toUpperCase());
        return new Vehicle(producer, model, productionYear, vehicleMileage, vinNumber, type);
    }

    private void printOptions() {
        System.out.println("Wybierz opcję");
        System.out.println("0 - zakończenie działania programu");
        System.out.println("1 - dodaj pojazd do przeglądu");
        System.out.println("2 - przeprowadzenie przeglądu pojazdu");
    }

    private int readUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        return userChoice;
    }
}
