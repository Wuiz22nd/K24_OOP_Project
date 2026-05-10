package view;

import model.Order;
import model.Tea;
import service.OrderService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PaymentDialog extends JDialog {

    private final Order order;
    private final OrderService orderService;
    private boolean paid = false;

    public PaymentDialog(Frame parent, Order order, OrderService orderService) {
        super(parent, "THANH TOÁN", true);
        this.order = order;
        this.orderService = orderService;
        initUI();
    }

    private void initUI() {
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(Color.WHITE);

        // Header
        JLabel header = new JLabel("BUBBLE TEA", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(new Color(0, 128, 128));

        JLabel subHeader = new JLabel("HÓA ĐƠN THANH TOÁN", SwingConstants.CENTER);
        subHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Thông tin hóa đơn
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        infoPanel.add(new JLabel("Thời gian: " + LocalDateTime.now().format(dtf)));
        infoPanel.add(new JLabel("Nhân viên: " + "Nhân viên 1"));
        infoPanel.add(new JLabel("Mã hóa đơn: #" + System.currentTimeMillis() % 100000));

        // Bảng chi tiết
        JPanel billPanel = createBillDetailPanel();

        // Tổng tiền
        JLabel totalLabel = new JLabel("TỔNG CỘNG: " + String.format("%,.0f", order.calculateTotal()) + " VND", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        totalLabel.setForeground(new Color(0, 128, 128));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnPay = new JButton("XÁC NHẬN THANH TOÁN");
        JButton btnCancel = new JButton("HỦY");

        styleButton(btnPay, new Color(0, 180, 0));
        styleButton(btnCancel, new Color(220, 50, 50));

        btnPay.addActionListener(e -> completePayment());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnPay);
        buttonPanel.add(btnCancel);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(billPanel, BorderLayout.CENTER);
        mainPanel.add(totalLabel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private JPanel createBillDetailPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        String[] columns = {"STT", "Tên món", "Giá", "SL", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);

        List<Tea> drinks = order.getDrinks();
        int stt = 1;
        for (Tea drink : drinks) {
            double price = drink.getCost();
            model.addRow(new Object[]{
                stt++,
                drink.getDescription(),
                String.format("%,.0f", price),
                "1",
                String.format("%,.0f", price)
            });
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFocusPainted(false);
    }

    private void completePayment() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Xác nhận thanh toán?\nTổng tiền: " + String.format("%,.0f", order.calculateTotal()) + " VND", 
            "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            order.printInvoice(); // In ra console (có thể cải tiến sau)
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!\nCảm ơn quý khách ❤️", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            paid = true;
            orderService.getCurrentOrder().clear();
            dispose();
        }
    }

    public boolean isPaid() {
        return paid;
    }
}