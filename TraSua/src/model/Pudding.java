/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public class Pudding extends ToppingDecorator {

    public Pudding(Tea tea) {
        super(tea);
    }

    @Override
    public double cost() {
        return wrappedTea.cost() + 7000;
    }

    @Override
    public String getDescription() {
        return wrappedTea.getDescription() + ", Pudding";
    }

    @Override
    public void deductInventory() {
        wrappedTea.deductInventory();
        Inventory.getInstance().checkAndDeduct("pudding", 1);
    }
}
