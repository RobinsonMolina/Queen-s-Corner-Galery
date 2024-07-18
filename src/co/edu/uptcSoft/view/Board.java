package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JFrame {
    private Components components;
    private JPanel contentPanel;
    private JPanel cardsPanel;
    private JButton orderButton;
    private JScrollPane cardScrollPanes;
    private JPanel mainContentPanel;
    private Logic logic = Logic.getInstance();

    public Board(JPanel mainContentPanel) {
        this.mainContentPanel = mainContentPanel;
        components = new Components(mainContentPanel);
    }

    public JPanel contentPanel() {
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
        addCard("Por Hacer", 0, logic.getOrdersDo());
        addCard("En Progreso", 1, logic.getOrdersProgress());
        addCard("Entregado", 2, logic.getOrdersDelivered());

        contentPanel.add(cardsPanel);
        add(contentPanel, BorderLayout.CENTER);
        return contentPanel;
    }

    private void addCard(String title, int position, ArrayList<String> orders) {
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
            changeWindow(orderButton);// permited to go to the specific order
            components.hoverButtonBoard(orderButton);

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

    public void changeWindow(JButton orderButton){
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // change the content of the main panel instead of opening a new window
                mainContentPanel.removeAll();
                mainContentPanel.add(new SpecificOrder(mainContentPanel).addSpecificOrder(1));
                mainContentPanel.revalidate();
                mainContentPanel.repaint();
            }
        });
    }
}

