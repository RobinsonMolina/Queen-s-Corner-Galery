package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.Border;
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
        setTitle("Order List");
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
        contentPanel = new JPanel(null);

        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // Título centrado
        titleLabel = new JLabel("TABLERO");
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
        contentPanel.add(contentTitle);

        // Datos de la tabla
        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega", ""};
        Object[][] data = {
                {"001", "SofaCama", "Juan David Pérez", "3001234567", "15/06/2024", ""},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"003", "Cojines", "Carlos Andrés Gómez", "323456789", "15/06/2024", ""},
                {"004", "Lampara", "Luisa Fernanda Fernández", "3109876543", "17/06/2024", ""},
                {"005", "Sofa", "Andrés Felipe López", "316879801", "17/06/2024", ""},
                {"006", "SofaCama", "Ana María Torres", "318678902", "20/06/2024", ""},
                {"007", "SofaCama", "Diego Alejandro Martínez", "317890123", "20/06/2024", ""},
                {"008", "SofaCama", "Laura Patricia Ramírez", "3198901234", "20/06/2024", ""},
                {"009", "Lampara", "Pedro José Jiménez", "3109021345", "25/06/2024", ""},
                {"010", "Mesa De Centro", "Sofía Margarita Morales", "3190173456", "25/06/2024", ""},
                {"011", "Cojines", "Jorge Luis Ríos", "3111234567", "25/06/2024", ""},
                {"012", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"013", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"014", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"015", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"016", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""},
                {"017", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(34);

        // Eliminar líneas del JScrollPane y la JTable
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Ajustar altura de la fila de encabezado (títulos)
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 34));
        header.setDefaultRenderer(new HeaderRenderer(table));

        // Establecer tamaños de las columnas
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50); // Primera columna
            } else if (i == 1) {
                column.setPreferredWidth(150);  // Segunda columna
            } else if (i == 2) {
                column.setPreferredWidth(150); // Tercera columna
            } else if (i == 3) {
                column.setPreferredWidth(100); // Cuarta columna
            } else if (i == 4) {
                column.setPreferredWidth(100); // Quinta columna
            } else if (i == 5) {
                column.setPreferredWidth(40); // Última columna
            }
        }

        // Añadir los iconos en la última columna a partir de la segunda fila
        table.getColumnModel().getColumn(5).setCellRenderer(new IconCellRenderer());

        // Colorear las filas y el encabezado
        table.setDefaultRenderer(Object.class, new TableCellRenderer());

        // JScrollPane que contendrá la tabla
        scrollPane.setBounds(50, 120, 1200, 408);
        contentPanel.add(scrollPane);

        //nuevo panel para boton agregar
        contentButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contentButton.setBounds(0, 550, 1286, 50);

        // Botón Agregar
        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(e -> System.out.println("Click"));
        contentButton.add(addButton);
        contentButton.setBounds(1010, 550, 100, 70);  // Posición x=800
        contentPanel.add(contentButton);


        add(contentPanel, BorderLayout.CENTER);
    }

    //nuevo clase para iconos en la última columna
    private class IconCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == 5) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                for (int i = 0; i < 3; i++) {
                    Image image;
                    switch (i) {
                        case 0:
                            image = new ImageIcon("src\\\\Utilities\\Images\\Admin.png").getImage();
                            break;
                        case 1:
                            image = new ImageIcon("src\\\\Utilities\\Images\\LogOut.png").getImage();
                            break;
                        case 2:
                            image = new ImageIcon("src\\\\Utilities\\Images\\Supplies.png").getImage();
                            break;
                        default:
                            image = new ImageIcon().getImage();
                    }
                    Image resizedImage = image.getScaledInstance(30, 34, Image.SCALE_SMOOTH);
                    JLabel icon = new JLabel(new ImageIcon(resizedImage));
                    panel.add(icon);
                }
                return panel;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    //nuevo clase para colorear las filas y el encabezado
    private class TableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column != 5) {
                if (row % 2 == 0) {
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.DARK_GRAY);
                    c.setForeground(Color.WHITE);
                }
            } else {
                c.setBackground(Color.LIGHT_GRAY);
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    }

    //nuevo clase para colorear la columna de iconos
    private class HeaderRenderer extends DefaultTableCellRenderer {
        public HeaderRenderer(JTable table) {
            setHorizontalAlignment(JLabel.CENTER);
            setForeground(Color.BLACK);
            setBackground(Color.GRAY);
            setFont(new Font("Serif", Font.BOLD, 15));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == 5) {
                c.setBackground(table.getTableHeader().getBackground()); // Cambio de color para la columna de iconos
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(Color.DARK_GRAY); // Color de fondo por defecto para las demás columnas
                c.setForeground(Color.WHITE);
            }
            return c;
        }
    }
}