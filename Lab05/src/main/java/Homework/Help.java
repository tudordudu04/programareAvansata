package Homework;

import Compulsory.RepositoryService;

public class Help extends Command {
    public Help() {
        this.type = Type.HELP;
    }

    public void execute(RepositoryService service) {
        System.out.println("[dudu repoShell]: List of commands:");
        System.out.println("[dudu repoShell]: help List of commands.");
        System.out.println("[dudu repoShell]: exit Exits the shell.");
        System.out.println("[dudu repoShell]: create \"Name\" Creates a repository with the given name.");
        System.out.println("[dudu repoShell]: add \"Name\", \"Date\", \"List<String> Tags\", \"Path\" Adds an image from {path} to the current repo.");
        System.out.println("[dudu repoShell]: remove \"Name\" Removes an image from the current repo.");
//        System.out.println("[dudu repoShell]: update [-n -d -t -p] \"Name\" [\"NewName\"] [\"Date\"] [\"List<String> Tags\"] [\"Path\"] Updates the attributes of an image from the current repo.");
        System.out.println("[dudu repoShell]: update  \"Name\" \"NewName\" \"Date\" \"List<String> Tags\" \"Path\". Updates the attributes of an image from the current repo.");
        System.out.println("[dudu repoShell]: load [-b] [-p] [-j] \"Path\" Loads a repo from path using the specified format in the argument. -b for binary, -p for plaintext and -j for JSON");
        System.out.println("[dudu repoShell]: save [-b] [-p] [-j] \"Path\" Saves a repo to path using the specified format in the argument. -b for binary, -p for plaintext and -j for JSON");
        System.out.println("[dudu repoShell]: report \"Path\" Create and open an HTML report representing the content of the repository.");
        System.out.println("[dudu repoShell]: addall \"Path\" Adds all the images from the directory and subdirectories of the given path.");
        System.out.println("[dudu repoShell]: tags Randomizes the tags of all the images in the current repo.");
    }
}
