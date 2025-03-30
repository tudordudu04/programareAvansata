package Compulsory;

import java.util.Date;
import java.util.List;

public record Image(String name, Date date, List<String> tags, String location) {}