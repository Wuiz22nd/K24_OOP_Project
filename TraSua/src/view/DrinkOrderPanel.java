package view;

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
    private JPanel contentPanel;

    public DrinkOrderPanel(OrderService orderService) {
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));
        setBackground(new Color(0, 140, 130));

        // Top Bar
        add(createTopBar(), BorderLayout.NORTH);

        // Center Area
        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.setBackground(new Color(240, 240, 240));

        center.add(createSidebar(), BorderLayout.WEST);
        center.add(createContentPanel(), BorderLayout.CENTER);
        center.add(createOrderTablePanel(), BorderLayout.SOUTH);   // ← Sửa ở đây

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

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new GridLayout(4, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(170, 0));
        sidebar.setBackground(new Color(245, 245, 245));

        String[] categories = {"Danh sách món", "Lượng đường", "Lượng đá", "Topping"};
        Color[] colors = {new Color(220, 80, 80), new Color(255, 140, 0), 
                         new Color(50, 205, 50), new Color(30, 144, 255)};

        for (int i = 0; i < categories.length; i++) {
            JButton btn = new JButton(categories[i]);
            btn.setBackground(colors[i]);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            final int index = i;
            btn.addActionListener(e -> switchCategory(index));
            sidebar.add(btn);
        }
        return sidebar;
    }

    private JPanel createContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        showDrinkList();
        return contentPanel;
    }

    private JPanel createOrderTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 280));
        panel.setBackground(new Color(0, 110, 100));

        String[] columns = {"STT", "Tên món", "Giá", "Số lượng", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        orderTable = new JTable(tableModel);
        orderTable.setRowHeight(32);
        orderTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        orderTable.setForeground(Color.BLACK);
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
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }

    private void switchCategory(int index) {
        contentPanel.removeAll();
        switch (index) {
            case 0 -> showDrinkList();
            case 1 -> showSugarLevelPanel();
            case 2 -> showIceLevelPanel();
            case 3 -> showToppingPanel();
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showDrinkList() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 12, 12));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        grid.setBackground(Color.WHITE);

        addDrinkButton(grid, "Trà Sữa Truyền Thống", new MilkTea());
        addDrinkButton(grid, "Trà Sữa Socola", new ChocolateTea());
        addDrinkButton(grid, "Trà Sữa Matcha", new MatchaTea());

        contentPanel.add(grid, BorderLayout.CENTER);
    }

    private void showSugarLevelPanel() {
        SugarLevelDialog dialog = new SugarLevelDialog((Frame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
    }

    private void showIceLevelPanel() {
        IceLevelDialog dialog = new IceLevelDialog((Frame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
    }

    private void showToppingPanel() {
        ToppingDialog dialog = new ToppingDialog(
            (Frame) SwingUtilities.getWindowAncestor(this), 
            null); // hoặc truyền currentTea nếu có
        
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            JOptionPane.showMessageDialog(this, 
                "Đã chọn Topping thành công!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void addDrinkButton(JPanel panel, String name, Tea baseTea) {
        JButton btn = new JButton("<html><center>" + name + "</center></html>");
        btn.setBackground(new Color(0, 128, 128));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setFocusPainted(false);

        btn.addActionListener(e -> {
            if (baseTea != null) {
                DrinkCustomizerDialog dialog = new DrinkCustomizerDialog(
                    (Frame) SwingUtilities.getWindowAncestor(this), baseTea);
                dialog.setVisible(true);
            }
        });
        panel.add(btn);
    }

    public void refreshOrderTable() {
        if (tableModel == null) return;
        
        tableModel.setRowCount(0);
        Order order = orderService.getCurrentOrder();
        List<Tea> drinks = order.getDrinks();
        int stt = 1;

        for (Tea drink : drinks) {
            double price = drink.getCost();
            Object[] row = {
                stt++,
                drink.getDescription(),
                String.format("%,.0f", price),
                "1",
                String.format("%,.0f", price)
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
        Order currentOrder = orderService.getCurrentOrder();
        if (currentOrder.getDrinks().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hóa đơn trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PaymentDialog paymentDialog = new PaymentDialog(
            (Frame) SwingUtilities.getWindowAncestor(this), 
            currentOrder, 
            orderService);
        
        paymentDialog.setVisible(true);

        if (paymentDialog.isPaid()) {
            refreshOrderTable();
        }
    }
}