package Homework;

import Compulsory.RepositoryService;

public class Remove extends Command {
    String imageName;

    public Remove(String imageName) {
        this.imageName = imageName;
    }

    public void execute(RepositoryService service) {
        service.getRepository().removeImage(this.imageName);
    }
}
