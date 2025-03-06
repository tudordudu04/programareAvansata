import java.util.ArrayList;

public class Student {
    String name;
    ArrayList<Project> preferredProjects = new ArrayList<>();
    public Student(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void addPreferredProjects(Project p){
        preferredProjects.add(p);
    }
    public void addPreferredProjects(ArrayList<Project> p){
        preferredProjects.addAll(p);
    }
    public ArrayList<Project> getPreferredProjects(){
        return preferredProjects;
    }

    public void printProjects(){
        for(Project p : preferredProjects){
            System.out.print(p.toString() + " ");
        }
    }

//    public String toString() {
//        System.out.println(this.name + ": "+ this.preferredProjects.toString());
//    }


}
