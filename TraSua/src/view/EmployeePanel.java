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
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeePanel extends JPanel {

    // =====================================
    // TABLE
    // =====================================
    private JTable table;

    private DefaultTableModel tableModel;

    // =====================================
    // INPUT
    // =====================================
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtRole;
    private JTextField txtSalary;

    // =====================================
    // BUTTON
    // =====================================
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    public EmployeePanel() {

        initUI();
    }

    // =====================================
    // UI
    // =====================================
    private void initUI() {

        setLayout(new BorderLayout(15, 15));

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================
        // TITLE
        // =====================================
        JLabel title =
                new JLabel(
                        "QUẢN LÝ NHÂN VIÊN",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        add(title, BorderLayout.NORTH);

        // =====================================
        // FORM PANEL
        // =====================================
        JPanel formPanel =
                new JPanel(
                        new GridLayout(4, 2, 10, 10)
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Thông tin nhân viên"
                )
        );

        JLabel lblId =
                new JLabel("Mã nhân viên:");

        JLabel lblName =
                new JLabel("Tên nhân viên:");

        JLabel lblRole =
                new JLabel("Chức vụ:");

        JLabel lblSalary =
                new JLabel("Lương:");

        txtId = new JTextField();

        txtName = new JTextField();

        txtRole = new JTextField();

        txtSalary = new JTextField();

        formPanel.add(lblId);
        formPanel.add(txtId);

        formPanel.add(lblName);
        formPanel.add(txtName);

        formPanel.add(lblRole);
        formPanel.add(txtRole);

        formPanel.add(lblSalary);
        formPanel.add(txtSalary);

        add(formPanel, BorderLayout.WEST);

        // =====================================
        // TABLE
        // =====================================
        String[] columns = {
                "Mã NV",
                "Tên nhân viên",
                "Chức vụ",
                "Lương"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        table.setRowHeight(35);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // =====================================
        // BUTTON PANEL
        // =====================================
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(1, 4, 10, 10)
                );

        btnAdd = new JButton("THÊM");

        btnUpdate = new JButton("SỬA");

        btnDelete = new JButton("XÓA");

        btnClear = new JButton("CLEAR");

        styleButton(btnAdd);
        styleButton(btnUpdate);
        styleButton(btnDelete);
        styleButton(btnClear);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);

        // =====================================
        // EVENTS
        // =====================================

        // =====================================
        // ADD
        // =====================================
        btnAdd.addActionListener(e -> {

            String id =
                    txtId.getText().trim();

            String name =
                    txtName.getText().trim();

            String role =
                    txtRole.getText().trim();

            String salaryText =
                    txtSalary.getText().trim();

            // CHECK EMPTY
            if (id.isEmpty()
                    || name.isEmpty()
                    || role.isEmpty()
                    || salaryText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập đầy đủ!"
                );

                return;
            }

            // CHECK NUMBER
            double salary;

            try {

                salary =
                        Double.parseDouble(
                                salaryText
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Lương phải là số!"
                );

                return;
            }

            // ADD ROW
            tableModel.addRow(new Object[]{

                    id,
                    name,
                    role,
                    salary
            });

            JOptionPane.showMessageDialog(
                    this,
                    "Thêm nhân viên thành công!"
            );

            clearForm();
        });

        // =====================================
        // TABLE CLICK
        // =====================================
        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            table.getSelectedRow();

                    if (row >= 0) {

                        txtId.setText(
                                tableModel.getValueAt(
                                        row,
                                        0
                                ).toString()
                        );

                        txtName.setText(
                                tableModel.getValueAt(
                                        row,
                                        1
                                ).toString()
                        );

                        txtRole.setText(
                                tableModel.getValueAt(
                                        row,
                                        2
                                ).toString()
                        );

                        txtSalary.setText(
                                tableModel.getValueAt(
                                        row,
                                        3
                                ).toString()
                        );
                    }
                });

        // =====================================
        // UPDATE
        // =====================================
        btnUpdate.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn nhân viên!"
                );

                return;
            }

            String salaryText =
                    txtSalary.getText().trim();

            double salary;

            try {

                salary =
                        Double.parseDouble(
                                salaryText
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Lương không hợp lệ!"
                );

                return;
            }

            tableModel.setValueAt(
                    txtId.getText(),
                    row,
                    0
            );

            tableModel.setValueAt(
                    txtName.getText(),
                    row,
                    1
            );

            tableModel.setValueAt(
                    txtRole.getText(),
                    row,
                    2
            );

            tableModel.setValueAt(
                    salary,
                    row,
                    3
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cập nhật thành công!"
            );

            clearForm();
        });

        // =====================================
        // DELETE
        // =====================================
        btnDelete.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng chọn nhân viên!"
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Bạn có chắc muốn xóa?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm == JOptionPane.YES_OPTION) {

                tableModel.removeRow(row);

                JOptionPane.showMessageDialog(
                        this,
                        "Xóa thành công!"
                );

                clearForm();
            }
        });

        // =====================================
        // CLEAR
        // =====================================
        btnClear.addActionListener(e -> {

            clearForm();

            table.clearSelection();
        });
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
    }

    // =====================================
    // CLEAR FORM
    // =====================================
    private void clearForm() {

        txtId.setText("");

        txtName.setText("");

        txtRole.setText("");

        txtSalary.setText("");
    }
}