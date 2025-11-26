package Grazioso.Animals;

import java.time.LocalDate;

public abstract class RescueAnimal {

    // Instance variables
    private String name;
    private String gender;
    private int age;
    private double weight;
    private LocalDate acquisitionDate;
    private String acquisitionLocation;

    private TrainingStatus trainingStatus;
    private boolean reserved;
    private String serviceLocation;

    // Constructor
    public RescueAnimal(String name,
                        String gender,
                        int age,
                        double weight,
                        LocalDate acquisitionDate,
                        String acquisitionLocation,
                        TrainingStatus trainingStatus,
                        boolean reserved,
                        String serviceLocation) {

        // Validation
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be empty.");

        if (age < 0)
            throw new IllegalArgumentException("Age must be non-negative.");

        if (weight <= 0)
            throw new IllegalArgumentException("Weight must be positive.");

        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;

        this.acquisitionDate = acquisitionDate;
        this.acquisitionLocation = acquisitionLocation;

        this.trainingStatus = trainingStatus;
        this.reserved = reserved;
        this.serviceLocation = serviceLocation;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public LocalDate getAcquisitionDate() { return acquisitionDate; }
    public String getAcquisitionLocation() { return acquisitionLocation; }
    public TrainingStatus getTrainingStatus() { return trainingStatus; }
    public boolean getReserved() { return reserved; }
    public String getServiceLocation() { return serviceLocation; }

    public void setTrainingStatus(TrainingStatus status) {
        this.trainingStatus = status;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setServiceLocation(String location) {
        this.serviceLocation = location;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + age + "\n" +
                "Weight: " + weight + "\n" +
                "Acquisition Date: " + acquisitionDate + "\n" +
                "Acquisition Location: " + acquisitionLocation + "\n" +
                "Training Status: " + trainingStatus + "\n" +
                "Reserved: " + (reserved ? "Yes" : "No") + "\n" +
                "Service Country: " + serviceLocation + "\n";
    }
}
