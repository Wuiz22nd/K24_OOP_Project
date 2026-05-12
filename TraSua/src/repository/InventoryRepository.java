/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Minhphat
 */
import java.util.HashMap;
import java.util.Map;

public class InventoryRepository {
    
    public static void useIngredient(String name, int amount) {

    if (stock.containsKey(name)) {

        int current = stock.get(name);

        stock.put(name, current - amount);
    }
}

    // =====================================================
    // STATIC INVENTORY
    // =====================================================
    // Dùng static để mọi panel / class dùng chung 1 kho

    private static final Map<String, Integer> stock =
            new HashMap<>();

    // =====================================================
    // DEFAULT STOCK
    // =====================================================
    static {

        stock.put("tra", 5000);

        stock.put("sua", 5000);

        stock.put("duong", 3000);

        stock.put("da", 5000);

        stock.put("tran chau", 2000);

        stock.put("thach", 2000);

        stock.put("pudding", 1500);

        stock.put("cheese", 1500);
    }

    // =====================================================
    // GET STOCK
    // =====================================================
    public Map<String, Integer> getStock() {

        return stock;
    }

    // =====================================================
    // CHECK ENOUGH INGREDIENT
    // =====================================================
    public boolean hasEnough(
            String ingredient,
            int quantity
    ) {

        ingredient = ingredient.toLowerCase();

        return stock.getOrDefault(
                ingredient,
                0
        ) >= quantity;
    }

    // =====================================================
    // DEDUCT INVENTORY
    // =====================================================
    public void deduct(
            String ingredient,
            int quantity
    ) {

        ingredient = ingredient.toLowerCase();

        int current =
                stock.getOrDefault(
                        ingredient,
                        0
                );

        current -= quantity;

        // KHÔNG CHO ÂM
        if (current < 0) {
            current = 0;
        }

        stock.put(ingredient, current);
    }

    // =====================================================
    // ADD INVENTORY
    // =====================================================
    public void addStock(
            String ingredient,
            int quantity
    ) {

        ingredient = ingredient.toLowerCase();

        int current =
                stock.getOrDefault(
                        ingredient,
                        0
                );

        current += quantity;

        stock.put(ingredient, current);
    }

    // =====================================================
    // RESET INVENTORY
    // =====================================================
    public void reset() {

        stock.clear();

        stock.put("tra", 5000);

        stock.put("sua", 5000);

        stock.put("duong", 3000);

        stock.put("da", 5000);

        stock.put("tran chau", 2000);

        stock.put("thach", 2000);

        stock.put("pudding", 1500);

        stock.put("cheese", 1500);
    }

    // =====================================================
    // PRINT CONSOLE
    // =====================================================
    public void print() {

        System.out.println(
                "\n=== KHO NGUYÊN LIỆU ==="
        );

        for (String name : stock.keySet()) {

            System.out.println(
                    name.toUpperCase()
                    + " : "
                    + stock.get(name)
            );
        }
    }

    // =====================================================
    // GET SINGLE INGREDIENT
    // =====================================================
    public int getQuantity(String ingredient) {

        ingredient = ingredient.toLowerCase();

        return stock.getOrDefault(
                ingredient,
                0
        );
    }
}