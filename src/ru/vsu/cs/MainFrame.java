package ru.vsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MainFrame extends JFrame{
    private JPanel panelMain;
    private JList defaultCustomersList;
    private JList customersExitTimeList;
    private JTextField arrivalTextField;
    private JTextField choosingTextField;
    private JTextField numberOfGoodsTextField;
    private JButton addNewBtn;
    private JButton loadFromFileBtn;
    private JButton clearBtn;
    private LinkedListShopQueue shopQueue = new LinkedListShopQueue();

    public MainFrame() {
        super("Application");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 900, 500);
        this.setMinimumSize(new Dimension(800, 400));


        addNewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int arrival = Integer.parseInt(arrivalTextField.getText());
                int choosing = Integer.parseInt(choosingTextField.getText());
                int numberOdGoods = Integer.parseInt(numberOfGoodsTextField.getText());
                Customer newCustomer = new Customer(arrival, choosing, numberOdGoods);
                shopQueue.add(newCustomer);
                reload();
            }
        });


        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./files"));
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    Scanner scanner;
                    try {
                        scanner = new Scanner(new File(name));
                    } catch (FileNotFoundException fileNotFoundException) {
                        return;
                    }
                    shopQueue = ShopQueueUtils.toCustomersQueue(scanner);
                    reload();
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopQueue = new LinkedListShopQueue();
                reload();
            }
        });
    }



    private void reload() {
        DefaultListModel newDefaultCustomersListModel = new DefaultListModel();
        DefaultListModel newCustomersExitTimeList = new DefaultListModel();

        HashMap<Customer, Integer> customersExitTime =  shopQueue.getCustomersExitTime();

        for (Customer customer: shopQueue) {
            newDefaultCustomersListModel.addElement(customer.toString());

            String exitTimeStr = String.format(
                    "%s | Exit time: %d",
                    customer.toString(),
                    customersExitTime.get(customer)
            );
            newCustomersExitTimeList.addElement(exitTimeStr);
        }
        defaultCustomersList.setModel(newDefaultCustomersListModel);
        customersExitTimeList.setModel(newCustomersExitTimeList);
    }
}
