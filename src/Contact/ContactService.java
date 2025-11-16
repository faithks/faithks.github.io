package Contact;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();
    private final String FILE_PATH = "contacts.csv";
    
    public ContactService() {
    	loadFromFile();
    }

    // Method to add a new contact
    public void addContact(Contact contact) {
    	// Validate Contact
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        // Adding
        contacts.put(contact.getContactID(), contact);
        saveToFile();
    }

    // Method to delete a contact by contact ID
    public void deleteContact(String contactID) {
    	// Search by contactID
        if (contactID == null || !contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("No contact found with ID");
        }
        // Removing contact
        contacts.remove(contactID);
        saveToFile();
    }

    // Method to update a contact's fields
    public void update(String contactID, Contact updated) {
        if (contactID == null || updated == null) {
            throw new IllegalArgumentException("Contact ID and/or updated contact cannot be null.");
        }
        Contact existingContact = contacts.get(contactID);
        if (existingContact == null) {
            throw new IllegalArgumentException("No contact found with ID.");
        }

        // Updating fields 
        existingContact.setFirstName(updated.getFirstName());
        existingContact.setLastName(updated.getLastName());
        existingContact.setPhone(updated.getPhone());
        existingContact.setAddress(updated.getAddress());
        
        saveToFile();
    }
    
    private void saveToFile() {
    	try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Contact c : contacts.values()) {
            	pw.println(c.getContactID() + "," +
            				c.getFirstName() + "," +
            				c.getLastName() + "," +
            				c.getPhone() + "," +
            				c.getAddress());
            	}
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving contacts: " + e.getMessage() + "\u001B[0m");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Contact c = new Contact(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    contacts.put(c.getContactID(), c);
                }
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError loading contacts: " + e.getMessage() + "\u001B[0m");
        }
    }
    
    // Method to retrieve a contact by ID for testing
    public Contact getContact(String contactID) {
        return contacts.get(contactID); // Returns null if not found
    }
    
    public boolean exists(String contactID) {
        return contacts.containsKey(contactID);
    }
}
