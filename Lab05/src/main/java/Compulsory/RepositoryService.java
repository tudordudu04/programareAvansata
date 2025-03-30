package Compulsory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Desktop;

public class RepositoryService{
    Repository repository;

    public RepositoryService(Repository repo) {
        this.repository = repo;
    }



    //work on this maybe
    public void printImage(String name) throws IOException {
        List<Image> images = repository.getImages();
        for (Image image : images) {
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
