import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("David"));
        students.add(new Student("John"));
        students.add(new Student("Mary"));
        Project proiect1 = new Project("Website", ProjectType.PRACTICAL);
        Project proiect2 = new Project("DataBase", ProjectType.PRACTICAL);
        Project proiect3 = new Project("Reading", ProjectType.PRACTICAL);
        Project proiect4 = new Project("Writing", ProjectType.PRACTICAL);
        Project proiect5 = new Project("Sightseeing", ProjectType.PRACTICAL);
        Project proiect6 = new Project("Exploring", ProjectType.PRACTICAL);

        ArrayList<Project> projectsAvailable = new ArrayList<>();
        projectsAvailable.add(proiect1);
        projectsAvailable.add(proiect2);
        System.out.println(projectsAvailable.getFirst().getName());
        students.getFirst().addPreferredProjects(projectsAvailable);
        System.out.println(students.getFirst().getPreferredProjects());
    }
}