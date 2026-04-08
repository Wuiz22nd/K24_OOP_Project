/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public abstract class Tea {
    protected String name;
    protected double basePrice;

    public Tea(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public abstract double cost();

    public String getDescription() {
        return name;
    }

    public abstract void deductInventory();
}
