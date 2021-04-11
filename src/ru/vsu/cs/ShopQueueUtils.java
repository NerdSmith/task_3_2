package ru.vsu.cs;

import java.util.Scanner;

public class ShopQueueUtils {
    public static LinkedListShopQueue toCustomersQueue(Scanner scanner) {
        LinkedListShopQueue shopQueue = new LinkedListShopQueue();
        while (scanner.hasNext()) {
            String[] splittedLine = scanner.nextLine().split(" ");
            shopQueue.add(new Customer(
                    Integer.parseInt(splittedLine[0]),
                    Integer.parseInt(splittedLine[1]),
                    Integer.parseInt(splittedLine[2])
            ));
        }
        return shopQueue;
    }
}
