/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trasua;

import model.MilkTea;
import model.Order;
import model.Pearl;
import model.Pudding;
import model.Tea;

/**
 *
 * @author wuiz
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tea tea1 = new MilkTea("Milk Tea", 20000);
        tea1 = new Pearl(tea1);
        tea1 = new Pudding(tea1);

        Tea tea2 = new MilkTea("Black Milk Tea", 25000);
        tea2 = new Pearl(tea2);

        Order order = new Order();
        order.addDrink(tea1);
        order.addDrink(tea2);

        // Trừ kho
        tea1.deductInventory();
        tea2.deductInventory();

        // In hóa đơn
        order.printInvoice();

        // Lưu kho
        Inventory.getInstance().saveToFile();
    }
    
}
