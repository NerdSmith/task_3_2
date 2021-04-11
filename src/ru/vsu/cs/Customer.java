package ru.vsu.cs;

public class Customer {
    public final int arrival;
    public final int choosing;
    public final int numberOfGoods;
    public Customer prev;
    public Customer next;

    public Customer(int arrival, int choosing, int numberOfGoods) {
        this.arrival = arrival;
        this.choosing = choosing;
        this.numberOfGoods = numberOfGoods;
    }

    @Override
    public String toString() {
        return String.format("Customer(arrival:%d, choosing:%d, numberOfGoods:%d)", arrival, choosing, numberOfGoods);
    }
}
