/* 
 *  IT 145
 *  This program keeps track of Dogs and Monkeys working as rescue animals
 *  The program takes in new animals, lists the ones in the system, and reserves new ones
 *  Programmer: Faith Sheppard
 *  Date: 11/9/25
 *  Code for Artifact two and Category Two and three
 */

package Grazioso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Grazioso.Animals.Dog;
import Grazioso.Animals.Monkey;
import Grazioso.Animals.TrainingStatus;
import Grazioso.database.DatabaseInitializer;

public class Driver {
    public static void main(String[] args) {

    	DatabaseInitializer.initialize();
    	
        Scanner scanner = new Scanner(System.in);
        char choice = 0;
        
        AnimalManager.loadAnimalsFromDB();


        do {
            displayMenu();  // Display the menu
            String input = scanner.nextLine();
            if (input.isEmpty()) continue;
            choice = input.charAt(0);

            switch (choice) {
                case '1':
                    AnimalManager.intakeNewDog(scanner);
                    break;
                case '2':
                    AnimalManager.intakeNewMonkey(scanner);
                    break;
                case '3':
                    AnimalManager.reserveAnimalInput(scanner);
                    break;
                case '4':
                    AnimalManager.printAllDogs();
                    break;
                case '5':
                    AnimalManager.printAllMonkeys();
                    break;
                case '6':
                    AnimalManager.printAvailableAnimals();
                    break;
                case 'q':
                case 'Q':
                    System.out.println("Quitting application");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'q' && choice != 'Q');
    }

    // This method prints the menu options
    public static void displayMenu() {
    	String border = "========================================";
        System.out.println("\n" + border);
        System.out.println("        \u001B[1mMain Menu\u001B[0m");
        System.out.println(border);
        System.out.println("\u001B[36m[1]\u001B[0m Intake a new dog");
        System.out.println("\u001B[36m[2]\u001B[0m Intake a new monkey");
        System.out.println("\u001B[36m[3]\u001B[0m Reserve an animal");
        System.out.println("\u001B[36m[4]\u001B[0m Print a list of all dogs");
        System.out.println("\u001B[36m[5]\u001B[0m Print a list of all monkeys");
        System.out.println("\u001B[36m[6]\u001B[0m Print a list of all animals that are not reserved");
        System.out.println("\u001B[36m[q]\u001B[0m Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }


    // Adds dogs to a list for testing
    public static void initializeDogList() {
        AnimalManager.addDog(new Dog("Spot", "German Shepherd", "male", 1, 25.6,
        		LocalDate.parse("05-12-2019", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "United States",
                TrainingStatus.fromString("intake"), false, "United States"));

        AnimalManager.addDog(new Dog("Rex", "Great Dane", "male", 3, 35.2,
        		LocalDate.parse("02-03-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "United States",
                TrainingStatus.fromString("in service"), false, "United States"));

        AnimalManager.addDog(new Dog("Bella", "Chihuahua", "female", 4, 25.6,
        		LocalDate.parse("12-12-2019", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "Canada",
                TrainingStatus.fromString("in service"), true, "Canada"));
    }

    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
        AnimalManager.addMonkey(new Monkey("Gold", "Capuchin", "male", 4, 25.0,
                12.4, 25.0, 6.8, LocalDate.parse("05-12-2020", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "Brazil",
                TrainingStatus.fromString("in service"), false, "Brazil"));

        AnimalManager.addMonkey(new Monkey("Blue", "Macaque", "female", 2, 18.5,
                8.1, 17.5, 9.6, LocalDate.parse("01-04-2022", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "India",
                TrainingStatus.fromString("Phase II"), false, "India"));

        AnimalManager.addMonkey(new Monkey("Azure", "Guenon", "male", 7, 32.3,
                16.7, 29.8, 23.4,LocalDate.parse("07-20-2019", DateTimeFormatter.ofPattern("MM-dd-yyyy")), "Africa",
                TrainingStatus.fromString("in service"), true, "Africa"));
    }       
}
