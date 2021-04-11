package ru.vsu.cs;

import java.util.Comparator;

public class CustomerComparator<T> implements Comparator<Customer> {
    @Override
    public int compare(Customer customer1, Customer customer2) {
        return (customer1.arrival + customer1.choosing) - (customer2.arrival + customer2.choosing);
    }
}
