package Homework;

import Compulsory.Repository;
import Compulsory.RepositoryService;

public class Create extends Command {
    String name;

    public Create(String name) {
        this.name = name;
    }
    public void execute(RepositoryService service){
        service.setRepository(new Repository(this.name));
    }
}
