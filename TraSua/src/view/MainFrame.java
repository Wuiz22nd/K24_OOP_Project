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
import view.EmployeePanel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final AuthService authService;
    private final OrderService orderService;

    // =====================================
    // CARD LAYOUT
    // =====================================
    private CardLayout cardLayout;
    private JPanel contentPanel;

    // =====================================
    // COLORS
    // =====================================
    private final Color SIDEBAR_COLOR =
            new Color(0, 128, 128);

    private final Color BUTTON_COLOR =
            new Color(0, 140, 140);

    // =====================================
    // CONSTRUCTOR
    // =====================================
    public MainFrame(
            AuthService authService,
            OrderService orderService
    ) {

        this.authService = authService;
        this.orderService = orderService;

        initUI();
    }

    // =====================================
    // UI
    // =====================================
    private void initUI() {

        setTitle("Bubble Tea POS");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // =====================================
        // TOP BAR
        // =====================================
        JPanel topBar =
                new JPanel(new BorderLayout());

        topBar.setBackground(
                new Color(0, 100, 100)
        );

        topBar.setPreferredSize(
                new Dimension(0, 60)
        );

        JLabel title =
                new JLabel(
                        "  BUBBLE TEA POS SYSTEM"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(Color.WHITE);

        // =====================================
        // USER LABEL
        // =====================================
        String username = "Unknown";

        if (authService.getCurrentUser() != null) {

            username =
                    authService
                            .getCurrentUser()
                            .getUsername();
        }

        JLabel userLabel =
                new JLabel(
                        "Nhân viên: "
                                + username
                                + "   "
                );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18
                )
        );

        userLabel.setForeground(Color.WHITE);

        topBar.add(title, BorderLayout.WEST);
        topBar.add(userLabel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // =====================================
        // SIDEBAR
        // =====================================
        JPanel sidebar = new JPanel();

        sidebar.setBackground(SIDEBAR_COLOR);

        sidebar.setPreferredSize(
                new Dimension(230, 0)
        );

        sidebar.setLayout(
                new GridLayout(10, 1, 10, 10)
        );

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        15,
                        20,
                        15
                )
        );

        // =====================================
        // SIDEBAR BUTTONS
        // =====================================
        JButton btnTea =
                createSidebarButton(
                        "TẠO TRÀ SỮA",
                        "/images/milk.png"
                );

        JButton btnInventory =
                createSidebarButton(
                        "KHO",
                        "/images/inventory.png"
                );

        JButton btnBill =
                createSidebarButton(
                        "HÓA ĐƠN",
                        "/images/bill.png"
                );

        JButton btnEmployee =
                createSidebarButton(
                        "NHÂN VIÊN",
                        "/images/staff.png"
                );

        JButton btnLogout =
                createSidebarButton(
                        "ĐĂNG XUẤT",
                        "/images/logout.png"
                );

        // =====================================
        // ADD SIDEBAR BUTTONS
        // =====================================
        sidebar.add(btnTea);

        sidebar.add(btnInventory);

        sidebar.add(btnBill);

        sidebar.add(btnEmployee);

        sidebar.add(new JLabel());

        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // =====================================
        // CONTENT PANEL
        // =====================================
        cardLayout = new CardLayout();

        contentPanel =
                new JPanel(cardLayout);

        // =====================================
        // PANELS
        // =====================================
        DrinkOrderPanel drinkPanel =
                new DrinkOrderPanel();

        InventoryPanel inventoryPanel =
                new InventoryPanel();

        BillPanel billPanel =
                new BillPanel();

        EmployeePanel employeePanel =
                new EmployeePanel();

        // =====================================
        // ADD PANELS
        // =====================================
        contentPanel.add(
                drinkPanel,
                "TEA"
        );

        contentPanel.add(
                inventoryPanel,
                "INVENTORY"
        );

        contentPanel.add(
                billPanel,
                "BILL"
        );

        contentPanel.add(
                employeePanel,
                "EMPLOYEE"
        );

        add(contentPanel, BorderLayout.CENTER);

        // =====================================
        // EVENTS
        // =====================================
        btnTea.addActionListener(e -> {

            cardLayout.show(
                    contentPanel,
                    "TEA"
            );
        });

        btnInventory.addActionListener(e -> {

            cardLayout.show(
                    contentPanel,
                    "INVENTORY"
            );
        });

        btnBill.addActionListener(e -> {

            cardLayout.show(
                    contentPanel,
                    "BILL"
            );
        });

        btnEmployee.addActionListener(e -> {

            cardLayout.show(
                    contentPanel,
                    "EMPLOYEE"
            );
        });

        btnLogout.addActionListener(
                e -> logout()
        );

        // =====================================
        // DEFAULT PAGE
        // =====================================
        cardLayout.show(
                contentPanel,
                "TEA"
        );
    }

    // =====================================
    // SIDEBAR BUTTON
    // =====================================
    private JButton createSidebarButton(
            String text,
            String iconPath
    ) {

        JButton btn =
                new JButton(text);

        // =====================================
        // LOAD ICON
        // =====================================
        try {

            ImageIcon icon =
                    new ImageIcon(
                            getClass()
                                    .getResource(iconPath)
                    );

            Image img =
                    icon.getImage()
                            .getScaledInstance(
                                    28,
                                    28,
                                    Image.SCALE_SMOOTH
                            );

            btn.setIcon(
                    new ImageIcon(img)
            );

        } catch (Exception e) {

            System.out.println(
                    "Không load được icon: "
                            + iconPath
            );
        }

        // =====================================
        // STYLE
        // =====================================
        btn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        btn.setBackground(BUTTON_COLOR);

        btn.setForeground(Color.BLACK);

        btn.setFocusPainted(false);

        btn.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        btn.setIconTextGap(15);

        btn.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        return btn;
    }

    // =====================================
    // LOGOUT
    // =====================================
    private void logout() {

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc muốn đăng xuất?",
                        "Đăng xuất",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm == JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame(authService);

            loginFrame.setVisible(true);
        }
    }
}