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

public class Bill {

    private String billId;

    private List<BillItem> items;

    private int total;

    public Bill(String billId) {

        this.billId = billId;

        items = new ArrayList<>();
    }

    public void addItem(BillItem item) {

        items.add(item);

        total += item.getTotal();
    }

    public String getBillId() {
        return billId;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }
}
