/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import javax.swing.*;
import java.awt.*;

public class SidebarPanel extends JPanel {

    private JButton btnTea;
    private JButton btnInventory;
    private JButton btnBill;
    private JButton btnLogout;

    public SidebarPanel() {

        setLayout(new GridLayout(4, 1, 0, 15));

        setBackground(new Color(0, 128, 128));

        setPreferredSize(new Dimension(180, 0));

        setBorder(BorderFactory.createEmptyBorder(
                20,
                15,
                20,
                15
        ));

        // =========================
        // BUTTONS
        // =========================
        btnTea = createMenuButton(
                "TẠO TRÀ SỮA",
                "/images/milk.png"
        );

        btnInventory = createMenuButton(
                "KHO",
                "/images/inventory.png"
        );

        btnBill = createMenuButton(
                "HÓA ĐƠN",
                "/images/bill.png"
        );

        btnLogout = createMenuButton(
                "ĐĂNG XUẤT",
                "/images/logout.png"
        );

        add(btnTea);
        add(btnInventory);
        add(btnBill);
        add(btnLogout);
    }

    // ==================================================
    // STYLE BUTTON
    // ==================================================
    private JButton createMenuButton(
            String text,
            String iconPath
    ) {

        JButton btn = new JButton(text);

        btn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        btn.setFocusPainted(false);

        btn.setBackground(new Color(0, 140, 140));

        btn.setForeground(Color.BLACK);

        btn.setHorizontalAlignment(SwingConstants.LEFT);

        btn.setIconTextGap(15);

        btn.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        10
                )
        );

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setPreferredSize(new Dimension(160, 90));

        // LOAD ICON
        try {

            ImageIcon icon = new ImageIcon(
                    getClass().getResource(iconPath)
            );

            Image img = icon.getImage().getScaledInstance(
                    36,
                    36,
                    Image.SCALE_SMOOTH
            );

            btn.setIcon(new ImageIcon(img));

        } catch (Exception e) {

            System.out.println(
                    "Không load được icon: " + iconPath
            );
        }

        return btn;
    }

    // ==================================================
    // GETTERS
    // ==================================================
    public JButton getBtnTea() {
        return btnTea;
    }

    public JButton getBtnInventory() {
        return btnInventory;
    }

    public JButton getBtnBill() {
        return btnBill;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }
}
