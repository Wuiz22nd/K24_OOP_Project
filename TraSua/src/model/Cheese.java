/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class Cheese extends ToppingDecorator {

    public Cheese(Tea tea) {
        super(tea, "Kem cheese", 15000);
    }

    @Override
    protected String[] getOwnIngredientNames() {
        return new String[]{"kem_cheese"};
    }

    @Override
    protected int[] getOwnIngredientQuantities() {
        return new int[]{30};
    }
}