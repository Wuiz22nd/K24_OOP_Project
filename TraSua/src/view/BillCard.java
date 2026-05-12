package view;

import model.Bill;

import javax.swing.*;
import java.awt.*;

public class BillCard extends JPanel {

    public BillCard(Bill bill) {

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("Mã: " + bill.getBillId());

        idLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel totalLabel = new JLabel(
                "Tổng tiền: "
                        + bill.getTotal()
                        + " VND"
        );

        totalLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        add(idLabel, BorderLayout.NORTH);
        add(totalLabel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(250, 80));
    }
}