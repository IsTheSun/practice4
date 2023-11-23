package service;

import service.operations.Operations;
import service.readers.ReaderXml;
import service.structure.Dorama;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ReaderXml read = new ReaderXml();
        String filename = "src\\main\\resources\\doramas.xml";
        List<Dorama> doramas = (List<Dorama>) read.readFile(filename);

        Scanner in = new Scanner(System.in);
        System.out.println("Выберите номер задания:\n" +
                "1) Отсортировать дорамы по названию в алфавитном порядке.\n" +
                "2) Получить список названий дорам из Китая.\n" +
                "3) Преобразовать дату выпуска дорамы в формат ДД.ММ.ГГГГ.\n" +
                "4) Фильтровать дорамы по жанру Сomedy и получить их названия.\n" +
                "5) Проверить, есть ли хотя бы одна дорама с указанным жанром.");
        int task = in.nextInt();
        in.nextLine();
        switch (task){
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