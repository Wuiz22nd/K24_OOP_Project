package view;

import model.*;
import javax.swing.*;
import java.awt.*;

public class ToppingDialog extends JDialog {

    private Tea currentTea;
    private Tea resultTea;
    private boolean confirmed = false;

    private JCheckBox cbPearl, cbCheese, cbPudding;

    public ToppingDialog(Frame parent, Tea currentTea) {
        super(parent, "Chọn Topping", true);
        this.currentTea = currentTea;
        this.resultTea = currentTea;
        initUI();
    }

    private void initUI() {
        setSize(560, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        mainPanel.setBackground(Color.WHITE);

        // Header
        JLabel title = new JLabel("CHỌN TOPPING", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 128, 128));

        // Topping Container
        JPanel toppingContainer = new JPanel();
        toppingContainer.setLayout(new GridLayout(3, 1, 0, 12));
        toppingContainer.setBackground(Color.WHITE);

        cbPearl = createToppingCheckBox("Trân Châu", "+10,000 VND", "tran_chau");
        cbCheese = createToppingCheckBox("Kem Cheese", "+15,000 VND", "kem_cheese");
        cbPudding = createToppingCheckBox("Thạch", "+12,000 VND", "thach");

        toppingContainer.add(cbPearl);
        toppingContainer.add(cbCheese);
        toppingContainer.add(cbPudding);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        JButton btnConfirm = new JButton("XÁC NHẬN");
        JButton btnCancel = new JButton("HỦY");

        btnConfirm.setBackground(new Color(0, 180, 0));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirm.setPreferredSize(new Dimension(180, 50));

        btnCancel.setBackground(new Color(220, 50, 50));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCancel.setPreferredSize(new Dimension(140, 50));

        btnConfirm.addActionListener(e -> confirmSelection());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancel);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(toppingContainer, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JCheckBox createToppingCheckBox(String name, String price, String iconName) {
        JCheckBox cb = new JCheckBox("<html><b>" + name + "</b><br><font color='#555555'>" + price + "</font></html>");
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cb.setForeground(Color.BLACK);
        cb.setBackground(Color.WHITE);
        cb.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        cb.setFocusPainted(false);
        return cb;
    }

    private void confirmSelection() {
        Tea finalTea = currentTea;

        if (cbPearl.isSelected()) finalTea = new Pearl(finalTea);
        if (cbCheese.isSelected()) finalTea = new Cheese(finalTea);
        if (cbPudding.isSelected()) finalTea = new Pudding(finalTea);

        resultTea = finalTea;
        confirmed = true;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Tea getResultTea() {
        return resultTea;
    }
}