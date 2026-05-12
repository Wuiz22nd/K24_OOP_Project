/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class OlongTea extends Tea {

    public OlongTea() {
        super("Tra Olong", 32000);
    }

    @Override
    public String[] getIngredientNames() {
        return new String[]{"tra", "sua", "duong", "da"};
    }

    @Override
    public int[] getIngredientQuantities() {
        return new int[]{200, 150, 70, 100};
    }
}
