package ru.vsu.cs;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        LinkedListShopQueue shopQueue = new LinkedListShopQueue();
        shopQueue.add(new Customer(0, 3, 4));
        shopQueue.add(new Customer(2, 3, 3));
        shopQueue.add(new Customer(1, 1, 4));
        shopQueue.add(new Customer(0, 1, 8));
        shopQueue.add(new Customer(2, 2, 4));

        shopQueue.add(new Customer(24, 2, 2));

        shopQueue.add(new Customer(26, 2, 3));

        HashMap<Customer, Integer> customerIntegerHashMap =  shopQueue.getCustomersExitTime();
        for (Customer customer: customerIntegerHashMap.keySet()) {
            System.out.print(customer);
            System.out.print("\t");
            System.out.println("Exit time: " + customerIntegerHashMap.get(customer));
        }

    }
}
