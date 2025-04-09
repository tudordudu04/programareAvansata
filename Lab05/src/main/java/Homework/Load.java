package Homework;

import Compulsory.Repository;
import Compulsory.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Load extends Command implements Serializable {
    String path;
    String argument;

    public Load(String path, String argument) {
        this.path = path;
        this.argument = argument;
    }

    public void execute(RepositoryService service) {

            if(this.argument .equals("-b")){
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path))) {
                    service.setRepository((Repository)ois.readObject());
                    System.out.println("Repository loaded successfully.");
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Failed to load repository.");
                }
            }
            if(this.argument.equals("-p")){
                try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    String repositoryData = content.toString();
                    // Assuming `Repository` has a method to parse itself from a string
//                    service.setRepository(Repository.parseFromString(repositoryData));
                    System.out.println("Repository loaded successfully from plaintext.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Failed to load repository from plaintext.");
                }
            }
            if(this.argument.equals("-j")){
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Repository repository = objectMapper.readValue(new File(this.path), Repository.class);
                    service.setRepository(repository);
                    System.out.println("Repository loaded successfully from JSON.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Failed to load repository from JSON.");
                }
            }
    }
}
