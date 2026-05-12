/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import repository.InventoryRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class InventoryPanel extends JPanel {

    private final InventoryRepository repo =
            new InventoryRepository();

    private JTable table;

    private DefaultTableModel model;

    public InventoryPanel() {

        setLayout(new BorderLayout());

        initUI();

        loadInventory();
    }

    // =====================================================
    // UI
    // =====================================================
    private void initUI() {

        setBackground(Color.WHITE);

        JLabel title = new JLabel(
                "KHO NGUYÊN LIỆU"
        );

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // =========================
        // TABLE
        // =========================
        String[] columns = {
            "STT",
            "Nguyên liệu",
            "Số lượng"
        };

        model = new DefaultTableModel(
                columns,
                0
        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        table = new JTable(model);

        table.setRowHeight(45);

        table.setFont(
                new Font("Segoe UI", Font.PLAIN, 18)
        );

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        // =========================
        // BUTTON
        // =========================
        JButton refreshBtn =
                new JButton("REFRESH");

        refreshBtn.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        refreshBtn.setBackground(
                new Color(0, 128, 128)
        );

        refreshBtn.setForeground(Color.WHITE);

        refreshBtn.setFocusPainted(false);

        refreshBtn.setPreferredSize(
                new Dimension(0, 55)
        );

        refreshBtn.addActionListener(
                e -> loadInventory()
        );

        add(title, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        add(refreshBtn, BorderLayout.SOUTH);
    }

    // =====================================================
    // LOAD INVENTORY
    // =====================================================
    private void loadInventory() {

        model.setRowCount(0);

        // LẤY MAP TỪ REPOSITORY
        Map<String, Integer> stock =
                repo.getStock();

        int stt = 1;

        for (String name : stock.keySet()) {

            model.addRow(new Object[]{

                stt++,

                name.toUpperCase(),

                stock.get(name) + " ml"

            });
        }
    }
}