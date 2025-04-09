package Homework;

import Compulsory.Image;
import Compulsory.RepositoryService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddAll extends Command {
    String originalPath;
    public AddAll(String path) {
        this.setType(Type.ADDALL);
        this.originalPath = path;
    }

    public void execute(RepositoryService service) {
        File directory = new File(originalPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory: " + originalPath);
            return;
        }

        try {
            Files.walk(directory.toPath())
                    .filter(Files::isRegularFile)
                    .filter(path -> isImage(path.toFile()))
                    .forEach(path -> {
                        Image image = new Image("AddAll", new Date(), new ArrayList<>(), path.toString());
                        service.getRepository().addImage(image);
                    });
        } catch (IOException e) {
            System.out.println("Error reading directory: " + e.getMessage());
        }
    }
    private boolean isImage(File path) {
        try {
            BufferedImage image = ImageIO.read(path);
            return image != null;
        } catch (IOException var) {
            return false;
        }
    }
}
