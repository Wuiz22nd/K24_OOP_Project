/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class StrawberryTea extends Tea {

    public StrawberryTea() {
        super("Tra Dau", 34000);
    }

    @Override
    public String[] getIngredientNames() {
        return new String[]{"tra", "siro_dau", "sua", "duong", "da"};
    }

    @Override
    public int[] getIngredientQuantities() {
        return new int[]{180, 60, 120, 70, 100};
    }
}