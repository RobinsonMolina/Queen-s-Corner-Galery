package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {
    private Components components;
    private JPanel contentPanel;
    private JPanel cardsPanel;
    private JButton orderButton;
    private JScrollPane cardScrollPanes;

    public Board() {
        setTitle("Board");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        components = new Components();

        contentPanel();
        setVisible(true);
    }

    public void contentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(1286, 590));

        // Title Center
        JLabel titleLabel = new JLabel("Tablero");
        titleLabel.setFont(components.createFont(0, 50));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Cards Panel
        cardsPanel = new JPanel();
        cardsPanel.setLayout(null);
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setPreferredSize(new Dimension(1286, 550));


        // Adding Cards
        addCard("Por Hacer", 0, new String[]{"Orden No. 33 Sofácama REF 006", "Orden No. 34 Mesa De Centro REF 002", "Orden No. 35 Cojines REF 003", "Orden No. 36 Sofácama REF 006", "Orden No. 37 Sofá REF 005", "Orden No. 38 Cojines REF 003", "Orden No. 39 Mesa De Centro REF 002", "Orden No. 40 Sofácama REF 006", "Orden No. 41 Sofá REF 005", "Orden No. 42 Cojines REF 003", "Orden No. 43 Sofácama REF 006", "Orden No. 44 Mesa De Centro REF 002", "Orden No. 45 Lámpara REF 004", "Orden No. 46 Sofá REF 005", "Orden No. 47 Cojines REF 003", "Orden No. 48 Sofácama REF 006"});
        addCard("En Progreso", 1, new String[]{"Orden No. 17 Sofá REF 005", "Orden No. 18 Mesa De Centro REF 002", "Orden No. 19 Cojines REF 003", "Orden No. 20 Sofácama REF 006", "Orden No. 21 Sofá REF 005", "Orden No. 22 Mesa De Centro REF 002", "Orden No. 23 Cojines REF 003", "Orden No. 24 Sofácama REF 006", "Orden No. 25 Sofá REF 005", "Orden No. 26 Mesa De Centro REF 002", "Orden No. 27 Cojines REF 003", "Orden No. 28 Sofácama REF 006", "Orden No. 29 Lámpara REF 004", "Orden No. 30 Sofá REF 005", "Orden No. 31 Cojines REF 003", "Orden No. 32 Sofácama REF 006"});
        addCard("Entregado", 2, new String[]{"Orden No. 1 Sofácama REF 006", "Orden No. 2 Mesa De Centro REF 002", "Orden No. 3 Cojines REF 003", "Orden No. 4 Sofácama REF 006", "Orden No. 5 Sofá REF 005", "Orden No. 6 Cojines REF 003", "Orden No. 7 Mesa De Centro REF 002", "Orden No. 8 Sofácama REF 006", "Orden No. 9 Sofá REF 005", "Orden No. 10 Cojines REF 003", "Orden No. 11 Sofácama REF 006", "Orden No. 12 Mesa De Centro REF 002", "Orden No. 13 Lámpara REF 004", "Orden No. 14 Sofá REF 005", "Orden No. 15 Cojines REF 003", "Orden No. 16 Sofácama REF 006"});

        contentPanel.add(cardsPanel);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addCard(String title, int position, String[] orders) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.decode("#D9D9D9")); // Color for the background
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Adjust corner radius
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Adjust corner radius
            }
        };

        card.setLayout(new BorderLayout());
        card.setBounds(50 + (position * 400), 50, 380, 420);
        card.setPreferredSize(new Dimension(380, 420));
        //card.setBackground(Color.decode("#D9D9D9"));
        card.setBackground(Color.WHITE);

        JLabel cardTitle = new JLabel(title);
        cardTitle.setBackground(Color.decode("#D9D9D9"));
        cardTitle.setFont(components.createFont(0, 40));
        cardTitle.setHorizontalAlignment(JLabel.CENTER);
        card.add(cardTitle, BorderLayout.NORTH);

        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(Color.decode("#D9D9D9")); // Color of the background of the panel
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        for (String order : orders) {
            orderButton = components.createRoundedButton(order, "#2F1940", "#FAF6F6", 10, 10);
            orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderButton.setFont(components.createFont(1, 20));
            orderButton.setForeground(Color.BLACK);
            orderButton.setPreferredSize(new Dimension(350, 30));
            orderButton.setMaximumSize(new Dimension(350, 30));
            orderButton.setMinimumSize(new Dimension(350, 30));
            orderButton.addActionListener(new OrderButtonListener()); // Agregar ActionListener al botón
            orderPanel.add(Box.createVerticalStrut(8)); // add vertical space between buttons
            orderPanel.add(orderButton);
        }

        cardScrollPanes = new JScrollPane(orderPanel);
        cardScrollPanes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardScrollPanes.setPreferredSize(new Dimension(360, 100));
        cardScrollPanes.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        card.add(cardScrollPanes);

        cardsPanel.add(card);
    }

    // Listener for the buttons
    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String orderNumber = button.getText();
            SpecificOrder specificOrder = new SpecificOrder();
            specificOrder.createWindow();
        }
    }
}

