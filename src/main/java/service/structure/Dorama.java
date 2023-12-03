package service.structure;

import service.structure.Character;
import java.util.List;
import java.util.ArrayList;

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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getData(){
        return date;
    }

    public void setData(String date){
        this.date = date;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public List<String> getGenres(){
        return genres;
    }

    public void setGenres(List<String> genres){
        this.genres = genres;
    }
    public List<Character> getCharacters() {
        return characters;
    }
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
