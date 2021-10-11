import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Cloth jacket = new Cloth(10);
        Grocery apple = new Grocery("Apples");
        apple.setCategory("Fruit");
        apple.setStock(1000);

        // let's call behaviors defined in our "Store" interface
        jacket.open();
        apple.open();

        // let's call some unique behavior
        System.out.println("We sell " + apple.getCategory());
        System.out.println("We sell different sizes of clothes like " + jacket.getSize());

        jacket.close();
        apple.close();

        // we can specify the type of an object as an interface, but the API of the type is only SStore
        Store pants = new Cloth(100);
        Store cookie = new Grocery("Cookies");

        /**
         *  In your Main class, instantiate an object of both of your store classes
         *  Put the two objects into a collection such as an array or arraylist
         *  Your collection type should be the Store interface
         *  Loop through the objects and call all of the defined interface methods on each object
         */
        ArrayList<Store> stores = new ArrayList<>();
        stores.add(pants);
        stores.add(cookie);

        for (Store s : stores) {
            s.open();
            System.out.println("==> We sell " + s.getItem() + ".");
            s.sell();
            s.restock();
            System.out.println(s.getRemainingStock());
            s.close();
        }
    }
}
