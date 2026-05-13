/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import model.Bill;
import model.BillItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFrame extends JFrame {

    public ReceiptFrame(Bill bill) {

        setTitle("Hóa đơn thanh toán");

        setSize(500, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // =====================================
        // MAIN PANEL
        // =====================================
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // =====================================
        // RECEIPT AREA
        // =====================================
        JTextArea area = new JTextArea();

        area.setEditable(false);

        area.setBackground(Color.WHITE);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        16
                )
        );

        // =====================================
        // FORMAT MONEY
        // =====================================
        DecimalFormat moneyFormat =
                new DecimalFormat("#,###");

        // =====================================
        // DATE TIME
        // =====================================
        LocalDateTime now =
                LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                );

        // =====================================
        // BUILD RECEIPT
        // =====================================
        StringBuilder sb = new StringBuilder();

        sb.append("========================================\n");
        sb.append("            BUBBLE TEA POS             \n");
        sb.append("========================================\n\n");

        sb.append("        PHIẾU THANH TOÁN\n\n");

        sb.append("Mã hóa đơn : ")
                .append(bill.getBillId())
                .append("\n");

        sb.append("Ngày giờ   : ")
                .append(now.format(formatter))
                .append("\n");

        sb.append("----------------------------------------\n");

        // =====================================
        // ITEMS
        // =====================================
        for (BillItem item : bill.getItems()) {

            sb.append(item.getName())
                    .append("\n");

            sb.append("SL: ")
                    .append(item.getQuantity());

            sb.append("   Đơn giá: ")
                    .append(
                            moneyFormat.format(
                                    item.getPrice()
                            )
                    )
                    .append(" VND\n");

            sb.append("Thành tiền: ")
                    .append(
                            moneyFormat.format(
                                    item.getTotal()
                            )
                    )
                    .append(" VND\n");

            sb.append("----------------------------------------\n");
        }

        // =====================================
        // TOTAL
        // =====================================
        sb.append("\n");

        sb.append("TỔNG TIỀN: ")
                .append(
                        moneyFormat.format(
                                bill.getTotal()
                        )
                )
                .append(" VND\n");

        sb.append("\n");

        sb.append("========================================\n");

        sb.append("      CẢM ƠN QUÝ KHÁCH ❤️\n");

        sb.append("      HẸN GẶP LẠI LẦN SAU\n");

        sb.append("========================================\n");

        area.setText(sb.toString());

        // =====================================
        // SCROLL
        // =====================================
        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setBorder(null);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =====================================
        // BUTTON PANEL
        // =====================================
        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(Color.WHITE);

        JButton printBtn =
                new JButton("IN HÓA ĐƠN");

        JButton closeBtn =
                new JButton("ĐÓNG");

        styleButton(printBtn);

        styleButton(closeBtn);

        // =====================================
        // EVENTS
        // =====================================
        printBtn.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Đã gửi hóa đơn đến máy in!"
            );
        });

        closeBtn.addActionListener(e -> {

            dispose();
        });

        buttonPanel.add(printBtn);

        buttonPanel.add(closeBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    // =====================================
    // STYLE BUTTON
    // =====================================
    private void styleButton(JButton btn) {

        btn.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        btn.setFocusPainted(false);

        btn.setBackground(
                new Color(0, 140, 140)
        );

        btn.setForeground(Color.BLACK);

        btn.setPreferredSize(
                new Dimension(160, 45)
        );
    }
}