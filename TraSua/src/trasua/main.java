/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trasua;

import service.AuthService;
import service.OrderService;
import view.LoginFrame;
import javax.swing.*;

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
            try {
                // Set Look and Feel đẹp hơn
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                AuthService authService = new AuthService();
                LoginFrame loginFrame = new LoginFrame(authService);
                loginFrame.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Lỗi khởi động chương trình: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}