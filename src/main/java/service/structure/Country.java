package service.structure;

import java.util.ArrayList;

public class Country {
    private String name;
    private ArrayList<Dorama> doramas;
    public Country(){
        name = null;
        doramas = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public ArrayList<Dorama> getDoramas(){
        return doramas;
    }
    public void setDoramas(ArrayList<Dorama> dorama){
        this.doramas = dorama;
    }
}
