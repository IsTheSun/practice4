import service.structure.Dorama;
import service.structure.Character;
import service.operations.Operations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
public class OperationsTest {
    @Test
    public void testSortDoramasByName() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama dorama1 = new Dorama();
        dorama1.setName("B");
        doramas.add(dorama1);

        Dorama dorama2 = new Dorama();
        dorama2.setName("A");
        doramas.add(dorama2);

        List<Dorama> sortedDoramas = Operations.sortDoramasByName(doramas);

        assertEquals("A", sortedDoramas.get(0).getName());
        assertEquals("B", sortedDoramas.get(1).getName());
    }
    @Test
    public void testGetChineseDoramas() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama chineseDorama = new Dorama();
        chineseDorama.setCountry("Китай");
        chineseDorama.setName("Дорама 1");
        doramas.add(chineseDorama);

        Dorama nonChineseDorama = new Dorama();
        nonChineseDorama.setCountry("Япония");
        nonChineseDorama.setName("Дорама 2");
        doramas.add(nonChineseDorama);

        List<String> chineseDoramas = Operations.getChineseDoramas(doramas);

        assertEquals(1, chineseDoramas.size());
        assertEquals("Дорама 1", chineseDoramas.get(0));
    }
    @Test
    public void testFormatDoramaDates() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama dorama = new Dorama();
        dorama.setDate("01 Jan 2023");
        doramas.add(dorama);

        List<String> formattedDates = Operations.formatDoramaDates(doramas);

        assertEquals(1, formattedDates.size());
        assertEquals("01.01.2023", formattedDates.get(0));
    }

    @Test
    public void testGetСomedyDramas() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama comedyDorama = new Dorama();
        comedyDorama.setGenres(List.of("Комедия"));
        comedyDorama.setName("Дорама 1");
        doramas.add(comedyDorama);

        Dorama nonComedyDorama = new Dorama();
        nonComedyDorama.setGenres(List.of("Драма"));
        nonComedyDorama.setName("Дорама 2");
        doramas.add(nonComedyDorama);

        List<String> comedyDramas = Operations.getСomedyDramas(doramas);

        assertEquals(1, comedyDramas.size());
        assertEquals("Дорама 1", comedyDramas.get(0));
    }

    @Test
    public void testHasGenreDrama() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama dorama = new Dorama();
        dorama.setGenres(List.of("Драма", "Комедия"));
        doramas.add(dorama);

        boolean hasGenreDrama = Operations.hasGenreDrama(doramas, "Драма");

        assertEquals(true, hasGenreDrama);
    }

    @Test
    public void testListToString() {
        List<Dorama> doramas = new ArrayList<>();
        Dorama dorama = new Dorama();
        dorama.setName("Дорама 1");
        dorama.setDate("01 Jan 2023");
        dorama.setCountry("Китай");
        dorama.setGenres(List.of("Драма", "Комедия"));

        List<Character> characters = new ArrayList<>();

        Character character1 = new Character();
        character1.setName("Персонаж 1");
        character1.setAge(25);
        characters.add(character1);

        Character character2 = new Character();
        character2.setName("Персонаж 2");
        character2.setAge(30);
        characters.add(character2);

        dorama.setCharacters(characters);

        doramas.add(dorama);

        String result = Operations.listToString(doramas);

        String expected = "Имя: Дорама 1\r\n" +
                "Дата: 01 Jan 2023\r\n" +
                "Страна: Китай\r\n" +
                "Жанры: [Драма, Комедия]\r\n" +
                "Персонажи:\r\n" +
                "\tИмя персонажа: Персонаж 1\r\n" +
                "\tВозраст: 25\r\n" +
                "\tИмя персонажа: Персонаж 2\r\n" +
                "\tВозраст: 30\r\n\r\n";

        assertEquals(expected, result);
    }
}
