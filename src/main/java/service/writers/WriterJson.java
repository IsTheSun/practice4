package service.writers;

import service.structure.Dorama;
import service.structure.Country;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriterJson implements Writer {

    @Override
    public void write(Object object, String filePath) {
        ArrayList<Country> countries = (ArrayList<Country>) object;
        JsonObject countriesObject = getCountriesObject(countries);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(countriesObject);
        File file = new File(filePath);
        FileWriter writer = null;
        try {
            file.createNewFile();
            writer = new FileWriter(filePath, false);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private JsonObject getCountriesObject(ArrayList<Country> countries) {
        JsonArray jsonCountries = new JsonArray();
        for (Country country : countries) {
            JsonObject jsonCountry = new JsonObject();
            jsonCountry.addProperty("name", country.getName());
            jsonCountry.add("doramas", getDoramasJson(country));

            JsonObject genreObject = new JsonObject();
            genreObject.add("country", jsonCountry);
            jsonCountries.add(genreObject);
        }
        JsonObject countriesObject = new JsonObject();
        countriesObject.add("countries", jsonCountries);
        return countriesObject;
    }
    private JsonArray getDoramasJson(Country country) {
        JsonArray jsonDoramas = new JsonArray();
        for (Dorama dorama : country.getDoramas()) {
            JsonObject doramaObject = new JsonObject();
            doramaObject.addProperty("name", dorama.getName());
            doramaObject.addProperty("date", dorama.getData());
            doramaObject.add("genres", getGenresJson(dorama));

            jsonDoramas.add(doramaObject);
        }
        return jsonDoramas;
    }
    private JsonArray getGenresJson(Dorama dorama) {
        JsonArray jsonGenres = new JsonArray();
        for (String tag : dorama.getGenres()) {
            jsonGenres.add(tag);
        }
        return jsonGenres;
    }
}
