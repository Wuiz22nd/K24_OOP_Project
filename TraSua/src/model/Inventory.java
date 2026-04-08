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
import java.io.*;

public class Inventory {
    private static Inventory instance;
    private Map<String, Integer> stock = new HashMap<>();
    private final String FILE_PATH = "inventory.txt";

    private Inventory() {
        loadFromFile();
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                stock.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (Exception e) {
            System.out.println("Khong doc duoc file!");
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String key : stock.keySet()) {
                bw.write(key + ":" + stock.get(key));
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file!");
        }
    }

    public boolean checkAndDeduct(String item, int amount) {
        int current = stock.getOrDefault(item, 0);
        if (current >= amount) {
            stock.put(item, current - amount);
            return true;
        } else {
            System.out.println("Khong du nguyen lieu: " + item);
            return false;
        }
    }
}
