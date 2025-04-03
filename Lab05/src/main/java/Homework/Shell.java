package Homework;

import Compulsory.Image;
import Compulsory.Repository;
import Compulsory.RepositoryService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class Shell {
    public Shell() {
        Repository repo = new Repository("Color Photos");
        RepositoryService service = new RepositoryService(repo);
        this.startShell(service);
    }

    private void startShell(RepositoryService service) {
        System.out.println("Enter commands for working with repos. Do help for list of commands.");
        System.out.print("[dudu repoShell]: ");
        Scanner scan = new Scanner(System.in);

        for(String inputBuffer = ""; inputBuffer.length() < 100 && scan.hasNextLine(); System.out.print("[dudu repoShell]: ")) {
            inputBuffer = scan.nextLine();
            if (inputBuffer.length() >= 100) {
                System.out.println("[dudu repoShell]: Too many characters in repoShell.");
                break;
            }

            try {
                Type test = Type.valueOf(((String)splitInput(inputBuffer).getFirst()).toUpperCase());
                Command cmd = this.parseInput(splitInput(inputBuffer));
                cmd.execute(service);
            } catch (Exception var) {
                System.out.println("[dudu repoShell]: Invalid command.");
            }
        }

        scan.close();
    }

    private Command parseInput(List<String> input) {
        Command cmd = null;
        Type ceva = Type.valueOf(((String) input.getFirst()).toUpperCase());
        try {
            switch (ceva) {
                case EXIT -> {
                    System.exit(0);
                }
                case HELP -> {
                    if (input.size() != 1) {
                        throw new Exception();
                    }
                    return new Help();
                }
                case ADD -> {
                    if (input.size() != 5) {
                        throw new Exception();
                    }
                    String name = (String) input.get(1);
                    Date data = null;
                    try {
                        data = (new SimpleDateFormat("dd-MM-yyyy")).parse(((String) input.get(2)).replace("\"", ""));
                    } catch (Exception var) {
                        System.out.println("[dudu repoShell]: Not a valid date.");
                        break;
                    }
                    List<String> tags = Arrays.stream(((String) input.get(3)).split(" ")).toList();
                    if (!this.isImage(((String) input.get(4)).replace("\"", ""))) {
                        throw new Exception();
                    }
                    String path = ((String) input.get(4)).replace("\"", "");
                    return new Add(name, data, tags, path);
                }
                case REMOVE -> {
                    if (input.size() != 2) {
                        throw new Exception();
                    }
                    String name = (String) input.get(1);
                    return new Remove(name);
                }
                case SAVE -> {
                    if (input.size() != 2) {
                        throw new Exception();
                    }
                    String path = ((String) input.get(1)).replace("\"", "");
                    return new Save(path);
                }
                case REPORT -> {
                    if (input.size() != 1) {
                        throw new Exception();
                    }
                    return new Report();
                }
                case UPDATE -> {
                    String oldName = (String) input.get(1);
                    String newName = (String) input.get(2);
                    Date date = null;
                    try {
                        date = (new SimpleDateFormat("dd-MM-yyyy")).parse(((String) input.get(3)).replace("\"", ""));
                    } catch (Exception var9) {
                        System.out.println("[dudu repoShell]: Not a valid date.");
                        break;
                    }
                    List<String> tags = Arrays.stream(((String) input.get(4)).split(" ")).toList();
                    if (!this.isImage(((String) input.get(5)).replace("\"", ""))) {
                        throw new Exception();
                    }
                    String path = ((String) input.get(5)).replace("\"", "");
                    return new Update(oldName, new Image(newName, date, tags, path));
                }
                case LOAD -> {
                    if (input.size() != 2) {
                        throw new Exception();
                    }
                    String path = ((String) input.get(1)).replace("\"", "");
                    return new Load(path);
                }
                case PRINT -> {
                    if (input.size() != 1) {
                        throw new Exception();
                    }

                    return new Print();
                }
            }
            } catch(Exception var){
                System.out.println("ERROR");

        }
        return cmd;
    }
    private boolean isImage(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        } else {
            try {
                BufferedImage image = ImageIO.read(file);
                return image != null;
            } catch (IOException var) {
                return false;
            }
        }
    }

    private static List<String> splitInput(String input) {
        List<String> result = new ArrayList();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            if (matcher.group(1) != null) {
                result.add("\"" + matcher.group(1) + "\"");
            } else {
                result.add(matcher.group(2));
            }
        }

        return result;
    }
}
