import  java.util.ArrayList;
import java.util.List;


public class FoodMenu {
    private final List<Food>  menu;

    public FoodMenu() {
        Food food1 = new Food(" Green salad", "healthy", 7);
        Food food2 = new Food("Greek salad", "tomato, cucumber, olives, 'Feta' (greek white cheese), onions, caper, oregano, olive oil", 12);
        Food food3 = new Food("Tacos", "Yumy steak tacos", 10);
        Food food4 = new Food("Ramen", "Hot pork ramen", 12);
        Food food5 = new Food("Meatballs", "half pork - half beef mixed meatballs, 10 pieces", 15 );
        this.menu = new ArrayList<>();
        menu.add(food1);
        menu.add(food2);
        menu.add(food3);
        menu.add(food4);
        menu.add(food5);
    }
    public Food getFood(int index) {
        if (index > menu.size() || index < 1) {
            return null;
        }
        return menu.get(index-1);
    }

    public Food getLowestCostFood(){
        int lowestPrice = menu.get(0).getPrice();
        Food lowestPriceFood = menu.get(0);
        for (Food food : menu) {
            int comparePrice = food.getPrice();
            if (comparePrice < lowestPrice) {
                lowestPrice = comparePrice;
                lowestPriceFood = food;
            }
        }
        return lowestPriceFood;
    }


    @Override
    public String toString() {
        String returnString = "";
        int index= 1;
        for (Food food: menu){
            returnString += index + ". " + food.toString() + "\n";
            index++;
        }
        return returnString;
    }

    public static void main(String[] args) {
        FoodMenu foodMenu = new FoodMenu();
        //System.out.println(foodMenu);

        System.out.println(foodMenu.getLowestCostFood());

    }


}


