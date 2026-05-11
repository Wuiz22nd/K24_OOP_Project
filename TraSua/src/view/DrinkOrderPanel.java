package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrinkOrderPanel extends JPanel {

    private JTable billTable;
    private DefaultTableModel tableModel;

    private JPanel centerPanel;

    private String selectedCategory = "Danh sách món";

    private String selectedDrink = "";
    private int selectedPrice = 0;

    private String selectedSize = "M";
    private String selectedSugar = "100%";
    private String selectedIce = "100%";

    private java.util.List<String> selectedToppings = new ArrayList<>();

    public DrinkOrderPanel() {

        setLayout(new BorderLayout());

        createTopBillPanel();
        createBottomPanel();

        showDrinkMenu();
    }

    // ==========================
    // TOP BILL
    // ==========================
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
        billTable.setFont(new Font("Arial", Font.PLAIN, 18));
        billTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        // button renderer
        billTable.getColumn("-").setCellRenderer(new ButtonRenderer());
        billTable.getColumn("+").setCellRenderer(new ButtonRenderer());

        // button editor
        billTable.getColumn("-").setCellEditor(new ButtonEditor(new JCheckBox()));
        billTable.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(billTable);

        scrollPane.setPreferredSize(new Dimension(0, 300));

        add(scrollPane, BorderLayout.NORTH);
    }

    // ==========================
    // BOTTOM AREA
    // ==========================
    private void createBottomPanel() {

        JPanel bottomPanel = new JPanel(new BorderLayout());

        // LEFT MENU
        JPanel leftMenu = new JPanel(new GridLayout(5, 1, 5, 5));

        String[] menus = {
            "Danh sách món",
            "Size",
            "Lượng đường",
            "Lượng đá",
            "Topping"
        };

        for (String menu : menus) {

            JButton btn = new JButton(menu);

            btn.setFont(new Font("Arial", Font.BOLD, 20));

            btn.addActionListener(e -> {

                selectedCategory = menu;

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

        // RIGHT BUTTONS
        JPanel rightButtons = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton cancelBtn = new JButton("Hủy đơn");
        JButton removeBtn = new JButton("Xóa món");
        JButton addBtn = new JButton("Thêm vào");
        JButton paymentBtn = new JButton("Thanh toán");

        Font btnFont = new Font("Arial", Font.BOLD, 22);

        cancelBtn.setFont(btnFont);
        removeBtn.setFont(btnFont);
        addBtn.setFont(btnFont);
        paymentBtn.setFont(btnFont);

        // ADD TO BILL
        addBtn.addActionListener(e -> {

            if (selectedDrink.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Vui lòng chọn món!");

                return;
            }

            String toppingText = "";

            if (!selectedToppings.isEmpty()) {

                toppingText =
                        " + "
                        + String.join(", ", selectedToppings);
            }

            String fullName =
                    selectedDrink
                    + " + " + selectedSize
                    + " + " + selectedSugar
                    + " + " + selectedIce
                    + toppingText;

            tableModel.addRow(new Object[]{
                tableModel.getRowCount() + 1,
                fullName,
                selectedPrice + " VND",
                "-",
                1,
                "+",
                selectedPrice + " VND"
            });
        });

        // REMOVE
        removeBtn.addActionListener(e -> {

            int row = billTable.getSelectedRow();

            if (row >= 0) {

                tableModel.removeRow(row);

                refreshSTT();
            }
        });

        // CANCEL
        cancelBtn.addActionListener(e -> {
            tableModel.setRowCount(0);
        });

        rightButtons.add(cancelBtn);
        rightButtons.add(removeBtn);
        rightButtons.add(addBtn);
        rightButtons.add(paymentBtn);

        bottomPanel.add(leftMenu, BorderLayout.WEST);
        bottomPanel.add(centerPanel, BorderLayout.CENTER);
        bottomPanel.add(rightButtons, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.CENTER);
    }

    // ==========================
    // DRINK MENU
    // ==========================
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
                + " VND</center></html>"
        );

        btn.setFont(new Font("Arial", Font.BOLD, 22));

        btn.addActionListener(e -> {

            selectedDrink = name;
            selectedPrice = price;
        });

        centerPanel.add(btn);
    }

    // ==========================
    // SIZE
    // ==========================
    private void showSizeMenu() {

        centerPanel.removeAll();

        String[] sizes = {"S", "M", "L"};

        for (String s : sizes) {

            JButton btn = new JButton(s);

            btn.setFont(new Font("Arial", Font.BOLD, 28));

            btn.addActionListener(e -> {
                selectedSize = s;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==========================
    // SUGAR
    // ==========================
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

            btn.setFont(new Font("Arial", Font.BOLD, 24));

            btn.addActionListener(e -> {
                selectedSugar = sugar;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==========================
    // ICE
    // ==========================
    private void showIceMenu() {

        centerPanel.removeAll();

        String[] ices = {
            "Không đá",
            "Ít đá",
            "50%",
            "100%"
        };

        for (String ice : ices) {

            JButton btn = new JButton(ice);

            btn.setFont(new Font("Arial", Font.BOLD, 24));

            btn.addActionListener(e -> {
                selectedIce = ice;
            });

            centerPanel.add(btn);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // ==========================
    // TOPPING
    // ==========================
    private void showToppingMenu() {

        centerPanel.removeAll();

        String[] toppings = {
            "Trân châu",
            "Thạch",
            "Pudding",
            "Kem cheese"
        };

        for (String topping : toppings) {

            JToggleButton btn = new JToggleButton(topping);

            btn.setFont(new Font("Arial", Font.BOLD, 20));

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

    // ==========================
    // REFRESH STT
    // ==========================
    private void refreshSTT() {

        for (int i = 0; i < tableModel.getRowCount(); i++) {

            tableModel.setValueAt(i + 1, i, 0);
        }
    }

    // ==========================
    // BUTTON RENDERER
    // ==========================
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

    // ==========================
    // BUTTON EDITOR
    // ==========================
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

                int quantity =
                        Integer.parseInt(
                                tableModel.getValueAt(row, 4).toString()
                        );

                String priceText =
                        tableModel.getValueAt(row, 2)
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

                tableModel.setValueAt(total + " VND", row, 6);
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