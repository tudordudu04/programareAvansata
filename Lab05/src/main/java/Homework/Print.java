package Homework;

import Compulsory.RepositoryService;

public class Print extends Command {
    public Print() {
    }

    public void execute(RepositoryService service) {
        try {
            service.printAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
