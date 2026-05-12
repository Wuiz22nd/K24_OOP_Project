/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
import java.util.HashMap;
import java.util.Map;

public class IngredientStock {

    private static Map<String, Integer> stock = new HashMap<>();

    static {

        stock.put("tra", 10000);
        stock.put("sua", 10000);
        stock.put("duong", 10000);
        stock.put("da", 10000);

        stock.put("tran chau", 5000);
        stock.put("thach", 5000);
        stock.put("pudding", 5000);
        stock.put("cheese", 5000);
    }

    public static void useIngredient(String name, int amount) {

        int current = stock.getOrDefault(name, 0);

        stock.put(name, current - amount);
    }

    public static int getStock(String name) {

        return stock.getOrDefault(name, 0);
    }

    public static Map<String, Integer> getAllStock() {
        return stock;
    }
}
