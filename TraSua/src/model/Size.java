/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class Size {

    private final String code;
    private final double priceMultiplier;
    private final double ingredientMultiplier;

    public static final Size SMALL  = new Size("S", 0.8,  0.7);
    public static final Size MEDIUM = new Size("M", 1.0,  1.0);
    public static final Size LARGE  = new Size("L", 1.2,  1.3);

    private Size(String code, double priceMult, double ingMult) {
        this.code = code;
        this.priceMultiplier = priceMult;
        this.ingredientMultiplier = ingMult;
    }

    public String getCode() {
        return code;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public double getIngredientMultiplier() {
        return ingredientMultiplier;
    }

    @Override
    public String toString() {
        return code;       
    }
}