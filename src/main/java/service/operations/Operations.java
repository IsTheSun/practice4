package service.operations;

import service.structure.Dorama;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
@FieldDefaults(makeFinal = true)
public class Operations {
    String COUNTRY = "Китай";
    String COMEDY_GENRE = "Комедия";

    /**
     * Сортирует дорамы по имени в алфавитном порядке.
     * @param doramas Список дорам
     * @return Список отсортированных имен дорам
     */
    public List<String> sortDoramasByName(List<Dorama> doramas){
        return doramas.stream()
                .map(Dorama::getName)
                .sorted()
                .toList();
    }

    /**
     * Получает имена китайских дорам из списка.
     * @param doramas Список дорам
     * @return Список имен китайских дорам
     */
    public List<String> getChineseDoramas(List<Dorama> doramas){
        return doramas.stream()
                .filter(dorama -> COUNTRY.equals(dorama.getCountry()))
                .map(Dorama::getName)
                .toList();
    }

    /**
     * Форматирует даты дорам в соответствии с заданными шаблонами.
     * @param doramas Список дорам
     * @return Список отформатированных дат
     */
    public List<String> formatDoramaDates(List<Dorama> doramas) {
        return doramas.stream()
                .map(dorama -> dorama.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .toList();
    }

    /**
     * Получает имена дорам, относящихся к жанру "Комедия".
     * @param doramas Список дорам
     * @return Список имен комедийных дорам
     */
    public List<String> getСomedyDramas(List<Dorama> doramas){
        return doramas.stream()
                .filter(dorama -> dorama.getGenres().contains(COMEDY_GENRE))
                .map(Dorama::getName)
                .toList();
    }

    /**
     * Проверяет, существует ли дорама с заданным жанром.
     * @param doramas Список дорам
     * @param genre Жанр для проверки
     * @return true, если существует дорама с указанным жанром, иначе false
     */
    public boolean hasGenreDrama(List<Dorama> doramas, String genre){
        try {
            Set<String> uniqueGenres = doramas.stream()
                    .flatMap(dorama -> dorama.getGenres().stream())
                    .collect(Collectors.toSet());

            return uniqueGenres.contains(genre);
        } catch (Exception exception) {
            throw new RuntimeException("Ошибка при определении жанра Дорамы", exception);
        }
    }
}
