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
import javax.swing.*;
import java.awt.*;

public class DrinkCustomizerDialog extends JDialog {

    private final Tea baseTea;
    private Tea resultTea;
    private boolean confirmed = false;

    private JComboBox<String> sizeCombo;
    private JComboBox<String> sugarCombo;
    private JComboBox<String> iceCombo;
    private JCheckBox cbPearl, cbCheese, cbPudding;

    public DrinkCustomizerDialog(Frame parent, Tea baseTea) {
        super(parent, "Tùy Chỉnh Đồ Uống", true);
        this.baseTea = baseTea;
        this.resultTea = baseTea;
        initUI();
    }

    private void initUI() {
        setSize(550, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 12, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
        mainPanel.setBackground(Color.WHITE);

        // Tên đồ uống
        String drinkTitle = baseTea.getDescription().split("\\(")[0].trim();
        JLabel drinkName = new JLabel(drinkTitle, SwingConstants.CENTER);
        drinkName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        drinkName.setForeground(new Color(0, 128, 128));

        // Các panel chọn
        JPanel sizePanel = createOptionPanel("Size:", 
            sizeCombo = new JComboBox<>(new String[]{"S (Nhỏ)", "M (Vừa)", "L (Lớn)"}));

        JPanel sugarPanel = createOptionPanel("Lượng Đường:", 
            sugarCombo = new JComboBox<>(new String[]{"0%", "30%", "50%", "70%", "100%"}));

        JPanel icePanel = createOptionPanel("Lượng Đá:", 
            iceCombo = new JComboBox<>(new String[]{"Không Đá", "Ít Đá", "Vừa Đá", "Nhiều Đá"}));

        // Topping
        JPanel toppingPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Chọn Topping"));
        toppingPanel.setBackground(Color.WHITE);

        cbPearl = new JCheckBox("Trân Châu (+10k)");
        cbCheese = new JCheckBox("Kem Cheese (+15k)");
        cbPudding = new JCheckBox("Thạch (+12k)");

        styleCheckBox(cbPearl);
        styleCheckBox(cbCheese);
        styleCheckBox(cbPudding);

        toppingPanel.add(cbPearl);
        toppingPanel.add(cbCheese);
        toppingPanel.add(cbPudding);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 15));
        JButton btnConfirm = new JButton("Xác Nhận");
        JButton btnCancel = new JButton("Hủy");

        btnConfirm.setBackground(new Color(0, 180, 0));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnCancel.setBackground(new Color(220, 50, 50));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 15));

        btnConfirm.addActionListener(e -> confirmSelection());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancel);

        mainPanel.add(drinkName);
        mainPanel.add(sizePanel);
        mainPanel.add(sugarPanel);
        mainPanel.add(icePanel);
        mainPanel.add(toppingPanel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createOptionPanel(String labelText, JComboBox<String> combo) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);           // ← Chữ đen

        // ComboBox chữ đen
        combo.setForeground(Color.BLACK);
        combo.setBackground(Color.WHITE);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(label, BorderLayout.WEST);
        panel.add(combo, BorderLayout.CENTER);
        return panel;
    }

    private void styleCheckBox(JCheckBox checkBox) {
        checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        checkBox.setForeground(Color.BLACK);        // ← Chữ đen
        checkBox.setBackground(Color.WHITE);
    }

    private void confirmSelection() {
        int sIdx = sizeCombo.getSelectedIndex();
        if (sIdx == 0) resultTea.setSize(Size.SMALL);
        else if (sIdx == 2) resultTea.setSize(Size.LARGE);
        else resultTea.setSize(Size.MEDIUM);

        int sugarIdx = sugarCombo.getSelectedIndex();
        SugarLevel[] sugars = {SugarLevel.ZERO, SugarLevel.THIRTY, SugarLevel.FIFTY, 
                              SugarLevel.SEVENTY, SugarLevel.FULL};
        resultTea.setSugarLevel(sugars[sugarIdx]);

        int iceIdx = iceCombo.getSelectedIndex();
        IceLevel[] ices = {IceLevel.NONE, IceLevel.LESS, IceLevel.NORMAL, IceLevel.MORE};
        resultTea.setIceLevel(ices[iceIdx]);

        Tea finalTea = resultTea;
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