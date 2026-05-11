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
import java.awt.*;

public class MenuItemButton extends JButton {

    public MenuItemButton(String name, int price) {

        setText(
            "<html><center>"
            + name
            + "<br>"
            + price + " VND"
            + "</center></html>"
        );

        setFont(new Font("Arial", Font.BOLD, 28));

        setFocusPainted(false);

        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(260, 180));
    }
}
