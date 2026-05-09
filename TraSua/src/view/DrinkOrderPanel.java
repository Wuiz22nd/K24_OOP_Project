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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DrinkOrderPanel extends JPanel {

    private final OrderService orderService;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;

    public DrinkOrderPanel(OrderService orderService) {
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));
        setBackground(new Color(0, 140, 130));

        add(createTopBar(), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.setBackground(new Color(240, 240, 240));
        center.add(createMenuPanel(), BorderLayout.CENTER);
        center.add(createOrderTablePanel(), BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);
    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 110, 100));
        panel.setPreferredSize(new Dimension(0, 55));

        JLabel title = new JLabel("   BUBBLE TEA POS - QUẢN LÝ BÁN HÀNG", SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(4, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(170, 0));
        sidebar.setBackground(new Color(245, 245, 245));

        String[] cat = {"Danh sách món", "Lượng đường", "Lượng đá", "Topping"};
        Color[] col = {new Color(220, 80, 80), new Color(255, 140, 0), 
                      new Color(50, 205, 50), new Color(30, 144, 255)};

        for (int i = 0; i < cat.length; i++) {
            JButton b = new JButton(cat[i]);
            b.setBackground(col[i]);
            b.setForeground(Color.BLACK);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            sidebar.add(b);
        }

        // Menu Grid
        JPanel grid = new JPanel(new GridLayout(0, 3, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        grid.setBackground(Color.WHITE);

        addDrinkButton(grid, "Trà Sữa Truyền Thống", new MilkTea());
        addDrinkButton(grid, "Trà Sữa Socola", new ChocolateTea());
        addDrinkButton(grid, "Trà Sữa Matcha", new MatchaTea());
        addDrinkButton(grid, "Trà Sữa Đường Đen", null);
        addDrinkButton(grid, "Trà Sữa Oolong", null);

        panel.add(sidebar, BorderLayout.WEST);
        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private void addDrinkButton(JPanel panel, String name, Tea baseTea) {
        JButton btn = new JButton("<html><center>" + name + "</center></html>");
        btn.setBackground(new Color(0, 128, 128));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);

        btn.addActionListener(e -> {
            if (baseTea != null) {
                DrinkCustomizerDialog dialog = new DrinkCustomizerDialog(
                    (Frame) SwingUtilities.getWindowAncestor(this), baseTea);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    try {
                        orderService.processOrder(dialog.getResultTea());
                        refreshOrderTable();
                        JOptionPane.showMessageDialog(this, "Đã thêm vào hóa đơn!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } catch (InsufficientStockException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Hết Nguyên Liệu", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panel.add(btn);
    }

    private JPanel createOrderTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 260));
        panel.setBackground(new Color(0, 110, 100));

        String[] columns = {"STT", "Tên món", "Giá", "Số lượng", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        orderTable = new JTable(tableModel);
        orderTable.setRowHeight(30);
        orderTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        orderTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(orderTable);

        totalLabel = new JLabel("TỔNG CỘNG: 0 VND", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        totalLabel.setForeground(Color.WHITE);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 8, 12));
        panel.setPreferredSize(new Dimension(150, 0));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        JButton btnClear = new JButton("Hủy Đơn");
        JButton btnDelete = new JButton("Xóa Món");
        JButton btnCheckout = new JButton("Thanh Toán");

        styleRightButton(btnClear, new Color(100, 100, 100));
        styleRightButton(btnDelete, new Color(220, 50, 50));
        styleRightButton(btnCheckout, new Color(0, 100, 200));

        btnClear.addActionListener(e -> clearOrder());
        btnCheckout.addActionListener(e -> checkout());

        panel.add(btnClear);
        panel.add(btnDelete);
        panel.add(new JLabel());
        panel.add(btnCheckout);

        return panel;
    }

    private void styleRightButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }

    public void refreshOrderTable() {
        tableModel.setRowCount(0);
        Order order = orderService.getCurrentOrder();
        List<Tea> drinks = order.getDrinks();
        int stt = 1;

        for (Tea drink : drinks) {
            Object[] row = {
                stt++,
                drink.getDescription(),
                String.format("%,.0f", drink.getCost()),
                "1",
                String.format("%,.0f", drink.getCost())
            };
            tableModel.addRow(row);
        }

        totalLabel.setText("TỔNG CỘNG: " + String.format("%,.0f", order.calculateTotal()) + " VND");
    }

    private void clearOrder() {
        int confirm = JOptionPane.showConfirmDialog(this, "Xóa toàn bộ hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            orderService.getCurrentOrder().clear();
            refreshOrderTable();
        }
    }

    private void checkout() {
        JOptionPane.showMessageDialog(this, "Chức năng thanh toán đang được phát triển!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}