/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class IceLevel {
    private final String label;
    private final double ingredientMultiplier;

    public static final IceLevel NONE   = new IceLevel("Khong da", 0.0);
    public static final IceLevel LESS   = new IceLevel("It da", 0.8);
    public static final IceLevel NORMAL = new IceLevel("Vua da", 1.0);
    public static final IceLevel MORE   = new IceLevel("Nhieu da", 1.2);

    private IceLevel(String label, double multiplier) {
        this.label = label;
        this.ingredientMultiplier = multiplier;
    }

    public double getIngredientMultiplier() {
        return ingredientMultiplier;
    }

    @Override
    public String toString() {
        return label;
    }
}
