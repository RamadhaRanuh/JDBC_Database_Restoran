package restaurant_AOL;

import javax.swing.*; // For JFrame, JPanel, JMenuBar, JMenu, JMenuItem, JOptionPane
import java.awt.*; // For BorderLayout, GridLayout, Font, SwingConstants
import java.awt.event.ActionEvent; // For ActionListener
import java.awt.event.ActionListener; // For ActionListener
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class FoodOrderingAppGUI {
    private JFrame frame; // Main window
    private JMenuBar menuBar; // Menu Container
    private JMenu manageCustomerProfileMenu, orderMenu; // Menus
    private JMenuItem addDataItem, updateDataItem, deleteDataItem, showAllDataItem, showMenuItem, topUpItem, orderFoodItem, orderDrinkItem, showOrdersItem, exitItem; // Menu items

    static Map<String, Customer> customers = new HashMap<>(); // Customer data
    static List<FoodItem> food = new ArrayList<>(); // Food menu
    static List<DrinkItem> drinks = new ArrayList<>(); // Beverage menu
    static List<Order> orders = new ArrayList<>();
    
    
    public void initialize_data() // Initialize data
    {
    	food.add(new FoodItem("Pizza", 100000, 100));
        food.add(new FoodItem("Burger", 50000, 50));
        food.add(new FoodItem("Nasi Goreng", 10000, 120));
        food.add(new FoodItem("Mie Ayam", 12000, 80));
        food.add(new FoodItem("Bakso Kepala Sapi", 10000, 120));
        customers.put("Rama", new Customer("Rama", 19, "rembo@gmail.com", 235000));
        customers.put("Kesia", new Customer("Kesia", 19, "keskes@gmail.com", 232000));
        customers.put("Richard", new Customer("Richard", 18, "ricatani@gmail.com", 450000));
        customers.put("Sindhu", new Customer("Sindhu", 20, "sindhuhindu@gmail.com", 1200000));
        customers.put("Kiki", new Customer("Kiki", 19, "kikidoyouloveme@gmail.com", 654322));
        customers.put("Hezki", new Customer("Hezki", 18, "hezkintamani@gmail.com", 23222));
        customers.put("Taqi", new Customer("Taqi", 25, "taqitampan@gmail.com", 2300));
        
        drinks.add(new DrinkItem("Coke", 12000, 200));
        drinks.add(new DrinkItem("Pepsi", 9000, 150));
        drinks.add(new DrinkItem("Mineral Water", 3000, 100));
        drinks.add(new DrinkItem("Es Teh", 5000, 60));
    }
    
    
    public FoodOrderingAppGUI() 
    {
        initialize_data(); 
        frame = new JFrame("Food Ordering App"); // Title of the GUI
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Set GUI to fullscreen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when GUI is closed
        frame.setLayout(new BorderLayout());   // Set layout of the GUI to BorderLayout

        JLabel welcomeLabel = new JLabel("Welcome to \"Edan Restaurant\"!", SwingConstants.CENTER); // Create a Welcoming label
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 72));  // Set the font size of the label
        frame.add(welcomeLabel, BorderLayout.CENTER);  // Add the label to the center of the GUI

        menuBar = new JMenuBar();  // Create a menu bar
        JPanel menuPanel = new JPanel(); // Create a panel to hold the menu bar 
        menuPanel.add(menuBar); // Add the menu bar to the panel
        frame.add(menuPanel, BorderLayout.NORTH);  // Add the panel to the top of the GUI

        frame.setJMenuBar(menuBar);  // Add the menu bar to the GUI
        frame.setVisible(true); // Show the GUI
        
        manageCustomerProfileMenu = new JMenu("Manage Customer Profile");

        addDataItem = new JMenuItem("Add Data");
        updateDataItem = new JMenuItem("Update Data");
        deleteDataItem = new JMenuItem("Delete Data");
        showAllDataItem = new JMenuItem("Show All Data");
        manageCustomerProfileMenu.add(addDataItem);
        manageCustomerProfileMenu.add(updateDataItem);
        manageCustomerProfileMenu.add(deleteDataItem);
        manageCustomerProfileMenu.add(showAllDataItem);

        showMenuItem = new JMenuItem("Show Menu");
        topUpItem = new JMenuItem("Top Up");

        orderMenu = new JMenu("Order");
        orderFoodItem = new JMenuItem("Order Food");
        orderDrinkItem = new JMenuItem("Order Beverage");
        showOrdersItem = new JMenuItem("Order History");
        orderMenu.add(orderFoodItem);
        orderMenu.add(orderDrinkItem);
        orderMenu.add(showOrdersItem);

        exitItem = new JMenuItem("Exit");

        menuBar.add(manageCustomerProfileMenu);
        menuBar.add(showMenuItem);
        menuBar.add(topUpItem);
        menuBar.add(orderMenu);
        
        menuBar.add(exitItem);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        
     
        addDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nameField = new JTextField(10);
                JTextField ageField = new JTextField(10);
                JTextField addressField = new JTextField(10);
                JTextField moneyField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Age:"));
                panel.add(ageField);
                panel.add(new JLabel("Address:"));
                panel.add(addressField);
                panel.add(new JLabel("Money:"));
                panel.add(moneyField);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Enter data",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    String ageStr = ageField.getText();
                    String address = addressField.getText();
                    String moneyStr = moneyField.getText();

                    if (name.isEmpty() || ageStr.isEmpty() || address.isEmpty() || moneyStr.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Program cannot handle Empty String!");
                        return;
                    }

                    if (!ageStr.matches("\\d+") || !moneyStr.matches("\\d+")) {
                        JOptionPane.showMessageDialog(frame, "Age or money cannot handle String!");
                        return;
                    }

                    int age = Integer.parseInt(ageStr);
                    int money = Integer.parseInt(moneyStr);

                    JOptionPane.showMessageDialog(frame, "Data Added!");
                    customers.put(name, new Customer(name, age, address, money));
                }
            }
        });
       
        updateDataItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldName = JOptionPane.showInputDialog(frame, "Enter current name:");
                if (oldName == null || oldName.isEmpty()) return; 
                if(!customers.containsKey(oldName))
                {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                    return;
                }

                JTextField nameField = new JTextField();
                JTextField ageField = new JTextField();
                JTextField addressField = new JTextField();
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("New Name:"));
                panel.add(nameField);
                panel.add(new JLabel("New Age:"));
                panel.add(ageField);
                panel.add(new JLabel("New Address:"));
                panel.add(addressField);
                int result = JOptionPane.showConfirmDialog(frame, panel, "Update", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String newName = nameField.getText().isEmpty() ? oldName : nameField.getText();
                    String newAgeStr = ageField.getText();
                    String newAddress = addressField.getText().isEmpty() ? customers.get(oldName).getAddress() : addressField.getText();

                    if (!newAgeStr.isEmpty() && !newAgeStr.matches("\\d+")) {
                        JOptionPane.showMessageDialog(frame, "Age cannot handle String!");
                        return;
                    }

                    Integer newAge = newAgeStr.isEmpty() ? customers.get(oldName).getAge() : Integer.parseInt(newAgeStr);

                    Customer customer = customers.get(oldName);
                    customers.remove(oldName); 
                    customer.updateProfile(newName, newAge, newAddress);
                    customers.put(newName, customer); 

                    JOptionPane.showMessageDialog(frame, "Data Updated!");
                }
            }
        });

        
        deleteDataItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter name:");
                if (customers.containsKey(name)) {
                    customers.remove(name);
                    JOptionPane.showMessageDialog(frame, "Data Deleted!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                }
            }
        });
        
        showAllDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columnNames = {"Name", "Age", "Address", "Money"};
                Object[][] data = new Object[customers.size()][4];
                int i = 0;
                for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                    Customer customer = entry.getValue();
                    data[i][0] = customer.getName();
                    data[i][1] = customer.getAge();
                    data[i][2] = customer.getAddress();
                    data[i][3] = String.format("Rp. %,d.00", customer.getMoney());
                    i++;
                }
                JTable table = new JTable(data, columnNames);
                table.setFont(new Font("Serif", Font.PLAIN, 20)); // Set the font size of the data in the table
                table.setRowHeight(30); // Set the row height to accommodate the larger font
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createTitledBorder("Data"));
                JOptionPane optionPane = new JOptionPane(scrollPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                JDialog dialog = optionPane.createDialog("Data");
                dialog.setSize(800, 600); // Set the size of the frame
                dialog.setVisible(true);
            }
        });
        
        
        
        frame.setLayout(new GridBagLayout()); 

        showMenuItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String[] columnNames = {"Item", "Price", "Quantity"};

                // Create data for food table
                Object[][] foodData = new Object[food.size()][3];
                for (int i = 0; i < food.size(); i++) 
                {
                    foodData[i][0] = food.get(i).name;
                    foodData[i][1] = food.get(i).price;
                    foodData[i][2] = food.get(i).quantity;
                }

                // Create data for drinks table
                Object[][] drinksData = new Object[drinks.size()][3];
                for (int i = 0; i < drinks.size(); i++) 
                {
                    drinksData[i][0] = drinks.get(i).name;
                    drinksData[i][1] = drinks.get(i).price;
                    drinksData[i][2] = drinks.get(i).quantity;
                }

                // Create food table and its scroll pane
                // Create food table and its scroll pane
                JTable foodTable = new JTable(foodData, columnNames);
                JScrollPane foodScrollPane = new JScrollPane(foodTable);
                foodScrollPane.setBorder(BorderFactory.createTitledBorder("Food")); // Add title to food table

                // Create drinks table and its scroll pane
                JTable drinksTable = new JTable(drinksData, columnNames);
                JScrollPane drinksScrollPane = new JScrollPane(drinksTable);
                drinksScrollPane.setBorder(BorderFactory.createTitledBorder("Beverage")); // Add title to drinks table

                // Create a panel to hold both tables
                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(foodScrollPane);
                panel.add(drinksScrollPane);

                // Show the panel in a dialog
                JOptionPane.showMessageDialog(frame, panel);
            }
        });
        
        
        
        topUpItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter customer name:");
                
                if(!customers.containsKey(name))
                {
                	JOptionPane.showMessageDialog(frame, "Customer not found.");
                	return;
                }
                
                int amount = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Top-up amount:"));
                if (customers.containsKey(name)) {
                    Customer customer = customers.get(name);
                    int oldBalance = customer.getMoney();
                    customer.topUp(amount);
                    int newBalance = customer.getMoney();
                    JOptionPane.showMessageDialog(frame, "Top-up successful. Old balance: " + oldBalance + ", New balance: " + newBalance);
                } else {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                }
            }
        });
        
        
        orderFoodItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter customer name:");
                
                if(!customers.containsKey(name))
                {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                    return;
                }
                
                // Generate food menu string
                StringBuilder foodMenu = new StringBuilder("Enter item number:\n");
                for (int i = 0; i < food.size(); i++) {
                    foodMenu.append((i + 1) + ". " + food.get(i).name + " - Rp " + food.get(i).price + ",00\n");
                }

                try {
                    int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, foodMenu.toString())) - 1;
                    if (itemNumber >= 0 && itemNumber < food.size()) {
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter quantity:"));
                        if (food.get(itemNumber).quantity < quantity) {
                            JOptionPane.showMessageDialog(frame, "Insufficient stock.");
                            return;
                        }
                        int totalCost = food.get(itemNumber).price * quantity;
                        
                        
                        if (customers.get(name).makePayment(totalCost)) {
                            food.get(itemNumber).quantity -= quantity;
                            JOptionPane.showMessageDialog(frame, "Order successful!");

                            // Create an Order object and add it to the list
                            Order order = new Order(orders.size() + 1, name, food.get(itemNumber).name, quantity, totalCost);
                            orders.add(order);
                        }
                        
                        
                        else {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid item number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number!");
                }
                
                
                
                
            }
        });

        orderDrinkItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter customer name:");
                
                if(!customers.containsKey(name))
                {
                    JOptionPane.showMessageDialog(frame, "Customer not found.");
                    return;
                }
                
                // Generate drinks menu string
                StringBuilder drinksMenu = new StringBuilder("Enter item number:\n");
                for (int i = 0; i < drinks.size(); i++) {
                    drinksMenu.append((i + 1) + ". " + drinks.get(i).name + " - Rp. " + drinks.get(i).price + ",00\n");
                }

                try {
                    int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, drinksMenu.toString())) - 1;
                    if (itemNumber >= 0 && itemNumber < drinks.size()) {
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter quantity:"));
                        if (drinks.get(itemNumber).quantity < quantity) {
                            JOptionPane.showMessageDialog(frame, "Insufficient stock.");
                            return;
                        }
                        int totalCost = drinks.get(itemNumber).price * quantity;
                       
                        
                        
                        if (customers.get(name).makePayment(totalCost)) {
                            food.get(itemNumber).quantity -= quantity;
                            JOptionPane.showMessageDialog(frame, "Order successful!");

                            // Create an Order object and add it to the list
                            Order order = new Order(orders.size() + 1, name, drinks.get(itemNumber).name, quantity, totalCost);
                            orders.add(order);
                        }
                        
                        
                        else {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid item number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number!");
                }
            }
        });
        
        showOrdersItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orders.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No Order History");
                } else {
                    String[] columnNames = {"ID", "Customer", "Menu", "Quantity", "Total Price"};
                    Object[][] data = new Object[orders.size()][5];
                    for (int i = 0; i < orders.size(); i++) {
                        Order order = orders.get(i);
                        data[i][0] = order.getId();
                        data[i][1] = order.getCustomerName();
                        data[i][2] = order.getMenuName();
                        data[i][3] = order.getQuantity();
                        data[i][4] = order.getTotalPrice();
                    }
                    JTable table = new JTable(data, columnNames);
                    JOptionPane.showMessageDialog(frame, new JScrollPane(table));
                }
            }
        });
        
        
        exitItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    }
}