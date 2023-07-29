package Toys.Lottery;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Toys.Items.ToyFactory;

public class Lottery<tf> {
    private static ToyFactory tf = ToyFactory.createToyFactory();
    private Random random = new Random();
    private static boolean rafflStarted = false;
    private static int itemsAmt = 0;
    private static List<Lot> content = new ArrayList<Lot>();

    public void addLot() {
        if (!rafflStarted) {
            Lot Lot = new Lot(tf.createToy(input("Введите название: ")));
            setAmtForLot(Lot);
            content.add(Lot);
            itemsAmt += Lot.getAmt();
            calcProbForLots();
            System.out.print("Лот добавлен\n");
        }
        else {
            System.out.print("Розыгрыш начался. Нельзя что либо менять\n");
        }
    }

    private static Lot getLotById(int id) {
        for (Lot Lot: content) {
            if (Lot.getId() == id) {
                System.out.println(Lot);
                return Lot;
            }
        }
        System.out.print("id не найден\n");
        return null;
    }

    private static void setAmtForLot(Lot Lot) {
        int amt;
        String input;
        while (true) {
            input = input("Введите количество игрушек: ");
            while (!isNumber(input)) {
                input = input("Введите количество игрушек: ");
            }
            amt = Integer.parseInt(input);
            if (isValidAmt(amt)) {
                break;
            }
        }
        Lot.setAmt(amt);
    }

    public void changeAmtForLot() {
        if (!rafflStarted) {
            String input;
            input = input("Введите id: ");
            while (!isNumber(input)) {
                input = input("Введите id: ");
            }
            int id = Integer.parseInt(input);
            Lot Lot = getLotById(id);
            if (Lot != null) {
                int oldAmt = Lot.getAmt();
                int newAmt;
                while (true) {
                    input = input("Введите новое количество: ");
                    while (!isNumber(input)) {
                        input = input("Введите новое количество: ");
                    }
                    newAmt = Integer.parseInt(input);
                    if (isValidAmt(newAmt)) {
                        break;
                    }
                }
                Lot.setAmt(newAmt);
                itemsAmt = itemsAmt - oldAmt + newAmt;
                calcProbForLots();
                System.out.print("Готово\n");
            }
        }
        else {
            System.out.print("Розыгрыш начался. Нельзя что либо менять\n");
        }
    }

    private static boolean isNumber(String str) {
        if (str.matches("^\\d+$")) {
            return true;
        }
        System.out.print("Нужно ввести число\n");
        return false;
    }

    private static boolean isValidAmt(int amt) {
        if (amt < 1) {
            System.out.print("Количество игрушек должно быть больше 0\n");
            return false;
        }
        return true;
    }

    private static void calcProbForLots() {
        double prob;
        for (Lot Lot: content) {
            prob = (double)Lot.getAmt() / (double)itemsAmt;
            Lot.setProb(prob);
        }
    }

    /**
     * @return
     */
    public Lot getPrize() {
        if (rafflStarted) {
            if (itemsAmt > 0) {
                double roll = random.nextDouble();
                double summ = content.get(0).getProb() + roll;
                int i = 1;
                while (summ <= 1) {
                    summ += content.get(i).getProb();
                    i += 1;
                }
                i -= 1;
                Lot Lot = content.get(i);
                Lot.decrAmt();
                if (Lot.getAmt() == 0) {
                    content.remove(i);
                }
                itemsAmt -= 1;
                calcProbForLots();
                System.out.println(Lot);
                return Lot;
            }
            System.out.print("Лот пуст\n");
            return null;
        }
        System.out.print("Начните розыгрыш, чтобы достать приз\n");
        return null;
    }

    public void startRaffl() {
        if (!rafflStarted) {
            rafflStarted = true;
            System.out.print("Розыгрыш начался\n");
        }
        else {
            System.out.print("Розыгрыш уже идёт\n");
        }
    }

    private static String input(String message) {
        System.out.print(message);
        return System.console().readLine().strip();
    }

    @Override
    public String toString() {
        if (itemsAmt > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("В лоте " + itemsAmt + " игрушек\n");
            for (Lot Lot: content) {
                sb.append(Lot.toString()).append("\n");
            }
            return sb.toString();
        }
        return "Лот пуст\n";
    }
}
