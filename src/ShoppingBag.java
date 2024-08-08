import java.util.Map;
import java.util.HashMap;


public class ShoppingBag<T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag;

    public ShoppingBag(){this.shoppingBag = new HashMap<>();}

    public void addItem(T item){
        if (shoppingBag.containsKey(item)){
            shoppingBag.put(item,shoppingBag.get(item) + 1);
        } else {
            shoppingBag.put(item, 1);
        }

    }

    public int getTotalPrice() {
        int grandTotal = 0;

        for (T item: shoppingBag.keySet()) {

            int sum = shoppingBag.get(item) * item.getPrice();
            grandTotal += sum;
        }
        return grandTotal;
    }


}
