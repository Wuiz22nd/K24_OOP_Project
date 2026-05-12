package view;

import model.Bill;
import model.BillItem;
import model.BillStorage;
import repository.InventoryRepository;
import service.BillIdGenerator;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrinkOrderPanel extends JPanel {

    private JTable billTable;
    private DefaultTableModel tableModel;

    private JPanel centerPanel;

    private String selectedDrink = "";
    private int selectedPrice = 0;

    private String selectedSize = "M";
    private String selectedSugar = "100%";
    private String selectedIce = "Vừa đá";

    private java.util.List<String> selectedToppings = new ArrayList<>();

    // TOPPING PRICE
    private final Map<String, Integer> toppingPrices = new HashMap<>();

    public DrinkOrderPanel() {

        toppingPrices.put("Trân châu", 5000);
        toppingPrices.put("Thạch", 7000);
        toppingPrices.put("Pudding", 12000);
        toppingPrices.put("Kem cheese", 15000);

        setLayout(new BorderLayout());

        createTopBillPanel();
        createBottomPanel();

        showDrinkMenu();
    }

    // ==================================================
    // TOP BILL
    // ==================================================
    private void createTopBillPanel() {

        String[] columns = {
            "STT",
            "Tên món",
            "Giá",
            "-",
            "SL",
            "+",
            "Tổng"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 5;
            }
        };

        billTable = new JTable(tableModel);

        billTable.setRowHeight(45);

        billTable.setFont(new Font("Arial", Font.PLAIN, 17));

        billTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        billTable.getColumn("-").setCellRenderer(new ButtonRenderer());
        billTable.getColumn("+").setCellRenderer(new ButtonRenderer());

        billTable.getColumn("-").setCellEditor(
                new ButtonEditor(new JCheckBox())
        );

        billTable.getColumn("+").setCellEditor(
                new ButtonEditor(new JCheckBox())
        );

        JScrollPane scrollPane = new JScrollPane(billTable);

        scrollPane.setPreferredSize(new Dimension(0, 300));

        add(scrollPane, BorderLayout.NORTH);
    }

    // ==================================================
    // BOTTOM PANEL
    // ==================================================
    private void createBottomPanel() {

        JPanel bottomPanel = new JPanel(new BorderLayout());

        // LEFT MENU
        JPanel leftMenu = new JPanel(new GridLayout(5, 1, 8, 8));

        String[] menus = {
            "Danh sách món",
            "Size",
            "Lượng đường",
            "Lượng đá",
            "Topping"
        };

        for (String menu : menus) {

            JButton btn = createMenuButton(menu);

            btn.addActionListener(e -> {

                switch (menu) {

                    case "Danh sách món":
                        showDrinkMenu();
                        break;

                    case "Size":
                        showSizeMenu();
                        break;

                    case "Lượng đường":
                        showSugarMenu();
                        break;

                    case "Lượng đá":
                        showIceMenu();
                        break;

                    case "Topping":
                        showToppingMenu();
                        break;
                }
            });

            leftMenu.add(btn);
        }

        // CENTER PANEL
        centerPanel = new JPanel(new GridLayout(2, 3, 15, 15));

        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                15, 15, 15, 15
        ));

        // RIGHT BUTTONS
        JPanel rightButtons = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton cancelBtn = createRightButton("Hủy đơn");
        JButton removeBtn = createRightButton("Xóa món");
        JButton addBtn = createRightButton("Thêm vào");
        JButton paymentBtn = createRightButton("Thanh toán");

        // =========================
        // ADD TO BILL
        // =========================
        addBtn.addActionListener(e -> {

            if (selectedDrink.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn món!"
                );

                return;
            }

            String toppingText = "";

            if (!selectedToppings.isEmpty()) {

                toppingText
                        = " + "
                        + String.join(", ", selectedToppings);
            }

            String fullName
                    = selectedDrink
                    + " + " + selectedSize
                    + " + " + selectedSugar
                    + " + " + selectedIce
                    + toppingText;

            // CALCULATE TOPPING PRICE
            int toppingTotal = 0;

            for (String topping : selectedToppings) {

                toppingTotal += toppingPrices.getOrDefault(topping, 0);
            }

            int finalPrice = selectedPrice + toppingTotal;

            tableModel.addRow(new Object[]{
                tableModel.getRowCount() + 1,
                fullName,
                finalPrice + " VND",
                "-",
                1,
                "+",
                finalPrice + " VND"
            });
        });

        // =========================
        // REMOVE
        // =========================
        removeBtn.addActionListener(e -> {

            int row = billTable.getSelectedRow();

            if (row >= 0) {

                tableModel.removeRow(row);

                refreshSTT();
            }
        });

        // =========================
        // CANCEL
        // =========================
        cancelBtn.addActionListener(e -> {

            tableModel.setRowCount(0);
        });

        // =========================
        // PAYMENT
        // =========================
        paymentBtn.addActionListener(e -> {

            if (tableModel.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hóa đơn trống!"
                );

                return;
            }

            Bill bill = new Bill(
                    BillIdGenerator.generateId()
            );

            for (int i = 0; i < tableModel.getRowCount(); i++) {

                String name
                        = tableModel.getValueAt(i, 1).toString();

                int quantity
                        = Integer.parseInt(
                                tableModel.getValueAt(i, 4).toString()
                        );

                String priceText
                        = tableModel.getValueAt(i, 2)
                                .toString()
                                .replace(" VND", "");

                int price = Integer.parseInt(priceText);

                BillItem item
                        = new BillItem(name, quantity, price);

                bill.addItem(item);

                // ======================
                // USE INGREDIENT
                // ======================
                InventoryRepository.useIngredient(
                        "tra",
                        200 * quantity
                );

                InventoryRepository.useIngredient(
                        "sua",
                        150 * quantity
                );

                InventoryRepository.useIngredient(
                        "duong",
                        50 * quantity
                );

                InventoryRepository.useIngredient(
                        "da",
                        100 * quantity
                );

                if (name.contains("Trân châu")) {

                    InventoryRepository.useIngredient(
                            "tran chau",
                            30 * quantity
                    );
                }

                if (name.contains("Thạch")) {

                    InventoryRepository.useIngredient(
                            "thach",
                            30 * quantity
                    );
                }

                if (name.contains("Pudding")) {

                    InventoryRepository.useIngredient(
                            "pudding",
                            30 * quantity
                    );
                }

                if (name.contains("Kem cheese")) {

    InventoryRepository.useIngredient(
            "cheese",
            30 * quantity
    );
}

} // đóng for

// SAVE BILL
BillStorage.saveBill(bill);

// SHOW RECEIPT
new ReceiptFrame(bill);

// CLEAR TABLE
tableModel.setRowCount(0);

}); // đóng action listener

rightButtons.add(cancelBtn);
rightButtons.add(removeBtn);
rightButtons.add(addBtn);
rightButtons.add(paymentBtn);

bottomPanel.add(leftMenu, BorderLayout.WEST);
bottomPanel.add(centerPanel, BorderLayout.CENTER);
bottomPanel.add(rightButtons, BorderLayout.EAST);

add(bottomPanel, BorderLayout.CENTER);

} // đóng createBottomPanel()

// ==================================================
// DRINK MENU
// ==================================================
private void showDrinkMenu() {

        centerPanel.removeAll();

        addDrinkButton("Trà Sữa", 30000);
        addDrinkButton("Matcha", 35000);
        addDrinkButton("Socola", 40000);
        addDrinkButton("Oolong", 32000);
        addDrinkButton("Dâu", 36000);
        addDrinkButton("Khoai môn", 38000);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void addDrinkButton(String name, int price) {

        JButton btn = new JButton(
                "<html><center>"
                + name
                + "<br>"
                + price
                + " VND"
                + "</center></html>"
        );

        styleBigButton(btn);

        btn.addActionListener(e -> {

            selectedDrink = name;
            selectedPrice = price;
        });

        centerPanel.add(btn);
    }

    // ==================================================
    // SIZE
    // ==================================================
    private void showSizeMenu() {

        centerPanel.removeAll();

        String[] sizes = {"S", "M", "L"};

        for (String size : sizes) {

            JButton btn = new JButton(size);

            styleBigButton(btn);

            btn.addActionListener(e -> {
                selectedSize = size;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==================================================
    // SUGAR
    // ==================================================
    private void showSugarMenu() {

        centerPanel.removeAll();

        String[] sugars = {
            "0%",
            "30%",
            "50%",
            "70%",
            "100%"
        };

        for (String sugar : sugars) {

            JButton btn = new JButton(sugar);

            styleBigButton(btn);

            btn.addActionListener(e -> {
                selectedSugar = sugar;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==================================================
    // ICE
    // ==================================================
    private void showIceMenu() {

        centerPanel.removeAll();

        String[] ices = {
            "Không đá",
            "Ít đá",
            "Vừa đá",
            "Nhiều đá"
        };

        for (String ice : ices) {

            JButton btn = new JButton(ice);

            styleBigButton(btn);

            btn.addActionListener(e -> {
                selectedIce = ice;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==================================================
    // TOPPING
    // ==================================================
    private void showToppingMenu() {

        centerPanel.removeAll();

        String[] toppings = {
            "Trân châu",
            "Thạch",
            "Pudding",
            "Kem cheese"
        };

        for (String topping : toppings) {

            JToggleButton btn = new JToggleButton(
                    "<html><center>"
                    + topping
                    + "<br>+"
                    + toppingPrices.get(topping)
                    + " VND"
                    + "</center></html>"
            );

            btn.setFont(new Font("Arial", Font.BOLD, 18));

            btn.setFocusPainted(false);

            btn.addActionListener(e -> {

                if (btn.isSelected()) {

                    if (!selectedToppings.contains(topping)) {
                        selectedToppings.add(topping);
                    }

                } else {

                    selectedToppings.remove(topping);
                }
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==================================================
    // REFRESH STT
    // ==================================================
    private void refreshSTT() {

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            tableModel.setValueAt(i + 1, i, 0);
        }
    }

    // ==================================================
    // STYLE
    // ==================================================
    private JButton createMenuButton(String text) {

        JButton btn = new JButton(text);

        btn.setFont(new Font("Arial", Font.BOLD, 18));

        btn.setFocusPainted(false);

        return btn;
    }

    private JButton createRightButton(String text) {

        JButton btn = new JButton(text);

        btn.setFont(new Font("Arial", Font.BOLD, 20));

        btn.setFocusPainted(false);

        return btn;
    }

    private void styleBigButton(AbstractButton btn) {

        btn.setFont(new Font("Arial", Font.BOLD, 22));

        btn.setFocusPainted(false);

        btn.setPreferredSize(new Dimension(180, 100));
    }

    // ==================================================
    // BUTTON RENDERER
    // ==================================================
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            setText(value.toString());

            return this;
        }
    }

    // ==================================================
    // BUTTON EDITOR
    // ==================================================
    class ButtonEditor extends DefaultCellEditor {

        private JButton button;

        private String label;

        private boolean clicked;

        private int row;
        private int column;

        public ButtonEditor(JCheckBox checkBox) {

            super(checkBox);

            button = new JButton();

            button.setOpaque(true);

            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table,
                Object value,
                boolean isSelected,
                int row,
                int column) {

            this.row = row;
            this.column = column;

            label = value.toString();

            button.setText(label);

            clicked = true;

            return button;
        }

        @Override
        public Object getCellEditorValue() {

            if (clicked) {

                int quantity
                        = Integer.parseInt(
                                tableModel.getValueAt(row, 4).toString()
                        );

                String priceText
                        = tableModel.getValueAt(row, 2)
                                .toString()
                                .replace(" VND", "");

                int price = Integer.parseInt(priceText);

                if (column == 5) {
                    quantity++;
                }

                if (column == 3 && quantity > 1) {
                    quantity--;
                }

                tableModel.setValueAt(quantity, row, 4);

                int total = quantity * price;

                tableModel.setValueAt(
                        total + " VND",
                        row,
                        6
                );
            }

            clicked = false;

            return label;
        }

        @Override
        public boolean stopCellEditing() {

            clicked = false;

            return super.stopCellEditing();
        }
    }
}
