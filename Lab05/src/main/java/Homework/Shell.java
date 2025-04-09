package Homework;

import Compulsory.Image;
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
        RepositoryService service = new RepositoryService();
        this.startShell(service);
    }

    private void startShell(RepositoryService service) {
        System.out.println("[dudu repoShell]: Enter commands for working with repos. Do help for list of commands.");
        System.out.print("[dudu repoShell]: ");
        Scanner scan = new Scanner(System.in);
        String inputBuffer;
        while(scan.hasNextLine()) {
            inputBuffer = scan.nextLine();
            if (inputBuffer.length() >= 100) {
                System.out.println("[dudu repoShell]: Too many characters in repoShell.");
                break;
            }

            try {
                Type test = Type.valueOf(splitInput(inputBuffer).getFirst().toUpperCase());
                if(splitInput(inputBuffer).getFirst().equalsIgnoreCase("exit"))
                    System.exit(0);
                else if(service.getRepository() == null && test != Type.CREATE && test != Type.LOAD && test != Type.HELP) throw new RepositoryNotInitialized();
                else {
                    Command cmd = this.parseInput(splitInput(inputBuffer));
                    cmd.execute(service);
                }
            }catch (RepositoryNotInitialized e) {
                System.out.println(e.getMessage());
            }catch (Exception var) {
                System.out.println("[dudu repoShell]: Invalid command.");
            }
            System.out.print("[dudu repoShell]: ");
        }

        scan.close();
    }

    private Command parseInput(List<String> input) {
        Command cmd = null;
        Type ceva = Type.valueOf(input.getFirst().toUpperCase());
            switch (ceva) {
                case HELP -> {
                    if(input.size() != 1)
                        break;
                    return new Help();
                }
                case ADD -> {
                    if(input.size() != 5)
                        break;
                    String name = input.get(1).replace("\"", "");
                    Date data;
                    try {
                        data = (new SimpleDateFormat("dd-MM-yyyy")).parse(input.get(2).replace("\"", ""));
                    } catch (Exception var) {
                        System.out.println("[dudu repoShell]: Not a valid date.");
                        break;
                    }
                    List<String> tags = Arrays.stream(input.get(3).split(" ")).toList();
                    if (!this.isImage(input.get(4).replace("\"", ""))) {
                        break;
                    }
                    String path = input.get(4).replace("\"", "");
                    return new Add(name, data, tags, path);
                }
                case REMOVE -> {
                    if(input.size() != 2)
                        break;
                    String name = input.get(1).replace("\"", "");
                    return new Remove(name);
                }
                case SAVE -> {
                    if(input.size() != 3)
                        break;
                    String path = input.get(2).replace("\"", "");
                    String arg = input.get(1);
                    return new Save(path, arg);
                }
                case REPORT -> {
                    if(input.size() != 2)
                        break;
                    String path = input.get(1).replace("\"", "");
                    return new Report(path);
                }
                case UPDATE -> {
                    if(input.size() != 5)
                        break;
                    String oldName = input.get(1).replace("\"", "");
                    String newName = input.get(2).replace("\"", "");
                    Date date;
                    try {
                        date = (new SimpleDateFormat("dd-MM-yyyy")).parse(input.get(3).replace("\"", ""));
                    } catch (Exception var) {
                        System.out.println("[dudu repoShell]: Not a valid date.");
                        break;
                    }
                    List<String> tags = Arrays.stream(input.get(4).split(" ")).toList();
                    if (!this.isImage(input.get(5).replace("\"", ""))) {
                        break;
                    }
                    String path = input.get(5).replace("\"", "");
                    return new Update(oldName, new Image(newName, date, tags, path));
                }
                case LOAD -> {
                    if(input.size() != 3)
                        break;
                    String path = input.get(2).replace("\"", "");
                    String arg = input.get(1);
                    return new Load(path, arg);
                }
                case PRINT -> {
                    if(input.size() != 1)
                        break;
                    return new Print();
                }
                case CREATE -> {
                    if(input.size() != 2)
                        break;
                    String name = input.get(1).replace("\"", "");
                    return new Create(name);
                }
                case ADDALL -> {
                    if(input.size() != 2)
                        break;
                    String path = input.get(1).replace("\"", "");
                    return new AddAll(path);
                }
                case TAGS -> {
                    if (input.size() != 1)
                        break;
                    return new RandomTags();
                }
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
        List<String> result = new ArrayList<>();
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
