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
    private final LoginFrame loginFrame;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    public RegisterFrame(
            AuthService authService,
            LoginFrame loginFrame
    ) {

        this.authService = authService;
        this.loginFrame = loginFrame;

        initUI();
    }

    private void initUI() {

        setTitle("Bubble Tea POS - Đăng Ký");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(520, 520);

        setLocationRelativeTo(null);

        setResizable(false);

        // =========================================
        // MAIN PANEL
        // =========================================
        JPanel mainPanel = new JPanel(new GridBagLayout());

        mainPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 30, 12, 30);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // =========================================
        // TITLE
        // =========================================
        JLabel title = new JLabel(
                "ĐĂNG KÝ TÀI KHOẢN",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setForeground(
                new Color(0, 128, 128)
        );

        // =========================================
        // USERNAME
        // =========================================
        JLabel lblUsername = new JLabel(
                "Email (@gmail.com)"
        );

        lblUsername.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );
        
        lblUsername.setForeground(Color.BLACK);

        txtUsername = new JTextField(20);

        styleTextField(txtUsername);

        // =========================================
        // PASSWORD
        // =========================================
        JLabel lblPassword = new JLabel(
                "Mật khẩu"
        );

        lblPassword.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );
        
        lblPassword.setForeground(Color.BLACK);

        txtPassword = new JPasswordField(20);

        styleTextField(txtPassword);

        // =========================================
        // CONFIRM PASSWORD
        // =========================================
        JLabel lblConfirm = new JLabel(
                "Xác nhận mật khẩu"
        );

        lblConfirm.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );
        
        lblConfirm.setForeground(Color.BLACK);

        txtConfirmPassword = new JPasswordField(20);

        styleTextField(txtConfirmPassword);

        // =========================================
        // NOTE
        // =========================================
        JLabel note = new JLabel(
                "<html>"
                + "• Email phải có ít nhất 6 ký tự trước @gmail.com"
                + "<br>"
                + "• Password phải có ít nhất 8 ký tự"
                + "</html>"
        );

        note.setFont(
                new Font("Segoe UI", Font.ITALIC, 13)
        );

        note.setForeground(Color.GRAY);

        // =========================================
        // BUTTONS
        // =========================================
        JButton btnRegister = new JButton("Đăng Ký");

        JButton btnBack = new JButton("Quay lại");

        styleBlackButton(btnRegister);

        styleBlackButton(btnBack);

        // =========================================
        // ADD COMPONENTS
        // =========================================
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        // USERNAME
        gbc.gridy = 1;

        mainPanel.add(lblUsername, gbc);

        gbc.gridy = 2;

        gbc.gridwidth = 2;

        mainPanel.add(txtUsername, gbc);

        // PASSWORD
        gbc.gridy = 3;

        gbc.gridwidth = 1;

        mainPanel.add(lblPassword, gbc);

        gbc.gridy = 4;

        gbc.gridwidth = 2;

        mainPanel.add(txtPassword, gbc);

        // CONFIRM
        gbc.gridy = 5;

        gbc.gridwidth = 1;

        mainPanel.add(lblConfirm, gbc);

        gbc.gridy = 6;

        gbc.gridwidth = 2;

        mainPanel.add(txtConfirmPassword, gbc);

        // NOTE
        gbc.gridy = 7;

        mainPanel.add(note, gbc);

        // BUTTONS
        gbc.gridy = 8;

        gbc.gridwidth = 1;

        mainPanel.add(btnRegister, gbc);

        gbc.gridx = 1;

        mainPanel.add(btnBack, gbc);

        add(mainPanel);

        // =========================================
        // EVENTS
        // =========================================
        btnRegister.addActionListener(
                e -> performRegister()
        );

        btnBack.addActionListener(
                e -> goBackToLogin()
        );
    }

    // =========================================
    // REGISTER
    // =========================================
    private void performRegister() {

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                ).trim();

        String confirm =
                new String(
                        txtConfirmPassword.getPassword()
                ).trim();

        // =========================
        // EMPTY
        // =========================
        if (
                username.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // EMAIL FORMAT
        // =========================
        if (
                !username.matches(
                        "^[a-zA-Z0-9]{6,}@gmail\\.com$"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Email phải có:\n"
                    + "- ít nhất 6 chữ hoặc số\n"
                    + "- kết thúc bằng @gmail.com",
                    "Sai định dạng",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =========================
        // PASSWORD LENGTH
        // =========================
        if (password.length() < 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Mật khẩu phải có ít nhất 8 ký tự!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =========================
        // CONFIRM PASSWORD
        // =========================
        if (!password.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Mật khẩu xác nhận không khớp!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );

            txtConfirmPassword.setText("");

            return;
        }

        // =========================
        // REGISTER
        // =========================
        boolean success =
                authService.signUp(
                        username,
                        password
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Đăng ký thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            goBackToLogin();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Email đã tồn tại!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================
    // BACK LOGIN
    // =========================================
    private void goBackToLogin() {

        dispose();

        if (loginFrame != null) {

            loginFrame.setVisible(true);
        }
    }

    // =========================================
    // STYLE BUTTON
    // =========================================
    private void styleBlackButton(
            JButton button
    ) {

        button.setBackground(
                new Color(245, 245, 245)
        );

        button.setForeground(Color.BLACK);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setPreferredSize(
                new Dimension(180, 45)
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(0, 128, 128),
                        2
                )
        );
    }

    // =========================================
    // STYLE TEXTFIELD
    // =========================================
    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        field.setPreferredSize(
                new Dimension(250, 40)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(180, 180, 180),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );
    }
}