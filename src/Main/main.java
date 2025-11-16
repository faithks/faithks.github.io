package Main;

import java.util.Scanner;
import java.util.Date;
import Appointment.*;
import Contact.*;
import Task.*;

public class main {

	public static void main(String[] args) {
		// Authenticate User before logging in
		Scanner scanner = new Scanner(System.in);
		userAuthentication userAuth = new userAuthentication();
		
		// Take in user's input and authenticate
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		if(!userAuth.authenticate(username, password)) {
			System.out.println("\u001B[31mInvalid ID. Please try again or contact your Admin.\u001B[0m");
			scanner.close();
			return;
		}
		
		System.out.println("Successful login. Please welcome to the main appointment menu.");
		
		// Initialize Services
		ContactService contactService = new ContactService();
        AppointmentService appointmentService = new AppointmentService(contactService);
        TaskService taskService = new TaskService(appointmentService);
        
        // Display Menu with a loop
        while (true) {
        	printMainMenu();
            int option = Integer.parseInt(scanner.nextLine());
            
            switch (option) {
            case 1:
                contactOptions(scanner, contactService);
                break;
            case 2:
                appointmentOptions(scanner, appointmentService, contactService);
                break;
            case 3:
                taskOptions(scanner, taskService, appointmentService);
                break;
            case 4:
                System.out.println("\u001B[31mLogging out...\u001B[0m");
                scanner.close();
                return;
            default:
                System.out.println("\u001B[31mInvalid option.\u001B[0m Please try again.");
            }
            
        }

	}
	
	private static void printMainMenu() {
        String border = "========================================";
        System.out.println("\n" + border);
        System.out.println("        \u001B[1mWelcome to Your Menu\u001B[0m");
        System.out.println(border);
        System.out.println("\u001B[36m1.\u001B[0m Manage Contacts");
        System.out.println("\u001B[36m2.\u001B[0m Manage Appointments");
        System.out.println("\u001B[36m3.\u001B[0m Manage Tasks");
        System.out.println("\u001B[36m4.\u001B[0m Exit");
        System.out.print("\nEnter option: ");
    }
	
	private static void printSubMenu(String title) {
		 String border = "+--------------------------------------+";
	     System.out.println("\n" + border);
	     System.out.printf("| %-36s |\n", title);
	     System.out.println(border);
	     System.out.println("\u001B[36m1.\u001B[0m Add");
	     System.out.println("\u001B[36m2.\u001B[0m Delete");
	     System.out.println("\u001B[36m3.\u001B[0m Update");
	     System.out.println("\u001B[36m4.\u001B[0m Return to main menu");
	     System.out.print("\nSelect option: ");  
	}

	private static void contactOptions(Scanner scanner, ContactService service) {
		// Display Contact menu
		printSubMenu("Contact Management");
        
        int option = Integer.parseInt(scanner.nextLine());
        
        try {
            switch (option) {
                case 1:
                    System.out.print("\nEnter ID: ");
                    String id = scanner.nextLine();
                    
                    System.out.print("First Name: ");
                    String fn = scanner.nextLine();
                    
                    System.out.print("Last Name: ");
                    String ln = scanner.nextLine();
                    
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    
                    service.addContact(new Contact(id, fn, ln, phone, address));
                    System.out.println("\u001B[32mContact created.\u001B[0m.");
                    break;
                case 2:
                    System.out.print("\nEnter ID to delete: ");
                    service.deleteContact(scanner.nextLine());
                    
                    System.out.println("\u001B[31mContact deleted.\u001B[0m");
                    break;
                case 3:
                    System.out.print("\nEnter ID to update: ");
                    String contactID = scanner.nextLine();
                    
                    System.out.print("New First Name: ");
                    fn = scanner.nextLine();
                    
                    System.out.print("New Last Name: ");
                    ln = scanner.nextLine();
                    
                    System.out.print("New Phone: ");
                    phone = scanner.nextLine();
                    
                    System.out.print("New Address: ");
                    address = scanner.nextLine();
                    
                    service.update(contactID, new Contact(contactID, fn, ln, phone, address));
                    System.out.println("\u001B[32mContact updated.\u001B[0m");
                    break;
                case 4:
                	break;
                default:
                    System.out.println("\u001B[31mInvalid option.\u001B[0m");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("\u001B[31mError:\u001B[0m" + ex.getMessage());
        }
	}
	
	private static void appointmentOptions(Scanner scanner, AppointmentService service, ContactService contactService) {
        // Display Appointment menu
		printSubMenu("Appointment Management");

        int option = Integer.parseInt(scanner.nextLine());

        try {
            switch (option) {
                case 1:
                    System.out.print("\nEnter ID: ");
                    String id = scanner.nextLine();
                    
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    Date date = java.sql.Date.valueOf(scanner.nextLine());
                    
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    
                    System.out.print("Enter contact ID for this appointment: ");;
                    String contactID = scanner.nextLine();
                    
                    if (contactService.getContact(contactID) == null) {
                        System.out.println("\u001B[31mError: Contact does not exist.\u001B[0m");
                    } else {
                    service.addAppointment(new Appointment(id, date, desc, contactID));
                    System.out.println("\u001B[32mAppointment added.\u001B[0m");
                    }
                    break;
                case 2:
                    System.out.print("\nEnter ID to delete: ");
                    service.deleteAppointment(scanner.nextLine());
                    System.out.println("\u001B[31mAppointment deleted.\u001B[0m");
                    break;
                case 3:
                    System.out.print("\nEnter ID to update: ");
                    String apptID = scanner.nextLine();

                    System.out.print("New description: ");
                    String newDesc = scanner.nextLine();

                    Appointment existing = service.getAppointment(apptID);
                    service.update(apptID, new Appointment(apptID, existing.getDate(), newDesc, existing.getContactID()));
                    System.out.println("\u001B[32mAppointment updated.\u001B[0m");
                    break;
                case 4:
                	break;
                default:
                    System.out.println("\u001B[31mInvalid option.\u001B[0m");
            }
        } catch (Exception ex) {
            System.out.println("\u001B[31mError:\u001B[0m " + ex.getMessage());
        }
    }
	
	 private static void taskOptions(Scanner scanner, TaskService service, AppointmentService appointmentService) {
		 	// Display Task menu
			printSubMenu("Task Management");

	        int option = Integer.parseInt(scanner.nextLine());

	        try {
	            switch (option) {
	                case 1:
	                    System.out.print("\nTask ID: ");
	                    String id = scanner.nextLine();
	                    
	                    System.out.print("Name: ");
	                    String name = scanner.nextLine();
	                    
	                    System.out.print("Description: ");
	                    String desc = scanner.nextLine();
	                    
	                    System.out.print("Enter Appointment ID for this task: ");
	                    String appointmentID = scanner.nextLine();
	                    
	                    if (appointmentService.getAppointment(appointmentID) == null) {
	                        System.out.println("\u001B[31mError: Appointment does not exist.\u001B[0m");
	                    } else {
	                        service.addTask(new Task(id, name, desc, appointmentID));
	                        System.out.println("\u001B[32mTask added.\u001B[0m");
	                    }
	                    break;
	                case 2:
	                    System.out.print("\nEnter ID to delete: ");
	                    service.deleteTask(scanner.nextLine());
	                    
	                    System.out.println("\u001B[31mTask deleted.\u001B[0m");
	                    break;
	                case 3:
	                    System.out.print("\nEnter ID to update: ");
	                    String taskID = scanner.nextLine();
	                    Task existing = service.getTask(taskID);
	                    
	                    System.out.print("New Name: ");
	                    name = scanner.nextLine();
	                    
	                    System.out.print("New Description: ");
	                    desc = scanner.nextLine();
	                    
	                    service.update(taskID, new Task(taskID, name, desc, existing.getAppointmentID()));
	                    System.out.println("\u001B[32mTask updated.\u001B[0m");
	                    break;
	                case 4:
	                	break;
	                default:
	                    System.out.println("\u001B[31mInvalid option.\u001B[0m");
	            }
	        } catch (IllegalArgumentException ex) {
	            System.out.println("\u001B[31mError:\u001B[0m " + ex.getMessage());
	        }
	    }

}
