/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public class Pearl extends ToppingDecorator {

    public Pearl(Tea tea) {
        super(tea);
    }

    @Override
    public double cost() {
        return wrappedTea.cost() + 5000;
    }

    @Override
    public String getDescription() {
        return wrappedTea.getDescription() + ", Pearl";
    }

    @Override
    public void deductInventory() {
        wrappedTea.deductInventory();
        Inventory.getInstance().checkAndDeduct("pearl", 1);
    }
}
