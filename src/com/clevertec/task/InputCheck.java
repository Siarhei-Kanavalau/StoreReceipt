package com.clevertec.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InputCheck {

    // метод для работы с данными из параметров
    public static ArrayList<String> checkRunner(String text) {
        String[] arrayText = text.split(" ");
        return check(arrayText);
    }

    // метод для работы с данными из консоли
    public static ArrayList<String> checkRunnerFromConsole() {
        System.out.println("Формат ввода данных: id товара - количество товара дисконтная карта" +
                            "\n пример: 1-4 3-5 6-3 5-1 7-2 card-3");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            String text = bufferedReader.readLine();
            String[] arrayText = text.split(" ");
            return check(arrayText);
        }catch (IOException e){
            System.out.println("ERROR: Ошибка ввода!");
            e.printStackTrace();
        }
        return null;
    }

    // метод для работы с данными из файла
    public static ArrayList<String> checkRunnerFromFile() {
        System.out.println("Введите путь расположения файла");
        while (true){
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                String text = bufferedReader.readLine();
                Path path = Paths.get(text);
                List<String> list = Files.readAllLines(path);
                String[] array = list.get(0).split(" ");
                return check(array);
            }catch (IOException e){
                System.out.println("ERROR: Неправильно задан путь к файлу или файл отсутствует" +
                                    "\n Попробуйте ещё раз.");
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> check(String[] arrayText) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> purchase = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        int cardMod = 0; // модификатор наличия/отсутствия карты для списка
        double totalSum = 0.0; // итоговая сумма по чеку
        double discountAmount = 0.0; // итоговая сумма скидки
        boolean haveDiscountCard = false; // наличие дисконтной карты
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        // проверка наличия скидочной карты
        if (arrayText[arrayText.length - 1].startsWith("card")){
            int cardNumber = 0;
            try {
                cardNumber = Integer.parseInt(arrayText[arrayText.length - 1].substring(5));
            }catch (NumberFormatException e){
                System.out.println("Предъявлена карта не нашего магазина");
            }
            if (DiscountCard.values().length < cardNumber){
                System.out.println("Warning! Неверная карта.");
            }else {
                haveDiscountCard = true;
                cardMod = 1;
            }
        }

        // преобразование массива к виду id товара - количество товара
        for (int i = 0; i < arrayText.length - cardMod; i++){
            try {
                int delimiter = arrayText[i].indexOf('-');
                purchase.add(Integer.parseInt(arrayText[i].substring(0, delimiter)));
                quantity.add(Integer.parseInt(arrayText[i].substring(delimiter + 1)));
            }catch (Exception e){
                System.out.println("Warning! В товаре #" + (i + 1) + " заданы неверные данные.");
                e.printStackTrace();
            }
        }

        for (int i = 0; i < purchase.size(); i++){
            if (purchase.get(i) > Purchase.values().length){
                System.out.println("Продукт не существует");
            }else {
                Purchase purch = Purchase.fromNumberId(purchase.get(i));
                if (purch.discount && quantity.get(i) > 3){
                    double discount = purch.getPrice() * quantity.get(i) * 0.1;
                    result.add(quantity.get(i) + "    " + purch.getName() + " $" + decimalFormat.format(purch.getPrice()) +
                            "      $" + decimalFormat.format((purch.getPrice() * quantity.get(i) - discount)));
                    result.add("Discount amount                 $" + decimalFormat.format(discount));
                    discountAmount += discount;
                }else {
                    result.add(quantity.get(i) + "    " + purch.getName() + " $" + decimalFormat.format(purch.getPrice()) +
                            "    $" + decimalFormat.format(purch.getPrice() * quantity.get(i)));
                }
                totalSum += purch.getPrice() * quantity.get(i);
            }
        }

        if (haveDiscountCard)
            discountAmount += totalSum * 0.05;

            result.add("____________________________________________");
            result.add("TOTAL:                        $" + decimalFormat.format(totalSum));
            result.add("SALE:                         $" + decimalFormat.format(discountAmount));
            result.add("TOTAL SUM WITH SALE:          $" + decimalFormat.format(totalSum - discountAmount));
            return result;
    }
}