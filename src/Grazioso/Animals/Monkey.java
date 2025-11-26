package Grazioso.Animals;

import java.time.LocalDate;

public class Monkey extends RescueAnimal {
	
	//Instance Variable
	private String species;
	private double tailLength;
	private double height;
	private double bodyLength;
	
	//Constructor
	public Monkey(String name, String species, String gender, int age,
		    double weight,double tailLength, double height, double bodyLength, LocalDate acquisitionDate, String acquisitionLocation,
			TrainingStatus trainingStatus, boolean reserved, String serviceLocation) {
		        
		        super(name, gender, age, weight, acquisitionDate, acquisitionLocation,
		                trainingStatus, reserved, serviceLocation);

		          this.species = species;
		          this.tailLength = tailLength;
		          this.height = height;
		          this.bodyLength = bodyLength;
		      }
	
	// Getters
	public String getSpecies() {
		return species;
	}
	
	public double getTailLength() {
		return tailLength;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getBodyLength() {
		return bodyLength;
	}
	
	// Setters
	public void setSpecies(String species) {
		this.species = species;
	}

	public void setTailLength(double tailLength) {
		this.tailLength = tailLength;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setBodyLength(double bodyLength) {
		this.bodyLength = bodyLength;
	}
	
	@Override
	public String toString() {
	    return "------------------------------\n" +
	           "MONKEY\n" +
	           "------------------------------\n" +
	           super.toString() +
	           "Species: " + species + "\n" +
	           "Tail Length: " + tailLength + " in\n" +
	           "Height: " + height + " in\n" +
	           "Body Length: " + bodyLength + " in\n";
	    }

	
}

