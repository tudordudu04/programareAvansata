package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Repository{
    String name;
    private List<Image> images = new ArrayList<>();
    //may have other properties such as a name

    public Repository(String name) {
        this.name = name;
    }
    // getImages

    List<Image> getImages(){
        return images;
    }

    void addImage(Image image) {
        images.add(image);
    }

    void removeImage(Image image) {
        images.remove(image);
    }

    // bonus problem ...
    public void addAll(String folder) {
// add all images in the folder
    }

    @Override
    public String toString(){
        return name;
    }

}
