package service.operations;

import service.structure.Dorama;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public class Operations {

    public static List<Dorama> sortDoramasByName(List<Dorama> doramas){
        try {
            return doramas.stream()
                    .sorted(Comparator.comparing(Dorama::getName))
                    .toList();
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return List.of();
        }
    }

    public static List<String> getChineseDoramas(List<Dorama> doramas){
        try {return doramas.stream()
                .filter(dorama -> "Китай".equals(dorama.getCountry()))
                .map(Dorama::getName)
                .toList();
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return List.of();
        }
    }

    public static List<String> formatDoramaDates(List<Dorama> doramas){
        try {
            return doramas.stream()
                    .map(Operations::formatDate)
                    .toList();
        }catch (Exception exception){
            exception.printStackTrace();
            return List.of();
        }
    }

    private static String formatDate (Dorama dorama){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            LocalDate date = LocalDate.parse(dorama.getDate(), inputFormatter);
            return date.format(outputFormatter);
        } catch (Exception e) {
            System.err.println("Error parsing date for Dorama");
            return "N/A";
        }
    }

    public static List<String> getСomedyDramas(List<Dorama> doramas){
        try {
            return doramas.stream()
                    .filter(dorama -> dorama.getGenres().contains("Комедия"))
                    .map(Dorama::getName)
                    .toList();
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return List.of();
        }
    }

    public static boolean hasGenreDrama(List<Dorama> doramas, String genre){
        try {
            Set<String> uniqueGenres = doramas.stream()
                    .flatMap(dorama -> dorama.getGenres().stream())
                    .collect(Collectors.toSet());

            return uniqueGenres.contains(genre);
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return false;
        }
    }

    public static String listToString(List<Dorama> doramas){
        try {
            StringBuilder str = new StringBuilder();
            doramas.forEach(x -> str.append("Имя: " + x.getName() + "\n" +
                    "Дата: " + x.getDate() + "\n" +
                    "Страна: " + x.getCountry() + "\n" +
                    "Жанры: " + x.getGenres() + "\n\n"));

            return str.toString();
        }catch (Exception exception){
            System.err.println(exception.getMessage());
            return "";
        }
    }
}
