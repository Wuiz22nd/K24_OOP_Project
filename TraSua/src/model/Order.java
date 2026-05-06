/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Tea> drinks = new ArrayList<>();

    public void addDrink(Tea drink) {
        drinks.add(drink);
    }

    public double calculateTotal() {
        double total = 0;
        for (Tea drink : drinks) {
            total += drink.getCost();
        }
        return total;
    }

    public List<Tea> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public void clear() {
        drinks.clear();
    }

    public void printInvoice() {
        System.out.println("\n=== HOA DON BUBBLE TEA ===");
        for (Tea drink : drinks) {
            System.out.printf("%-40s : %, .0f VND%n", drink.getDescription(), drink.getCost());
        }
        System.out.printf("TONG CONG: %, .0f VND%n", calculateTotal());
        System.out.println("============================");
    }
}
