package Homework;

import java.util.Date;

/**
 * Represents a student with a registration number, a list of preferred projects and a project he is assigned to.
 * It inherits the Person class.
 */

public class Student extends Person {
    String registrationNumber;
    Project[] preferredProjects;
    Project currentProject = null;

    /**
     * Constructs a new Student with the specified name, date of birth, registration number and list of preferred projects.
     * @param name The name of the student.
     * @param date The date of birth of the student.
     * @param registrationNumber The registration number of the student.
     * @param preferredProjects The list of preferred projects of the student.
     */
    public Student(String name, Date date, String registrationNumber, Project[] preferredProjects) {
        this.dateOfBirth = date;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.preferredProjects = preferredProjects;
    }

    /**
     * Gets the registration number of the student.
     * @return The registration number of the student.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    /**
     * Gets the list of preferred projects of the student.
     * @return The list of preferred projects of the student.
     */
    public Project[] getPreferredProjects() {
        return preferredProjects;
    }

    /**
     * Sets the project the student is working on to the specified one.
     * @param currentProject The project that the student is currently working on.
     */
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    /**
     * Gets the project the student is currently working on.
     * @return The project the student is currently working on.
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    /**
     * Verifies if the student is currently assigned a project.
     * @return Returns true if the student has a project assigned and false otherwise.
     */
    public boolean hasProject(){
        return currentProject != null;
    }

    /**
     * Checks if this student is the same as another object
     * @param other The object it is compared to
     * @return Returns true if the students are the same and false otherwise
     */
    @Override
    public boolean equals(Object other){
        boolean result;
        if((other == null) || (this.getClass() != other.getClass())){
            result = false;
        }
        else{
            Student otherProject = (Student) other;
            result = this.name.equals(otherProject.name);
        } // end else

        return result;
    } // end equals
}
