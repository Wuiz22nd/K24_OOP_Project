/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import model.*;
import service.OrderService;
import service.InsufficientStockException;
import java.util.Scanner;

public class MainView {
    private final OrderService orderService;
    private final Scanner sc;

    public MainView(OrderService orderService, Scanner sc) {
        this.orderService = orderService;
        this.sc = sc;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== BUBBLE TEA POS SYSTEM ===");
            System.out.println("1. Tao do uong moi");
            System.out.println("2. Xem hoa don hien tai");
            System.out.println("3. Thanh toan & In hoa don");
            System.out.println("4. Xem ton kho");
            System.out.println("5. Reset kho");
            System.out.println("6. Dang xuat");
            System.out.print("Chon chuc nang: ");
            int choice = inputInt();

            if (choice == 1) {
                createDrink();
            } else if (choice == 2) {
                orderService.getCurrentOrder().printInvoice();
            } else if (choice == 3) {
                checkout();
            } else if (choice == 4) {
                orderService.showStock();
            } else if (choice == 5) {
                orderService.resetInventory();
                System.out.println("Da reset kho ve mac dinh.");
            } else if (choice == 6) {
                System.out.println("Da dang xuat.");
                return;
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private void createDrink() {
        System.out.println("\n=== TAO DO UONG MOI ===");

        Tea tea = null;
        boolean done = false;

        while (!done) {
            tea = chooseBaseTea();
            if (tea == null) return;                    // Quay lại menu chính

            if (!chooseSize(tea)) continue;             // Quay lại chọn loại trà

            if (!chooseSugarLevel(tea)) continue;       // Quay lại chọn size

            if (!chooseIceLevel(tea)) continue;         // Quay lại chọn đường

            tea = addToppings(tea);                     // Topping xong thì tạo luôn

            try {
                orderService.processOrder(tea);
                System.out.println("\n✅ Đã thêm thành công: " + tea.getDescription());
                done = true;
            } catch (InsufficientStockException e) {
                System.out.println("❌ Lỗi: " + e.getMessage());
                done = true; // Thoát khỏi tạo drink nếu hết hàng
            } catch (Exception e) {
                System.out.println("❌ Lỗi không xác định: " + e.getMessage());
                done = true;
            }
        }
    }

    // ==================== CÁC HÀM CHỌN TỪNG BƯỚC ====================

    private Tea chooseBaseTea() {
        while (true) {
            System.out.println("\n--- Chon loai tra sua ---");
            System.out.println("1. Tra sua truyen thong");
            System.out.println("2. Tra sua socola");
            System.out.println("3. Tra sua matcha");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");
            int loai = inputInt();

            if (loai == 0) return null;
            if (loai == 2) return new ChocolateTea();
            if (loai == 3) return new MatchaTea();
            return new MilkTea();           // mặc định là MilkTea
        }
    }

    private boolean chooseSize(Tea tea) {
        while (true) {
            System.out.println("\n--- Chon Size ---");
            System.out.println("1. S (Nho)");
            System.out.println("2. M (Vua)");
            System.out.println("3. L (Lon)");
            System.out.println("0. Quay lai chon loai tra");
            System.out.print("Chon: ");
            int choice = inputInt();

            if (choice == 0) return false;
            if (choice == 1) {
                tea.setSize(Size.SMALL);
            } else if (choice == 3) {
                tea.setSize(Size.LARGE);
            } else {
                tea.setSize(Size.MEDIUM);
            }
            return true;
        }
    }

    private boolean chooseSugarLevel(Tea tea) {
        while (true) {
            System.out.println("\n--- Chon muc duong ---");
            System.out.println("1. 0%");
            System.out.println("2. 30%");
            System.out.println("3. 50%");
            System.out.println("4. 70%");
            System.out.println("5. 100%");
            System.out.println("0. Quay lai chon size");
            System.out.print("Chon: ");
            int choice = inputInt();

            if (choice == 0) return false;
            
            if (choice == 1) {
                tea.setSugarLevel(SugarLevel.ZERO);
            } else if (choice == 2) {
                tea.setSugarLevel(SugarLevel.THIRTY);
            } else if (choice == 4) {
                tea.setSugarLevel(SugarLevel.SEVENTY);
            } else if (choice == 5) {
                tea.setSugarLevel(SugarLevel.FULL);
            } else {
                tea.setSugarLevel(SugarLevel.FIFTY);   // mặc định 50%
            }
            return true;
        }
    }

    private boolean chooseIceLevel(Tea tea) {
        while (true) {
            System.out.println("\n--- Chon muc da ---");
            System.out.println("1. Khong da");
            System.out.println("2. It da");
            System.out.println("3. Vua da");
            System.out.println("4. Nhieu da");
            System.out.println("0. Quay lai chon duong");
            System.out.print("Chon: ");
            int choice = inputInt();

            if (choice == 0) return false;
            
            if (choice == 1) {
                tea.setIceLevel(IceLevel.NONE);
            } else if (choice == 2) {
                tea.setIceLevel(IceLevel.LESS);
            } else if (choice == 4) {
                tea.setIceLevel(IceLevel.MORE);
            } else {
                tea.setIceLevel(IceLevel.NORMAL);   // mặc định Normal
            }
            return true;
        }
    }

    private Tea addToppings(Tea tea) {
        while (true) {
            System.out.println("\n--- Chon Topping ---");
            System.out.println("1. Tran chau");
            System.out.println("2. Kem cheese");
            System.out.println("3. Thach");
            System.out.println("0. Xong (Hoan thanh do uong)");
            System.out.print("Chon: ");
            int choice = inputInt();

            if (choice == 1) {
                tea = new Pearl(tea);
                System.out.println("→ Da them Tran chau");
            } else if (choice == 2) {
                tea = new Cheese(tea);
                System.out.println("→ Da them Kem cheese");
            } else if (choice == 3) {
                tea = new Pudding(tea);
                System.out.println("→ Da them Thach");
            } else if (choice == 0) {
                return tea;
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private void checkout() {
        Order order = orderService.getCurrentOrder();
        if (order.getDrinks().isEmpty()) {
            System.out.println("Chua co do uong nao trong hoa don!");
            return;
        }
        order.printInvoice();
        System.out.println("Thanh toan thanh cong!");
        order.clear();
    }

    private int inputInt() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }
}