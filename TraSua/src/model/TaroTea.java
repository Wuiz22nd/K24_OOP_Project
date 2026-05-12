/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Minhphat
 */
public class TaroTea extends Tea {

    public TaroTea() {
        super("Tra Khoai Mon", 36000);
    }

    @Override
    public String[] getIngredientNames() {
        return new String[]{"bot_khoai_mon", "sua", "duong", "da"};
    }

    @Override
    public int[] getIngredientQuantities() {
        return new int[]{80, 150, 70, 100};
    }
}
