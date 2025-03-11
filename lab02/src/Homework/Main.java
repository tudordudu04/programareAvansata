package Homework;

import java.util.Date;

/**
 * Main class that solves the Student Project Allocation Problem with a greedy algorithm
 */

public class Main {
    public static void main(String[] args) {

        //Made some problems
        Problem one = new Problem("Student Problem Allocation Problem");
        Problem two = new Problem("3-SAT");
        Problem three = new Problem("MST");
        Problem four = new Problem("Clique Cover Problem");
        Problem five = new Problem("Hamiltonian Path");
        Problem six = new Problem("Graph coloring");
        Problem seven = new Problem("Longest Path");
        Problem eight = new Problem("Maximum independent set");
        Problem[] allProblems = new Problem[]{one, two, three, four, five, six, seven, eight};

        //Made some projects
        Project first = new Project("Easy", new Problem[]{two, three});
        Project second = new Project("Medium", new Problem[]{four, five, six});
        Project third = new Project("Hard", new Problem[]{two, three, seven, eight});
        Project fourth = new Project("Insane", new Problem[]{one, four, six, seven, eight});
        Project fifth = new Project("Very Easy", new Problem[]{two});
        Project[] allProjects = new Project[]{first, second, third, fourth, fifth};

        //Made two teachers
        Teacher firstPeriod = new Teacher("David", new Date(8 / 1947), new Project[]{first, second, fifth});
        Teacher secondPeriod = new Teacher("Fred", new Date(5/9/1946), new Project[]{second, third, fourth});
//        Teacher thirdPeriod = new Teacher("Michael", new Date(29/8/1958), new Project[]{fourth, fifth});
        Teacher[] allTeachers = new Teacher[]{firstPeriod, secondPeriod};

        //Make some students
        Student studentOne = new Student("Jeff", new Date(17/11/1966), "0001", new Project[]{second, third});
        Student studentTwo = new Student("Bruce", new Date(23/9/1949), "0002", new Project[]{third, fourth, fifth});
        Student studentThree = new Student("Lauryn", new Date(26/5/1975), "0003", new Project[]{first, third, second});
        Student studentFour = new Student("Thom", new Date(7/10/1968), "0004", new Project[]{first, second, third, fourth});
        Student studentFifth = new Student("Tyler", new Date(6/3/1991), "0005", new Project[]{first, second, third});
        Student[] allStudents = new Student[]{studentOne, studentTwo, studentThree, studentFour, studentFifth};

        //Iterate over the list of students to assign them their preferred project if possible
        for(int i = 0; i < allStudents.length; i++){
            Student s = allStudents[i];
            for(Project p : s.preferredProjects){
                if(!p.isAssigned())
                    for(Teacher t : allTeachers) {
                        for (int j = 0; j < t.projects.length; j++) {
                            if (t.projects[j].equals(p)) {
                                t.assignProject(p, s);
                                break;
                            }
                        }
                        if(p.isAssigned()) {
                            break;
                        }
                    }
                if(s.hasProject()){
                    break;
                }
            }
            if(!s.hasProject()) {
                for(Project p : allProjects){
                    if(!p.isAssigned())
                        for(Teacher t : allTeachers) {
                            for (int j = 0; j < t.projects.length; j++) {
                                if (t.projects[j].equals(p)) {
                                    t.assignProject(p, s);
                                    break;
                                }
                            }
                            if(p.isAssigned()) {
                                break;
                            }
                        }
                }
                if(s.hasProject()){
                    break;
                }
            }
        }

        //Show a list of all the persons involved with every problem.
        for(Problem problem : allProblems) {
            Person[] persons = problem.getPersons();
            for(Person p : persons) {
                String name = p.getName();
                Date birthDate = p.dateOfBirth;
                System.out.print(name + " " + birthDate);
                System.out.println();
            }
        }

        //Show a list of all the students with their assigned projects.
        for(Student s : allStudents){
            Project aux = s.getCurrentProject();
            System.out.println("Student: " + s.getName() + " has the project: " + aux.getProjectName());
        }

    }
}
