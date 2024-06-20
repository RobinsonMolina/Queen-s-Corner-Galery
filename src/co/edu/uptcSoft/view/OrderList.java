package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
public class OrderList extends JFrame {
    public OrderList() {
        setTitle("Another Window");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        // Add your content here
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("This is another window"));
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
