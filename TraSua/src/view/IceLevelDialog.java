/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import model.IceLevel;
import javax.swing.*;
import java.awt.*;

public class IceLevelDialog extends JDialog {

    private IceLevel selectedLevel = IceLevel.NORMAL;
    private boolean confirmed = false;

    public IceLevelDialog(Frame parent) {
        super(parent, "Chọn Lượng Đá", true);
        initUI();
    }

    private void initUI() {
        setSize(500, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Chọn lượng đá", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 128, 128));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBackground(Color.WHITE);

        Object[][] data = {
            {"Không Đá", IceLevel.NONE},
            {"Ít Đá", IceLevel.LESS},
            {"Vừa Đá", IceLevel.NORMAL},
            {"Nhiều Đá", IceLevel.MORE}
        };

        for (Object[] item : data) {
            JButton btn = new JButton((String) item[0]);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setBackground(new Color(0, 128, 128));
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);

            final IceLevel level = (IceLevel) item[1];
            btn.addActionListener(e -> {
                selectedLevel = level;
                confirmed = true;
                dispose();
            });
            buttonPanel.add(btn);
        }

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public IceLevel getSelectedLevel() {
        return selectedLevel;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
