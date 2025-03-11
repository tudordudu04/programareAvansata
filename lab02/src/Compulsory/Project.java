package Compulsory;

public class Project {
    String name;

    ProjectType type;

    public Project(String name, ProjectType type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(ProjectType type) {
        this.type = type;
    }
    public String getType() {
        return this.type.toString();
    }
    public String toString() {
        return this.name;
    }
}
