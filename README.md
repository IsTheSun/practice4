# Менеджер Дорам

`practice4` - программа для управления информацией о Дорамах (японских телесериалах), которая может выполнять различные операции с Дорамами и отображать результаты.

## Начало работы

1. Клонируйте репозиторий на свой локальный компьютер.
2. Откройте проект в вашей предпочтительной среде разработки Java.
3. Запустите класс `Main`, расположенный в пакете `service`.

## ПО для сборки и запуска
Для успешной сборки и запуска программы рекомендуется следующее программное обеспечение:
- `Java`: Рекомендуемая версия: 20.0.2
- `Maven`: Рекомендуемая версия: 3.9.2
- `IDE`: Intellij Idea. Рекомендуемая версия: 2023.2.1

## Использование

1. После запуска приложения вам будет предложено ввести путь к XML-файлу, содержащему информацию о Дорамах. Пример: `src\\test\\resources\\doramas.xml`
3. Выберите задачу из меню, введя соответствующий номер задачи.
4. Приложение выполнит выбранную задачу и отобразит результаты.

## Функции

1. **Сортировка Дорам**: сортирует дорамы `в алфавитном порядке` по названию.

2. **Дорамы из Китая**: перечисляет названия дорам `из Китая`.

3. **Форматирование Дат Дорам**: преобразует даты выпуска дорам в формат `DD.MM.YYYY`.

4. **Фильтрация Комедийных Дорам**: отфильтровывает дорамы по жанру `Комедия` и отображает их имена.

5. **Проверка Жанра**: проверяет, существует ли хотя бы одна дорама `с указанным жанром`.

## Структура проекта

- `src`: основная папка проекта
  - `main/java/service`: папка в которой находится основной код проекта
    - `operations`: папка с классом операций.
    - `readers`: классы для чтения xml файла. 
    - `structure`: классы, в которых представлена структура xml файла.
  - `test`: пакет, содержащий классы тестов и временные файлы.
    - `java`: классы юнит-тестов для проверки работы программы.
    - `resources`: пакет с файлом xml.
