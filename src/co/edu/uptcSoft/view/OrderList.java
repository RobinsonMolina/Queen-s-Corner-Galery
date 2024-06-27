package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class OrderList extends JFrame {

    JPanel contentPanel;
    JPanel contentTitle;
    JTextField searchField;
    JPanel contentButton;
    JLabel titleLabel;
    JTable table;
    JScrollPane scrollPane;

    public OrderList() {
        setTitle("Insumos");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Simulación de cabeceras personalizadas
        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        contentPanel();
        setVisible(true);
    }

    public void contentPanel() {
        contentPanel = new JPanel(new BorderLayout());

        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // Título centrado
        titleLabel = new JLabel("Lista De Ordenes");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setBounds(0, 0, 1286, 100);
        contentTitle.add(titleLabel);

        // Campo de búsqueda con bordes redondeados y icono alineado a la derecha
        searchField = new JTextField();
        searchField.setBounds(900, 45, 200, 45);
        searchField.setFont(new Font("Serif", Font.BOLD, 15));
        searchField.setForeground(Color.BLACK);
        searchField.setBackground(Color.WHITE);

        // Crear un borde redondeado para el campo de búsqueda
        Border roundedBorder = new Border() {
            int radius = 15;
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius, radius, radius, radius);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };

        searchField.setBorder(BorderFactory.createCompoundBorder(
                roundedBorder,
                BorderFactory.createEmptyBorder(0, 20, 0, 5)  // Espacio para el icono
        ));

        // Añadir un icono a la derecha del campo de búsqueda
        ImageIcon searchIcon = new ImageIcon("src\\Utilities\\Images\\NewOrder.png");
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel searchLabel = new JLabel(new ImageIcon(scaledSearchIcon));
        // Alinear el icono a la derecha dentro del campo de búsqueda
        searchLabel.setBounds(5, 12, 24, 24);
        searchField.add(searchLabel);

        contentTitle.add(searchField);

        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\AdminB.png");
        Image image = icon.getImage();
        ImageIcon defIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        // Datos de la tabla
        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega", "", "", ""};
        Object[][] data = {
                {"001", "SofaCama", "Juan David Pérez", "3001234567", "15/06/2024", defIcon, defIcon, defIcon},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"003", "Cojines", "Carlos Andrés Gómez", "323456789", "15/06/2024", defIcon, defIcon, defIcon},
                {"004", "Lampara", "Luisa Fernanda Fernández", "3109876543", "17/06/2024", defIcon, defIcon, defIcon},
                {"005", "Sofa", "Andrés Felipe López", "316879801", "17/06/2024", defIcon, defIcon, defIcon},
                {"006", "SofaCama", "Ana María Torres", "318678902", "20/06/2024", defIcon, defIcon, defIcon},
                {"007", "SofaCama", "Diego Alejandro Martínez", "317890123", "20/06/2024", defIcon, defIcon, defIcon},
                {"008", "SofaCama", "Laura Patricia Ramírez", "3198901234", "20/06/2024", defIcon, defIcon, defIcon},
                {"009", "Lampara", "Pedro José Jiménez", "3109021345", "25/06/2024", defIcon, defIcon, defIcon},
                {"010", "Mesa De Centro", "Sofía Margarita Morales", "3190173456", "25/06/2024", defIcon, defIcon, defIcon},
                {"011", "Cojines", "Jorge Luis Ríos", "3111234567", "25/06/2024", defIcon, defIcon, defIcon},
                {"012", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"013", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"014", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"015", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"016", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon},
                {"017", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", defIcon, defIcon, defIcon}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==5 || column == 6 || column == 7) {
                    return Icon.class;
                }
                return super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//...

// Configurar las columnas de la tabla
        JTable table = new JTable(model);
        table.setRowHeight(34);
        table.setShowGrid(false);
        table.getColumnModel().getColumn(0).setMaxWidth(150);// Esto ajusta el ancho máximo de la columna 0
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setMaxWidth(250);
        table.getColumnModel().getColumn(5).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMaxWidth(50);
        table.getColumnModel().getColumn(7).setMaxWidth(50);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));

        // Colorear el encabezado de la tabla
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Enfoca el texto
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                // Cambiar color del encabezado de la columna
                if (column == 5 || column == 6 || column == 7) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(header.getBackground());
                }

                return c;
            }
        });

        // Colorear las filas de la tabla
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Enfoca el texto
                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                // Cambiar el color de las filas
                if (row % 2 == 0) {
                    cell.setBackground(Color.white);
                } else {
                    cell.setBackground(Color.decode("#D9D9D9"));
                }

                return cell;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.setBorder(new EmptyBorder(10, 40, 0, 40));

        jPanel.setPreferredSize(new Dimension(1366, 136));
        jPanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);
        contentPanel.add(jPanel, BorderLayout.CENTER);

        // Botón redondeado
        contentButton = new JPanel();
        contentButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 940, 25, 0));

        RoundedButton createButton = new RoundedButton("Agregar");
        createButton.setPreferredSize(new Dimension(150, 34));
        contentButton.add(createButton);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);

    }

    // Clase para el botón redondeado
    public class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setFont(new Font("Serif", Font.BOLD, 20));
            setForeground(Color.WHITE);
            setBackground(Color.decode("#2F1940"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }
}