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
    private Tea wrappedTea;
    private String toppingName;
    private double extraPrice;

    protected ToppingDecorator(Tea tea, String toppingName, double extraPrice) {
        super("", 0);
        this.wrappedTea = tea;
        this.toppingName = toppingName;
        this.extraPrice = extraPrice;
    }

    @Override
    public double getCost() {
        return wrappedTea.getCost() + extraPrice;
    }

    @Override
    public String getDescription() {
        return wrappedTea.getDescription() + " + " + toppingName;
    }

    protected abstract String[] getOwnIngredientNames();
    protected abstract int[] getOwnIngredientQuantities();

    @Override
    public String[] getIngredientNames() {
        String[] base = wrappedTea.getIngredientNames();
        String[] own = getOwnIngredientNames();
        String[] result = new String[base.length + own.length];
        System.arraycopy(base, 0, result, 0, base.length);
        System.arraycopy(own, 0, result, base.length, own.length);
        return result;
    }

    @Override
    public int[] getIngredientQuantities() {
        int[] base = wrappedTea.getIngredientQuantitiesWithSize();  // Quan trọng
        int[] own = getOwnIngredientQuantities();
        int[] result = new int[base.length + own.length];
        System.arraycopy(base, 0, result, 0, base.length);
        System.arraycopy(own, 0, result, base.length, own.length);
        return result;
    }
}
