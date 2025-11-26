package Grazioso.Animals;

import java.time.LocalDate;

public class Dog extends RescueAnimal {

    // Instance variable
    private String breed;

    // Constructor
    public Dog(String name, String breed, String gender, int age,
    double weight, LocalDate acquisitionDate, String acquisitionLocation,
	TrainingStatus trainingStatus, boolean reserved, String serviceLocation) {
       
        super(name, gender, age, weight,
                acquisitionDate, acquisitionLocation,
                trainingStatus, reserved, serviceLocation);

          this.breed = breed;

    }

    // Getters
    public String getBreed() {
        return breed;
    }

    // Setters
    public void setBreed(String dogBreed) {
        breed = dogBreed;
    }
    
    @Override
    public String toString() {
        return "------------------------------\n" +
               "DOG\n" +
               "------------------------------\n" +
               super.toString() +
               "Breed: " + breed + "\n";
    }



}
