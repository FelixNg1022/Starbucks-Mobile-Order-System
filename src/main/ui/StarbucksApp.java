package ui;

import model.Drink;
import model.lists.DrinkList;
import model.lists.MadeList;
import model.lists.MakingList;
import model.lists.OrderList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StarbucksApp {
    private static final String JSON_STORE1 = "./data/order.json";
    private static final String JSON_STORE2 = "./data/making.json";
    private static final String JSON_STORE3 = "./data/made.json";
    private DrinkList orderList;
    private DrinkList makingList;
    private DrinkList madeList;
    private Scanner input;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    private JsonWriter jsonWriter3;
    private JsonReader jsonReader3;

    // EFFECTS: runs the Starbucks app
    public StarbucksApp() throws FileNotFoundException {
        runStarbucks();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runStarbucks() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n-----Ending Session-----");
    }

    // MODIFIES: this
    // EFFECTS; processes user command
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void processCommand(String command) {
        switch (command.toLowerCase()) {
            case "a":
                doAddDrink();
                break;
            case "r":
                doRemoveDrink();
                break;
            case "h":
                doHasDrink();
                break;
            case "g":
                doGetDrink();
                break;
            case "l":
                doListSize();
                break;
            case "s":
                doChangeStatus();
            case "save":
                doSave();
            case "load":
                doLoad();
            default:
                System.out.println("-----Invalid Command-----");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes drink list
    private void init() {
        orderList = new OrderList();
        makingList = new MakingList();
        madeList = new MadeList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n-----Choose from the following commands: -----");
        System.out.println("\ta -> add a drink");
        System.out.println("\tr -> cancel a drink order");
        System.out.println("\th -> check if the drink list has a drink");
        System.out.println("\tg -> print the information of a drink from the list");
        System.out.println("\tl -> print the drink list size");
        System.out.println("\ts -> change a drink's status and add it to the corresponding list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add a new drink to the list
    private void doAddDrink() {
        System.out.println("-----What drink would you like? We have Americano, Latte, and Cappuccino!-----");
        String type = input.next();

        System.out.println("-----What size would you like? We have Tall, Grande, and Venti!-----");
        String size = input.next();

        System.out.println("-----May I have your name for the order?-----");
        String name = input.next();

        Drink drink = new Drink(name, model.Drink.Size.valueOf(size), model.Drink.Type.valueOf(type));
        DrinkList.addDrink(drink);

        System.out.println(drink);
    }

    // MODIFIES: this
    // EFFECTS: remove a drink from the list if the drink is not being made or made
    private void doRemoveDrink() {
        System.out.println("-----What's the drink ID?-----");
        int id = input.nextInt();

        if (orderList.removeDrink(id) && orderList.getDrink(id).getStatus() == Drink.Status.Making) {
            orderList.removeDrink(id);
            System.out.println("-----Removed the drink successfully!-----");
        } else {
            System.out.println("-----Invalid drink ID! Please try again!-----");
        }
    }

    // MODIFIES: this
    // EFFECTS: check if a list has a drink
    private void doHasDrink() {
        System.out.println("-----What's the drink ID?-----");
        int id = input.nextInt();

        if (orderList.hasDrink(id)) {
            System.out.println("-----The list has the drink!-----");
        } else {
            System.out.println("-----Invalid drink ID! Please try again!-----");
        }
    }

    // MODIFIES: this
    // EFFECTS: get a drink from the list and display its information
    private void doGetDrink() {
        System.out.println("-----What's the drink ID?-----");
        int id = input.nextInt();

        if (orderList.hasDrink(id)) {
            System.out.println("-----The list has the drink! Below is the information of the drink!-----");
            System.out.println(orderList.getDrink(id));
        } else {
            System.out.println("-----Invalid drink ID! Please try again!-----");
        }
    }

    // EFFECTS: print the size of the list
    private void doListSize() {
        System.out.println("-----The list has " + orderList.listSize() + " drink!-----");
    }

    // REQUIRES: drink to exist in a drink list
    private boolean doChangeStatus() {
        System.out.println("-----What's the drink ID?-----");
        int id = input.nextInt();

        Drink drink;

        for (Drink d: orderList.getList()) {
            if (d.getDrinkID() == id) {
                drink = orderList.getDrink(id);
                drink.changeStatus();
                orderList.removeDrink(id);
                MakingList.addDrink(drink);
                return true;
            }
        }

        for (Drink d: makingList.getList()) {
            if (d.getDrinkID() == id) {
                drink = makingList.getDrink(id);
                drink.changeStatus();
                makingList.removeDrink(id);
                MadeList.addDrink(drink);
                return true;
            }
        }

        return false;
    }

    // EFFECTS: saves the lists to file
    private void doSave() {
        try {
            jsonWriter1.open();
            jsonWriter1.write(orderList);
            jsonWriter1.close();
            jsonWriter2.open();
            jsonWriter2.write(makingList);
            jsonWriter2.close();
            jsonWriter3.open();
            jsonWriter3.write(madeList);
            jsonWriter3.close();
            System.out.println("Saved " + orderList.getType() + " list to " + JSON_STORE1);
            System.out.println("Saved " + MakingList.getType() + " list to " + JSON_STORE2);
            System.out.println("Saved " + MadeList.getType() + " list to " + JSON_STORE3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE1
                    + " or " + JSON_STORE2 + " or "
                    + JSON_STORE3);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads lists from file
    private void doLoad() {
        try {
            orderList = jsonReader1.read();
            makingList = jsonReader2.read();
            madeList = jsonReader3.read();
            System.out.println("Loaded " + orderList.getType() + " list from " + JSON_STORE1);
            System.out.println("Loaded " + makingList.getType() + " list from " + JSON_STORE2);
            System.out.println("Loaded " + madeList.getType() + " list from " + JSON_STORE3);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE1
                    + " or " + JSON_STORE2
                    + " or " + JSON_STORE3);
        }
    }
}
