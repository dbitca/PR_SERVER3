package com.example.KitchenService.models;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<Integer> items;
    private int priority;
    private int max_wait;

    public Order(int id, ArrayList<Integer> items, int priority, int max_wait) {
        this.id = id;
        this.items = items;
        this.priority = priority;
        this.max_wait = max_wait;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(int max_wait) {
        this.max_wait = max_wait;
    }


}
