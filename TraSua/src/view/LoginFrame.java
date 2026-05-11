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
    private MainFrame mainFrame;

    // Constants - Dễ chỉnh sửa
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

        // Left Panel - Logo
        JPanel leftPanel = createLeftPanel();
        
        // Right Panel - Form
        JPanel rightPanel = createRightPanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Default focus
        txtUsername.requestFocusInWindow();
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PRIMARY_COLOR);
        panel.setPreferredSize(new Dimension(430, 620));

        JLabel logoLabel = new JLabel();
        ImageIcon icon = loadImage("/images/bubble_tea_logo.png");

        if (icon != null) {
            Image scaledImage = icon.getImage().getScaledInstance(320, 320, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            logoLabel.setText("🧋");
            logoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 180));
            logoLabel.setForeground(Color.WHITE);
        }

        JLabel brandLabel = new JLabel("TRÀ SỮA", SwingConstants.CENTER);
        brandLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        brandLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
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

        // Title
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(PRIMARY_COLOR);

        txtUsername = new JTextField(22);
        txtPassword = new JPasswordField(22);

        JButton btnLogin = createStyledButton("Sign In");
        JButton btnRegister = createStyledButton("Register");

        // Add components
        int row = 0;
        panel.add(titleLabel, getGbc(gbc, row++));
        JLabel lblUsername = new JLabel("Tên người dùng");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblUsername.setForeground(new Color(70, 70, 70));

        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblPassword.setForeground(new Color(70, 70, 70));

        panel.add(lblUsername, getGbc(gbc, row++));
        panel.add(txtUsername, getGbc(gbc, row++));
        panel.add(lblPassword, getGbc(gbc, row++));
        panel.add(txtPassword, getGbc(gbc, row++));
        panel.add(btnLogin, getGbc(gbc, row++));
        panel.add(btnRegister, getGbc(gbc, row));

        // Events
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

    private ImageIcon loadImage(String path) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            return (imgURL != null) ? new ImageIcon(imgURL) : null;
        } catch (Exception e) {
            System.err.println("Không load được ảnh: " + path);
            return null;
        }
    }

    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Vui lòng nhập Username và Password!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (authService.login(username, password)) {
            dispose();
            if (mainFrame == null) {
                mainFrame = new MainFrame(authService, new OrderService());
            }
            mainFrame.setVisible(true);
        } else {
            showMessage("Sai tên đăng nhập hoặc mật khẩu!", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtUsername.requestFocus();
        }
    }

    private void performRegister() {
        dispose();
        new RegisterFrame(authService, this).setVisible(true);
    }

    private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, "Thông báo", messageType);
    }
}