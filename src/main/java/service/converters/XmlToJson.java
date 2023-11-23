package service.converters;

import service.structure.Dorama;
import service.structure.Country;
import service.readers.ReaderXml;
import service.writers.WriterJson;

import java.util.ArrayList;

public class XmlToJson implements Converter{
    @Override
    public void convert(String inputFile, String outputFile){
        ReaderXml reader = new ReaderXml();
        ArrayList<Dorama> doramas = (ArrayList<Dorama>) reader.readFile(inputFile);
        ArrayList<Country> countries = convertStructure(doramas);
        WriterJson writer = new WriterJson();
        writer.write(countries, outputFile);
    }
    private ArrayList<Country> convertStructure(ArrayList<Dorama> doramas){
        ArrayList<Country> countries = new ArrayList<>();
        for (Dorama dorama1 : doramas){
            Country country = new Country();
            country.setName(dorama1.getCountry());
            if (contains(countries, country.getName()))
                continue;
            ArrayList<Dorama> countryDoramas = new ArrayList<>();
            for (Dorama dorama2 : doramas){
                if (dorama2.getCountry().equals(country.getName()))
                    countryDoramas.add(dorama2);
            }
            country.setDoramas(countryDoramas);
            countries.add(country);
        }
        return countries;
    }
    private boolean contains(ArrayList<Country> countries, String countryName) {
        for (Country country : countries)
            if (country.getName().equals(countryName))
                return true;
        return false;
    }
}
