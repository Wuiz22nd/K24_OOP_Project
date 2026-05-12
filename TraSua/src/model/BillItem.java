/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class BillItem {

    private String name;

    private int quantity;

    private int price;

    public BillItem(String name, int quantity, int price) {

        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getTotal() {
        return quantity * price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}