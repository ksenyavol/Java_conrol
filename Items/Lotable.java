package Toys.Items;

public class Lotable {
    private static ToyFactory instance = null;
    private static int ToyId = 0;

    public static ToyFactory createToyFactory() {
        if (instance == null) {
            instance = new ToyFactory();
        }
        return instance;
    }

    private Lotable() {}

    public Toy createToy(String name) {
        ToyId += 1;
        return new Toy(ToyId, name);
    }

    public int getId() {
        return 0;
    }
}
