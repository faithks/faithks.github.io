package Grazioso;

public class Monkey extends RescueAnimal {
	
	//Instance Variable
	private String species;
	private String tailLength;
	private String height;
	private String bodyLength;
	
	//Constructor
	public Monkey(String name, String species, String gender, String age,
		    String weight,String tailLength, String height, String bodyLength, String acquisitionDate, String acquisitionCountry,
			String trainingStatus, boolean reserved, String inServiceCountry) {
		        setName(name);
		        setSpecies (species);
		        setGender(gender);
		        setAge(age);
		        setWeight(weight);
		        setTailLength (tailLength);
		        setHeight (height);
		        setBodyLength (bodyLength);
		        setAcquisitionDate(acquisitionDate);
		        setAcquisitionLocation(acquisitionCountry);
		        setTrainingStatus(trainingStatus);
		        setReserved(reserved);
		        setInServiceCountry(inServiceCountry);
	}
	
	// @return the species
	public String getSpecies() {
		return species;
	}
	
	//@param species the species to set
	public void setSpecies(String species) {
		this.species = species;
	}
	
	//@return the tailLength
	public String getTailLength() {
		return tailLength;
	}

	//@param tailLength the tailLength to set
	public void setTailLength(String tailLength) {
		this.tailLength = tailLength;
	}

	//@return the height
	public String getHeight() {
		return height;
	}

	//@param height the height to set
	public void setHeight(String height) {
		this.height = height;
	}
	
	//@return the bodyLength
	public String getBodyLength() {
		return bodyLength;
	}
	
	//@param bodyLength the bodyLength to set
	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
	}

	
}

