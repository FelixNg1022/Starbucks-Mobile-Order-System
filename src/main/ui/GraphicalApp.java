package ui;

import model.Event;
import model.EventLog;
import model.lists.DrinkList;
import model.lists.MadeList;
import model.lists.MakingList;
import model.lists.OrderList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Starbucks Order System application with GUI
public class GraphicalApp {
    static JFrame frame;
    private static final String JSON_STORE1 = "./data/order.json";
    private static final String JSON_STORE2 = "./data/making.json";
    private static final String JSON_STORE3 = "./data/made.json";
    private static JsonWriter jsonWriter1;
    private static JsonReader jsonReader1;
    private static JsonWriter jsonWriter2;
    private static JsonReader jsonReader2;
    private static JsonWriter jsonWriter3;
    private static JsonReader jsonReader3;
    private static DrinkList orderList;
    private static DrinkList makingList;
    private static DrinkList madeList;

    // EFFECTS: constructs the JPanel
    public static JPanel createUI() throws IOException {

        init();

        //Create the labels.
        JLabel title = new JLabel("Starbucks Mobile Order APP");
        BufferedImage myPicture = ImageIO.read(new File("./data/Starbucks.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        title.setFont(new Font("Helvetica Neue", Font.BOLD, 14));

        final JButton button1 = makeOrderedButton();

        final JButton button2 = makeMakingButton();

        final JButton button3 = makeMadeButton();

        final JButton button4 = makeSaveButton();

        final JButton button5 = makeLoadButton();

        return createPanel(title, picLabel, button1, button2, button3, button4, button5);
    }

    // MODIFIES: this
    // EFFECTS: makes the order list button and open its window
    private static JButton makeOrderedButton() {
        //Create the "Finished" button.
        final JButton button1 = new JButton("Ordered");
        button1.addActionListener(e -> Order.createAndShowGUI(orderList));
        return button1;
    }

    // MODIFIES: this
    // EFFECTS: makes the making list button and open its window
    private static JButton makeMakingButton() {
        final JButton button2 = new JButton("Making");
        button2.addActionListener(e -> Making.createAndShowGUI(makingList));
        return button2;
    }

    // MODIFIES: this
    // EFFECTS: makes the made list button and open its window
    private static JButton makeMadeButton() {
        final JButton button3 = new JButton("Made");
        button3.addActionListener(e -> Made.createAndShowGUI(madeList));
        return button3;
    }

    // MODIFIES: this
    // EFFECTS: makes the save button and saves lists to file
    private static JButton makeSaveButton() {
        //Create the "Save" button.
        final JButton button4 = new JButton("Save");
        button4.addActionListener(e -> {
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
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        return button4;
    }

    // MODIFIES: this
    // EFFECTS: makes the load button and loads lists from file
    private static JButton makeLoadButton() {
        //Create the "Load" button.
        final JButton button5 = new JButton("Load");
        button5.addActionListener(e -> {
            try {
                orderList = jsonReader1.read();
                makingList = jsonReader2.read();
                madeList = jsonReader3.read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return button5;
    }

    // EFFECTS: creates the JPanel with all labels
    private static JPanel createPanel(JLabel title, JLabel picLabel, JButton button1,
                                      JButton button2, JButton button3, JButton button4, JButton button5) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,
                BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button4.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button5.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        picLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(picLabel);

        panel.add(Box.createRigidArea(new Dimension(150,10)));

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: initializes lists and JSON
    private static void init() {
        orderList = new OrderList();
        makingList = new MakingList();
        madeList = new MadeList();
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter3 = new JsonWriter(JSON_STORE3);
        jsonReader3 = new JsonReader(JSON_STORE3);
    }

    // EFFECTS: prints the event log to console
    public static void printLog(EventLog eventLog) {
        for (Event next: eventLog) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Set up and display the window.
    public static void createAndShowGUI() throws IOException {
        frame = new JFrame("Starbucks Mobile APP");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });

        JComponent newContentPane = createUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
