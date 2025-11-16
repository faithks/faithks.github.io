package Contact;

public class Contact {
    private final String contactID;  // Cannot be modified after initialization
    private String firstName;
    private String lastName;
    private String phone;  
    private String address;

    // Constructor with validation
    public Contact(String contactID, String firstName, String lastName, String phone, String address) {
        // Validate contactID
    	if (contactID == null || contactID.isEmpty() || contactID.length() > 10) {
            throw new IllegalArgumentException("Contact ID must not be null, empty, and must be 10 characters or less.");
        }
    	// Validate first name
        if (firstName == null || firstName.isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("First name must not be null, empty, and must be 10 characters or less.");
        }
        // Validate last name
        if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name must not be null, empty, and must be 10 characters or less.");
        }
        // Validate phone number
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {  
            throw new IllegalArgumentException("Phone must not be null, and must contain exactly 10 digits.");
        }
        // Validate address
        if (address == null || address.isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Address must not be null, empty, and must be 30 characters or less.");
        }

        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getters
    public String getContactID() {
        return contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters with validation
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("First name must not be null, empty, and must be 10 characters or less.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name must not be null, empty, and must be 10 characters or less.");
        }
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {  
            throw new IllegalArgumentException("Phone must not be null, and must contain exactly 10 digits.");
        }
        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Address must not be null, empty, and must be 30 characters or less.");
        }
        this.address = address;
    }
    
    // No setter for contactID to keep it immutable
}
