/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public abstract class Tea implements Billable {

    private String name;
    private double basePrice;

    private Size size = Size.MEDIUM;
    private SugarLevel sugarLevel = SugarLevel.FULL;
    private IceLevel iceLevel = IceLevel.NORMAL;

    protected Tea(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public void setSize(Size size) { this.size = size; }
    public void setSugarLevel(SugarLevel sugarLevel) { this.sugarLevel = sugarLevel; }
    public void setIceLevel(IceLevel iceLevel) { this.iceLevel = iceLevel; }

    @Override
    public double getCost() {
        return basePrice * size.getPriceMultiplier();
    }

    @Override
    public String getDescription() {
        return name + " (" + size + ", " + sugarLevel + ", " + iceLevel + ")";
    }

    public abstract String[] getIngredientNames();
    public abstract int[] getIngredientQuantities();

    public int[] getIngredientQuantitiesWithSize() {

        String[] names = getIngredientNames();
        int[] base = getIngredientQuantities();
        int[] result = new int[base.length];

        double sizeFactor = size.getIngredientMultiplier();

        for (int i = 0; i < base.length; i++) {

            double factor = sizeFactor;

            if (names[i].equals("duong")) {
                factor *= sugarLevel.getIngredientMultiplier();
            }

            if (names[i].equals("da")) {
                factor *= iceLevel.getIngredientMultiplier();
            }

            result[i] = (int)(base[i] * factor);
        }

        return result;
    }
}
