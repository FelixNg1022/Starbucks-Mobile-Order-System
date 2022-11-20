package persistence;

import model.Drink;
import model.lists.DrinkList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.lists.OrderList;
import model.lists.MakingList;
import model.lists.MadeList;
import org.json.*;

// Represents a reader that reads drinks from JSON data stored in file
// This class references code from CPSC 210 JsonSerializaitionDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads drink list from file and returns it
    // throws IOException if an error occurs while reading date from the file
    public DrinkList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDrinkList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses drink list from JSON object and returns it
    private DrinkList parseDrinkList(JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        DrinkList list;
        switch (type) {
            case "Ordered":
                list = new OrderList();
                break;
            case "Making":
                list = new MakingList();
                break;
            case "Made":
                list = new MadeList();
                break;
            default:
                throw new IllegalStateException("Invalid type value " + type);
        }
        addDrinks(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses entries from JSON object and adds them to drink list
    private void addDrinks(DrinkList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addDrink(list, nextEntry);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses drink from JSON object and adds it to drink list
    private void addDrink(DrinkList list, JSONObject jsonObject) {
        String customerName = jsonObject.getString("customer name");
        Drink.Status status = Drink.Status.valueOf(jsonObject.getString("status"));
        Drink.Size size = Drink.Size.valueOf(jsonObject.getString("size"));
        Drink.Type type = Drink.Type.valueOf(jsonObject.getString("type"));
        Drink drink = new Drink(customerName, status, size, type);
        DrinkList.addDrink(drink);
    }
}
