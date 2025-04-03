package Homework;

import Compulsory.RepositoryService;

public abstract class Command {
    Type type;

    public Command() {
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public abstract void execute(RepositoryService var1);
}
