package Homework;

import java.util.Arrays;

/**
 * Represents a problem with a name, a solution, a list of students that are solving this problem,
 * a list of teachers that assigned this problem and a list of projects in which this problem is present.
 */

public class Problem {
    String problemName;
    Student[] students = new Student[0];
    Teacher[] teachers = new Teacher[0];
    Project[] projects = new Project[0];
    Solution solution;

    /**
     * Constructs a new problem with the specified name.
     * Creates a solution with the specified name + "Solution" at the end
     * @param problemName The name of this problem.
     */
    public Problem(String problemName){
        this.problemName = problemName;
        this.solution = new Solution(problemName+"Solution");
    }

    /**
     * Adds the specified student to the list of students that are working on this problem, without duplicates.
     * @param student The student that is working on this problem.
     */
    public void addStudent(Student student){
        int ok = 1;
        for(Student s : this.students){
            if(s.equals(student)){
                ok = 0;
                break;
            }
        }
        if(ok == 1) {
            this.students = Arrays.copyOf(this.students, this.students.length + 1);
            this.students[this.students.length - 1] = student;
        }
    }

    /**
     * Adds the specified teacher to the list of teachers that proposed this problem, without duplicates.
     * @param teacher The teacher that proposed this problem
     */
    public void addTeacher(Teacher teacher){
        int ok = 1;
        for(Teacher t : this.teachers){
            if(t.equals(teacher)){
                ok = 0;
                break;
            }
        }
        if(ok == 1) {
            this.teachers = Arrays.copyOf(this.teachers, this.teachers.length + 1);
            this.teachers[this.teachers.length - 1] = teacher;
        }
    }

    /**
     * Adds the specified project to the list of projects in which this problem is found, without duplicates.
     * @param project The projects in which this problem is found.
     */
    public void addProject(Project project){
        int ok = 1;
        for(Project p : this.projects){
            if(p.equals(project)){
                ok = 0;
                break;
            }
        }
        if(ok == 1) {
            this.projects = Arrays.copyOf(this.projects, this.projects.length + 1);
            this.projects[this.projects.length - 1] = project;
        }
    }

    /**
     * Gets the list of persons that are involved with this problem.
     * @return The list of persons that are involved with this problem.
     */
    public Person[] getPersons(){
        Person[] array = new Person[this.students.length+this.teachers.length];
        int i;
        for(i = 0; i < this.students.length; i++){
            array[i] = this.students[i];
        }
        for(int j = 0; j < this.teachers.length; j++){
            array[i] = this.teachers[j];
            i++;
        }
        return array;
    }

}
