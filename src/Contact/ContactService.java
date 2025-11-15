package Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    // Method to add a new contact
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        //adding
        contacts.put(contact.getContactID(), contact);
    }

    // Method to delete a contact by contact ID
    public void deleteContact(String contactID) {
        if (contactID == null || !contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("No contact found with ID");
        }
        //removing
        contacts.remove(contactID);
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
    }
    
    // Method to retrieve a contact by ID for testing
    public Contact getContact(String contactID) {
        return contacts.get(contactID); // Returns null if not found
    }
}
