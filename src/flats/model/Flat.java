package flats.model;

public class Flat {

    private String typeOfHouse;
    private String zone;
    private String street;
    private String zoneChange;
    private String streetChange;
    private int area;
    private int level;
    private int levels;
    private int price;
    private int rooms;
    private int house;
    private int levelChange;

    public String getTypeOfHouse() {
        return typeOfHouse;
    }
    public void setTypeOfHouse(String typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }

    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getZoneChange() {
        return zoneChange;
    }
    public void setZoneChange(String zoneChange) {
        this.zoneChange = zoneChange;
    }

    public String getStreetChange() {
        return streetChange;
    }
    public void setStreetChange(String streetChange) {
        this.streetChange = streetChange;
    }

    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevels() {
        return levels;
    }
    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevelChange() {
        return levelChange;
    }
    public void setLevelChange(int levelChange) {
        this.levelChange = levelChange;
    }

    public int getHouse() {
        return house;
    }
    public void setHouse(int house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Адрес: " + zone + ", " + street + ", " + house +
                "\nТип дома: " + typeOfHouse +
                ", Площадь: " + area +
                ", Количество комнат: " + rooms +
                ", Этаж: " + level +
                ", Этажей в доме: " + levels +
                "\nСтоимость: " + price +
                "\nАдрес для обмена: " + zoneChange + ", " + streetChange + ", " + levelChange + "\n";
    }
}
