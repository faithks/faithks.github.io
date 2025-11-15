/*
 *  CS 320 
 *  This program creates contacts, appointments, and tasks 
 *  Each service has multiple variables which are all validated
 *  Each item and services is tested using JUnit testing
 *  Programmer: Faith Sheppard
 *  Code to use for Artifact 1 Category 1
 */

package Appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentID;
    private final Date appointmentDate;
    private String apptDescription;

    // Constructor with validation
    public Appointment(String appointmentID, Date appointmentDate, String apptDescription) {
        // Validate appointmentID
        if (appointmentID == null || appointmentID.isEmpty() || appointmentID.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null, empty, and must be 10 characters or less.");
        }

        // Validate appointmentDate
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }

        // Validate description
        if (apptDescription == null || apptDescription.isEmpty() || apptDescription.length() > 50) {
            throw new IllegalArgumentException("The appointment�s description must not be null, empty, and must be 50 characters or less.");
        }

        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.apptDescription = apptDescription;
    }

    // Getters
    public String getAppointmentID() {
        return appointmentID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getApptDescription() {
        return apptDescription;
    }

    // Setters
    public void setApptDescription(String apptDescription) {
        if (apptDescription == null || apptDescription.isEmpty() || apptDescription.length() > 50) {
            throw new IllegalArgumentException("The appointment�s description must not be null, empty, and must be 50 characters or less.");
        }
        this.apptDescription = apptDescription;
    }

    // No setter for appointmentID or appointmentDate to keep them immutable
}
