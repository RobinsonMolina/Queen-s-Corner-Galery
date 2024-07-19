package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Supply;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerList extends JFrame implements ActionListener {

    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel contentButton;
    private JLabel titleLabel;
    private JButton addButton;
    private JTextField searchTextField;
    private Components components;
    private JTable table;
    private DefaultTableModel model;
    private Object customerListTable[][];
    private Logic logic = Logic.getInstance();
    private JPanel mainContentPanel;
    private TableRowSorter<DefaultTableModel> filter;

    public CustomerList(JPanel mainContentPanel) {
        components = new Components(mainContentPanel);
    }

    public JPanel initializeContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1366, 590));

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1058, 25, 0));

        // Button add
        addButton = components.createRoundedButton("Agregar", "#000000", "#2F1940", 30, 30);
        addButton.setPreferredSize(new Dimension(150, 34));
        contentButton.add(addButton);

        addButton.addActionListener(this);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
        return contentPanel;
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

        // Add DocumentListener to the JTextField to filter the table
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });
    }

    // Method for initializing table
    private void initializeTable() {
        // Data of the table
        String[] columnNames = {"Documento", "Nombre", "Correo", "", "", ""};

        DefaultTableModel model = new DefaultTableModel(getCustomerList(), columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 3 || column == 4 || column == 5) ? Icon.class : super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Table
        table = new JTable(model);
        table.setFont(components.createFont(1, 20));
        table.setForeground(Color.decode("#2F2F2F"));
        table.setRowHeight(34);
        table.setShowGrid(false);
        setColumnWidths(table);

        // TableRowSorter for filtering
        filter = new TableRowSorter<>(model);
        table.setRowSorter(filter);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));
        header.setFont(components.createFont(0, 20));

        // Move configuration of renderer after setting font
        header.setDefaultRenderer(createHeaderRenderer(header.getFont()));
        table.setDefaultRenderer(Object.class, createTableRowRenderer());

        // Configurar el MouseListener para la tabla
        setupTableMouseListener(table);

        // Scroll pane of table
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        tableScrollPane.getViewport().setBackground(Color.WHITE);// change background of content scroll pane
        tableScrollPane.setBackground(Color.WHITE); // change background of scroll pane

        // Panel of table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 80, 0, 60));
        tablePanel.setPreferredSize(new Dimension(1366, 136));
        tablePanel.setBackground(Color.WHITE);
        ;
        contentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    // Method for setting column widths
    private void setColumnWidths(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    // Method for creating header renderer
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

    // Method for creating table row renderer
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
            contentPanel.add(new NewCustomer(mainContentPanel).addSpecificOrder());
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
                int row = table.rowAtPoint(e.getPoint());
                String valor;
                if (column == 3) {
                    contentPanel.removeAll();
                    contentPanel.add(new ViewCustomer(contentPanel).addSpecificOrder());
                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else if (column == 4) {
                    valor = table.getValueAt(row, 0).toString();
                    contentPanel.removeAll();
                    contentPanel.add(new UpdateCustomer(contentPanel).addSpecificCustomer()); // Sent valor to update customer
                    contentPanel.revalidate();
                    contentPanel.repaint();
                } else if (column == 5) {
                    valor = table.getValueAt(row, 0).toString();
                    components.windowConfirmation("¿Está seguro de eliminar este cliente?", "Cancelar", "Eliminar", "Cliente eliminado con éxito", valor);
                }
            }
        });
    }

    // Method for getting the order list and its icons
    public Object[][] getCustomerList() {

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Eye.png");
        Image image = icon.getImage();
        ImageIcon eyeIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon2 = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image2 = icon2.getImage();
        ImageIcon pencilIcon = new ImageIcon(image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image3 = icon3.getImage();
        ImageIcon trashIcon = new ImageIcon(image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ArrayList<Customer> customerList = new ArrayList<>(logic.getCustomerList().values());

        customerListTable = new Object[customerList.size()][9];
        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < customerList.size(); i++) {
            customerListTable[i][0] = customerList.get(i).getDocumentNumber();
            customerListTable[i][1] = customerList.get(i).getName();
            customerListTable[i][2] = customerList.get(i).getEmail();
            customerListTable[i][3] = eyeIcon;
            customerListTable[i][4] = pencilIcon;
            customerListTable[i][5] = trashIcon;
        }
        return customerListTable;
    }

    // Method for filtering table
    private void filterTable() {
        String text = searchTextField.getText();
        if (text.trim().length() == 0) {
            filter.setRowFilter(null);
        } else {
            filter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
}
