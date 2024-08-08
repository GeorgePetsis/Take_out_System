import java.util.Scanner;


public class TakeOutSimulator  {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.menu = new FoodMenu();
        this.input = input;

    }

    public  <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {
        while (true) {

            System.out.println(userInputPrompt);
            if (input.hasNextInt()) {
                int userInput = input.nextInt();
                input.nextLine();

                try {
                    return intUserInputRetriever.produceOutputOnIntUserInput(userInput);

                } catch (IllegalArgumentException e) {
                    System.out.println(userInput + " is not a valid input. Try again!");
                }

            } else {
                System.out.println("Input needs to be an 'int' type.");
                input.nextLine();
            }

        }
    }

    public boolean shouldSimulate() {
        String userPrompt = "Enter 1 to CONTINUE simulation or 0 to EXIT program: ";


        IntUserInputRetriever<Boolean> intUserInputRetriever = (int selection) -> {

                if (selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                    return true;

                } else if ( selection == 0) {
                    System.out.println("Thank you for eating with us! Hope to see you soon!");
                    return false;
                } else  {
                    throw new IllegalArgumentException("Selection must be 0 or 1.");
                }
        };
        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);

    }

    public Food getMenuSelection() {

        System.out.println("Today's Menu Options!");
        System.out.println(menu);

        String userPrompt = "Choose a menu item: ";

        IntUserInputRetriever<Food> intUserInputRetriever = (int selection) -> {
            if (menu.getFood(selection) != null) {
                return menu.getFood(selection);
            } else {
                throw new IllegalArgumentException();
            }
        };
        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public boolean isStillOrderingFood() {
        String userPrompt = "Enter 1 to CONTINUE shopping or 0 to CHECKOUT: ";
        IntUserInputRetriever<Boolean> intUserInputRetriever = (int selection) -> {
            if (selection == 1) {
                return true;
            } else if (selection == 0) {
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        };
        return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Processing payment...");

        int remainingMoney = customer.getMoney() - shoppingBag.getTotalPrice();
        System.out.println("Your remaining money: $" + remainingMoney);

        customer.setMoney(remainingMoney);
        System.out.println("Thank you and enjoy your food!");


    }

    public void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        boolean stillOrdering = true;

        while (stillOrdering) {
            System.out.println("You have $" + customerMoneyLeft + " left to spend\n");

            Food item = this.getMenuSelection();


            if (customerMoneyLeft >= item.getPrice()) {
                customerMoneyLeft -= item.getPrice();
                shoppingBag.addItem(item);

            } else {
                System.out.println("\nOops! Looks like you don't have enough for that. Choose another item or checkout.");

            }

            stillOrdering = this.isStillOrderingFood();

            if (stillOrdering == false) {
                checkoutCustomer(shoppingBag);
            }
        }
    }

    public void startTakeOutSimulator() {
        boolean wantSimulate = true;

        System.out.println("\nHello, welcome to my restaurant!\n");
        while (wantSimulate) {
            System.out.println("Welcome " + customer.getName());

            this.takeOutPrompt();
            wantSimulate = this.shouldSimulate();

        }

    }

    /*public static void main(String[] args) {
        TakeOutSimulator simulator = new TakeOutSimulator(new Customer("George", 50), new Scanner(System.in));
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        shoppingBag.addItem(new Food("sdfs", "fs", 15));

        simulator.checkoutCustomer(shoppingBag);
    }*/



}

