package view;

import model.Bill;
import model.BillItem;

import javax.swing.*;
import java.awt.*;

public class BillDetailPanel extends JPanel {

    private JTextArea area;

    public BillDetailPanel() {

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(350, 0));

        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
        ));

        JLabel title = new JLabel("CHI TIẾT HÓA ĐƠN");

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        area = new JTextArea();

        area.setEditable(false);

        area.setFont(new Font("Monospaced", Font.PLAIN, 16));

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    // =====================================================
    // SHOW BILL
    // =====================================================
    public void showBill(Bill bill) {

        StringBuilder sb = new StringBuilder();

        sb.append("Mã hóa đơn: #")
                .append(bill.getBillId())
                .append("\n\n");

        sb.append("========================\n");

        int stt = 1;

        for (BillItem item : bill.getItems()) {

            sb.append(stt++)
                    .append(". ")
                    .append(item.getName())
                    .append("\n");

            sb.append("SL: ")
                    .append(item.getQuantity())
                    .append("\n");

            sb.append("Giá: ")
                    .append(item.getPrice())
                    .append(" VND\n");

            sb.append("------------------------\n");
        }

        sb.append("\nTỔNG TIỀN: ")
                .append(bill.getTotal())
                .append(" VND");

        area.setText(sb.toString());
    }
}