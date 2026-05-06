/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class MatchaTea extends Tea {

    public MatchaTea() {
        super("Tra sua matcha", 35000);
    }

    @Override
    public String[] getIngredientNames() {
        return new String[]{"tra", "sua", "duong", "da"};
    }

    @Override
    public int[] getIngredientQuantities() {
        return new int[]{200, 180, 60, 100};
    }
}
