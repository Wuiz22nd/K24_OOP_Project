/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import model.SugarLevel;
import javax.swing.*;
import java.awt.*;

public class SugarLevelDialog extends JDialog {

    private SugarLevel selectedLevel = SugarLevel.FIFTY; // mặc định 50%
    private boolean confirmed = false;

    public SugarLevelDialog(Frame parent) {
        super(parent, "Chọn Lượng Đường", true);
        initUI();
    }

    private void initUI() {
        setSize(500, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Chọn lượng đường", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 128, 128));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        buttonPanel.setBackground(Color.WHITE);

        String[] levels = {"0%", "30%", "50%", "70%", "100%", "120%"};
        SugarLevel[] sugarLevels = {SugarLevel.ZERO, SugarLevel.THIRTY, SugarLevel.FIFTY,
                                   SugarLevel.SEVENTY, SugarLevel.FULL, null};

        for (int i = 0; i < levels.length; i++) {
            JButton btn = new JButton(levels[i]);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setBackground(new Color(0, 128, 128));
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);

            final SugarLevel level = sugarLevels[i];
            btn.addActionListener(e -> {
                if (level != null) {
                    selectedLevel = level;
                    confirmed = true;
                    dispose();
                }
            });
            buttonPanel.add(btn);
        }

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public SugarLevel getSelectedLevel() {
        return selectedLevel;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
