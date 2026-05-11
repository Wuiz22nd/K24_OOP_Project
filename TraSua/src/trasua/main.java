/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trasua;

import service.AuthService;
import javax.swing.*;
import view.LoginFrame;


/**
 *
 * @author wuiz
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Chạy giao diện GUI theo kiểu Swing
        SwingUtilities.invokeLater(() -> {

            setSystemLookAndFeel();

            AuthService authService = new AuthService();

            LoginFrame loginFrame = new LoginFrame(authService);
            loginFrame.setVisible(true);

        });
    }

    private static void setSystemLookAndFeel() {
        try {

            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );

        } catch (Exception e) {

            System.out.println("Khong the set giao dien he thong.");
        }
    }
}