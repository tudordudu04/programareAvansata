package Homework;

import Compulsory.Repository;
import Compulsory.RepositoryService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Load extends Command implements Serializable {
    String path;

    public Load(String path) {
        this.path = path;
    }

    public void execute(RepositoryService service) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path))) {
            service.setRepository((Repository)ois.readObject());
            System.out.println("Repository loaded successfully.");
        } catch (ClassNotFoundException | IOException e) {
            ((Exception)e).printStackTrace();
            System.out.println("Failed to load repository.");
        }

    }
}
