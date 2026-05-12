/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class MilkTea extends Tea {

    public MilkTea() {
        super("Tra sua truyen thong", 30000);
    }

    @Override
    public String[] getIngredientNames() {
        return new String[]{"tra", "sua", "duong", "da"};
    }

    @Override
    public int[] getIngredientQuantities() {

        String[] names =
                getIngredientNames();

        int[] base = new int[]{
                200,
                150,
                50,
                100
        };

        return scaleIngredients(
                names,
                base
        );
    }
}
