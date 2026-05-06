/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wuiz
 */
public class Pearl extends ToppingDecorator {

    public Pearl(Tea tea) {
        super(tea, "Tran chau", 10000);
    }

    @Override
    protected String[] getOwnIngredientNames() {
        return new String[]{"tran_chau"};
    }

    @Override
    protected int[] getOwnIngredientQuantities() {
        return new int[]{50};
    }
}
