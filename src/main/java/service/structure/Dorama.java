package service.structure;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class Dorama {
    private  String name;
    private  String date;
    private  String country;

    private List<String> genres;
    private List<Character> characters;

    public Dorama(){
        genres = new ArrayList<>();
        characters = new ArrayList<>();
    }
}
