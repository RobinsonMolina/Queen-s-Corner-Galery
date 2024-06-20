package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    private JPanel contentPanel;
    private JPanel cardsPanel;
    private JScrollPane cardScrollPanes;

    public Board() {
        setTitle("Queen's Corner Gallery");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        contentPanel();
        setVisible(true);
    }

    public void contentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1286, 590));

        // Title Center
        JLabel titleLabel = new JLabel("BOARD");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Cards Panel
        cardsPanel = new JPanel();
        cardsPanel.setLayout(null);
        cardsPanel.setPreferredSize(new Dimension(1286, 600));

        // Adding Cards
        addCard("Por Hacer", 0, new String[]{"Orden No. 33 Sofácama REF 006", "Orden No. 34 Mesa De Centro REF 002", "Orden No. 35 Cojines REF 003", "Orden No. 36 Sofácama REF 006", "Orden No. 37 Sofá REF 005", "Orden No. 38 Cojines REF 003", "Orden No. 39 Mesa De Centro REF 002", "Orden No. 40 Sofácama REF 006", "Orden No. 41 Sofá REF 005", "Orden No. 42 Cojines REF 003", "Orden No. 43 Sofácama REF 006", "Orden No. 44 Mesa De Centro REF 002", "Orden No. 45 Lámpara REF 004", "Orden No. 46 Sofá REF 005", "Orden No. 47 Cojines REF 003", "Orden No. 48 Sofácama REF 006"});
        addCard("En Progreso", 1, new String[]{"Orden No. 17 Sofá REF 005", "Orden No. 18 Mesa De Centro REF 002", "Orden No. 19 Cojines REF 003", "Orden No. 20 Sofácama REF 006", "Orden No. 21 Sofá REF 005", "Orden No. 22 Mesa De Centro REF 002", "Orden No. 23 Cojines REF 003", "Orden No. 24 Sofácama REF 006", "Orden No. 25 Sofá REF 005", "Orden No. 26 Mesa De Centro REF 002", "Orden No. 27 Cojines REF 003", "Orden No. 28 Sofácama REF 006", "Orden No. 29 Lámpara REF 004", "Orden No. 30 Sofá REF 005", "Orden No. 31 Cojines REF 003", "Orden No. 32 Sofácama REF 006"});
        addCard("Entregado", 2, new String[]{"Orden No. 1 Sofácama REF 006", "Orden No. 2 Mesa De Centro REF 002", "Orden No. 3 Cojines REF 003", "Orden No. 4 Sofácama REF 006", "Orden No. 5 Sofá REF 005", "Orden No. 6 Cojines REF 003", "Orden No. 7 Mesa De Centro REF 002", "Orden No. 8 Sofácama REF 006", "Orden No. 9 Sofá REF 005", "Orden No. 10 Cojines REF 003", "Orden No. 11 Sofácama REF 006", "Orden No. 12 Mesa De Centro REF 002", "Orden No. 13 Lámpara REF 004", "Orden No. 14 Sofá REF 005", "Orden No. 15 Cojines REF 003", "Orden No. 16 Sofácama REF 006"});

        contentPanel.add(cardsPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addCard(String title, int position, String[] orders) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBounds(30 + (position * 400), 50, 380, 420);
        card.setBackground(Color.LIGHT_GRAY);

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Serif", Font.BOLD, 40));
        cardTitle.setHorizontalAlignment(JLabel.CENTER);
        card.add(cardTitle, BorderLayout.NORTH);

        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(Color.LIGHT_GRAY);
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        for (String order : orders) {
            JButton orderButton = new JButton(order);
            orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderButton.setPreferredSize(new Dimension(350, 30));
            orderButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            orderPanel.add(Box.createVerticalStrut(8));
            orderPanel.add(orderButton);
        }

        cardScrollPanes = new JScrollPane(orderPanel);
        cardScrollPanes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardScrollPanes.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        card.add(cardScrollPanes);

        cardsPanel.add(card);
    }
}

