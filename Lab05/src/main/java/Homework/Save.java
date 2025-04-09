package Homework;

import Compulsory.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class Save extends Command implements Serializable {
    private static final long serialVersionUID = 1L;
    public String path;
    public String argument;

    public Save(String path, String argument) {
        this.path = path;
        this.argument = argument;
    }

    public void execute(RepositoryService service) {
        this.path = this.path + "/" + service.getRepository().toString();
        switch (this.argument) {
            case "-b" -> {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.path))) {
                    oos.writeObject(service.getRepository());
                    System.out.println("Repository saved successfully.");
                } catch (IOException e) {
                    System.out.println("Failed to save repository.");
                }
            }
            case "-p" -> {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
                    writer.write(service.getRepository().toString());
                    System.out.println("Repository saved successfully in plaintext format.");
                } catch (IOException e) {
                    System.out.println("Failed to save repository in plaintext format.");
                }
            }
            case "-j" -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writeValue(new File(this.path), service.getRepository());
                    System.out.println("Repository saved successfully in JSON format.");
                } catch (IOException e) {
                    System.out.println("Failed to save repository in JSON format.");
                }
            }
        }
    }
}
