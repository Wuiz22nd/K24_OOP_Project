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
import service.OrderService;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthService authService;

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    // UI Colors (giữ như cũ)
    private static final Color PRIMARY_COLOR = new Color(0, 128, 128);
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color BUTTON_BG = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = Color.BLACK;

    public LoginFrame(AuthService authService) {
        this.authService = authService;
        initUI();
    }

    private void initUI() {
        setTitle("Bubble Tea POS System - Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);

        txtUsername.requestFocusInWindow();
    }

    private JPanel createLeftPanel() {

    JPanel panel = new JPanel(new GridBagLayout());

    panel.setBackground(PRIMARY_COLOR);

    panel.setPreferredSize(new Dimension(430, 620));

    // LOAD IMAGE
    ImageIcon icon = new ImageIcon(
            getClass().getResource(
                    "/images/bubble_tea_logo.png"
            )
    );

    Image img = icon.getImage().getScaledInstance(
            280,
            280,
            Image.SCALE_SMOOTH
    );

    JLabel logoLabel = new JLabel(
            new ImageIcon(img)
    );

    JLabel brandLabel = new JLabel(
            "TRÀ SỮA",
            SwingConstants.CENTER
    );

    brandLabel.setFont(
            new Font("Segoe UI", Font.BOLD, 34)
    );

    brandLabel.setForeground(Color.WHITE);

    GridBagConstraints gbc =
            new GridBagConstraints();

    gbc.gridx = 0;

    gbc.gridy = 0;

    panel.add(logoLabel, gbc);

    gbc.gridy = 1;

    gbc.insets = new Insets(20, 0, 0, 0);

    panel.add(brandLabel, gbc);

    return panel;
}
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 50, 15, 50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(PRIMARY_COLOR);

        txtUsername = new JTextField(22);
        txtPassword = new JPasswordField(22);

        JButton btnLogin = createStyledButton("Sign In");
        JButton btnRegister = createStyledButton("Register");

        int row = 0;
        panel.add(titleLabel, getGbc(gbc, row++));
        JLabel userLabel = new JLabel("Username");
userLabel.setForeground(Color.BLACK);
userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

JLabel passLabel = new JLabel("Password");
passLabel.setForeground(Color.BLACK);
passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

panel.add(userLabel, getGbc(gbc, row++));
panel.add(txtUsername, getGbc(gbc, row++));

panel.add(passLabel, getGbc(gbc, row++));
panel.add(txtPassword, getGbc(gbc, row++));
        panel.add(btnLogin, getGbc(gbc, row++));
        panel.add(btnRegister, getGbc(gbc, row));

        btnLogin.addActionListener(e -> performLogin());
        btnRegister.addActionListener(e -> performRegister());
        txtPassword.addActionListener(e -> performLogin());

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_BG);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(220, 48));
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 2));
        return button;
    }

    private GridBagConstraints getGbc(GridBagConstraints gbc, int row) {
        gbc.gridy = row;
        return gbc;
    }

    // ===================== LOGIN FLOW =====================
private void performLogin() {

    String username = txtUsername.getText().trim();
    String password = new String(txtPassword.getPassword()).trim();

    // CHECK EMPTY
    if (username.isEmpty() || password.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Vui lòng nhập đầy đủ thông tin!"
        );

        return;
    }

    // LOGIN SUCCESS
    if (authService.login(username, password)) {

        dispose(); // đóng login

        MainFrame mainFrame = new MainFrame(
                authService,
                new OrderService()
        );

        mainFrame.setVisible(true);

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Sai tài khoản hoặc mật khẩu!"
        );

        txtPassword.setText("");
        txtUsername.requestFocus();
    }
}

    private void performRegister() {

    setVisible(false);

    RegisterFrame registerFrame =
            new RegisterFrame(authService, this);

    registerFrame.setVisible(true);
    }
}