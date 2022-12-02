package ui;

import model.Drink;
import model.lists.DrinkList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button for the made list
public class Made extends JPanel {
    DrinkList ml;
    private static final String addString = "Add Drink";
    private static final String removeString = "Remove Drink";
    private JTextField entryName1;
    private JTextField entryName2;
    private JTextField entryName3;
    private JTextField entryName4;
    private final JTable table;

    private final String[] col = {"Customer name", "Size", "Type", "Drink ID"};
    DefaultTableModel tableModel;

    // constructor for ordered list
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public Made(DrinkList l) {
        super(new BorderLayout());

        ml = l;
        tableModel = new DefaultTableModel(col, 0);
        for (Drink next: ml.getList()) {
            String customerName = next.getCustomerName();
            String size = next.getSize().toString();
            String type = next.getType().toString();
            int drinkID = next.getDrinkID();
            Object[] data = {customerName, size, type, drinkID};
            tableModel.addRow(data);
        }

        table = new JTable(tableModel);
        JScrollPane listScrollPane = new JScrollPane(table);

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        JButton removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

        textEntries(addListener, removeListener);

        createPanel(listScrollPane, addButton, removeButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes text entries
    private void textEntries(AddListener addListener, RemoveListener removeListener) {
        entryName1 = new JTextField(10);
        entryName1.addActionListener(addListener);
        entryName1.getDocument().addDocumentListener(addListener);
        entryName2 = new JTextField(10);
        entryName2.addActionListener(removeListener);
        entryName2.getDocument().addDocumentListener(addListener);
        entryName3 = new JTextField(10);
        entryName3.addActionListener(addListener);
        entryName3.getDocument().addDocumentListener(addListener);
        entryName4 = new JTextField(10);
        entryName4.addActionListener(addListener);
        entryName4.getDocument().addDocumentListener(removeListener);
    }

    // EFFECTS: creates JPanel
    private void createPanel(JScrollPane listScrollPane, JButton addButton, JButton removeButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(entryName4);
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(entryName1);
        buttonPane.add(entryName2);
        buttonPane.add(entryName3);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // event listener for remove button and its text field
    private class RemoveListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public RemoveListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String drinkID = entryName4.getText();

            tableModel.removeRow(ml.getList().indexOf(ml.getDrink(Integer.parseInt(drinkID))));
            ml.removeDrink(Integer.parseInt(drinkID));

            //Reset the text field.
            entryName4.requestFocusInWindow();
            entryName4.setText("");
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // event listener for add button and its text fields
    private class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String customerName = entryName1.getText();
            String size = entryName2.getText();
            String type = entryName3.getText();

            Drink drink1 = new Drink(customerName, Drink.Size.valueOf(size), Drink.Type.valueOf(type));
            ml.addDrink(drink1);
            Object[] entry = {customerName, size, type, drink1.getDrinkID()};
            tableModel.addRow(entry);

            //Reset the text field.
            entryName1.requestFocusInWindow();
            entryName1.setText("");
            entryName2.requestFocusInWindow();
            entryName2.setText("");
            entryName3.requestFocusInWindow();
            entryName3.setText("");
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and displays the list
    public static void createAndShowGUI(DrinkList list) {
        //Create and set up the window.
        JFrame frame = new JFrame("Made List");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Order(list);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
