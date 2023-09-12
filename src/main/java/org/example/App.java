package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    static int[] prices = new int[24];

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
                minMaxMedel();
            }
            if (s.equals("3")) {
                sortera();
            }
            if (s.equals("4")) {
                optimizedCharging();
            }
            printMainMenu();
        }

    }

    private static void optimizedCharging() {
        int hour = 0;
        int cheapestFourHours = Integer.MAX_VALUE;
        for (int i = 0; i < (prices.length - 3); i++) {
            int sum = prices[i] + prices[i + 1] + prices[i + 2] + prices[i + 3];
            if (sum < cheapestFourHours) {
                cheapestFourHours = sum;
                hour = i;
            }
        }
        String response = """
                Påbörja laddning klockan %02d
                Medelpris 4h: %.1f öre/kWh
                """;
        System.out.print(String.format(response, hour, ((float) cheapestFourHours) / 4));
    }

    private static void sortera() {
        List<PriceAndHour> priceAndHours = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            priceAndHours.add(new PriceAndHour(prices[i], i));
        }
        Collections.sort(priceAndHours);
        for (PriceAndHour position : priceAndHours) {
            System.out.print(position + "\n");
        }
    }

        private static void minMaxMedel() {
       Database database = new Database(prices);
        String response = """
                Lägsta pris: %s/kWh
                Högsta pris: %s/kWh
                Medelpris: %.2f öre/kWh
                """;
        System.out.print(String.format(response,database.min().toStringWithComma(),database.max().toStringWithComma(),database.mean()));

    }

    public static void userInput(Scanner scanner) {
        for (int i = 0; i < prices.length; i++) {
            prices[i] = scanner.nextInt();
        }

    }
}
