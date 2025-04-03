package Homework;

public class RepositoryNotInitialized extends RuntimeException {
    public RepositoryNotInitialized() {
        super("Repository not initialized");
    }
}
