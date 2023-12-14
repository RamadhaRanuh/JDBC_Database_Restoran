package restaurant_AOL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class FoodOrderingAppGUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu manageCustomerProfileMenu, orderMenu;
    private JMenuItem addDataItem, updateDataItem, deleteDataItem, showAllDataItem, showMenuItem, topUpItem, orderFoodItem, orderDrinkItem, exitItem;

    static Map<String, Customer> customers = new HashMap<>();
    static List<FoodItem> food = new ArrayList<>();
    static List<DrinkItem> drinks = new ArrayList<>();
    
    public void initialize_data()
    {
    	food.add(new FoodItem("Pizza", 100000, 100));
        food.add(new FoodItem("Burger", 50000, 50));
        food.add(new FoodItem("Nasi Goreng", 10000, 120));
        food.add(new FoodItem("Mie Ayam", 12000, 80));
        food.add(new FoodItem("Bakso Kepala Sapi", 10000, 120));
        customers.put("Rama", new Customer("Rama", 19, "rembo@gmail.com", 235000));
        customers.put("Kesia", new Customer("Kesia", 19, "keskes@gmail.com", 232000));
        customers.put("Richard", new Customer("Richard", 18, "ricatani@gmail.com", 450000));
        customers.put("Sindhu", new Customer("Kiki", 20, "sindhuhindu@gmail.com", 1200000));
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
        frame = new JFrame("Food Ordering App");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());  

        JLabel welcomeLabel = new JLabel("Welcome to \"Edan Restaurant\"!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 24)); 
        frame.add(welcomeLabel, BorderLayout.CENTER);  

        menuBar = new JMenuBar();  
        JPanel menuPanel = new JPanel();
        menuPanel.add(menuBar);
        frame.add(menuPanel, BorderLayout.SOUTH);  

        frame.setJMenuBar(menuBar);  
        frame.setVisible(true);
        

        menuBar = new JMenuBar();

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
        orderDrinkItem = new JMenuItem("Order Drink");
        orderMenu.add(orderFoodItem);
        orderMenu.add(orderDrinkItem);

        exitItem = new JMenuItem("Exit");

        menuBar.add(manageCustomerProfileMenu);
        menuBar.add(showMenuItem);
        menuBar.add(topUpItem);
        menuBar.add(orderMenu);
        menuBar.add(exitItem);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        
     
        addDataItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter name:");
                if (name == null) return; // User pressed "Cancel"

                String ageStr = JOptionPane.showInputDialog(frame, "Enter age:");
                if (ageStr == null) return; 
                int age = Integer.parseInt(ageStr);

                String address = JOptionPane.showInputDialog(frame, "Enter address:");
                if (address == null) return; 

                String moneyStr = JOptionPane.showInputDialog(frame, "Enter money:");
                if (moneyStr == null) return; 
                int money = Integer.parseInt(moneyStr);

                customers.put(name, new Customer(name, age, address, money));
            }
        });

       
        updateDataItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldName = JOptionPane.showInputDialog(frame, "Enter current name:");
                if (oldName == null) return; 
                if(!customers.containsKey(oldName))
                {
                	JOptionPane.showMessageDialog(frame, "Customer not found.");
                	return;
                }
                String newName = JOptionPane.showInputDialog(frame, "Enter new name:");
                if (newName == null) return; 
                String ageStr = JOptionPane.showInputDialog(frame, "Enter new age:");
                if (ageStr == null) return; 
                int newAge = Integer.parseInt(ageStr);
                String newAddress = JOptionPane.showInputDialog(frame, "Enter new address:");
                if (newAddress == null) return; 
                
                Customer customer = customers.get(oldName);
                customers.remove(oldName); 
                customer.updateProfile(newName, newAge, newAddress);
                customers.put(newName, customer); 
                
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
                    data[i][3] = customer.getMoney();
                    i++;
                }
                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createTitledBorder("Data"));
                JOptionPane.showMessageDialog(frame, scrollPane);
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
                drinksScrollPane.setBorder(BorderFactory.createTitledBorder("Drinks")); // Add title to drinks table

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
                    foodMenu.append((i + 1) + ". " + food.get(i).name + " - " + food.get(i).price + "\n");
                }

                try {
                    int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, foodMenu.toString())) - 1;
                    if (itemNumber >= 0 && itemNumber < food.size()) {
                        if (customers.get(name).makePayment(food.get(itemNumber).price)) {
                            food.get(itemNumber).quantity--;
                            JOptionPane.showMessageDialog(frame, "Order successful!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid item number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid item. Order with number!");
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
                    drinksMenu.append((i + 1) + ". " + drinks.get(i).name + " - " + drinks.get(i).price + "\n");
                }

                try
                {
                	 int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, drinksMenu.toString())) - 1;
                     if (itemNumber >= 0 && itemNumber < drinks.size()) {
                         if (customers.get(name).makePayment(drinks.get(itemNumber).price)) {
                             drinks.get(itemNumber).quantity--;
                             JOptionPane.showMessageDialog(frame, "Order successful!");
                         } else {
                             JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                         }
                     } else {
                         JOptionPane.showMessageDialog(frame, "Invalid item number.");
                     }
                }
                
                catch (NumberFormatException ex)
                {
                	JOptionPane.showMessageDialog(frame, "Invalid item. Order with number!");
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