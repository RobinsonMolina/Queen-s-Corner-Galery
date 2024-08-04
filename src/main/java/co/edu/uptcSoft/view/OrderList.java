package co.edu.uptcSoft.view;

import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.logic.Logic;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class OrderList extends JFrame implements ActionListener {

    private Components components;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel contentButton;
    private JLabel titleLabel;
    private JTextField searchTextField;
    private JButton buttonAdd;
    private JTable table;
    private DefaultTableModel model;
    private Object orderListTable[][];
    private Logic logic = Logic.getInstance();
    private JPanel mainContentPanel;
    private TableRowSorter<DefaultTableModel> filter;

    public OrderList(JPanel mainContentPanel) {
        this.mainContentPanel = mainContentPanel;
        components = new Components(mainContentPanel);
        contentPanel = new JPanel(new BorderLayout());
    }

    // Method for initializing content panel
    public JPanel initializeContentPanel() {
        contentPanel.setPreferredSize(new Dimension(1286, 590));

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1058, 25, 0));

        // Button add
        buttonAdd = components.createRoundedButton("Agregar", "#000000", "#2F1940", 30, 30);
        buttonAdd.setPreferredSize(new Dimension(150, 34));
        contentButton.add(buttonAdd);

        buttonAdd.addActionListener(this);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
        return contentPanel;
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
    public void initializeTable() {
        // Data of the table
        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega", "", "", ""};

        // Table model
        model = new DefaultTableModel(getOrderList(), columnNames) {
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
        table = new JTable(model);
        table.setFont(components.createFont(1, 20));
        table.setForeground(Color.decode("#2F2F2F"));
        table.setRowHeight(34);
        table.setShowGrid(false);
        setColumnWidths(table);

        // TableRowSorter for filtering
        filter = new TableRowSorter<>(model);
        table.setRowSorter(filter);

        // Table header
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            // change the content of the main panel instead of opening a new window
            mainContentPanel.removeAll();
            mainContentPanel.add(new NewOrder(mainContentPanel).addSpecificOrder(2));
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        }
    }

    // Method for configuring the MouseListener, view line 175
    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (column == 5) {
                    // change the content of the main panel instead of opening a new window
                    mainContentPanel.removeAll();
                    mainContentPanel.add(new SpecificOrder(mainContentPanel).addSpecificOrder(3));
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                } else if (column == 6) {
                    // change the content of the main panel instead of opening a new window
                    mainContentPanel.removeAll();
                    mainContentPanel.add(new UpdateOrder(mainContentPanel).addSpecificOrder(2));
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                } else if (column == 7) {
                    String valor = table.getValueAt(row, 0).toString();
                    components.windowConfirmation("¿Está seguro de eliminar esta orden?", "Cancelar", "Eliminar", "Orden eliminada con éxito", valor);

                }
            }
        });
    }

    // Method for getting the order list and its icons
    public Object[][] getOrderList() {

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Eye.png");
        Image image = icon.getImage();
        ImageIcon eyeIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon2 = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image2 = icon2.getImage();
        ImageIcon pencilIcon = new ImageIcon(image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image3 = icon3.getImage();
        ImageIcon trashIcon = new ImageIcon(image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ArrayList<Order> orderList = new ArrayList<>(logic.getOrderList().values());

        orderListTable = new Object[orderList.size()][8];
        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < orderList.size(); i++) {
            orderListTable[i][0] = orderList.get(i).getOrderNumber();
            orderListTable[i][1] = orderList.get(i).getProductName();
            orderListTable[i][2] = orderList.get(i).getCustomer().getName();
            orderListTable[i][3] = orderList.get(i).getCustomer().getPhoneNumber();
            orderListTable[i][4] = formato.format(orderList.get(i).getDeliveryDate());
            orderListTable[i][5] = eyeIcon;
            orderListTable[i][6] = pencilIcon;
            orderListTable[i][7] = trashIcon;
        }
        return orderListTable;
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
