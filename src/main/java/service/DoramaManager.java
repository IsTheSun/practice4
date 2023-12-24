package service;

import service.structure.Dorama;
import service.readers.ReaderXml;
import service.operations.Operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Scanner;
import lombok.val;
import service.structure.Doramas;

public class DoramaManager {
    static final Logger LOGGER = LoggerFactory.getLogger(DoramaManager.class);
    static String FILENAME;
    List<Dorama> doramas;

    public void run() {
        setFilePathFromUser();

        LOGGER.info("Пользователь предоставил путь к входному файлу: {}", FILENAME);

        initializeDoramas();
        displayMenu();
        performTask();
    }

    void setFilePathFromUser() {
        val scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу:");
        FILENAME = scanner.nextLine();
    }

    void initializeDoramas() {
        try {
            val reader = new ReaderXml();
            Doramas doramasObject = reader.readFile(FILENAME);

            if (doramasObject != null) {
                doramas = doramasObject.getDoramas();
                LOGGER.info("Дорамы успешно инициализированы из файла.");
            } else {
                LOGGER.error("Файл не удалось прочитать или конвертировать в объект Doramas.");
            }
        } catch (Exception exception) {
            LOGGER.error("Произошла непредвиденная ошибка при инициализации дорам из файла: {}", exception.getMessage(), exception);
        }
    }

    void displayMenu() {
        System.out.println("Выберите номер задания:\n" +
                "1) Отсортировать дорамы по названию в алфавитном порядке.\n" +
                "2) Получить список названий дорам из Китая.\n" +
                "3) Преобразовать дату выпуска дорамы в формат ДД.ММ.ГГГГ.\n" +
                "4) Фильтровать дорамы по жанру Сomedy и получить их названия.\n" +
                "5) Проверить, есть ли хотя бы одна дорама с указанным жанром.");
    }

    void performTask() {
        try {
            val in = new Scanner(System.in);
            val task = in.nextInt();
            in.nextLine();

            switch (task) {
                case 1:
                    System.out.println("\nОтсортированные дорамы по названию:\n" + Operations.sortDoramasByName(doramas));
                    break;
                case 2:
                    System.out.println("\nСписок дорам из Китая:\n" + Operations.getChineseDoramas(doramas));
                    break;
                case 3:
                    System.out.println("\nПреобразованные даты выпуска дорам:\n" + Operations.formatDoramaDates(doramas));
                    break;
                case 4:
                    System.out.println("\nДорамы жанра Comedy:\n" + Operations.getСomedyDramas(doramas));
                    break;
                case 5:
                    System.out.println("\nВведите жанр:");
                    val genre = in.nextLine();
                    System.out.println("Есть ли дорамы с жанром '" + genre + "':\n" + Operations.hasGenreDrama(doramas, genre));
                    break;
                default:
                    System.out.println("\nТакого задания нет.");
                    break;
            }
        }catch (Exception exception){
            LOGGER.error("Ошибка выполнения задачи: {}", exception.getMessage(), exception);
        }
    }
}
