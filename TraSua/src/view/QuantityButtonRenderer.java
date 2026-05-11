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
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class QuantityButtonRenderer extends JButton implements TableCellRenderer {

    public QuantityButtonRenderer() {
        setFocusPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        setText(value.toString());

        return this;
    }
}
