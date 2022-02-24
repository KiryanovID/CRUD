package ru.kiryanov;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleIO {
    private final CountryDAO crud = new CountryDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";

    public void create() {
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        System.out.println("Введите континент на котором находится страна:");
        String continentTemp = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        System.out.println("Введите площадь страны:");
        long square = scanner.nextLong();
        System.out.println("Введите население страны:");
        int population = scanner.nextInt();
        scanner.nextLine();
        Continent continent = Continent.valueOf(continentTemp);
        crud.create(name, square, population, continent);
        System.out.println(GREEN + "Запись добавлена");
        System.out.println(RESET + "Введите команду: ");
    }

    public void read() {
        System.out.println("введите ID страны");
        long find = scanner.nextLong();
        scanner.nextLine();
        Country country = crud.read(find);
        System.out.println(country);
        System.out.println("Введите команду: ");
    }

    public void update() {
        System.out.println("введите ID страны");
        long find = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        if(name.equals("")){
            name = "unknown";
        }
        System.out.println("Введите континент на котором находится страна:");
        String continent = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        if(continent.equals("")){
            continent = "UNKNOWN";
        }
        Continent continentTemp = Continent.valueOf(continent);
        System.out.println("Введите площадь страны:");
        String strLong = scanner.nextLine();
        if(strLong.equals("")){
           strLong = "0";
        }
        long square = Long.parseLong(strLong);
        System.out.println("Введите население страны:");
        String strInt = scanner.nextLine();
        if(strInt.equals("")){
            strInt = "0";
        }
        int population = Integer.parseInt(strInt);
        crud.update(find, name, square, population, continentTemp);
        System.out.println("Введите команду: ");
    }

    public void delete() {
        System.out.println("введите ID страны");
        long find = scanner.nextInt();
        scanner.nextLine();
        crud.delete(find);
        System.out.println(RED + "Запись удалена ");
        System.out.println(RESET + "Введите команду: ");
    }

    public void showAll() {
        List<Country> country = crud.showAll();
        for (Country x : country) {
            System.out.println(x);
        }
        System.out.println("Введите команду: ");
    }
}
