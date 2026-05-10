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
import javax.swing.*;
import java.awt.*;

public class BubbleTeaPOSPanel extends JPanel {

    private final OrderService orderService;
    private JTextArea orderArea;
    private JLabel totalLabel;

    public BubbleTeaPOSPanel(OrderService orderService) {
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 140, 130));

        // ==================== TOP BAR ====================
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(0, 110, 100));
        topBar.setPreferredSize(new Dimension(0, 50));

        JLabel title = new JLabel("   BUBBLE TEA POS - QUẢN LÝ BÁN HÀNG", SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        topBar.add(title, BorderLayout.CENTER);
        add(topBar, BorderLayout.NORTH);

        // ==================== CENTER LAYOUT ====================
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        centerPanel.setBackground(new Color(240, 240, 240));

        // Left Sidebar + Menu Grid
        JPanel menuArea = createMenuArea();
        
        // Right Sidebar
        JPanel rightPanel = createRightPanel();

        centerPanel.add(menuArea, BorderLayout.CENTER);
        centerPanel.add(rightPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom: Hóa đơn tóm tắt (có thể thay bằng bảng sau)
        add(createOrderSummaryPanel(), BorderLayout.SOUTH);
    }

    private JPanel createMenuArea() {
        JPanel panel = new JPanel(new BorderLayout());

        // Sidebar Danh mục
        JPanel sidebar = new JPanel(new GridLayout(5, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(180, 0));
        sidebar.setBackground(new Color(255, 100, 100));

        String[] categories = {"Danh sách món", "Lượng đường", "Lượng đá", "Topping"};
        Color[] colors = {new Color(220, 80, 80), new Color(255, 140, 0), 
                         new Color(50, 205, 50), new Color(30, 144, 255)};

        for (int i = 0; i < categories.length; i++) {
            JButton btn = new JButton(categories[i]);
            btn.setBackground(colors[i]);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            sidebar.add(btn);
        }

        // Grid Món
        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        grid.setBackground(Color.WHITE);

        // Thêm một số món mẫu
        addDrinkButton(grid, "Trà Sữa Truyền Thống");
        addDrinkButton(grid, "Trà Sữa Socola");
        addDrinkButton(grid, "Trà Sữa Matcha");
        addDrinkButton(grid, "Trà Sữa Trà Đen");
        addDrinkButton(grid, "Trà Sữa Oolong");

        panel.add(sidebar, BorderLayout.WEST);
        panel.add(grid, BorderLayout.CENTER);

        return panel;
    }

    private void addDrinkButton(JPanel panel, String name) {
        JButton btn = new JButton(name);
        btn.setBackground(new Color(0, 128, 128));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);
        panel.add(btn);
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 8, 8));
        panel.setPreferredSize(new Dimension(160, 0));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));

        String[] actions = {"Hủy đơn", "Xóa món", "Thêm vào", "Thanh toán"};
        Color[] colors = {new Color(100, 100, 100), new Color(220, 50, 50), 
                         new Color(0, 180, 0), new Color(0, 100, 200)};

        for (int i = 0; i < actions.length; i++) {
            JButton btn = new JButton(actions[i]);
            btn.setBackground(colors[i]);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            panel.add(btn);
        }

        return panel;
    }

    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 140));
        panel.setBackground(new Color(0, 110, 100));

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setBackground(Color.WHITE);
        orderArea.setForeground(Color.BLACK);

        totalLabel = new JLabel("TỔNG CỘNG: 0 VND", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setForeground(Color.WHITE);

        panel.add(new JLabel("   HÓA ĐƠN HIỆN TẠI", SwingConstants.LEFT), BorderLayout.NORTH);
        panel.add(new JScrollPane(orderArea), BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);

        return panel;
    }
}
