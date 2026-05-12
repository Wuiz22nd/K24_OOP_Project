/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
import java.util.ArrayList;
import java.util.List;

public class BillStorage {

    private static List<Bill> bills = new ArrayList<>();

    public static void saveBill(Bill bill) {

        bills.add(bill);
    }

    public static List<Bill> getBills() {

        return bills;
    }
}
