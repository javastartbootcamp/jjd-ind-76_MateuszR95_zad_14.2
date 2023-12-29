package pl.javastart.task;

public class Vehicle {

    private String producer;
    private String model;
    private int yearProduction;
    private long vehicleMileage;
    private String vinNumber;
    private Type type;

    public enum Type { MOTORBIKE, CAR, TRUCK }

    public Vehicle(String producer, String model, int yearProduction, long vehicleMileage, String vinNumber, Type type) {
        this.producer = producer;
        this.model = model;
        this.yearProduction = yearProduction;
        this.vehicleMileage = vehicleMileage;
        this.vinNumber = vinNumber;
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearProduction() {
        return yearProduction;
    }

    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    public long getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(long vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Marka: " + producer + ", model: " + model + ", rok produkcji: " + yearProduction
                + ", przebieg pojazdu: " + vehicleMileage + "km" + ", numer VIN: " + vinNumber + ", typ pojazdu: " + printType();
    }

    public String toCsv() {
        return producer + "," + model + "," + yearProduction + "," + vehicleMileage + "," + vinNumber + "," + type;
    }

    private String printType() {
        String type;
        switch (getType()) {
            case CAR -> type = "samochód";
            case MOTORBIKE -> type = "motocykl";
            case TRUCK -> type = "samochód ciężarowy";
            default -> type = "nieprawidłowy typ pojazdu";
        }
        return type;
    }
}
