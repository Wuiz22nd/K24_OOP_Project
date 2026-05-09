/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import service.AuthService;
import service.OrderService;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final AuthService authService;
    private final OrderService orderService;
    private DrinkOrderPanel drinkOrderPanel;

    public MainFrame(AuthService authService, OrderService orderService) {
        this.authService = authService;
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setTitle("BUBBLE TEA POS - Quản Lý Bán Hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 720);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0, 128, 128));

        // Top Bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(0, 110, 100));
        topBar.setPreferredSize(new Dimension(0, 60));

        JLabel title = new JLabel("   BUBBLE TEA POS - QUẢN LÝ BÁN HÀNG", SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("Nhân viên: " + authService.getCurrentUser().getUsername());
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userLabel.setForeground(Color.WHITE);

        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setForeground(Color.BLACK);
        btnLogout.addActionListener(e -> logout());

        topBar.add(title, BorderLayout.WEST);
        topBar.add(userLabel, BorderLayout.CENTER);
        topBar.add(btnLogout, BorderLayout.EAST);

        // Drink Order Panel
        drinkOrderPanel = new DrinkOrderPanel(orderService);

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(drinkOrderPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame(authService).setVisible(true);
        }
    }
}