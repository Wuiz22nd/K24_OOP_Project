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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AuthView extends JFrame {

    private JTextField loginEmailField;
    private JPasswordField loginPasswordField;

    private JTextField registerEmailField;
    private JPasswordField registerPasswordField;

    public AuthView() {

        setTitle("Đăng nhập / Đăng ký");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1, 2));

        add(createLoginPanel());

        add(createRegisterPanel());

        setVisible(true);
    }

    // =====================================================
    // LOGIN PANEL
    // =====================================================
    private JPanel createLoginPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(
                BorderFactory.createTitledBorder("Đăng nhập")
        );

        panel.setLayout(new GridLayout(5, 1, 10, 10));

        loginEmailField = new JTextField();

        loginPasswordField = new JPasswordField();

        JButton loginBtn = new JButton("Đăng nhập");

        panel.add(new JLabel("Email:"));

        panel.add(loginEmailField);

        panel.add(new JLabel("Mật khẩu:"));

        panel.add(loginPasswordField);

        panel.add(loginBtn);

        // ==========================================
        // LOGIN ACTION
        // ==========================================
        loginBtn.addActionListener(e -> {

            String email =
                    loginEmailField.getText().trim();

            String password =
                    new String(
                            loginPasswordField.getPassword()
                    );

            // EMPTY
            if (email.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập đầy đủ thông tin!"
                );

                return;
            }

            // EMAIL FORMAT
            if (!isValidLoginEmail(email)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Email phải có ít nhất 6 ký tự và kết thúc bằng @gmail.com"
                );

                return;
            }

            // LOGIN SUCCESS
            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thành công!"
            );

            // MỞ MAIN FRAME
            // new MainFrame();

        });

        return panel;
    }

    // =====================================================
    // REGISTER PANEL
    // =====================================================
    private JPanel createRegisterPanel() {

        JPanel panel = new JPanel();

        panel.setBorder(
                BorderFactory.createTitledBorder("Đăng ký")
        );

        panel.setLayout(new GridLayout(5, 1, 10, 10));

        registerEmailField = new JTextField();

        registerPasswordField = new JPasswordField();

        JButton registerBtn = new JButton("Đăng ký");

        panel.add(new JLabel("Email:"));

        panel.add(registerEmailField);

        panel.add(new JLabel("Mật khẩu:"));

        panel.add(registerPasswordField);

        panel.add(registerBtn);

        // ==========================================
        // REGISTER ACTION
        // ==========================================
        registerBtn.addActionListener(e -> {

            String email =
                    registerEmailField.getText().trim();

            String password =
                    new String(
                            registerPasswordField.getPassword()
                    );

            // EMPTY
            if (email.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập đầy đủ thông tin!"
                );

                return;
            }

            // EMAIL FORMAT
            if (!isValidLoginEmail(email)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Email phải có ít nhất 6 ký tự và kết thúc bằng @gmail.com"
                );

                return;
            }

            // PASSWORD FORMAT
            if (!isValidRegisterPassword(password)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Mật khẩu phải có ít nhất 8 chữ hoặc số"
                );

                return;
            }

            // REGISTER SUCCESS
            JOptionPane.showMessageDialog(
                    this,
                    "Đăng ký thành công!"
            );
        });

        return panel;
    }

    // =====================================================
    // VALIDATE LOGIN EMAIL
    // Ít nhất 6 ký tự + @gmail.com
    // =====================================================
    private boolean isValidLoginEmail(String email) {

        return email.matches(
                "^[a-zA-Z0-9]{6,}@gmail\\.com$"
        );
    }

    // =====================================================
    // VALIDATE PASSWORD
    // Ít nhất 8 chữ hoặc số
    // =====================================================
    private boolean isValidRegisterPassword(
            String password
    ) {

        return password.matches(
                "^[a-zA-Z0-9]{8,}$"
        );
    }

    // =====================================================
    // MAIN
    // =====================================================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new AuthView();
        });
    }
}