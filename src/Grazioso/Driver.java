/* 
 *  IT 145
 *  This program keeps track of Dogs and Monkeys working as rescue animals
 *  The program takes in new animals, lists the ones in the system, and reserves new ones
 *  Programmer: Faith Sheppard
 *  Date: 11/9/25
 *  Code for Artifact two and Category Two and three
 */

package Grazioso;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    // Instance variables (if needed)

    public static void main(String[] args) {

        initializeDogList();
        initializeMonkeyList();
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            displayMenu();  // Display the menu
            choice = scanner.nextLine().charAt(0);  // Read the user's choice

            switch (choice) {
                case '1':
                    intakeNewDog(scanner);
                    break;
                case '2':
                    intakeNewMonkey(scanner);
                    break;
                case '3':
                    reserveAnimal(scanner);
                    break;
                case '4':
                    printAnimals("dog");
                    break;
                case '5':
                    printAnimals("monkey");
                    break;
                case '6':
                    printAnimals("available");
                    break;
                case 'q':
                case 'Q':
                    System.out.println("Quitting application");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'q' && choice != 'Q');
    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "in service", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
    }


    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
    	 Monkey monkey1 = new Monkey("Gold", "Capuchin", "male", "4", "25.0", "12.4", "25.0", "6.8", "12-15-2020", "Brazil", "in service", false, "Brazil");
    	 Monkey monkey2 = new Monkey("Blue", "Macaque", "female", "2", "18.5", "8.1", "17.5", "9.6", "01-04-2022", "India", "Phase II", false, "India");
    	 Monkey monkey3 = new Monkey("Azure", "Guenon", "male", "7", "32.3", "16.7", "29.8", "23.4", "07-20-2019", "Africa", "in service", true, "Africa");
    	 
    	 monkeyList.add(monkey1);
    	 monkeyList.add(monkey2);
    	 monkeyList.add(monkey3);
    }



    // add a new dog and validate they aren't already in the system
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; //returns to menu
            }
        }

        System.out.println("What is the dog's Breed?");
        String breed = scanner.nextLine();
        
        System.out.println("What is the dog's Gender?");
        String gender = scanner.nextLine();

        System.out.println("What is the dog's Age?");
        String age = scanner.nextLine();

        System.out.println("What is the dog's Weight?");
        String weight = scanner.nextLine();
        
        System.out.println("What is the dog's Acquistion date?");
        String acquisitionDate = scanner.nextLine();
        
        System.out.println("What is the dog's Acquistion Location?");
        String acquistionCountry = scanner.nextLine();
        
        System.out.println("What is the dog's Training Status?");
        String trainingStatus = scanner.nextLine();
        
        System.out.println("Is the dog reserved true or false?");
        boolean reserved = scanner.nextBoolean();
        
        // Consume the newline character left in the buffer
        scanner.nextLine();
        
        System.out.println("What is the dog's Service County?");
        String inServiceCountry = scanner.nextLine();
        
        dogList.add(new Dog(name, breed, gender, age, weight, acquisitionDate, acquistionCountry,
        		trainingStatus, reserved, inServiceCountry));
    }

    	// add a new Monkey and validate they aren't in the system and they are the right species
        public static void intakeNewMonkey(Scanner scanner) {          
            System.out.println("What is the monkey's name?");  //make sure the monkey doesn't exist
            String name = scanner.nextLine();
            for(Monkey monkey: monkeyList) {
                if(monkey.getName().equalsIgnoreCase(name)) {
                    System.out.println("\n\nThis monkey is already in our system\n\n");
                    return; //returns to menu
                }
            }
            System.out.println("What is the monkey's species?");
            System.out.println("[1] Capuchin");
            System.out.println("[2] Macaque");
            System.out.println("[3] Guenon");
            System.out.println("[4] Marmoset");
            System.out.println("[5] Squirrel Monkey");
            System.out.println("[6] Tamarin");//make sure the species is allowed
            
            char option;
            String species;
            
            option = scanner.nextLine().charAt(0);
            switch (option) {
            case '1':
                species = "Capuchin";
                break;
            case '2':
            	species = "Macaque";
                break;
            case '3':
            	species = "Guenon";
                break;
            case '4':
            	species = "Marmoset";
                break;
            case '5':
            	species = "Squirrel monkey";
                break;
            case '6':
            	species = "Tamarin";
                break;
            default:
            	System.out.println("\n\nThis Monkey is not a correct species\n\n");
            	return;
            }
                
                System.out.println("What is the monkey's Gender?");
                String gender = scanner.nextLine();

                System.out.println("What is the monkey's Age?");
                String age = scanner.nextLine();

                System.out.println("What is the monkey's Weight?");
                String weight = scanner.nextLine();

                System.out.println("What is the monkey's Tail Length?");
                String tailLength = scanner.nextLine();

                System.out.println("What is the monkey's Height?");
                String height = scanner.nextLine();

                System.out.println("What is the monkey's Body Length?");
                String bodyLength = scanner.nextLine();
                
                System.out.println("What is the monkey's Acquistion date?");
                String acquisitionDate = scanner.nextLine();
                
                System.out.println("What is the monkey's Acquistion Location?");
                String acquistionCountry = scanner.nextLine();
                
                System.out.println("What is the monkey's Training Status?");
                String trainingStatus = scanner.nextLine();
                
                System.out.println("Is the monkey reserved true or false?");
                boolean reserved = scanner.nextBoolean();
                
                // Consume the newline character left in the buffer
                scanner.nextLine();
                
                System.out.println("What is the monkey's Service County?");
                String inServiceCountry = scanner.nextLine();
                
                monkeyList.add(new Monkey(name, gender, age, weight, tailLength, height, bodyLength, species,
                        acquisitionDate, acquistionCountry, trainingStatus, reserved, inServiceCountry));
               
           
            
        }


        // You will need to find the animal by animal type and in service country. This then reserves them.
        public static void reserveAnimal(Scanner scanner) {
            System.out.println("Please enter desired animal type.");
            String type = scanner.nextLine();
            
            if (type.equalsIgnoreCase("dog")) {
            	for(Dog dog: dogList) {
            		System.out.println("Enter country");
            		String country = scanner.nextLine();
            		
            		if (dog.getInServiceLocation().equalsIgnoreCase(country) && !dog.getReserved()) {
            			System.out.println("We found an animal for you. Setting the dog to reserved.");
            			dog.setReserved(true);
            			return;
            		}
            		else {
            			System.out.println("No dog in this location.");
            			return;
            		}
            	}
            }
            else if (type.equalsIgnoreCase("monkey")){
            	for(Monkey monkey: monkeyList) {
            		System.out.println("Enter country");
            		String country = scanner.nextLine();
            		
            		if (monkey.getInServiceLocation().equalsIgnoreCase(country) && !monkey.getReserved()) {
            			System.out.println("We found an animal for you. Setting the monkey to reserved.");
            			monkey.setReserved(true);
            			return;
            		}
            		else {
            			System.out.println("No monkey in this location.");
            			return;
            		}
            	}
            }
        }

        // print animals based on menu choice 
        public static void printAnimals(String option) {
            System.out.println("The method printAnimals needs to be implemented");
            
            //all dogs
            if (option.equalsIgnoreCase("dog")) {
                System.out.println("Dogs:");
                for (Dog dog : dogList) {
                    System.out.println("Name: " + dog.getName() +
                            ", Status: " + dog.getTrainingStatus() +
                            ", Acquisition Country: " + dog.getAcquisitionLocation() +
                            ", Reserved: " + dog.getReserved());
                }
            //all monkey's
            } else if (option.equalsIgnoreCase("monkey")) {
                System.out.println("Monkeys:");
                for (Monkey monkey : monkeyList) {
                    System.out.println("Name: " + monkey.getName() +
                            ", Status: " + monkey.getTrainingStatus() +
                            ", Acquisition Country: " + monkey.getAcquisitionLocation() +
                            ", Reserved: " + monkey.getReserved());
                }
            //all available, ready for service but not reserved
            } else if (option.equalsIgnoreCase("available")) {
                System.out.println("Available Animals:");
                System.out.println("Dogs:");
                for (Dog dog : dogList) {
                    if (dog.getTrainingStatus().equalsIgnoreCase("in service") && !dog.getReserved()) {
                        System.out.println("Name: " + dog.getName() +
                                ", Status: " + dog.getTrainingStatus() +
                                ", Acquisition Country: " + dog.getAcquisitionLocation() +
                                ", Reserved: " + dog.getReserved());
                    }
                }
                System.out.println("Monkeys:");
                for (Monkey monkey : monkeyList) {
                    if (monkey.getTrainingStatus().equalsIgnoreCase("in service") && !monkey.getReserved()) {
                        System.out.println("Name: " + monkey.getName() +
                                ", Status: " + monkey.getTrainingStatus() +
                                ", Acquisition Country: " + monkey.getAcquisitionLocation() +
                                ", Reserved: " + monkey.getReserved());
                    }
                }
            } else { //default just in case
                System.out.println("Invalid option.");
            }

        }
}
