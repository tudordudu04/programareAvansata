package Homework;

import java.util.Date;

/**
 * Represents a person with their name and date of birth.
 * Is used as a template for the Student and Teacher classes.
 */
public class Person {
    String name;
    Date dateOfBirth;

    /**
     * Gets the name of this person.
     * @return The name of this person.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the date of birth of this person.
     * @return The date of birth of this person.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
