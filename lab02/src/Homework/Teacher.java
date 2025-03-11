package Homework;

import java.util.Date;

/**
 * Represents a teacher with a list of proposed projects.
 * It inherits the Person class.
 */

public class Teacher extends Person {
    Project[] projects;

    /**
     * Constructs a new Teacher with the specified name, date of birth and list of proposed projects
     * @param name The name of the teacher.
     * @param dateOfBirth The date of birth of the teacher.
     * @param projects The list of proposed projects by the teacher.
     */
    public Teacher(String name, Date dateOfBirth, Project[] projects) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.projects = projects;
    }

    /**
     * Gets a list of the proposed projects by the teacher.
     * @return A list of the proposed projects by the teacher.
     */
    public Project[] getProjects(){
        return projects;
    }

    /**
     * Assigns the specified project to the specified student.
     * @param project The project that is assigned by the teacher.
     * @param student The student to which the project is assigned to.
     */
    public void assignProject(Project project, Student student){
        project.assignProject(student, this);
        student.setCurrentProject(project);
    }

//    public Solution getSolution(){
//
//    }

    /**
     * Checks if this teacher is the same as another object
     * @param other The object it is compared to
     * @return Returns true if the teachers are the same and false otherwise
     */
    @Override
    public boolean equals(Object other){
        boolean result;
        if((other == null) || (this.getClass() != other.getClass())){
            result = false;
        }
        else{
            Teacher otherProject = (Teacher)other;
            result = this.name.equals(otherProject.name);
        } // end else

        return result;
    } // end equals
}
