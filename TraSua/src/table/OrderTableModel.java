/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

/**
 *
 * @author Minhphat
 */
import model.OrderItem;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrderTableModel extends AbstractTableModel {

    private final String[] columns = {
        "STT", "Tên món", "Giá", "-", "SL", "+", "Tổng"
    };

    private final ArrayList<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
        fireTableDataChanged();
    }

    public OrderItem getItem(int row) {
        return items.get(row);
    }

    public void removeItem(int row) {
        items.remove(row);
        fireTableDataChanged();
    }

    public void clear() {
        items.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        OrderItem item = items.get(row);

        switch (col) {

            case 0:
                return row + 1;

            case 1:
                return item.getName();

            case 2:
                return item.getPrice() + " VND";

            case 3:
                return "-";

            case 4:
                return item.getQuantity();

            case 5:
                return "+";

            case 6:
                return item.getTotal() + " VND";
        }

        return "";
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 3 || col == 5;
    }
}
