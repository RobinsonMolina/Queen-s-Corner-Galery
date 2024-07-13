package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class CustomerList extends JFrame implements ActionListener {

    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel contentButton;
    private JLabel titleLabel;
    private JButton addButton;
    private JTextField searchField;
    private Components components;
    private JPanel mainContentPanel;

    public CustomerList(JPanel mainContentPanel) {
        components = new Components(mainContentPanel);
    }

    public JPanel initializeContentPanel() {
        contentPanel = new JPanel(new BorderLayout());

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1055, 25, 0));

        addButton = createRoundedButton("Agregar");
        addButton.setPreferredSize(new Dimension(150, 34));
        contentButton.add(addButton);

        addButton.addActionListener(this);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
        return contentPanel;
    }

    private Font createFont(int style, int size) {
        try {
            return (style == 0) ? Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\Buenard-Bold.ttf")).deriveFont(Font.PLAIN, size) : Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\Buenard-Regular.ttf")).deriveFont(Font.PLAIN, size);
        }catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // Centered title
        titleLabel = new JLabel("Clientes", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(createFont(0, 40));

        contentTitle.add(titleLabel);

        // Rounded search field
        JTextField searchTextField = createRoundedTextField(5);
        searchTextField.setBounds(875, 45, 200, 45);
        searchTextField.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 15));
        searchTextField.setFont(createFont(1, 20));

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

    private JTextField createRoundedTextField(int columns) {
        return new JTextField(columns) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 30, 30));
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 30, 30));
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                return shape.contains(x, y);
            }
        };
    }

    private JButton createRoundedButton(String text) {
        return new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            public void updateUI() {
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);
                setFont(createFont(1, 20));
                setForeground(Color.WHITE);
                setBackground(Color.decode("#2F1940"));
                super.updateUI();
            }
        };
    }

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

        String[] columnNames = {"Documento", "Nombre", "Correo", "", "", ""};
        Object[][] data = {
                {"1012345678", "Juan David Pérez", "jdperez@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1019876543", "María Alejandra Rodríguez", "mar@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1023456789", "Carlos Andrés Gómez", "cag@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1034567890", "Luisa Fernanda Fernández", "lff@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1045678901", "Andrés Felipe López", "afl@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1056789012", "Ana María Torres", "amt@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1067890123", "Diego Alejandro Martínez", "dam@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1078901234", "Laura Patricia Ramírez", "lpr@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1089012345", "Pedro José Jiménez", "pjj@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1089012345", "Pedro José Jiménez", "pjj@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1089012345", "Pedro José Jiménez", "pjj@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1089012345", "Pedro José Jiménez", "pjj@example.com", eyeIcon, pencilIcon, trashIcon},
                {"1089012345", "Pedro José Jiménez", "pjj@example.com", eyeIcon, pencilIcon, trashIcon}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 3 || column == 4 || column == 5) ? Icon.class : super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(createFont(1, 20));
        table.setForeground(Color.decode("#2F2F2F"));
        table.setRowHeight(34);
        table.setShowGrid(false);
        setColumnWidths(table);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));
        header.setFont(createFont(0, 20));

        // Mover la configuración del renderizador después de establecer la fuente
        header.setDefaultRenderer(createHeaderRenderer(header.getFont()));
        table.setDefaultRenderer(Object.class, createTableRowRenderer());

        // Configurar el MouseListener para la tabla
        setupTableMouseListener(table);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 80, 0, 60));
        tablePanel.setPreferredSize(new Dimension(1366, 136));
        tablePanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private void setColumnWidths(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private DefaultTableCellRenderer createHeaderRenderer(Font headerFont) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setFont(headerFont);// set font
                    label.setForeground(Color.BLACK); // set color of text
                }

                if (column == 3 || column == 4 || column == 5) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(table.getTableHeader().getBackground());
                }

                return c;
            }
        };
    }

    private DefaultTableCellRenderer createTableRowRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                if (row % 2 == 0) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.decode("#D9D9D9"));
                }

                return cell;
            }
        };
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            contentPanel.removeAll();
            contentPanel.add(new NewCustomer(contentPanel).addSpecificOrder());
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    // Method for configuring the MouseListener, view line 225
    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column == 3) {
                    dispose();
                    ViewCustomer viewCustomer = new ViewCustomer();
                    viewCustomer.createWindow();
                } else if (column == 4) {
                    dispose();
                    UpdateCustomer updateCustomer = new UpdateCustomer();
                    updateCustomer.createWindow();
                } else if (column == 5) {
                    components.windowConfirmation("¿Está seguro de eliminar este cliente?", "Cancelar", "Eliminar", "Cliente eliminado con éxito");
                }
            }
        });
    }
}
