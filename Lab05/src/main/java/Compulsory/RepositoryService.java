package Compulsory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class RepositoryService implements Serializable {
    Repository repository;

    public RepositoryService(Repository repo) {
        this.repository = repo;
    }

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
        }

    }

    public void printImage(String name) throws IOException {
        for(Image image : this.repository.getImages()) {
            if (image.name().equals(name)) {
                File imageFile = new File(image.location());
                if (imageFile.exists() && Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(imageFile);
                } else {
                    System.out.println("Image file not found or cannot be opened.");
                }

                return;
            }
        }

        System.out.println("Image not found in the repository.");
    }
}
