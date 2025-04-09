package Compulsory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class RepositoryService implements Serializable {
    Repository repository = null;

    public Repository getRepository() {
        return this.repository;
    }

    public void setRepository(Repository repo) {
        this.repository = repo;
    }

    public void printAll() throws IOException {
        for(Image image : this.repository.getImages()) {
            File imageFile = new File(image.location());
            if (imageFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(imageFile);
            } else {
                System.out.println("Image file not found or cannot be opened: " + image.location());
            }
            System.out.println(image.tags());
        }
    }
}
