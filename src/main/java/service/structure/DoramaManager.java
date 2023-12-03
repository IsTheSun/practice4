package service.structure;

import service.readers.ReaderXml;
import service.operations.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Scanner;

public class DoramaManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoramaManager.class);
    private List<Dorama> doramas;

    public void run() {
        initializeDoramas();
        displayMenu();
        performTask();
    }

    private void initializeDoramas() {
        ReaderXml reader = new ReaderXml();
        final String FILENAME = "src\\test\\resources\\doramas.xml";
        doramas = (List<Dorama>) reader.readFile(FILENAME);
    }

    private void displayMenu() {
        LOGGER.info("Выберите номер задания:\n" +
                "1) Отсортировать дорамы по названию в алфавитном порядке.\n" +
                "2) Получить список названий дорам из Китая.\n" +
                "3) Преобразовать дату выпуска дорамы в формат ДД.ММ.ГГГГ.\n" +
                "4) Фильтровать дорамы по жанру Сomedy и получить их названия.\n" +
                "5) Проверить, есть ли хотя бы одна дорама с указанным жанром.");
    }

    private void performTask() {
        Scanner in = new Scanner(System.in);
        int task = in.nextInt();
        in.nextLine();

        switch (task) {
            case 1:
                List<Dorama> sorted = Operations.sortDoramasByName(doramas);
                LOGGER.info("\nОтсортированные дорамы по названию:\n{}", Operations.listToString(sorted));
                break;
            case 2:
                List<String> chineseDoramas = Operations.getChineseDoramas(doramas);
                LOGGER.info("\nСписок дорам из Китая:\n{}", chineseDoramas);
                break;
            case 3:
                List<String> format = Operations.formatDoramaDates(doramas);
                LOGGER.info("\nПреобразованные даты выпуска дорам:\n{}", format);
                break;
            case 4:
                List<String> comedy = Operations.getСomedyDramas(doramas);
                LOGGER.info("\nДорамы жанра Comedy:\n{}", comedy);
                break;
            case 5:
                System.out.println("\nВведите жанр:");
                String genre = in.nextLine();
                boolean hasGenre = Operations.hasGenreDrama(doramas, genre);
                LOGGER.info("Есть ли дорамы с жанром '{}':\n{}", genre, hasGenre);
                break;
            default:
                LOGGER.warn("\nТакого задания нет.");
                break;
        }
    }
}
