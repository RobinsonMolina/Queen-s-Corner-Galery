package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderMenu {
    private JPanel menuPanel;
    private JPanel menuPanel2;
    private JPanel headerPanel;

    public JPanel getHeaderPanel() {
        if (headerPanel == null) {
            headerPanel = new JPanel();
            headerPanel.setPreferredSize(new Dimension(1366, 80));
            headerPanel.setBackground(new Color(47, 25, 64));

            // Title
            JLabel headerLabel = new JLabel("QUEEN'S CORNER GALLERY");
            headerLabel.setForeground(Color.WHITE);
            headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
            headerPanel.add(headerLabel);
        }
        return headerPanel;
    }

    public JPanel getMenuPanel() {
        if (menuPanel == null) {
            menuPanel = new JPanel();
            menuPanel.setPreferredSize(new Dimension(80, 590));
            menuPanel.setBackground(new Color(47, 25, 64));
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

            // Rutas de las imágenes
            String[] imagePaths = {
                    "src\\Utilities\\Images\\Board.png",
                    "src\\Utilities\\Images\\OrderList.png",
                    "src\\Utilities\\Images\\NewOrder.png",
                    "src\\Utilities\\Images\\Customers.png",
                    "src\\Utilities\\Images\\Supplies.png",
                    "src\\Utilities\\Images\\Admin.png",
                    "src\\Utilities\\Images\\LogOut.png"
            };

            // Crear y agregar JLabels con iconos redimensionados
            for (String imagePath : imagePaths) {
                Image image = new ImageIcon(imagePath).getImage();
                Image resizedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

                JLabel iconLabel = new JLabel(new ImageIcon(resizedImage));
                iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                iconLabel.setPreferredSize(new Dimension(40, 40));

                // Agregar un espacio vertical de 10 píxeles entre los íconos
                menuPanel.add(Box.createVerticalStrut(30));
                menuPanel.add(iconLabel);
            }

            // Añadir evento para mostrar menuPanel2 al pasar el mouse
            menuPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLayeredPane layeredPane = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, menuPanel);
                    layeredPane.add(getMenuPanel2(), JLayeredPane.PALETTE_LAYER);
                    getMenuPanel2().setBounds(0, 0, 235, 700);
                    layeredPane.revalidate();
                    layeredPane.repaint();
                }
            });
        }
        return menuPanel;
    }

    public JPanel getMenuPanel2() {
        if (menuPanel2 == null) {
            menuPanel2 = new JPanel();
            menuPanel2.setPreferredSize(new Dimension(235, 670));
            menuPanel2.setBackground(new Color(30, 30, 30));
            menuPanel2.setLayout(new BoxLayout(menuPanel2, BoxLayout.Y_AXIS));

            // Rutas de las imágenes y nombres de las opciones
            String[][] menuItems = {
                    {"src\\Utilities\\Images\\Board.png", "Board"},
                    {"src\\Utilities\\Images\\OrderList.png", "Order List"},
                    {"src\\Utilities\\Images\\NewOrder.png", "New Order"},
                    {"src\\Utilities\\Images\\Customers.png", "Customers"},
                    {"src\\Utilities\\Images\\Supplies.png", "Supplies"},
                    {"src\\Utilities\\Images\\Admin.png", "Admin"},
                    {"src\\Utilities\\Images\\LogOut.png", "Log Out"}
            };

            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(47, 25, 64));

            // Crear y agregar botones con iconos redimensionados y nombres
            for (String[] item : menuItems) {
                Image image = new ImageIcon(item[0]).getImage();
                Image resizedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

                JPanel panel = new JPanel(new GridLayout(1, 2, 0, 0));
                panel.setBackground(new Color(47, 25, 64));
                JLabel iconLabel = new JLabel(new ImageIcon(resizedImage));
                JLabel textLabel = new JLabel(item[1]);
                textLabel.setForeground(Color.WHITE);
                panel.add(iconLabel);
                panel.add(textLabel);

                JButton button = new JButton();
                button.add(panel);
                button.setHorizontalTextPosition(SwingConstants.RIGHT);
                button.setPreferredSize(new Dimension(210, 66));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Serif", Font.BOLD, 20));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setBackground(new Color(47, 25, 64));
                button.setBorderPainted(false);
                button.setFocusPainted(true);

                itemPanel.add(button, BorderLayout.WEST);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuPanel2);
                        frame.dispose(); // Cerrar la ventana actual
                        if (item[1].equals("Board")) {
                            Board board = new Board();
                        }else if (item[1].equals("Order List")) {
                            OrderList orderList = new OrderList();
                        }else if (item[1].equals("New Order")) {
                            //newOrder();
                        }else if (item[1].equals("Customers")) {
                            //customers();
                        }else if (item[1].equals("Supplies")) {
                            //supplies();
                        }else if (item[1].equals("Admin")) {
                            //admin();
                        }else if (item[1].equals("Log Out")) {
                            //logOut();
                        }
                        System.out.println(item[1] + " button clicked");
                    }
                });
            }

            menuPanel2.add(itemPanel, BorderLayout.NORTH);

            // Añadir evento para volver a mostrar menuPanel al salir el mouse de menuPanel2
            menuPanel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    Point p = e.getPoint();
                    if (p.x < 0 || p.y < 0 || p.x >= menuPanel2.getWidth() || p.y >= menuPanel2.getHeight()) {
                        JLayeredPane layeredPane = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, menuPanel2);
                        layeredPane.remove(menuPanel2);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                    }
                }
            });
        }
        return menuPanel2;
    }
}