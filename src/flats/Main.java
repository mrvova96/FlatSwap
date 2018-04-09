package flats;

import flats.controller.Controller;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws Exception {
        try {
            new Controller("flats.json");
        } catch (IOException e) {
            System.out.println("Не удается найти указанный файл!");
        }
    }
}
