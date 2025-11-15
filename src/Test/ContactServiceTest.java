package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Contact.Contact;
import Contact.ContactService;

class ContactServiceTest {
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }

    @Test
    void testAddContact() {
        Contact contact = new Contact("1", "First", "Last", "1234567890", "123 Main Street");
        contactService.addContact(contact);
        
     // Verify that the contact was added successfully
        Contact retrieved = contactService.getContact("1");
        assertEquals(contact, retrieved);
    }

    @Test
    void testAddDuplicateContact() {
        Contact contact1 = new Contact("1", "First", "Last", "1234567890", "123 Main Street");
        contactService.addContact(contact1);

        Contact contact2 = new Contact("1", "Second", "Last", "0987654321", "456 Another St");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Contact("11", "First", "Last", "1234567890", "123 Main Street");
        contactService.addContact(contact);
        contactService.deleteContact("11");
    }

    @Test
    void testUpdateContact() {
        Contact contact = new Contact("12", "First", "Last", "1234567890", "123 Main Street");
        contactService.addContact(contact);
        
        Contact updatedContact = new Contact("12", "UpdatedFir", "UpdatedLas", "0987654321", "Updated Address");
        contactService.update("12", updatedContact);
        
        //Test update was successful
        Contact result = contactService.getContact("12");
        assertEquals("UpdatedFir", result.getFirstName());
        assertEquals("UpdatedLas", result.getLastName());
        assertEquals("0987654321", result.getPhone());
        assertEquals("Updated Address", result.getAddress());

    }
    
    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });
    }

    @Test
    void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("5555");
        });
    }

    @Test
    void testUpdateNonExistentContact() {
        Contact updatedContact = new Contact("1", "UpdatedFir", "UpdatedLas", "0987654321", "Updated Address");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.update("5555", updatedContact);
        });
    }

    @Test
    void testAddAndDelteMultipleContacts() {
        Contact contact1 = new Contact("1", "First", "Last", "1234567890", "123 Main Street");
        Contact contact2 = new Contact("2", "Second", "Last", "0987654321", "456 Another St");
        contactService.addContact(contact1);
        contactService.addContact(contact2);
        
        //test both contacts were added
        assertEquals(contact1, contactService.getContact("1"));
        assertEquals(contact2, contactService.getContact("2"));
        
        //delete one contact
        contactService.deleteContact("1");
        
        //ensure only one contact was deleted
        assertNull(contactService.getContact("1"));
        assertEquals(contact2, contactService.getContact("2"));
        
        //delete the second contact
        contactService.deleteContact("2");
        
        //ensure both were deleted
        assertNull(contactService.getContact("1"));
        assertNull(contactService.getContact("2"));
    }

}
