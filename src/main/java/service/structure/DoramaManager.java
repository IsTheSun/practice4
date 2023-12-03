package service.structure;

import service.readers.ReaderXml;
import service.operations.Operations;
import java.util.List;
import java.util.Scanner;

public class DoramaManager {
    private List<Dorama> doramas;

    public void run() {
        initializeDoramas();
        displayMenu();
        performTask();
    }

    private void initializeDoramas() {
        ReaderXml reader = new ReaderXml();
        String filename = "src\\main\\resources\\doramas.xml";
        doramas = (List<Dorama>) reader.readFile(filename);
    }

    private void displayMenu() {
        System.out.println("Выберите номер задания:\n" +
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
                System.out.println(Operations.listToString(sorted));
                break;
            case 2:
                List<String> chineseDoramas = Operations.getChineseDoramas(doramas);
                System.out.println(chineseDoramas);
                break;
            case 3:
                List<String> format = Operations.formatDramaDates(doramas);
                System.out.println(format);
                break;
            case 4:
                List<String> comedy = Operations.getСomedyDramas(doramas);
                System.out.println(comedy);
                break;
            case 5:
                System.out.println("Введите жанр:");
                String genre = in.nextLine();
                boolean hasGenre = Operations.hasGenreDrama(doramas, genre);
                System.out.println(hasGenre);
                break;
            default:
                System.out.println("Такого задания нет.");
                break;
        }
    }
}
