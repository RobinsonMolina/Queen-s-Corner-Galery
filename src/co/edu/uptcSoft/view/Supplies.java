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

public class Supplies extends JFrame {

    private Components components;
    private JPanel contentButton;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JLabel titleLabel;
    private JTextField searchTextField;
    private JButton buttonAdd;
    private Font font;

    public Supplies(){
        setTitle("Insumos");
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
        contentButton.setBorder(new EmptyBorder(23, 1055, 25, 0));

        buttonAdd = components.createRoundedButton("Agregar");
        buttonAdd.setPreferredSize(new Dimension(150, 34));
        contentButton.add(buttonAdd);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
    }

    // Method for creating title and search field
    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // title label
        titleLabel = new JLabel("Insumos", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        // Rounded search field
        searchTextField = components.createRoundedTextField(30,30);
        searchTextField.setBounds(928, 45, 200, 45);
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

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image = icon.getImage();
        ImageIcon pencilIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon icon1 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image1 = icon1.getImage();
        ImageIcon trashIcon = new ImageIcon(image1.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        // Data of the table
        String[] columnNames = {"Código", "Material", "Categoria", "Características", "Valor Unitario", "Cantidad", "Total", "", ""};
        Object[][] data = {
                {"T001", "Lino", "Telas", "Resistente, Transpirable, Beige", "80,000 /M", "50 Metros", "4,000,000", trashIcon, pencilIcon},
                {"T002", "Chenille", "Telas", "Textura Suave, Verde", "95,000 /M", "40 Metros", "3,800,000", trashIcon, pencilIcon},
                {"T003", "Terciopelo", "Telas", "Lujo, Suave, Rojo", "120,000 /M", "40 Metros", "4,800,000", trashIcon, pencilIcon},
                {"T004", "Cuero Sintético", "Telas", "Fácil De Limpiar, Resistente, Negro", "90,000 /M", "55 Metros", "4,950,000", trashIcon, pencilIcon},
                {"T005", "Pana", "Telas", "Textura Acanalada,Duradero,Maron", "100,000 /M", "30 Metros", "3,000,000", trashIcon, pencilIcon},
                {"T006", "Jacquard", "Telas", "Diseño Intrincado, Resistente, Azul", "110,000 /M", "35 Metros", "3,850,000", trashIcon, pencilIcon},
                {"T007", "Tweed", "Telas", "Textura Rugosa, Duradero, Gris", "75,000 /M", "50 Metros", "3,750,000", trashIcon, pencilIcon},
                {"T008", "Franela", "Telas", "Suave, Cálida, Color Gris Claro", "45,000 /M", "35 Metros", "1,575,000", trashIcon, pencilIcon},
                {"T009", "Seda", "Telas", "Brillante, Suave, Color Blanco Perla", "70,000 /M", "25 Metros", "1,750,000", trashIcon, pencilIcon},
                {"T010", "Gamuza", "Telas", "Suave Al Tacto, Elegante, Beige", "65,000 /M", "20 Metros", "1,300,000", trashIcon, pencilIcon},
                {"MCO01", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO02", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO03", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO04", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO05", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO06", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO07", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO08", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO09", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO10", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO11", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon},
                {"MCO12", "Madera", "Muebles", "Rectangular, Color Nogal", "60,000", "4", "240,000", trashIcon, pencilIcon}
        };

        // Table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 7 || column == 8) ? Icon.class : super.getColumnClass(column);
            }

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
        table.getColumnModel().getColumn(0).setPreferredWidth(120);// Esto ajusta el ancho máximo de la columna 0
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(310);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);
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
                if (column == 7 || column == 8) {
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
