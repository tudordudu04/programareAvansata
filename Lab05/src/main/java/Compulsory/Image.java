package Compulsory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record Image(String name, Date date, List<String> tags, String location) implements Serializable {}