package com.clevertec.task;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OutputCheck {

    // вывод чека в консоль
    public static void checkInConsole(ArrayList<String> check) {
        ArrayList<String> header = checkHeader();

        for (String printHeader : header){
            System.out.println(printHeader);
        }

        for (String text : check){
            System.out.println(text);
        }
    }

    // запись чека в файл
    public static void checkInFile(ArrayList<String> check) {
        System.out.println("Введите путь к файлу для записи чека");
        BufferedWriter bufferedWriter;

        while (true){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                String text = bufferedReader.readLine();
                File file = new File(text);
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                break;
            }catch (IOException e){
                System.out.println("ERROR: Путь указан неверно или файл не найден. Попробуйте ещё раз.");
                e.printStackTrace();
            }
        }

        ArrayList<String> header = checkHeader();
        try{
            for (String string : header){
                bufferedWriter.write(string);
                bufferedWriter.write("\n");
            }

            for (String string : check){
                bufferedWriter.write(string);
                bufferedWriter.write("\n");
            }
            System.out.println("Чек успешно записан в файл.");
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("ERROR: Ошибка записи файла.");
            e.printStackTrace();
        }
    }

    // метод для формирования шапки чека
    private static ArrayList<String> checkHeader() {
        int cashier = 1 + (int)(Math.random() *100);

        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        ArrayList<String> header = new ArrayList<>();

        header.add("");
        header.add("=============== CASH RECEIPT ===============");
        header.add("              Supermarket 777");
        header.add("          123, Pavlova Str., Gomel");
        header.add("           tel: +375 29 123-45-67");
        header.add("CASHIER #: 0" + cashier + "      DATE: " + formatDate.format(date) +
                "\n                    TIME: " + formatTime.format(date));
        header.add("--------------------------------------------");
        header.add("QTY  DESCRIPTION     PRICE   TOTAL");
        return header;
    }
}