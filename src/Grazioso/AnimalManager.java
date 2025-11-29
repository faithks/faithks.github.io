package Grazioso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Grazioso.Animals.*;
import Grazioso.database.DogDAO;
import Grazioso.database.MonkeyDAO;

public class AnimalManager {

    private static final HashMap<String, Dog> dogsByName = new HashMap<>();
    private static final HashMap<String, Monkey> monkeysByName = new HashMap<>();

    private static final DogDAO dogDAO = new DogDAO();
    private static final MonkeyDAO monkeyDAO = new MonkeyDAO();

    
    // Secondary index maps
    private static final HashMap<String, List<Dog>> dogsByCountry = new HashMap<>();
    private static final HashMap<TrainingStatus, List<Dog>> dogsByTraining = new HashMap<>();

    private static final HashMap<String, List<Monkey>> monkeysByCountry = new HashMap<>();
    private static final HashMap<TrainingStatus, List<Monkey>> monkeysByTraining = new HashMap<>();

    // Load data from SQLite database
    public static void loadAnimalsFromDB() {
        // Load dogs
        List<Dog> dogsFromDB = dogDAO.getAllDogs();
        for (Dog dog : dogsFromDB) addDog(dog);

        // Load monkeys
        List<Monkey> monkeysFromDB = monkeyDAO.getAllMonkeys();
        for (Monkey monkey : monkeysFromDB) addMonkey(monkey);
    }


    // Add animals
    public static void addDog(Dog dog) {

        String key = dog.getName().toLowerCase();
        dogsByName.put(key, dog);

        // Country index
        dogsByCountry
            .computeIfAbsent(dog.getServiceLocation().toLowerCase(), x -> new ArrayList<>())
            .add(dog);

        // Training index
        dogsByTraining
            .computeIfAbsent(dog.getTrainingStatus(), x -> new ArrayList<>())
            .add(dog);
    }

    public static void addMonkey(Monkey monkey) {

        String key = monkey.getName().toLowerCase();
        monkeysByName.put(key, monkey);

        // Country index
        monkeysByCountry
            .computeIfAbsent(monkey.getServiceLocation().toLowerCase(), x -> new ArrayList<>())
            .add(monkey);

        // Training index
        monkeysByTraining
            .computeIfAbsent(monkey.getTrainingStatus(), x -> new ArrayList<>())
            .add(monkey);
    }
    
    // Check if they exist before adding
    public static boolean dogExists(String name) {
        if (name == null) return false;
        return dogsByName.containsKey(name.toLowerCase());
    }

    public static boolean monkeyExists(String name) {
        if (name == null) return false;
        return monkeysByName.containsKey(name.toLowerCase());
    }
    
    // Add new animals from user input
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine().trim();
        
        if (dogExists(name)) {
            System.out.println("\n\nThis dog is already in our system\n\n");
            return;
        }

        System.out.println("What is the dog's Breed?");
        String breed = scanner.nextLine();
        
        System.out.println("What is the dog's Gender?");
        String gender = scanner.nextLine();

        System.out.println("What is the dog's Age in years?");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("What is the dog's Weight in pounds?");
        double weight = Double.parseDouble(scanner.nextLine());
        
        System.out.println("What is the dog's Acquistion date (MM-DD-YYYY)?");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate acquisitionDate = LocalDate.parse(scanner.nextLine(), formatter);
        
        System.out.println("What is the dog's Acquistion Location?");
        String acquisitionLocation = scanner.nextLine();
        
        System.out.println("What is the dog's Training Status - Intake, Phase I, Phase II, Phase III, or In Service?");
        String trainingStatusInput = scanner.nextLine();

        // Convert user input to enum
        TrainingStatus trainingStatus;
        try {
        	trainingStatus = TrainingStatus.fromString(trainingStatusInput);
        } catch (IllegalArgumentException e) {
        	System.out.println("Invalid training status. Using INTAKE as default.");
        	trainingStatus = TrainingStatus.INTAKE;
        }
        
        System.out.println("Is the dog reserved true or false?");
        boolean reserved = scanner.nextBoolean();
        
        // Consume the newline character left in the buffer
        scanner.nextLine();
        
        System.out.println("What is the dog's Service County?");
        String inServiceCountry = scanner.nextLine();
        
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate,
                acquisitionLocation, trainingStatus, reserved, inServiceCountry);

        // Add to hash maps and to database
        addDog(newDog);
        dogDAO.insertDog(newDog);

    }
    
    public static void intakeNewMonkey(Scanner scanner) {          
        System.out.println("What is the monkey's name?");
        String name = scanner.nextLine().trim();
        
        if (monkeyExists(name)) {
            System.out.println("This monkey is already in the system.");
            return;
        }

        String border = "+--------------------------------------+";
	     	System.out.println("\n" + border);
	        System.out.println("        \u001B[1mWhat is the monkey's species?\u001B[0m");
	     	System.out.println(border);
	     	System.out.println("\u001B[36m[1]\u001B[0m Capuchin");
	     	System.out.println("\u001B[36m[2]\u001B[0m Macaque");
	     	System.out.println("\u001B[36m[3]\u001B[0m Guenon");
	     	System.out.println("\u001B[36m[4]\u001B[0m Marmoset");
     	System.out.println("\u001B[36m[5]\u001B[0m Squirrel Monkey");
     	System.out.println("\u001B[36m[6]\u001B[0m Tamarin");
        
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

            System.out.println("What is the monkey's Age in years?");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.println("What is the monkey's Weight in pounds?");
            double weight = Double.parseDouble(scanner.nextLine());

            System.out.println("What is the monkey's Tail Length in inches?");
            double tailLength = Double.parseDouble(scanner.nextLine());

            System.out.println("What is the monkey's Height in inches?");
            double height = Double.parseDouble(scanner.nextLine());

            System.out.println("What is the monkey's Body Length in inches?");
            double bodyLength = Double.parseDouble(scanner.nextLine());
            
            System.out.println("What is the monkey's Acquistion date (MM-DD-YYYY)?");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate acquisitionDate = LocalDate.parse(scanner.nextLine(), formatter);
            
            System.out.println("What is the monkey's Acquistion Location?");
            String acquisitionLocation = scanner.nextLine();
            
            System.out.println("What is the monkey's Training Status - Intake, Phase I, Phase II, Phase III, or In Service?");
            String trainingStatusInput = scanner.nextLine();
            
            TrainingStatus trainingStatus;
            try {
                trainingStatus = TrainingStatus.fromString(trainingStatusInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid training status. Using INTAKE as default.");
                trainingStatus = TrainingStatus.INTAKE;
            }
            
            System.out.println("Is the monkey reserved true or false?");
            boolean reserved = scanner.nextBoolean();
            
            // Consume the newline character left in the buffer
            scanner.nextLine();
            
            System.out.println("What is the monkey's Service County?");
            String serviceCountry = scanner.nextLine();
            
            Monkey newMonkey = (new Monkey(name, species, gender, age, weight, tailLength, height, bodyLength,
                    acquisitionDate, acquisitionLocation, trainingStatus, reserved, serviceCountry));
           
            // Add to hash maps and to database
            addMonkey(newMonkey);
            monkeyDAO.insertMonkey(newMonkey);

    }

    // Filters
    private static <T> List<T> filter(Collection<T> src, Predicate<T> p) {
        return src.stream().filter(p).collect(Collectors.toList());
    }

    public static List<Dog> getAvailableDogs() {
        return filter(dogsByName.values(),
            d -> d.getTrainingStatus() == TrainingStatus.IN_SERVICE && !d.getReserved()
        );
    }

    public static List<Monkey> getAvailableMonkeys() {
        return filter(monkeysByName.values(),
            m -> m.getTrainingStatus() == TrainingStatus.IN_SERVICE && !m.getReserved()
        );
    }

    // Reserve animals
    public static boolean reserveAnimal(String type, String country) {

        country = country.toLowerCase();

        if (type.equals("dog")) {
            List<Dog> candidates = dogsByCountry.get(country);
            if (candidates == null) return false;

            for (Dog d : candidates) {
                if (!d.getReserved() && d.getTrainingStatus() == TrainingStatus.IN_SERVICE) {
                    d.setReserved(true);
                    dogDAO.updateReserved(d.getName(), true); // persist change
                    return true;
                }
            }
        }

        else if (type.equals("monkey")) {
            List<Monkey> candidates = monkeysByCountry.get(country);
            if (candidates == null) return false;

            for (Monkey m : candidates) {
                if (!m.getReserved() && m.getTrainingStatus() == TrainingStatus.IN_SERVICE) {
                    m.setReserved(true);
                    monkeyDAO.updateReserved(m.getName(), true); // persist change
                    return true;

                }
            }
        }

        return false;
    }
    
    // Reserve animals from input
    public static void reserveAnimalInput(Scanner scanner) {
        System.out.println("Please enter desired animal type (dog or monkey):");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter the country for service:");
        String country = scanner.nextLine().trim().toLowerCase();

        boolean success = reserveAnimal(type, country);

        if (success)
            System.out.println("Animal successfully reserved!");
        else
            System.out.println("No available animals found for that request.");
    }

    // Printing
    public static void printAllDogs() {
        dogsByName.values().forEach(System.out::println);
    }

    public static void printAllMonkeys() {
        monkeysByName.values().forEach(System.out::println);
    }

    public static void printAvailableAnimals() {
        System.out.println("\n--- AVAILABLE DOGS ---");
        getAvailableDogs().forEach(System.out::println);

        System.out.println("\n--- AVAILABLE MONKEYS ---");
        getAvailableMonkeys().forEach(System.out::println);
    }
}
