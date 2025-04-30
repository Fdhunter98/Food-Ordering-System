import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class FoodOrderSystemGUI extends JFrame implements ActionListener {

    private ArrayList<MenuItem> menuItems;
    private ArrayList<MenuItem> orderItems;
    private ArrayList<MenuItem> drinkItems;
    private JTextArea orderTextArea;
    private BigDecimal totalCost;

    public FoodOrderSystemGUI() {
        menuItems = createMenu();
        drinkItems = createDrinks();
        orderItems = new ArrayList<>();
        totalCost = BigDecimal.ZERO;

        setTitle("Savor Spot Eatery");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Arial", Font.PLAIN, 14);

        for (MenuItem menuItem : menuItems) {
            JLabel nameLabel = new JLabel(menuItem.getName() + " - ₱" + menuItem.getPrice());
            nameLabel.setFont(font);
            menuPanel.add(nameLabel);

            JComboBox<String> varietyComboBox = new JComboBox<>(menuItem.getVarieties().toArray(new String[0]));
            varietyComboBox.setFont(font);
            varietyComboBox.addActionListener(this);
            menuPanel.add(varietyComboBox);
        }

        panel.add(menuPanel, BorderLayout.WEST);

        JPanel drinkPanel = new JPanel(new GridLayout(0, 1));
        drinkPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));
        drinkPanel.setBackground(Color.LIGHT_GRAY);

        for (MenuItem drinkItem : drinkItems) {
            JLabel nameLabel = new JLabel(drinkItem.getName() + " - ₱" + drinkItem.getPrice());
            nameLabel.setFont(font);
            drinkPanel.add(nameLabel);

            JComboBox<String> varietyComboBox = new JComboBox<>(drinkItem.getVarieties().toArray(new String[0]));
            varietyComboBox.setFont(font);
            varietyComboBox.addActionListener(this);
            drinkPanel.add(varietyComboBox);
        }

        panel.add(drinkPanel, BorderLayout.EAST);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Your Order"));
        orderPanel.setBackground(Color.WHITE);
        orderTextArea = new JTextArea(10, 20);
        orderTextArea.setEditable(false);
        orderTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        orderPanel.add(new JScrollPane(orderTextArea), BorderLayout.CENTER);

        panel.add(orderPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(this);
        placeOrderButton.setFont(new Font("Sans serif", Font.BOLD, 15));
        buttonPanel.add(placeOrderButton);

        JButton changeOrderButton = new JButton("Change Order");
        changeOrderButton.addActionListener(e -> changeOrder());
        changeOrderButton.setFont(new Font("Sans serif", Font.BOLD, 15));
        buttonPanel.add(changeOrderButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
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
        adoboVarieties.add("Adobong Talóng");
        adoboVarieties.add("Adobong Kangkóng");
        menu.add(new MenuItem("Adobo", adoboVarieties, new BigDecimal("105.00")));

        ArrayList<String> saladVarieties = new ArrayList<>();
        saladVarieties.add("Caesar Salad");
        saladVarieties.add("Leafy Green Salad");
        saladVarieties.add("Greek Salad");
        saladVarieties.add("Fattoush");
        saladVarieties.add("Niçoise Salad");
        menu.add(new MenuItem("Salad", saladVarieties, new BigDecimal("100.00")));

        ArrayList<String> chickenVarieties = new ArrayList<>();
        chickenVarieties.add("Lechon Manok");
        chickenVarieties.add("Chicken Curry");
        chickenVarieties.add("Chicken Marsala");
        chickenVarieties.add("Chicken Tikka Masala");
        chickenVarieties.add("Butter Chicken");
        menu.add(new MenuItem("Chicken", chickenVarieties, new BigDecimal("250.00")));

        ArrayList<String> riceVarieties = new ArrayList<>();
        riceVarieties.add("Garlic Rice");
        riceVarieties.add("Biryani Rice");
        riceVarieties.add("Brown Rice");
        riceVarieties.add("Onion Rice");
        riceVarieties.add("Basmati Rice");
        menu.add(new MenuItem("Rice", riceVarieties, new BigDecimal("25.00")));

        return menu;
    }

    private ArrayList<MenuItem> createDrinks() {
        ArrayList<MenuItem> drinks = new ArrayList<>();
        ArrayList<String> pitcherVarieties = new ArrayList<>();
        pitcherVarieties.add("Water");
        pitcherVarieties.add("Nestea");
        pitcherVarieties.add("Orange Juice");
        pitcherVarieties.add("Apple Juice");
        drinks.add(new MenuItem("Pitcher", pitcherVarieties, new BigDecimal("50.00")));

        ArrayList<String> canVarieties = new ArrayList<>();
        canVarieties.add("Coke");
        canVarieties.add("Sprite");
        canVarieties.add("Mountain Dew");
        canVarieties.add("Pepsi");
        drinks.add(new MenuItem("Can", canVarieties, new BigDecimal("20.00")));

        ArrayList<String> literVarieties = new ArrayList<>();
        literVarieties.add("Coke");
        literVarieties.add("Sprite");
        literVarieties.add("Pepsi");
        drinks.add(new MenuItem("Liter", literVarieties, new BigDecimal("35.00")));

        return drinks;
    }

    private void updateOrderTextArea() {
        orderTextArea.setText("");
        for (MenuItem item : orderItems) {
            orderTextArea.append(item.getName() + " - ₱" + item.getPrice() + "\n");
        }
        orderTextArea.append("\nTotal Cost: ₱" + totalCost);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command != null && command.equals("Place Order")) {
            JOptionPane.showMessageDialog(this, "Order placed successfully!", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
            orderItems.clear();
            totalCost = BigDecimal.ZERO;
            updateOrderTextArea();
        } else {
            if (e.getSource() instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedVariety = (String) comboBox.getSelectedItem();

                MenuItem selectedItem = findMenuItem(selectedVariety);

                if (selectedItem != null) {
                    orderItems.add(selectedItem);
                    totalCost = totalCost.add(selectedItem.getPrice());
                    updateOrderTextArea();
                }
            }
        }
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

    private MenuItem findMenuItem(String variety) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getVarieties().contains(variety)) {
                return new MenuItem(menuItem.getName() + " (" + variety + ")", null, menuItem.getPrice());
            }
        }
        for (MenuItem drinkItem : drinkItems) {
            if (drinkItem.getVarieties().contains(variety)) {
                return new MenuItem(drinkItem.getName() + " (" + variety + ")", null, drinkItem.getPrice());
            }
        }
        return null;
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