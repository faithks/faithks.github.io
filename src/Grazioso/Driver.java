/* 
 *  IT 145
 *  This program keeps track of Dogs and Monkeys working as rescue animals
 *  The program takes in new animals, lists the ones in the system, and reserves new ones
 *  Programmer: Faith Sheppard
 *  Date: 11/9/25
 *  Code for Artifact two and Category Two and three
 */

package Grazioso;

import java.util.Scanner;

import Grazioso.database.DatabaseInitializer;

public class Driver {
    public static void main(String[] args) {

    	// Initialize database
    	DatabaseInitializer.initialize();
    	
        Scanner scanner = new Scanner(System.in);
        char choice = 0;
        
        // Load in data to hash maps
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
}
