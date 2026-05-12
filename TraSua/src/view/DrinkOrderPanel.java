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

    private java.util.List<String> selectedToppings
            = new ArrayList<>();

    private final Map<String, Integer> toppingPrices
            = new HashMap<>();

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
            public boolean isCellEditable(
                    int row,
                    int column
            ) {

                return column == 3 || column == 5;
            }
        };

        billTable = new JTable(tableModel);

        billTable.setRowHeight(45);

        billTable.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        billTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        billTable.getColumn("-")
                .setCellRenderer(new ButtonRenderer());

        billTable.getColumn("+")
                .setCellRenderer(new ButtonRenderer());

        billTable.getColumn("-")
                .setCellEditor(
                        new ButtonEditor(new JCheckBox())
                );

        billTable.getColumn("+")
                .setCellEditor(
                        new ButtonEditor(new JCheckBox())
                );

        JScrollPane scrollPane
                = new JScrollPane(billTable);

        scrollPane.setPreferredSize(
                new Dimension(0, 300)
        );

        add(scrollPane, BorderLayout.NORTH);
    }

    // ==================================================
    // BOTTOM PANEL
    // ==================================================
    private void createBottomPanel() {

        JPanel bottomPanel
                = new JPanel(new BorderLayout());

        // =====================================
        // LEFT MENU
        // =====================================
        JPanel leftMenu
                = new JPanel(new GridLayout(5, 1, 8, 8));

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

        // =====================================
        // CENTER PANEL
        // =====================================
        centerPanel = new JPanel(
                new GridLayout(2, 3, 15, 15)
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================
        // RIGHT BUTTONS
        // =====================================
        JPanel rightButtons
                = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton cancelBtn = createRightButton(
                "Hủy đơn",
                "/images/cancel.png"
        );

        JButton removeBtn = createRightButton(
                "Xóa món",
                "/images/delete.png"
        );

        JButton addBtn = createRightButton(
                "Thêm vào",
                "/images/add.png"
        );

        JButton paymentBtn = createRightButton(
                "Thanh toán",
                "/images/payment.png"
        );

        // =====================================
        // ADD TO BILL
        // =====================================
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

                toppingText =
                        " + "
                                + String.join(
                                ", ",
                                selectedToppings
                        );
            }

            String fullName =
                    selectedDrink
                            + " + " + selectedSize
                            + " + " + selectedSugar
                            + " + " + selectedIce
                            + toppingText;

            int toppingTotal = 0;

            for (String topping : selectedToppings) {

                toppingTotal +=
                        toppingPrices.getOrDefault(
                                topping,
                                0
                        );
            }

            int finalPrice =
                    selectedPrice + toppingTotal;

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

        // =====================================
        // REMOVE
        // =====================================
        removeBtn.addActionListener(e -> {

            int row = billTable.getSelectedRow();

            if (row >= 0) {

                tableModel.removeRow(row);

                refreshSTT();
            }
        });

        // =====================================
        // CANCEL
        // =====================================
        cancelBtn.addActionListener(e -> {

            tableModel.setRowCount(0);
        });

        // =====================================
        // PAYMENT
        // =====================================
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

            for (int i = 0;
                 i < tableModel.getRowCount();
                 i++) {

                String name =
                        tableModel.getValueAt(i, 1)
                                .toString();

                int quantity =
                        Integer.parseInt(
                                tableModel.getValueAt(i, 4)
                                        .toString()
                        );

                String priceText =
                        tableModel.getValueAt(i, 2)
                                .toString()
                                .replace(" VND", "");

                int price =
                        Integer.parseInt(priceText);

                BillItem item =
                        new BillItem(
                                name,
                                quantity,
                                price
                        );

                bill.addItem(item);

                // =====================================
                // TRÀ + SỮA
                // =====================================
                InventoryRepository.useIngredient(
                        "tra",
                        200 * quantity
                );

                InventoryRepository.useIngredient(
                        "sua",
                        150 * quantity
                );

                // =====================================
                // ĐƯỜNG
                // =====================================
                int sugarAmount = 0;

                if (name.contains("+ 30% +")) {

                    sugarAmount = 15;
                }

                else if (name.contains("+ 50% +")) {

                    sugarAmount = 25;
                }

                else if (name.contains("+ 70% +")) {

                    sugarAmount = 35;
                }

                else if (name.contains("+ 100% +")) {

                    sugarAmount = 50;
                }

                // 0% thì sugarAmount vẫn = 0

                InventoryRepository.useIngredient(
                        "duong",
                        sugarAmount * quantity
                );

                // =====================================
                // ĐÁ
                // =====================================
                if (!name.contains("Không đá")) {

                    int iceAmount = 100;

                    if (name.contains("Ít đá")) {
                        iceAmount = 50;
                    }

                    else if (name.contains("Vừa đá")) {
                        iceAmount = 100;
                    }

                    else if (name.contains("Nhiều đá")) {
                        iceAmount = 150;
                    }

                    InventoryRepository.useIngredient(
                            "da",
                            iceAmount * quantity
                    );
                }

                // =====================================
                // TOPPING
                // =====================================
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
            }

            BillStorage.saveBill(bill);

            new ReceiptFrame(bill);

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

    // ==================================================
    // DRINK MENU
    // ==================================================
    private void showDrinkMenu() {

        centerPanel.removeAll();

        addDrinkButton(
                "Truyền Thống",
                30000,
                "/images/traditional.png"
        );

        addDrinkButton(
                "Matcha",
                35000,
                "/images/matcha.png"
        );

        addDrinkButton(
                "Socola",
                40000,
                "/images/chocolate.png"
        );

        addDrinkButton(
                "Oolong",
                32000,
                "/images/oolong.png"
        );

        addDrinkButton(
                "Dâu",
                36000,
                "/images/strawberry.png"
        );

        addDrinkButton(
                "Khoai môn",
                38000,
                "/images/taro.png"
        );

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void addDrinkButton(
            String name,
            int price,
            String imagePath
    ) {

        JButton btn = new JButton();

        btn.setLayout(new BorderLayout());

        btn.setBackground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        JPanel wrapper =
                new JPanel(new BorderLayout());

        wrapper.setOpaque(false);

        wrapper.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JPanel textPanel = new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        JLabel priceLabel =
                new JLabel(price + " VND");

        priceLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(nameLabel);
        textPanel.add(Box.createRigidArea(
                new Dimension(0, 10)
        ));
        textPanel.add(priceLabel);
        textPanel.add(Box.createVerticalGlue());

        JLabel imageLabel = new JLabel();

        try {

            ImageIcon icon =
                    new ImageIcon(
                            getClass().getResource(imagePath)
                    );

            Image img =
                    icon.getImage().getScaledInstance(
                            40,
                            50,
                            Image.SCALE_SMOOTH
                    );

            imageLabel.setIcon(
                    new ImageIcon(img)
            );

        } catch (Exception e) {

            System.out.println(
                    "Khong load duoc anh: "
                            + imagePath
            );
        }

        JPanel imagePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                15
                        )
                );

        imagePanel.setOpaque(false);

        imagePanel.add(imageLabel);

        wrapper.add(textPanel, BorderLayout.CENTER);
        wrapper.add(imagePanel, BorderLayout.EAST);

        btn.add(wrapper);

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

        String[] sizes = {
                "S",
                "M",
                "L"
        };

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

    addToppingButton(
            "Trân châu",
            5000,
            "/images/pearl.png"
    );

    addToppingButton(
            "Thạch",
            7000,
            "/images/jelly.png"
    );

    addToppingButton(
            "Pudding",
            12000,
            "/images/pudding.png"
    );

    addToppingButton(
            "Kem cheese",
            15000,
            "/images/cheese.png"
    );

    centerPanel.revalidate();
    centerPanel.repaint();
}

// ==================================================
// TOPPING BUTTON UI
// ==================================================
private void addToppingButton(
        String topping,
        int price,
        String imagePath
) {

    JToggleButton btn = new JToggleButton();

    btn.setLayout(new BorderLayout());

    btn.setBackground(Color.WHITE);

    btn.setFocusPainted(false);

    btn.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
    );

    JPanel wrapper =
            new JPanel(new BorderLayout());

    wrapper.setOpaque(false);

    wrapper.setBorder(
            BorderFactory.createEmptyBorder(
                    15,
                    15,
                    15,
                    15
            )
    );

    // ============================
    // TEXT PANEL
    // ============================
    JPanel textPanel = new JPanel();

    textPanel.setOpaque(false);

    textPanel.setLayout(
            new BoxLayout(
                    textPanel,
                    BoxLayout.Y_AXIS
            )
    );

    JLabel nameLabel =
            new JLabel(topping);

    nameLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
    );

    JLabel priceLabel =
            new JLabel("+" + price + " VND");

    priceLabel.setFont(
            new Font("Arial", Font.BOLD, 14)
    );

    textPanel.add(Box.createVerticalGlue());
    textPanel.add(nameLabel);
    textPanel.add(Box.createRigidArea(
            new Dimension(0, 10)
    ));
    textPanel.add(priceLabel);
    textPanel.add(Box.createVerticalGlue());

    // ============================
    // IMAGE
    // ============================
    JLabel imageLabel = new JLabel();

    try {

        ImageIcon icon =
                new ImageIcon(
                        getClass().getResource(imagePath)
                );

        Image img =
                icon.getImage().getScaledInstance(
                        40,
                        50,
                        Image.SCALE_SMOOTH
                );

        imageLabel.setIcon(
                new ImageIcon(img)
        );

    } catch (Exception e) {

        System.out.println(
                "Khong load duoc anh topping: "
                        + imagePath
        );
    }

    JPanel imagePanel =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.RIGHT,
                            40,
                            60
                    )
            );

    imagePanel.setOpaque(false);

    imagePanel.add(imageLabel);

    wrapper.add(textPanel, BorderLayout.CENTER);
    wrapper.add(imagePanel, BorderLayout.EAST);

    btn.add(wrapper);

    // ============================
    // SELECT TOPPING
    // ============================
    btn.addActionListener(e -> {

        if (btn.isSelected()) {

            if (!selectedToppings.contains(
                    topping
            )) {

                selectedToppings.add(topping);
            }

        } else {

            selectedToppings.remove(topping);
        }
    });

    centerPanel.add(btn);
}

    // ==================================================
    // REFRESH STT
    // ==================================================
    private void refreshSTT() {

        for (int i = 0;
             i < tableModel.getRowCount();
             i++) {

            tableModel.setValueAt(
                    i + 1,
                    i,
                    0
            );
        }
    }

    // ==================================================
    // STYLE
    // ==================================================
    private JButton createMenuButton(String text) {

        JButton btn = new JButton(text);

        btn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        btn.setFocusPainted(false);

        return btn;
    }

    private JButton createRightButton(
            String text,
            String iconPath
    ) {

        JButton btn = new JButton(text);

        btn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        btn.setFocusPainted(false);

        btn.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        btn.setIconTextGap(15);

        btn.setBackground(Color.WHITE);

        try {

            ImageIcon icon =
                    new ImageIcon(
                            getClass().getResource(iconPath)
                    );

            Image img =
                    icon.getImage().getScaledInstance(
                            28,
                            28,
                            Image.SCALE_SMOOTH
                    );

            btn.setIcon(new ImageIcon(img));

        } catch (Exception e) {

            System.out.println(
                    "Khong load duoc icon: "
                            + iconPath
            );
        }

        return btn;
    }

    private void styleBigButton(
            AbstractButton btn
    ) {

        btn.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        btn.setFocusPainted(false);

        btn.setBackground(Color.WHITE);

        btn.setPreferredSize(
                new Dimension(180, 100)
        );
    }

    // ==================================================
    // BUTTON RENDERER
    // ==================================================
    class ButtonRenderer
            extends JButton
            implements TableCellRenderer {

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
                int column
        ) {

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

        public ButtonEditor(
                JCheckBox checkBox
        ) {

            super(checkBox);

            button = new JButton();

            button.setOpaque(true);

            button.addActionListener(
                    e -> fireEditingStopped()
            );
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table,
                Object value,
                boolean isSelected,
                int row,
                int column
        ) {

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
                                tableModel.getValueAt(
                                        row,
                                        4
                                ).toString()
                        );

                String priceText =
                        tableModel.getValueAt(
                                        row,
                                        2
                                )
                                .toString()
                                .replace(" VND", "");

                int price =
                        Integer.parseInt(priceText);

                if (column == 5) {
                    quantity++;
                }

                if (column == 3 && quantity > 1) {
                    quantity--;
                }

                tableModel.setValueAt(
                        quantity,
                        row,
                        4
                );

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