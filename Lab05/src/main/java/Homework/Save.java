package Homework;

import Compulsory.RepositoryService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Save extends Command implements Serializable {
    String path;

    public Save(String path) {
        this.path = path;
    }

    public void execute(RepositoryService service) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path))) {
            oos.writeObject(service.getRepository());
            System.out.println("Repository saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save repository.");
        }

    }
}
