/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Minhphat
 */
import model.*;
import repository.InventoryRepository;

public class OrderService {
    private final InventoryRepository inventoryRepo = new InventoryRepository();
    private Order currentOrder = new Order();

        public void processOrder(Tea tea)
        throws InsufficientStockException {

    if (tea == null) return;

    String[] names =
            tea.getIngredientNames();

    int[] quantities =
            tea.getIngredientQuantities();

    // CHECK STOCK
    for (int i = 0; i < names.length; i++) {

        if (!inventoryRepo.hasEnough(
                names[i],
                quantities[i]
        )) {

            throw new InsufficientStockException(
                    "Không đủ nguyên liệu: "
                            + names[i]
            );
        }
    }

    // DEDUCT
    for (int i = 0; i < names.length; i++) {

        inventoryRepo.deduct(
                names[i],
                quantities[i]
        );
    }

    currentOrder.addDrink(tea);

    System.out.println(
            "Da luu vao hoa don."
    );
}

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void showStock() {
        inventoryRepo.print();
    }

    public void resetInventory() {
        inventoryRepo.reset();
    }
}