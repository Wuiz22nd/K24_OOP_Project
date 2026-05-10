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
import java.awt.*;

public class DrinkCreatorDialog extends JDialog {
    private final OrderService orderService;
    private Tea currentTea;

    private JComboBox<String> baseTeaCombo;
    private JComboBox<String> sizeCombo;
    private JComboBox<String> sugarCombo;
    private JComboBox<String> iceCombo;

    public DrinkCreatorDialog(Frame parent, OrderService orderService) {
        super(parent, "Tạo Đồ Uống Mới", true);
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setSize(520, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        // Tiêu đề
        JLabel title = new JLabel("TẠO ĐỒ UỐNG MỚI", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 128, 128));

        // Chọn loại trà
        JPanel p1 = createOptionPanel("Loại Trà Sữa:", 
            baseTeaCombo = new JComboBox<>(new String[]{"Trà Sữa Truyền Thống", "Trà Sữa Socola", "Trà Sữa Matcha"}));

        // Size
        JPanel p2 = createOptionPanel("Size:", 
            sizeCombo = new JComboBox<>(new String[]{"S (Nhỏ)", "M (Vừa)", "L (Lớn)"}));

        // Đường
        JPanel p3 = createOptionPanel("Mức Đường:", 
            sugarCombo = new JComboBox<>(new String[]{"0%", "30%", "50%", "70%", "100%"}));

        // Đá
        JPanel p4 = createOptionPanel("Mức Đá:", 
            iceCombo = new JComboBox<>(new String[]{"Không Đá", "Ít Đá", "Vừa Đá", "Nhiều Đá"}));

        // Topping
        JPanel toppingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Topping (có thể chọn nhiều)"));
        toppingPanel.setBackground(Color.WHITE);
        
        JCheckBox cbPearl = new JCheckBox("Trân Châu (+10k)");
        JCheckBox cbCheese = new JCheckBox("Kem Cheese (+15k)");
        JCheckBox cbPudding = new JCheckBox("Thạch (+12k)");

        toppingPanel.add(cbPearl);
        toppingPanel.add(cbCheese);
        toppingPanel.add(cbPudding);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAdd = new JButton("Thêm Vào Hóa Đơn");
        JButton btnCancel = new JButton("Hủy");

        styleBlackButton(btnAdd);
        styleBlackButton(btnCancel);

        btnAdd.addActionListener(e -> addDrink(cbPearl, cbCheese, cbPudding));
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnCancel);

        // Thêm tất cả vào mainPanel
        mainPanel.add(title);
        mainPanel.add(p1);
        mainPanel.add(p2);
        mainPanel.add(p3);
        mainPanel.add(p4);
        mainPanel.add(toppingPanel);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createOptionPanel(String labelText, JComboBox<String> combo) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        panel.add(label, BorderLayout.WEST);
        panel.add(combo, BorderLayout.CENTER);
        return panel;
    }

    private void styleBlackButton(JButton button) {
        button.setBackground(new Color(245, 245, 245));   // Nền xám nhạt
        button.setForeground(Color.BLACK);                // Chữ màu đen
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 45));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 128), 2));
    }

    private void addDrink(JCheckBox pearl, JCheckBox cheese, JCheckBox pudding) {
        // Tạo base tea
        int baseIndex = baseTeaCombo.getSelectedIndex();
        if (baseIndex == 0) currentTea = new MilkTea();
        else if (baseIndex == 1) currentTea = new ChocolateTea();
        else currentTea = new MatchaTea();

        // Set Size
        int sizeIndex = sizeCombo.getSelectedIndex();
        if (sizeIndex == 0) currentTea.setSize(Size.SMALL);
        else if (sizeIndex == 2) currentTea.setSize(Size.LARGE);
        else currentTea.setSize(Size.MEDIUM);

        // Set Sugar
        int sugarIndex = sugarCombo.getSelectedIndex();
        SugarLevel[] sugarLevels = {SugarLevel.ZERO, SugarLevel.THIRTY, SugarLevel.FIFTY, 
                                   SugarLevel.SEVENTY, SugarLevel.FULL};
        currentTea.setSugarLevel(sugarLevels[sugarIndex]);

        // Set Ice
        int iceIndex = iceCombo.getSelectedIndex();
        IceLevel[] iceLevels = {IceLevel.NONE, IceLevel.LESS, IceLevel.NORMAL, IceLevel.MORE};
        currentTea.setIceLevel(iceLevels[iceIndex]);

        // Thêm Topping
        Tea finalTea = currentTea;
        if (pearl.isSelected()) finalTea = new Pearl(finalTea);
        if (cheese.isSelected()) finalTea = new Cheese(finalTea);
        if (pudding.isSelected()) finalTea = new Pudding(finalTea);

        try {
            orderService.processOrder(finalTea);
            JOptionPane.showMessageDialog(this, 
                "Đã thêm thành công!\n" + finalTea.getDescription(), 
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (InsufficientStockException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Hết Nguyên Liệu", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
