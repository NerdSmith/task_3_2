package ru.vsu.cs;

import java.util.HashMap;
import java.util.Iterator;

public class LinkedListShopQueue implements Iterable<Customer>{

    private Customer head = null;
    private final CustomerComparator<Customer> customerComparator = new CustomerComparator<>();

    private int count = 0;


    public void add(Customer customer) {
        Customer currCustomer = head;
        if (count > 0) {
            int key = customerComparator.compare(customer, currCustomer);
            while (key > 0) {
                if (currCustomer.next == null) {
                    customer.prev = currCustomer;
                    currCustomer.next = customer;
                    count++;
                    return;
                }
                currCustomer = currCustomer.next;
                key = customerComparator.compare(customer, currCustomer);
            }
            Customer prevCustomer = currCustomer.prev;
            if (prevCustomer == null) {
                currCustomer.prev = customer;
                customer.next = currCustomer;
                head = customer;
            }
            else {
                prevCustomer.next = customer;
                customer.prev = prevCustomer;

                customer.next = currCustomer;
                currCustomer.prev = customer;
            }
        }
        else {
            head = customer;
        }
        count++;
    }

    public Customer remove(){
        Customer customer = head;
        head = customer.next;
        head.prev = null;
        count--;
        return customer;
    }

    public Customer element() {
        return head;
    }

    public HashMap<Customer, Integer> getCustomersExitTime() {
        HashMap<Customer, Integer> customersExitTime = new HashMap<>();
        Customer currCustomer = head;
        int time = 0;

        while (currCustomer != null) {
            int checkoutArrival = currCustomer.arrival + currCustomer.choosing;
            if (time < checkoutArrival) {
                time = checkoutArrival;
                time += currCustomer.numberOfGoods;
                customersExitTime.put(currCustomer, time);
            } else {
                time += currCustomer.numberOfGoods;
                customersExitTime.put(currCustomer, time);
            }
            currCustomer = currCustomer.next;
        }
        return customersExitTime;
    }

    @Override
    public Iterator<Customer> iterator() {
        class LinkedListIterator implements Iterator<Customer> {
            Customer customer;

            public LinkedListIterator(Customer head) {
                customer = head;
            }

            @Override
            public boolean hasNext() {
                return customer != null;
            }

            @Override
            public Customer next() {
                Customer result = customer;
                customer = customer.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }
}
