// Необходимо написать программу – розыгрыша игрушек в магазине детских товаров.

package Toys;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import Toys.Lottery.Lot;
import Toys.Lottery.Lottery;
// import Toys.Lottery.*;
import java.util.LinkedList;

public class Main {
    // private static final char[] Lottery = null;
    private static String file_name = "Raffl_result";
    private static Queue<Lot> prizes = new LinkedList<Lot>();
    private static Lottery Lottery = new Lottery();
    public static void main(String[] args) {
        String menu = "Команды:\n" +
                      "0 - показать команды\n" +
                      "1 - добавить новый лот\n" +
                      "2 - изменить количество игрушек в лоте\n" +
                      "3 - показать что в лоте\n" +
                      "4 - начать розыгрыш\n" +
                      "5 - достать игрушку из лота и поместить в выдачу\n" +
                      "6 - записать игрушку в файл из выдачи\n" +
                      "7 - завершить программу\n";

        System.out.print("Розыгрыш игрушек\n");
        System.out.print(menu);
        boolean run = true;
        Lot Lot;
        String command;
        while (run) {
            command = input("Введите команду: ");
            switch (command) {
                case "0":
                    System.out.print(menu);
                    break;
                case "1":
                    Lottery.addLot();
                    break;
                case "2":
                    Lottery.changeAmtForLot();
                    break;
                case "3":
                    System.out.print(Lottery);
                    break;
                case "4":
                    Lottery.startRaffl();
                    break;
                case "5":
                    Lot = Lottery.getPrize();
                    if (Lot != null) {
                        prizes.add(Lot);
                    }
                    break;
                case "6":
                    writePrize();
                    break;
                case "7":
                    run = false;
                    break;
                default:
                    System.out.print("Нет такой команды. Введите 0 для просмотра всех команд\n");
                    break;
            }
        }
    }

    public static void writePrize() {
        if (prizes.size() > 0) {
            Lot Lot = prizes.poll();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true));
                writer.write(Lot.getItem().toString() + "\n");
                writer.close();
                System.out.print("Игрушка успешно записана в файл\n");
            }
            catch (IOException e) {
                System.out.println("Ошибка при записи файла: " + e.getMessage());
            }
        }
        else {
            System.out.print("В выдаче нет игрушек\n");
        }
    }

    public static String input(String message) {
        System.out.print(message);
        return System.console().readLine().strip();
    }
}