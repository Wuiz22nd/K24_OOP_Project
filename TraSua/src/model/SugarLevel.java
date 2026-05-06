/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class SugarLevel  {
    private final String label;
    private final double ingredientMultiplier;

    public static final SugarLevel ZERO    = new SugarLevel("0%", 0.0);
    public static final SugarLevel THIRTY  = new SugarLevel("30%", 0.3);
    public static final SugarLevel FIFTY   = new SugarLevel("50%", 0.5);
    public static final SugarLevel SEVENTY = new SugarLevel("70%", 0.7);
    public static final SugarLevel FULL    = new SugarLevel("100%", 1.0);

    private SugarLevel(String label, double multiplier) {
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
