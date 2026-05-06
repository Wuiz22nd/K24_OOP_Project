/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trasua;

import service.AuthService;
import service.OrderService;
import view.AuthView;
import view.MainView;
import java.util.Scanner;

/**
 *
 * @author wuiz
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        AuthService authService = new AuthService();
        OrderService orderService = new OrderService();
        
        AuthView authView = new AuthView(authService, scanner);
        MainView mainView = new MainView(orderService, scanner);

        System.out.println("=========================================");
        System.out.println("   BUBBLE TEA MANAGEMENT SYSTEM");
        System.out.println("          Chao mung ban! ");
        System.out.println("=========================================");

        while (true) {
            boolean loggedIn = authView.start();
            
            if (!loggedIn) {
                System.out.println("Cam on ban da su dung chuong trinh. Hen gap lai!");
                break;
            }

            String username = authService.getCurrentUser().getUsername();
            System.out.println("\nXin chao " + username + "!");

            mainView.start();

            authService.logout();
            System.out.println("Da dang xuat.");
        }

        scanner.close();
    }
}