package Toys.Lottery;
import Toys.Items.Lotable;
// import Toys.Items.Toy;

public class Lot {
    public static final String Lottery = null;
    // private static final Lotable Toy = null;
    private int id;
    private Lotable Item;
    private int amt;
    private double prob;
    public Toys.Lottery.Lot Lot;

    public Lot(Lotable Toy) {
        this.Item = Toy;
        this.id = Toy.getId();
    }

    public Lot(Toys.Items.Toy createToy) {
    }

    public Lot() {
    }

    public int getId() {
        return id;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public void decrAmt() {
        amt -= 1;
    }

    public double getProb() {
        return prob;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public Lotable getItem() {
        return Item;
    }

    @Override
    public String toString() {
        return Item.toString() + " " + amt + "шт" + " " + prob;
    }

    public void addLot() {
    }

    public void changeAmtForLot() {
    }

    public void startRaffl() {
    }

    public Lot getPrize() {
        return null;
    }
}
