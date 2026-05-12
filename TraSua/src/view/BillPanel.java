package view;

import model.Bill;
import model.BillStorage;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class BillPanel extends JPanel {

    private JTextField searchField;

    private JPanel listPanel;

    private BillDetailPanel detailPanel;

    private java.util.List<Bill> allBills;

    public BillPanel() {

        setLayout(new BorderLayout());

        setBackground(new Color(0, 128, 128));

        initUI();

        loadBills();
    }
    // =====================================================
    // UI
    // =====================================================
    private void initUI() {

        // =========================
        // TOP SEARCH
        // =========================
        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel title = new JLabel("QUẢN LÝ HÓA ĐƠN");

        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        title.setForeground(Color.WHITE);

        searchField = new JTextField();

        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        searchField.setPreferredSize(new Dimension(300, 45));

        searchField.addActionListener(e -> searchBills());

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));

        searchPanel.add(new JLabel("Tìm mã hóa đơn:"), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // =========================
        // CENTER
        // =========================
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15)
        );

        centerPanel.setBackground(new Color(0, 128, 128));

        // LEFT LIST
        listPanel = new JPanel();

        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        listPanel.setBackground(new Color(0, 128, 128));

        JScrollPane scrollPane = new JScrollPane(listPanel);

        scrollPane.setPreferredSize(new Dimension(550, 0));

        // RIGHT DETAIL
        detailPanel = new BillDetailPanel();

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(detailPanel, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);
    }

    // =====================================================
    // LOAD BILL
    // =====================================================
    private void loadBills() {

        allBills = BillStorage.getBills();

        renderBills(allBills);
    }

    // =====================================================
    // SEARCH
    // =====================================================
    private void searchBills() {

        String keyword = searchField.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {

            renderBills(allBills);

            return;
        }

        List<Bill> filtered = allBills.stream()
                .filter(b -> b.getBillId().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        renderBills(filtered);
    }

    // =====================================================
    // RENDER BILLS
    // =====================================================
    private void renderBills(List<Bill> bills) {

        listPanel.removeAll();

        for (Bill bill : bills) {

            BillCard card = new BillCard(bill);

            card.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {

                    detailPanel.showBill(bill);
                }
            });

            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(10));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }
}