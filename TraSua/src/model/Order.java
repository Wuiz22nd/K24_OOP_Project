/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
import java.util.*;

public class Order {
    private List<Tea> items = new ArrayList<>();

    public void addDrink(Tea tea) {
        items.add(tea);
    }

    public double calculateTotal() {
        double total = 0;
        for (Tea t : items) {
            total += t.cost();
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("===== HOA DON =====");
        for (Tea t : items) {
            System.out.println(t.getDescription() + " - " + t.cost());
        }
        System.out.println("Tong: " + calculateTotal());
    }
}
