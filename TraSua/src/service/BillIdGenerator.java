/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Minhphat
 */
public class BillIdGenerator {
    private static int current = 1;

    public static String generateId() {

        String id = String.format("%06d", current);

        current++;

        return id;
    }
}