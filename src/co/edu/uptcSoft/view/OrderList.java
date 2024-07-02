package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class OrderList extends JFrame {

    private Components components;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel contentButton;
    private JLabel titleLabel;
    private JTextField searchTextField;
    private JButton buttonAdd;
    private Font font;

    public OrderList() {
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

        components = new Components();
        initializeContentPanel();
        setVisible(true);
    }

    // Method for initializing content panel
    private void initializeContentPanel() {
        contentPanel = new JPanel(new BorderLayout());

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1058, 25, 0));

        // Button add
        buttonAdd = components.createRoundedButton("Agregar");
        buttonAdd.setPreferredSize(new Dimension(150, 34));
        contentButton.add(buttonAdd);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
    }

    // Method for creating title
    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // title label
        titleLabel = new JLabel("Lista De Ordenes", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        // Rounded search field
        searchTextField = components.createRoundedTextField(30,30);
        searchTextField.setBounds(888, 45, 200, 45);
        searchTextField.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 15));
        searchTextField.setFont(components.createFont(1, 20));

        // Add icon to the right of the search field
        ImageIcon searchIcon = new ImageIcon("src\\Utilities\\Images\\Glass.png");
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel searchLabel = new JLabel(new ImageIcon(scaledSearchIcon));
        searchLabel.setBounds(5, 12, 24, 24);
        searchTextField.add(searchLabel);

        titleLabel.add(searchTextField);
        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);
    }

    // Method for initializing table
    private void initializeTable() {

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Eye.png");
        Image image = icon.getImage();
        ImageIcon eyeIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon2 = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image2 = icon2.getImage();
        ImageIcon pencilIcon = new ImageIcon(image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image3 = icon3.getImage();
        ImageIcon trashIcon = new ImageIcon(image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        // Data of the table
        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega", "", "", ""};
        Object[][] data = {
                {"001", "SofaCama", "Juan David Pérez", "3001234567", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"003", "Cojines", "Carlos Andrés Gómez", "323456789", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"004", "Lampara", "Luisa Fernanda Fernández", "3109876543", "17/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"005", "Sofa", "Andrés Felipe López", "316879801", "17/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"006", "SofaCama", "Ana María Torres", "318678902", "20/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"007", "SofaCama", "Diego Alejandro Martínez", "317890123", "20/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"008", "SofaCama", "Laura Patricia Ramírez", "3198901234", "20/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"009", "Lampara", "Pedro José Jiménez", "3109021345", "25/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"010", "Mesa De Centro", "Sofía Margarita Morales", "3190173456", "25/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"011", "Cojines", "Jorge Luis Ríos", "3111234567", "25/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"012", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"013", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"014", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"015", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"016", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon},
                {"017", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024", eyeIcon, pencilIcon, trashIcon}
        };


        // Table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // Method for setting the column class
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 5 || column == 6 || column == 7) ? Icon.class : super.getColumnClass(column);
            }

            // Method for checking if the cell is editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Table
        JTable table = new JTable(model);
        table.setFont(components.createFont(1, 20));
        table.setForeground(Color.decode("#2F2F2F"));
        table.setRowHeight(34);
        table.setShowGrid(false);
        setColumnWidths(table);

        // Table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));
        header.setFont(components.createFont(0, 20));

        // Move configuration of renderer after setting font
        header.setDefaultRenderer(createHeaderRenderer(header.getFont()));
        table.setDefaultRenderer(Object.class, createTableRowRenderer());

        // Scroll pane of table
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        // Panel of table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 80, 0, 60));
        tablePanel.setPreferredSize(new Dimension(1366, 136));
        tablePanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    // Method for setting column widths
    private void setColumnWidths(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(150);// Esto ajusta el ancho máximo de la columna 0
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
    }

    // Method for creating header renderer
    private DefaultTableCellRenderer createHeaderRenderer(Font headerFont) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Focuses the text
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setFont(headerFont);// set font
                    label.setForeground(Color.BLACK); // set color of text
                }

                // Change column header color
                if (column == 5 || column == 6 || column == 7) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(table.getTableHeader().getBackground());
                }
                return c;
            }
        };
    }

    // Method for creating table row renderer
    private DefaultTableCellRenderer createTableRowRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Focuses the text
                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                // Change the color of the rows
                if (row % 2 == 0) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.decode("#D9D9D9"));
                }
                return cell;
            }
        };
    }
}
