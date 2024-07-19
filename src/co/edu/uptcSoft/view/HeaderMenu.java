package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class HeaderMenu extends JFrame {
    private JPanel menuPanel;
    private JPanel menuPanel2;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private static HeaderMenu instance; // Single instance of HeaderMenu

    public HeaderMenu() {
        setTitle("Board");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        add(getHeaderPanel(), BorderLayout.NORTH);
        add(getMenuPanel(), BorderLayout.WEST);
        contentPanel = new JPanel();
        setVisible(true);
    }

    // Static method to get the single instance of HeaderMenu
    public static HeaderMenu getInstance() {
        if (instance == null) {
            instance = new HeaderMenu();
        }
        return instance;
    }

    public JPanel getHeaderPanel() {
        if (headerPanel == null) {
            headerPanel = new JPanel();
            headerPanel.setPreferredSize(new Dimension(1366, 80));
            headerPanel.setBackground(new Color(47, 25, 64));

            // Title
            JLabel headerLabel = new JLabel("QUEEN'S CORNER GALLERY");
            try {
                headerLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\CinzelDecorative-Regular.ttf")).deriveFont(Font.PLAIN, 60));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            headerLabel.setForeground(Color.decode("#E1B958"));
            headerLabel.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
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
                    getMenuPanel2().setBounds(0, 0, 235, 750);
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
            menuPanel2.setPreferredSize(new Dimension(235, 750));
            menuPanel2.setLayout(new BoxLayout(menuPanel2, BoxLayout.Y_AXIS));
            menuPanel2.setBackground(new Color(47, 25, 64));

            // Rutas de las imágenes y nombres de las opciones
            String[][] menuItems = {
                    {"src\\Utilities\\Images\\Board.png", "Tablero"},
                    {"src\\Utilities\\Images\\OrderList.png", "Lista"},
                    {"src\\Utilities\\Images\\NewOrder.png", "Nueva Orden"},
                    {"src\\Utilities\\Images\\Customers.png", "Clientes"},
                    {"src\\Utilities\\Images\\Supplies.png", "Insumos"},
                    {"src\\Utilities\\Images\\Admin.png", "Administrador"},
                    {"src\\Utilities\\Images\\LogOut.png", "Salir"}
            };

            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(47, 25, 64));
            itemPanel.add(Box.createVerticalStrut(80));

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
                        //JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuPanel2);
                        //frame.dispose(); // Cerrar la ventana actual
                        if (item[1].equals("Tablero")) {
                            paneles(new Board(contentPanel).contentPanel());
                        }else if (item[1].equals("Lista")) {
                            paneles(new OrderList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Nueva Orden")) {
                            paneles(new NewOrder(contentPanel).addSpecificOrder(1));
                        }else if (item[1].equals("Clientes")) {
                            paneles(new CustomerList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Insumos")) {
                            paneles(new SupplyList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Administrador")) {
                            paneles(new Admin(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Salir")) {
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuPanel2);
                            frame.dispose(); // Cerrar la ventana actual
                            Login login = new Login();
                            login.createWindow();
                        }
                    }
                });
            }

            menuPanel2.add(Box.createVerticalStrut(80));
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

    public JPanel getContentPanel() {
        contentPanel = new JPanel();
        return contentPanel;
    }

    public void paneles(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(panel);
        add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}