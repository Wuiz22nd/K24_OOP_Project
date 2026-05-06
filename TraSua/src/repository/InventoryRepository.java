/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Minhphat
 */
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryRepository {
    private static final String FILE = "inventory.txt";
    private Map<String, Integer> inventory = new LinkedHashMap<>();

    public InventoryRepository() {
        initDefault();
        load();
    }

    private void initDefault() {
        if (inventory.isEmpty()) {
            inventory.put("tra", 10000);
            inventory.put("sua", 8000);
            inventory.put("duong", 5000);
            inventory.put("da", 20000);
            inventory.put("tran_chau", 5000);
            inventory.put("kem_cheese", 3000);
            inventory.put("thach", 4000);
        }
    }

    private void load() {
        File file = new File(FILE);
        if (!file.exists()) {
            save();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int qty = Integer.parseInt(parts[1].trim());
                    inventory.put(name, qty);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                pw.println(entry.getKey() + "=" + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasEnough(String ingredient, int quantity) {
        return inventory.getOrDefault(ingredient, 0) >= quantity;
    }

    public void deduct(String ingredient, int quantity) {
        int current = inventory.getOrDefault(ingredient, 0);
        inventory.put(ingredient, Math.max(0, current - quantity));
        save();
    }

    public void print() {
        System.out.println("\n=== TON KHO NGUYEN LIEU ===");
        System.out.println("Nguyen lieu     : So luong");
        System.out.println("-----------------------------");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=============================");
    }

    public void reset() {
        inventory.clear();
        initDefault();
        save();
    }
}