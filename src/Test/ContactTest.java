package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import Contact.Contact;

class ContactTest {

    @Test
    void testSuccessfulGetters() {
        Contact contact = new Contact("1", "First", "Last", "1234567891", "123 Main Street");
        
        assertEquals("First", contact.getFirstName());
        assertEquals("Last", contact.getLastName());
        assertEquals("1234567891", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }
    @Test
    void testSuccessfulSetters() {
        Contact contact = new Contact("1", "First", "Last", "1234567890", "123 Main Street");
        
        contact.setFirstName("UpdatedFir");
        contact.setLastName("UpdatedLas");
        contact.setPhone("0987654321");
        contact.setAddress("Updated Address");
        
        assertEquals("UpdatedFir", contact.getFirstName());
        assertEquals("UpdatedLas", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("Updated Address", contact.getAddress());
    }

    @Test
    void testInvalidPhoneInSetter() {
        Contact contact = new Contact("1", "First", "Last", "1234567890", "123 Main Street");
        
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("Invalid123");  // Non-numeric phone number
        });
    }

    @ParameterizedTest
    @CsvSource({
        "1, First, Last, 123, 123 Main Street",               // Invalid phone number (too short)
        "1, First, Last, abcdefghij, 123 Main Street",         // Invalid phone number (non-numeric)
        "1, First, Last, 1234567891, ''",                     // Empty address
        "1, First, Last, 1234567891, 1234567890123456789012345678901" // Address too long
    })
    void testInvalidContactCreation(String contactID, String firstName, String lastName, String number, String address) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(contactID, firstName, lastName, number, address);
        });
    }
    
    @Test
    void testMaxLength() {
        Contact contact = new Contact("1234567890", "FirstName1", "LastName10", "1234567890", "1234 Max Length Street City CA");
        
        assertEquals("1234567890", contact.getContactID());
        assertEquals("FirstName1", contact.getFirstName());
        assertEquals("LastName10", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("1234 Max Length Street City CA", contact.getAddress());
    }
    
    @ParameterizedTest
    @CsvSource({
        "12345678901, FirstName1, LastName10, 1234567890, 1234 Max Length Street City CA",  // Contact ID too long
        "1234567890, FirstName11, LastName10, 1234567890, 1234 Max Length Street City CA",  // First name too long
        "1234567890, FirstName1, LastName101, 1234567890, 1234 Max Length Street City CA",  // Last name too long
        "1234567890, FirstName1, LastName10, 12345678901, 1234 Max Length Street City CA",  // Phone Number too long
        "1234567890, FirstName1, LastName10, 1234567890, 1234 Extra Length Street City CA"  // Address too long
    })
    void testLongLengths(String contactID, String firstName, String lastName, String phone, String address) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(contactID, firstName, lastName, phone, address);
        });
    }
    
    @ParameterizedTest
    @CsvSource({
        "'', First, Last, 1234567890, 123 Main Street",     // Empty contact ID
        "1, '', Last, 1234567890, 123 Main Street",         // Empty first name
        "1, First, '', 1234567890, 123 Main Street",        // Empty last name
        "1, First, Last, '', 123 Main Street",				// Empty phone
        "1, First, Last, 1234567890, ''"					// Empty address
    })
    void testEmptyFields(String contactID, String firstName, String lastName, String phone, String address) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(contactID, firstName, lastName, phone, address);
        });
    }


}
