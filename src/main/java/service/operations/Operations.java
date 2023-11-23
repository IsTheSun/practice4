package service.operations;

import service.structure.Dorama;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

public class Operations {
    public static List<Dorama> sortDoramasByName(List<Dorama> doramas){
        return doramas.stream()
                .sorted(Comparator.comparing(Dorama::getName))
                .collect(Collectors.toList());
    }
    public static List<String> getChineseDoramas(List<Dorama> doramas){
        return doramas.stream()
                .filter(d -> "China".equals(d.getCountry()))
                .map(Dorama::getName)
                .collect(Collectors.toList());
    }
    public static List<String> formatDramaDates(List<Dorama> doramas){
        DateTimeFormatter input = DateTimeFormatter.ofPattern("dd MMM yyy", Locale.ENGLISH);
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return doramas.stream()
                .map(d -> LocalDate.parse(d.getData(), input).format(output))
                .collect(Collectors.toList());
    }
    public static List<String> getСomedyDramas(List<Dorama> doramas){
        return doramas.stream()
                .filter(d -> d.getGenres().contains("Сomedy"))
                .map(Dorama::getName)
                .collect(Collectors.toList());
    }
    public static boolean hasGenreDrama(List<Dorama> doramas, String genre){
        return doramas.stream()
                .anyMatch(d -> d.getGenres().contains(genre));
    }
    public static String listToString(List<Dorama> doramas){
        StringBuilder str = new StringBuilder();
        doramas.forEach(x -> str.append("Имя: " + x.getName() + "\n" +
                "Дата: " + x.getData() + "\n" +
                "Страна: " + x.getCountry() + "\n" +
                "Жанры: " + x.getGenres() + "\n\n"));
        return str.toString();
    }
}
