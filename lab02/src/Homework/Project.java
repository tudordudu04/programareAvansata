package Homework;

/**
 * Represents a project with a name, a set of problems, a student which is assigned this project and a teacher that assigned that project.
 */

public class Project {
    String projectName;
    Problem[] problems = new Problem[0];
    Student student = null;
    Teacher teacher;

    /**
     * Constructs a new project with the specified name and list of problems it is composed of.
     * @param projectName The name of the project.
     * @param problems The list of problems it is composed of.
     */
    public Project(String projectName, Problem[] problems) {
        this.projectName = projectName;
        this.problems = problems;
    }

    /**
     * Checks if this project is assigned to any student.
     * @return Return true if it is currently assigned to any student and false otherwise.
     */
    public boolean isAssigned(){
        return student != null;
    }

    /**
     * Assigns the tuple of student, teacher and project to the list of problems the project is composed of.
     */
    void addToProblems(){
        for (Problem problem : problems) {
            problem.addStudent(this.student);
            problem.addTeacher(this.teacher);
            problem.addProject(this);
        }
    }

    /**
     * Set this project as assigned to the specified student and teacher pair.
     * @param student The student that has this project assigned.
     * @param teacher The teacher that assigned this project.
     */
    public void assignProject(Student student, Teacher teacher){
        if(!this.isAssigned()){
            this.student = student;
            this.teacher = teacher;
            addToProblems();
        }
    }

    /**
     * Gets the name of this project.
     * @return The name of this project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Checks if this project is the same as another object
     * @param other The object it is compared to
     * @return Returns true if the projects are the same and false otherwise
     */
    @Override
    public boolean equals(Object other){
        boolean result;
        if((other == null) || (this.getClass() != other.getClass())){
            result = false;
        }
        else{
            Project otherProject = (Project)other;
            result = this.projectName.equals(otherProject.projectName);
        } // end else

        return result;
    } // end equals
}
