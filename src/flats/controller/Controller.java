package flats.controller;

import com.eclipsesource.json.*;
import flats.model.Flat;
import flats.view.View;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    private View view = new View();
    private static Flat flat = new Flat();
    private int count = 0;

    // Создаем ArrayList для хранения наших квартир
    private static ArrayList<Flat> flatsList = new ArrayList<>();

    // Путь к нашему json-файлу, в который будем записывать выбранную нами квартиру из нашего листа
    private static final String FILE_PATH = "flat.json";

    public Controller(String pathName) throws Exception {
        readJsonFromFile(pathName); //Читаем данные с json-файла
        showMenu(); //Выводим меню и выбираем нужный пункт
    }

    // Читаем данные с json-файла и заполняем список квартирами
    private void readJsonFromFile(String pathName) throws IOException {
        Reader jsonReader = new FileReader(pathName);
        JsonArray jsonArray = Json.parse(jsonReader).asObject().get("flats").asArray();
        for (JsonValue jsonValue : jsonArray) {
            if (jsonValue.isObject()) {
                addFlat(jsonValue.asObject());
            }
        }
        jsonReader.close();
    }

    // Добавляем в наш ArrayList квартиру из json-файла
    private void addFlat(JsonObject jsonItem) {
        flat.setArea(jsonItem.getInt("area", 0));
        flat.setRooms(jsonItem.getInt("rooms", 0));
        flat.setLevel(jsonItem.getInt("level", 0));
        flat.setTypeOfHouse(jsonItem.getString("typeOfHouse", ""));
        flat.setLevels(jsonItem.getInt("levels", 0));
        flat.setPrice(jsonItem.getInt("price", 0));
        flat.setZone(jsonItem.getString("zone", ""));
        flat.setStreet(jsonItem.getString("street", ""));
        flat.setHouse(jsonItem.getInt("house", 0));
        flat.setZoneChange(jsonItem.getString("zoneChange", ""));
        flat.setStreetChange(jsonItem.getString("streetChange", ""));
        flat.setLevelChange(jsonItem.getInt("levelChange", 0));
        flatsList.add(flat);
    }

    // Приводим объект типа flat к объекту типа json
    private static JsonObject toJson(Flat flat) {
        JsonObject database = Json.object().add("flats", Json.array());
        database.get("flats").asArray().add(Json.object()
                .add("area", flat.getArea())
                .add("rooms", flat.getRooms())
                .add("level", flat.getLevel())
                .add("typeOfHouse", flat.getTypeOfHouse())
                .add("levels", flat.getLevels())
                .add("price", flat.getPrice())
                .add("zone", flat.getZone())
                .add("street", flat.getStreet())
                .add("house", flat.getHouse()));
        return database;
    }

    // Сохраняет объект типа json в json-файл
    private static void saveJsonToFile(String filePath, JsonObject jsonObject) throws IOException {
        FileWriter json = new FileWriter(filePath);
        json.write(jsonObject.toString(WriterConfig.PRETTY_PRINT));
        json.close();
    }

    // Выводим список всех квартир
    public static void showFlats() {
        int i = 0;
        for (Flat flat : flatsList) {
            System.out.println(++i + ") " + flat.toString());
        }
    }

    // Выводим меню
    private void showMenu() throws Exception {
        view.showMenu();
    }

    // a) Добавление новой квартиры
    public static void addFlat() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("площадь: ");
            flat.setArea(sc.nextInt());
            System.out.print("количество комнат: ");
            flat.setRooms(sc.nextInt());
            System.out.print("этаж: ");
            flat.setLevel(sc.nextInt());
            System.out.print("тип дома: ");
            String typeOfHouse = sc.next();
            if (!typeOfHouse.equals("кирпичный") && !typeOfHouse.equals("панельный")) {
                throw new Exception();
            }
            System.out.print("этажей в доме: ");
            flat.setLevels(sc.nextInt());
            System.out.print("стоимость: ");
            flat.setPrice(sc.nextInt());
            System.out.print("район: ");
            flat.setZone(sc.next());
            System.out.print("улица: ");
            flat.setStreet(sc.next());
            System.out.print("дом: ");
            flat.setHouse(sc.nextInt());
            System.out.print("район обмена: ");
            flat.setZoneChange(sc.next());
            System.out.print("улица обмена: ");
            flat.setStreetChange(sc.next());
            System.out.print("этаж обмена: ");
            flat.setLevelChange(sc.nextInt());
            flatsList.add(flat);
        } catch (Exception e) {
            System.out.println("\nОшибка! Пожалуйста, повторите ввод!\n");
        }
    }

    // b) Удаление квартиры
    public static void removeFlat() {
        showFlats();
        try {
            int choice = new Scanner(System.in).nextInt();
            flatsList.remove(choice - 1);
            System.out.println("\nКвартира под номером " + choice + " была удалена из списка!\n");
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("\nНеобходимо выбрать одну из существующих квартир!\n");
        }
    }

    // c) Этот метод ищет квартиры на обмен для выбранной квартиры
    public static void searchFlatSwap() throws IOException {
        showFlats();
        try {
            int choice = new Scanner(System.in).nextInt();
            Flat flat = flatsList.get(choice - 1);
            List<Flat> flatsChange = flatsList.stream()
                    .filter(flatChange -> flatChange.getStreet().equals(flat.getStreetChange()) &&
                            flatChange.getZone().equals(flat.getZoneChange()) &&
                            flatChange.getLevel() == flat.getLevelChange())
                    .collect(Collectors.toList());
            if (flatsChange.isEmpty()) {
                System.out.println("\nНе существует вариантов обмена для данной квартиры!\n");
            }
            else {
                System.out.println("Выберите подходящую квартиру для обмена:\n");
                for (int i = 0; i < flatsChange.size(); i++) {
                    System.out.println((i + 1) + ") " + flatsList.get(i).toString());
                }
                choice = new Scanner(System.in).nextInt();
                Flat flatChange = flatsChange.get(choice - 1);
                saveJsonToFile(FILE_PATH, toJson(flatChange));
                System.out.println("\nГотово! Данные о вашей квартире были добавлены в файл.\n");
            }
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("\nНеобходимо выбрать одну из существующих квартир!\n");
        }
    }

    // d) Выводим список квартир, отсортированных по району
    public static void sortByZone() {
        int i = 0;
        List<Flat> sortedList = flatsList.stream()
                .sorted(Comparator.comparing(Flat::getZone))
                .collect(Collectors.toList());
        for (Flat flat : sortedList) {
            System.out.println(++i + ") " + flat);
        }
    }

    // e) Выводим список квартир, отсортированных по количеству комнат
    public static void sortByRooms() {
        int i = 0;
        List<Flat> sortedList = flatsList.stream()
                .sorted(Comparator.comparing(Flat::getRooms))
                .collect(Collectors.toList());
        for (Flat flat : sortedList) {
            System.out.println(++i + ") " + flat);
        }
    }

    // f) Выводим список квартир для заданного диапазона стоимости
    public static void filterFlats() {
        int min, max, i = 0;
        try {
            System.out.print("От: ");
            min = new Scanner(System.in).nextInt();
            System.out.print("До: ");
            max = new Scanner(System.in).nextInt();
            if (min > max || min < 0 || max < 0) {
                System.out.println("\nДиапазон задан неверно!\n");
            }
            else {
                List<Flat> filterList = flatsList.stream()
                        .filter((flat -> flat.getPrice() >= min && flat.getPrice() <= max))
                        .collect(Collectors.toList());
                if (filterList.isEmpty()) {
                    System.out.println("\nНет квартир, подходящих этому диапазону!\n");
                } else {
                    System.out.println("\nСписок подходящих квартир:\n");
                    for (Flat flat : filterList) {
                        System.out.println(++i + ") " + flat);
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\nДиапазон должен быть натуральным числом!\n");
        }
    }
}
