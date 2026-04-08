/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public abstract class ToppingDecorator extends Tea {
    protected Tea wrappedTea;

    public ToppingDecorator(Tea tea) {
        super(tea.getDescription(), tea.cost());
        this.wrappedTea = tea;
    }

    @Override
    public abstract double cost();

    @Override
    public abstract String getDescription();

    @Override
    public abstract void deductInventory();
}