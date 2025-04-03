package Homework;

import Compulsory.Image;
import Compulsory.RepositoryService;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Report extends Command {
    String path;
    public Report(String path) {
        this.path = path;
    }

    public void execute(RepositoryService service) {
        List<Image> images = service.getRepository().getImages();
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties props = new Properties();
        props.setProperty("resource.loaders", "class");
        props.setProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init(props);

        Template template = velocityEngine.getTemplate("report.vm");

        VelocityContext context = new VelocityContext();
        context.put("images", images);

        try (FileWriter writer = new FileWriter(this.path + "/report.html")) {
            template.merge(context, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Path reportPath = Paths.get(this.path + "/report.html");
            if (Files.exists(reportPath)) {
                Desktop.getDesktop().browse(reportPath.toUri());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
