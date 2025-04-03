package Compulsory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    List<Image> images = new ArrayList();

    public Repository(String name) {
        this.name = name;
    }

    List<Image> getImages() {
        return this.images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void updateImage(String imageName, Image newImage) {
        for(Image image : this.images) {
            if (image.name().equals(imageName)) {
                this.images.remove(image);
                this.images.add(newImage);
            }
        }

    }

    public void removeImage(String imageName) {
        for(Image image : this.images) {
            if (image.name().equals(imageName)) {
                this.images.remove(image);
            }
        }

    }

    public void addAll(String folder) {
    }

    public String toString() {
        return this.name;
    }
}
