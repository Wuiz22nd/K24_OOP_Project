/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import service.AuthService;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    
    private final AuthService authService;
    private final LoginFrame loginFrame;   // ← Thêm dòng này
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    // Constructor mới - nhận thêm LoginFrame
    public RegisterFrame(AuthService authService, LoginFrame loginFrame) {
        this.authService = authService;
        this.loginFrame = loginFrame;
        initUI();
    }

    private void initUI() {
        setTitle("Bubble Tea POS - Đăng Ký");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 30, 12, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("ĐĂNG KÝ TÀI KHOẢN", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 128, 128));

        JLabel lblUsername = new JLabel("Username");
        txtUsername = new JTextField(20);

        JLabel lblPassword = new JLabel("Password");
        txtPassword = new JPasswordField(20);

        JLabel lblConfirm = new JLabel("Xác nhận Password");
        txtConfirmPassword = new JPasswordField(20);

        JButton btnRegister = new JButton("Đăng Ký");
        JButton btnBack = new JButton("Quay lại");

        styleBlackButton(btnRegister);
        styleBlackButton(btnBack);

        // Add components
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        mainPanel.add(lblUsername, gbc);
        gbc.gridy = 2;
        mainPanel.add(txtUsername, gbc);

        gbc.gridy = 3;
        mainPanel.add(lblPassword, gbc);
        gbc.gridy = 4;
        mainPanel.add(txtPassword, gbc);

        gbc.gridy = 5;
        mainPanel.add(lblConfirm, gbc);
        gbc.gridy = 6;
        mainPanel.add(txtConfirmPassword, gbc);

        gbc.gridy = 7;
        mainPanel.add(btnRegister, gbc);
        gbc.gridx = 1;
        mainPanel.add(btnBack, gbc);

        add(mainPanel);

        // Events
        btnRegister.addActionListener(e -> performRegister());
        btnBack.addActionListener(e -> goBackToLogin());
    }

    private void styleBlackButton(JButton button) {
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 45));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 128, 128), 2));
    }

    private void performRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String confirm = new String(txtConfirmPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", 
                                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!", 
                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.setText("");
            return;
        }

        if (authService.signUp(username, password)) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!\nBạn có thể đăng nhập ngay bây giờ.", 
                                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
            goBackToLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại!\nUsername đã tồn tại hoặc mật khẩu quá ngắn.", 
                                        "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToLogin() {
        dispose();                    // Đóng RegisterFrame
        if (loginFrame != null) {
            loginFrame.setVisible(true);   // Mở lại LoginFrame
        }
    }
}