package org.example;

import java.util.List;
import java.util.Scanner;

public class App {
    static Database database;

    public static void main(String[] args) {
        printMainMenu();
    }

    public static void printMainMenu() {
        System.out.print("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("e")) {
                return;
            }
            if (s.equalsIgnoreCase("1")) {
                userInput(scanner);
            }
            if (s.equals("2")) {
                minMaxMean();
            }
            if (s.equals("3")) {
                sort();
            }
            if (s.equals("4")) {
                optimizedCharging();
            }
            printMainMenu();
        }

    }

    private static void optimizedCharging() {
        MeanAndHour meanAndHour = database.optimizedCharging();
        String response = """
                Påbörja laddning klockan %02d
                Medelpris 4h: %.1f öre/kWh
                """;
        System.out.printf(response, meanAndHour.getHour(), meanAndHour.getMean());
    }

    private static void sort() {
        List<PriceAndHour> priceAndHours = database.sortedList();
        for (PriceAndHour position : priceAndHours) {
            System.out.print(position + "\n");
        }
    }

    private static void minMaxMean() {
        String response = """
                Lägsta pris: %s/kWh
                Högsta pris: %s/kWh
                Medelpris: %.2f öre/kWh
                """;
        System.out.printf(response, database.min().toStringWithComma(), database.max().toStringWithComma(), database.mean());

    }

    public static void userInput(Scanner scanner) {
        int[] prices = new int[24];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = scanner.nextInt();
        }
        database = new Database(prices);
    }
}
