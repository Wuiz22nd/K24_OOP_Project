/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Minhphat
 */
import model.OrderItem;
import table.OrderTableModel;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class QuantityButtonEditor extends DefaultCellEditor {

    private JButton button;
    private JTable table;
    private OrderTableModel model;
    private String type;

    public QuantityButtonEditor(
            JCheckBox checkBox,
            JTable table,
            OrderTableModel model,
            String type) {

        super(checkBox);

        this.table = table;
        this.model = model;
        this.type = type;

        button = new JButton();
        button.setFocusPainted(false);

        button.addActionListener(e -> {

            int row = table.getSelectedRow();

            OrderItem item = model.getItem(row);

            if (type.equals("+")) {
                item.increaseQuantity();
            } else {
                item.decreaseQuantity();
            }

            model.fireTableDataChanged();

            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column) {

        button.setText(value.toString());

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }
}
