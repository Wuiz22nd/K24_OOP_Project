/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public class MilkTea extends Tea {

    public MilkTea(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double cost() {
        return basePrice;
    }

    @Override
    public void deductInventory() {
        Inventory.getInstance().checkAndDeduct("tea", 1);
        Inventory.getInstance().checkAndDeduct("milk", 1);
    }
}