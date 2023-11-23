package service.readers;

import service.structure.Dorama;
import service.structure.Country;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderJson implements Reader{
    @Override
    public Object readFile(String filename) {
        JSONParser parser = new JSONParser();
        FileReader reader = null;
        JSONObject document = null;
        try {
            reader = new FileReader(filename);
            document = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return getCountries(document);
    }
    private ArrayList<Country> getCountries(JSONObject document) {
        ArrayList<Country> countries = new ArrayList<>();
        JSONArray countriesJson = (JSONArray) document.get("countries");
        for (Object countryJson : countriesJson) {
            JSONObject countryData = (JSONObject) countryJson;
            JSONObject countryProperties = (JSONObject) countryData.get("country");
            Country country = getCountry(countryProperties);
            fillCountriesInDoramas(country);
            countries.add(country);
        }
        return countries;
    }
    private Country getCountry(JSONObject countryProperties) {
        Country country = new Country();
        country.setName(countryProperties.get("name").toString());
        JSONArray doramasJson = (JSONArray) countryProperties.get("doramas");
        ArrayList<Dorama> doramas = getDoramas(doramasJson);
        country.setDoramas(doramas);
        return country;
    }

    private void fillCountriesInDoramas(Country country) {
        ArrayList<Dorama> doramas = country.getDoramas();
        for (Dorama dorama : doramas)
            dorama.setCountry(country.getName());
    }

    private ArrayList<Dorama> getDoramas(JSONArray doramasJson) {
        ArrayList<Dorama> doramas = new ArrayList<>();
        for (Object doramaJson : doramasJson) {
            JSONObject gameData = (JSONObject) doramaJson;
            Dorama dorama = getDorama(gameData);
            doramas.add(dorama);
        }
        return doramas;
    }

    private Dorama getDorama(JSONObject doramaData) {
        Dorama dorama = new Dorama();
        dorama.setName(doramaData.get("name").toString());
        dorama.setData(doramaData.get("date").toString());
        JSONArray genersJson = (JSONArray) doramaData.get("genres");
        setGeners(genersJson, dorama);
        return dorama;
    }
    private void setGeners(JSONArray genersJson, Dorama dorama) {
        ArrayList<String> geners = new ArrayList<>();
        for (Object gener : genersJson) {
            geners.add(gener.toString());
        }
        dorama.setGenres(geners);
    }
}
