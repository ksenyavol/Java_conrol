package Toys.Items;

public class ToyFactory {
    private static ToyFactory instance = null;
    private static int ToyId = 0;

    public static ToyFactory createToyFactory() {
        if (instance == null) {
            instance = new ToyFactory();
        }
        return instance;
    }

    ToyFactory() {}

    public Toy createToy(String name) {
        ToyId += 1;
        return new Toy(ToyId, name);
    }
}
