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
        System.out.println("[dudu repoShell]: add \"Name\", \"Date\", \"List<String> Tags\", \"Path\" Adds an image from {path} to the current repo.");
        System.out.println("[dudu repoShell]: remove \"Name\" Removes an image from the current repo.");
        System.out.println("[dudu repoShell]: update [-n -d -t -p] \"Name\" [\"NewName\"] [\"Date\"] [\"List<String> Tags\"] [\"Path\"] Updates the attributes of an image from the current repo.");
        System.out.println("[dudu repoShell]: load [-type \"type of external file\"] \"Path\" Loads a repo from {path}. As default the file is opened as plain text.");
        System.out.println("[dudu repoShell]: save [-type \"type of external file\"] \"Path\" Saves a repo to {path}. As default the file is saved as plain text.");
        System.out.println("[dudu repoShell]: report Create and open an HTML report representing the content of the repository.");
    }
}
