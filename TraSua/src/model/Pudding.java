/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class Pudding extends ToppingDecorator {

    public Pudding(Tea tea) {
        super(tea, "Pudding", 12000);
    }

    @Override
    protected String[] getOwnIngredientNames() {
        return new String[]{"pudding"};
    }

    @Override
    protected int[] getOwnIngredientQuantities() {
        return new int[]{40};
    }
}
