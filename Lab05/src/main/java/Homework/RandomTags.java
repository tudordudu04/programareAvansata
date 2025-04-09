package Homework;

import Compulsory.Image;
import Compulsory.RepositoryService;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTags extends Command {
    public void execute(RepositoryService service) {
        Random rand = new Random();
        Faker faker = new Faker();
        List<Image> list = service.getRepository().getImages();

        for (Image image : list) {
            int random = rand.nextInt(4) + 2;
            List<String> tags = new ArrayList<>();

            for (int j = 0; j < random; j++) {
                tags.add(faker.artist().name());
            }

            image.tags().clear();
            image.tags().addAll(tags);
        }
    }
}
