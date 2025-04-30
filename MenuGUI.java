import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuGUI extends JFrame implements ActionListener {

    private ArrayList<MenuItem> menuItems;
    private ArrayList<MenuItem> orderItems;
    private JTextArea orderTextArea;
    private JLabel totalCostLabel;
    private BigDecimal totalCost;

    public FoodOrderSystemGUI() {
        menuItems = createMenu();
        orderItems = new ArrayList<>();
        totalCost = BigDecimal.ZERO;

        setTitle("Food Ordering System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGUI();
    }

    private void createGUI() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel menuPanel = createMenuPanel();
        JPanel orderPanel = createOrderPanel();
        JPanel buttonPanel = createButtonPanel();

        panel.add(menuPanel, BorderLayout.WEST);
        panel.add(orderPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        Font font = new Font("Arial", Font.PLAIN, 14); 

        for (MenuItem menuItem : menuItems) {
            JPanel menuItemPanel = new JPanel(new BorderLayout());
            menuItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel nameLabel = new JLabel(menuItem.getName() + " - ₱" + menuItem.getPrice());
            nameLabel.setFont(font);
            menuItemPanel.add(nameLabel, BorderLayout.CENTER);

            JComboBox<String> varietyComboBox = new JComboBox<>(menuItem.getVarieties().toArray(new String[0]));
            varietyComboBox.setFont(font);
            varietyComboBox.addActionListener(e -> addToOrder(menuItem, (String) varietyComboBox.getSelectedItem()));
            menuItemPanel.add(varietyComboBox, BorderLayout.EAST);

            menuPanel.add(menuItemPanel);
        }

        return menuPanel;
    }

    private JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Your Order"));

        orderTextArea = new JTextArea(10, 20);
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        orderPanel.add(new JScrollPane(orderTextArea), BorderLayout.CENTER);

        totalCostLabel = new JLabel("Total Cost: ₱" + totalCost);
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        orderPanel.add(totalCostLabel, BorderLayout.SOUTH);

        return orderPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(e -> placeOrder());
        placeOrderButton.setFont(new Font("Sans serif", Font.BOLD, 15));
        buttonPanel.add(placeOrderButton);

        JButton changeOrderButton = new JButton("Change Order");
        changeOrderButton.addActionListener(e -> changeOrder());
        changeOrderButton.setFont(new Font("Sans serif", Font.BOLD, 15));
        buttonPanel.add(changeOrderButton);

        return buttonPanel;
    }

    private ArrayList<MenuItem> createMenu() {
        ArrayList<MenuItem> menu = new ArrayList<>();

        ArrayList<String> burgerVarieties = new ArrayList<>();
        burgerVarieties.add("Beef Burger");
        burgerVarieties.add("Chicken Burger");
        burgerVarieties.add("Classic Burger");
        burgerVarieties.add("Specialty Burger");
        burgerVarieties.add("Cheese Burger");
        menu.add(new MenuItem("Burger", burgerVarieties, new BigDecimal("65.00")));

        ArrayList<String> pizzaVarieties = new ArrayList<>();
        pizzaVarieties.add("Margherita Pizza");
        pizzaVarieties.add("Pepperoni Pizza");
        pizzaVarieties.add("California Pizza");
        pizzaVarieties.add("New York Style Pizza");
        pizzaVarieties.add("Greek Pizza");
        menu.add(new MenuItem("Pizza", pizzaVarieties, new BigDecimal("120.00")));

        ArrayList<String> silogVarieties = new ArrayList<>();
        silogVarieties.add("Tapsilog");
        silogVarieties.add("Longsilog");
        silogVarieties.add("Tocilog");
        silogVarieties.add("Bangsilog");
        silogVarieties.add("Danggitsilog");
        menu.add(new MenuItem("Silog", silogVarieties, new BigDecimal("80.00")));

        ArrayList<String> nachosVarieties = new ArrayList<>();
        nachosVarieties.add("Nachos Supreme");
        nachosVarieties.add("Trash Can Nachos");
        nachosVarieties.add("BBQ Chicken Nachos");
        nachosVarieties.add("Best Totchos");
        nachosVarieties.add("Nacho Cheese");
        menu.add(new MenuItem("Nachos", nachosVarieties, new BigDecimal("65.00")));

        ArrayList<String> adoboVarieties = new ArrayList<>();
        adoboVarieties.add("Adobong Manok");
        adoboVarieties.add("Adobo sa Gatâ");
        adoboVarieties.add("Adobong Baboy");
        adoboVarieties.add("Adobong Talóng.");
        adoboVarieties.add("Adobong Kangkóng");
        menu.add(new MenuItem("Adobo", adoboVarieties, new BigDecimal("105.00")));

        ArrayList<String> saladVarieties = new ArrayList<>();
        saladVarieties.add("Caesar Salad");
        saladVarieties.add("Leafy Green Salad");
        saladVarieties.add("Greek Salad");
        saladVarieties.add("Fattoush");
        saladVarieties.add("Niçoise Salad");
        menu.add(new MenuItem("Salad", saladVarieties, new BigDecimal("100.00")));

        return menu;
    }

    private void updateOrderTextArea() {
        orderTextArea.setText("");
        for (MenuItem item : orderItems) {
            orderTextArea.append(item.getName() + " - ₱" + item.getPrice() + "\n");
        }
        totalCostLabel.setText("Total Cost: ₱" + totalCost);
    }

    private void addToOrder(MenuItem menuItem, String variety) {
        MenuItem selectedItem = new MenuItem(menuItem.getName() + " (" + variety + ")", null, menuItem.getPrice());
        orderItems.add(selectedItem);
        totalCost = totalCost.add(selectedItem.getPrice());
        updateOrderTextArea();
    }

    private void placeOrder() {
        JOptionPane.showMessageDialog(this, "Order placed successfully!");
        orderItems.clear();
        totalCost = BigDecimal.ZERO;
        updateOrderTextArea();
    }

    private void changeOrder() {
        String input = JOptionPane.showInputDialog(this, "Enter the index of the item to remove:");
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < orderItems.size()) {
                MenuItem removedItem = orderItems.remove(index);
                totalCost = totalCost.subtract(removedItem.getPrice());
                updateOrderTextArea();
                JOptionPane.showMessageDialog(this, "Item removed from order.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid index. Please enter a valid index.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new FoodOrderSystemGUI();
    }
}

class MenuItem {
    private String name;
    private ArrayList<String> varieties;
    private BigDecimal price;

    public MenuItem(String name, ArrayList<String> varieties, BigDecimal price) {
        this.name = name;
        this.varieties = varieties;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getVarieties() {
        return varieties;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
