package Homework;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shell {

    public Shell(){
        startShell();
    }

    private void startShell(){
        System.out.println("Enter commands for working with repos. Do -help for list of commands.");

        System.out.print("[dudu repoShell]: ");
        Scanner scan = new Scanner(System.in);
        String inputBuffer = "";
        while (inputBuffer.length() < 100 && scan.hasNextLine()) {
            inputBuffer = scan.nextLine();

            if (inputBuffer.length() >= 100) {
                System.out.print("[dudu repoShell]: Too many characters in repoShell.");
                break;
            }
            if(!validFormat(splitInput(inputBuffer))){
                break;
            }
            parseInput(splitInput(inputBuffer));
        }
        scan.close();
    }
    private static boolean validFormat(List<String> input){
        Iterator<String> iterator = input.iterator();

        try {
            if(input.isEmpty()) throw new Exception();
            String word = iterator.next();
            if(!word.toLowerCase().equals(word)) throw new Exception();
            Type ceva = Type.valueOf(word.toUpperCase());
            if(iterator.hasNext()){
                word = iterator.next();
                if(!(word.startsWith("-") || word.startsWith("\"") && word.endsWith("\""))) throw new Exception();
            }
            while(iterator.hasNext()){
                word = iterator.next();
                if(!(word.startsWith("\"") && word.endsWith("\"")))
                    throw new Exception();
            }
            return true;
        }
        catch (Exception e){
            System.out.println("ERROR");
            return false;
        }

    }
    private void parseInput(List<String> input){

        Type ceva = Type.valueOf(input.getFirst().toUpperCase());
        try {
            switch (ceva) {
                case EXIT -> System.exit(0);
                case HELP -> {
                    if (input.size() != 1)
                        throw new Exception();
                    System.out.println("[dudu repoShell]: List of commands:");
                    System.out.println("[dudu repoShell]: help List of commands.");
                    System.out.println("[dudu repoShell]: exit Exits the shell.");
                    System.out.println("[dudu repoShell]: add [\"Name\", \"Date\", \"List<String> Tags\", \"Location\"] Adds an image from {path} to the current repo.");
                    System.out.println("[dudu repoShell]: remove \"Name\" Removes an image from the current repo.");
                    System.out.println("[dudu repoShell]: update \"Name\" [\"Name\", \"Date\", \"List<String> Tags\", \"Location\"] Updates the attributes of an image from the current repo.");
                    System.out.println("[dudu repoShell]: load [-type \"type of external file\"] \"Path\" Loads a repo from {path}. As default the file is opened as plain text.");
                    System.out.println("[dudu repoShell]: save [-type \"type of external file\"] \"Path\" Saves a repo to {path}. As default the file is saved as plain text.");
                    System.out.println("[dudu repoShell]: report Create and open an HTML report representing the content of the repository.");
                }
                default -> throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
    private static List<String> splitInput(String input) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Add quoted string without quotes
                result.add("\"" + matcher.group(1) + "\"");
            } else {
                // Add unquoted word
                result.add(matcher.group(2));
            }
        }

        return result;
    }
}
