package flats.view;

import flats.controller.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    public void showMenu() throws Exception {
        while (true) {
            Menu();
            getUserSelection();
        }
    }

    private void Menu() {
        System.out.println("Выберите нужный пункт меню:\n\n" +
                "1 - вывести список квартир\n" +
                "2 - добавить кваритиру в список\n" +
                "3 - удалить кваритру из списка\n" +
                "4 - поиск вариантов обмена для квартиры\n" +
                "5 - вывести список квартир, отсортированных по районам\n" +
                "6 - вывести список квартир, отсортированных по количеству комнат\n" +
                "7 - вывести список квартир по заданному диапазону стоимости\n" +
                "0 - для выхода");
    }

    private void getUserSelection() throws Exception {
        int choice;
        try {
            choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Список доступных квартир:\n");
                    Controller.showFlats();
                    break;
                case 2:
                    System.out.println("---Добавление новой квартиры---\n");
                    Controller.addFlat();
                    break;
                case 3:
                    System.out.println("\n---Удаление квартиры---\n");
                    Controller.removeFlat();
                    break;
                case 4:
                    System.out.println("\n---Поиск вариантов обмена для квартиры из списка---\n");
                    Controller.searchFlatSwap();
                    break;
                case 5:
                    System.out.println("\n---Сортировка квартир по району---\n");
                    Controller.sortByZone();
                    break;
                case 6:
                    System.out.println("\n---Сортировка квартир по количеству комнат---\n");
                    Controller.sortByRooms();
                    break;
                case 7:
                    System.out.println("\n---Введите диапазон стоимости квартиры---\n");
                    Controller.filterFlats();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("\nОшибка! Повторите ввод!\n");
                    break;
            }
        } catch(InputMismatchException e) {
            System.out.println("\nОшибка! Повторите ввод!\n");
        }
    }
}