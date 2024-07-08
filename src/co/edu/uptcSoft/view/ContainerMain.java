package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class ContainerMain extends JFrame {

    public ContainerMain() {
        setTitle("Lista De Ordenes");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window on open
        setLayout(new BorderLayout());

        // Menu
        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        initializeContentPanel();
        setVisible(true);
    }

    private void initializeContentPanel() {

        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(1286, 590));
        add(contentPanel, BorderLayout.CENTER);
    }
}
