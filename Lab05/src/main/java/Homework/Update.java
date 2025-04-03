package Homework;

import Compulsory.Image;
import Compulsory.RepositoryService;

public class Update extends Command {
    String name;
    Image image;

    public Update(String oldName, Image newImage) {
        this.name = oldName;
        this.image = newImage;
    }

    public void execute(RepositoryService service) {
        service.getRepository().updateImage(this.name, this.image);
    }
}
