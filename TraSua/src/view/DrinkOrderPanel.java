package view;

import model.*;
import service.OrderService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DrinkOrderPanel extends JPanel {

    private final OrderService orderService;

    private JPanel contentPanel;

    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;

    private Tea currentTea;

    public DrinkOrderPanel(OrderService orderService) {
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        add(createSidebar(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        showDrinkMenu();

        add(contentPanel, BorderLayout.CENTER);

        add(createRightPanel(), BorderLayout.EAST);
    }

    // ================= SIDEBAR =================

    private JPanel createSidebar() {

        JPanel panel = new JPanel(new GridLayout(5,1,5,5));

        panel.setPreferredSize(new Dimension(180,0));

        String[] menus = {
                "Danh sách món",
                "Size",
                "Đường",
                "Đá",
                "Topping"
        };

        for (int i = 0; i < menus.length; i++) {

            int index = i;

            JButton btn = new JButton(menus[i]);

            btn.addActionListener(e -> switchMenu(index));

            panel.add(btn);
        }

        return panel;
    }

    private void switchMenu(int index) {

        contentPanel.removeAll();

        switch (index) {

            case 0:
                showDrinkMenu();
                break;

            case 1:
                showSizeMenu();
                break;

            case 2:
                showSugarMenu();
                break;

            case 3:
                showIceMenu();
                break;

            case 4:
                showToppingMenu();
                break;
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // ================= DRINK =================

    private void showDrinkMenu() {

        JPanel panel = new JPanel(new GridLayout(1,3,20,20));

        JButton milkTea = new JButton("Trà Sữa");
        JButton matcha = new JButton("Matcha");
        JButton chocolate = new JButton("Socola");

        milkTea.addActionListener(e -> {
            currentTea = new MilkTea();
        });

        matcha.addActionListener(e -> {
            currentTea = new MatchaTea();
        });

        chocolate.addActionListener(e -> {
            currentTea = new ChocolateTea();
        });

        panel.add(milkTea);
        panel.add(matcha);
        panel.add(chocolate);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    // ================= SIZE =================

    private void showSizeMenu() {

        if (currentTea == null) {
            JOptionPane.showMessageDialog(this, "Chọn món trước");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(1,3,20,20));

        JButton s = new JButton("S");
        JButton m = new JButton("M");
        JButton l = new JButton("L");

        s.addActionListener(e -> currentTea.setSize(Size.SMALL));
        m.addActionListener(e -> currentTea.setSize(Size.MEDIUM));
        l.addActionListener(e -> currentTea.setSize(Size.LARGE));

        panel.add(s);
        panel.add(m);
        panel.add(l);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    // ================= SUGAR =================

    private void showSugarMenu() {

        if (currentTea == null) {
            JOptionPane.showMessageDialog(this, "Chọn món trước");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(1,5,15,15));

        addSugarButton(panel,"0%",SugarLevel.ZERO);
        addSugarButton(panel,"30%",SugarLevel.THIRTY);
        addSugarButton(panel,"50%",SugarLevel.FIFTY);
        addSugarButton(panel,"70%",SugarLevel.SEVENTY);
        addSugarButton(panel,"100%",SugarLevel.FULL);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    private void addSugarButton(JPanel panel, String text, SugarLevel level) {

        JButton btn = new JButton(text);

        btn.addActionListener(e -> {
            currentTea.setSugarLevel(level);
        });

        panel.add(btn);
    }

    // ================= ICE =================

    private void showIceMenu() {

        if (currentTea == null) {
            JOptionPane.showMessageDialog(this, "Chọn món trước");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(1,4,15,15));

        addIceButton(panel,"Không đá",IceLevel.NONE);
        addIceButton(panel,"Ít đá",IceLevel.LESS);
        addIceButton(panel,"Vừa đá",IceLevel.NORMAL);
        addIceButton(panel,"Nhiều đá",IceLevel.MORE);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    private void addIceButton(JPanel panel, String text, IceLevel level) {

        JButton btn = new JButton(text);

        btn.addActionListener(e -> {
            currentTea.setIceLevel(level);
        });

        panel.add(btn);
    }

    // ================= TOPPING =================

    private void showToppingMenu() {

        if (currentTea == null) {
            JOptionPane.showMessageDialog(this, "Chọn món trước");
            return;
        }

        JPanel panel = new JPanel(new BorderLayout());

        JPanel checkPanel = new JPanel(new GridLayout(3,1));

        JCheckBox pearl = new JCheckBox("Trân châu");
        JCheckBox cheese = new JCheckBox("Cheese");
        JCheckBox pudding = new JCheckBox("Pudding");

        checkPanel.add(pearl);
        checkPanel.add(cheese);
        checkPanel.add(pudding);

        JButton confirm = new JButton("Xác nhận");

        confirm.addActionListener(e -> {

            Tea finalTea = currentTea;

            if (pearl.isSelected()) {
                finalTea = new Pearl(finalTea);
            }

            if (cheese.isSelected()) {
                finalTea = new Cheese(finalTea);
            }

            if (pudding.isSelected()) {
                finalTea = new Pudding(finalTea);
            }

            currentTea = finalTea;

            JOptionPane.showMessageDialog(this, "Đã thêm topping");
        });

        panel.add(checkPanel, BorderLayout.CENTER);
        panel.add(confirm, BorderLayout.SOUTH);

        contentPanel.add(panel, BorderLayout.CENTER);
    }

    // ================= RIGHT PANEL =================

    private JPanel createRightPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setPreferredSize(new Dimension(350,0));

        panel.add(createOrderTable(), BorderLayout.CENTER);

        JButton addButton = new JButton("THÊM VÀO HÓA ĐƠN");

        addButton.addActionListener(e -> addToOrder());

        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOrderTable() {

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {
                "Tên món",
                "Giá"
        };

        tableModel = new DefaultTableModel(columns,0);

        orderTable = new JTable(tableModel);

        totalLabel = new JLabel("Tổng: 0 VND");

        panel.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);

        return panel;
    }

    // ================= ADD ORDER =================

    private void addToOrder() {

        if (currentTea == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn món");
            return;
        }

        try {

            orderService.processOrder(currentTea);

            refreshTable();

            currentTea = null;

            JOptionPane.showMessageDialog(this, "Đã thêm vào hóa đơn");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        Order order = orderService.getCurrentOrder();

        List<Tea> drinks = order.getDrinks();

        for (Tea tea : drinks) {

            Object[] row = {
                    tea.getDescription(),
                    tea.getCost()
            };

            tableModel.addRow(row);
        }

        totalLabel.setText(
                "Tổng: " + order.calculateTotal() + " VND"
        );
    }
}