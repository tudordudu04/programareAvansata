package Compulsory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import Homework.*;

public class Main {
    public static void main(String[] args) {
//        Main app = new Main();
//        app.testRepo();
        Shell shell = new Shell();
//        app.testLoadView();
    }
    private void testRepo() {
        var repo = new Repository("Color Photos"); //Model
        repo.addImage(new Image("Duke", new Date(), List.of("Cute", "Beautiful"),"/home/tudor/Desktop/duke.jpg"));
        repo.addImage(new Image("Java Logo", new Date(), List.of("Cold", "Hard"), "/home/tudor/Desktop/java_logo.jpg"));
//        System.out.println(repo);

        var service = new RepositoryService(repo); //Logic
        try {
            service.printImage("Duke");
            service.printImage("Java Logo");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        var repo = service.load("c:/repo.txt"); //or .json, .xml, .ser, ...
//        service.view(repo.findImageByName("Duke"));
//        repo.add(...);
//        service.save(repo);
    }

}
