package service.structure;

import java.util.ArrayList;

public class Dorama {
    private  String name, date, country;
    private ArrayList<String> genres;
    public Dorama(){
        name = null;
        date = null;
        country = null;
        genres = new ArrayList<>();
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
    public ArrayList<String> getGenres(){
        return genres;
    }
    public void setGenres(ArrayList<String> genres){
        this.genres = genres;
    }
}
