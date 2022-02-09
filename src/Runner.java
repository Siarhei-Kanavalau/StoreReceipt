import com.clevertec.task.InputCheck;
import com.clevertec.task.OutputCheck;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        ArrayList<String> check = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int firstAnswer;
        int secondAnswer;

        while(true){
            try{
                System.out.println("Источник получения данных. Сделайте выбор:" +
                                "\n 1 - параметры уже заданы" +
                                "\n 2 - ввод данных через консоль" +
                                "\n 3 - чтение данных из файла");

                firstAnswer = scanner.nextInt();

                if (firstAnswer > 0 && firstAnswer <= 3){
                    break;
                }else {
                    System.out.println("Вы ввели неправильное число. Попробуйте ещё\n");
                }
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("ERROR: Ведены неверные данные!");
            }
        }

        while (true){
            try{
                System.out.println("Выберите формат вывода данных:" +
                                "\n 1 - вывод чека на консоль" +
                                "\n 2 - запись чека в файл");

                secondAnswer = scanner.nextInt();

                if (secondAnswer > 0 && secondAnswer <= 2){
                    break;
                }else {
                    System.out.println("Вы ввели неправильное число. Попробуйте ещё\n");
                }
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("ERROR: Ведены неверные данные!");
            }
        }

        switch (firstAnswer){
            case 1:
                check = InputCheck.checkRunner("1-4 5-5 card-1");
                break;
            case 2:
                check = InputCheck.checkRunnerFromConsole();
                break;
            case 3:
                check = InputCheck.checkRunnerFromFile();
                break;
            default:
                System.out.println("Oooops, something wrong!");
        }

        if (secondAnswer == 1){
            OutputCheck.checkInConsole(check);
        }else {
            OutputCheck.checkInFile(check);
        }
    }
}