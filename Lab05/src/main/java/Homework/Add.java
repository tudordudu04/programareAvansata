package Homework;

import Compulsory.Image;
import Compulsory.RepositoryService;
import java.util.Date;
import java.util.List;

public class Add extends Command {
    String name;
    Date date;
    List<String> tags;
    String path;

    public Add(String name, Date date, List<String> tags, String path) {
        this.setType(Type.ADD);
        this.name = name;
        this.date = date;
        this.tags = tags;
        this.path = path;
    }

    public void execute(RepositoryService service) {
        service.getRepository().addImage(new Image(this.name, this.date, this.tags, this.path));
    }
}
