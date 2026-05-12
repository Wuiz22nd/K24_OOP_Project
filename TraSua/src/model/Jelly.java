/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class Jelly extends ToppingDecorator {

    public Jelly(Tea tea) {
        super(tea, "Thạch", 8000);
    }

    @Override
    protected String[] getOwnIngredientNames() {
        return new String[]{"thach"};
    }

    @Override
    protected int[] getOwnIngredientQuantities() {
        return new int[]{40};
    }
}