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
import java.awt.*;

public class ReceiptFrame extends JFrame {

    public ReceiptFrame(Bill bill) {

        setTitle("Hóa đơn");

        setSize(420, 700);

        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();

        area.setFont(new Font("Monospaced", Font.PLAIN, 16));

        area.setEditable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("      BUBBLE TEA POS\n");
        sb.append("    PHIEU THANH TOAN\n\n");

        sb.append("Ma HD: ")
                .append(bill.getBillId())
                .append("\n\n");

        sb.append("----------------------------------\n");

        for (BillItem item : bill.getItems()) {

            sb.append(item.getName())
                    .append("\n");

            sb.append("SL: ")
                    .append(item.getQuantity())
                    .append("   ");

            sb.append(item.getPrice())
                    .append(" VND");

            sb.append("\n");

            sb.append("Thanh tien: ")
                    .append(item.getTotal())
                    .append(" VND\n");

            sb.append("----------------------------------\n");
        }

        sb.append("\nTong tien: ")
                .append(bill.getTotal())
                .append(" VND\n");

        sb.append("\nCam on quy khach!");

        area.setText(sb.toString());

        add(new JScrollPane(area));

        setVisible(true);
    }
}
