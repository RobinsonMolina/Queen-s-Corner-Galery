package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
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
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SupplyList extends JFrame implements ActionListener {

    private Components components;
    private JPanel contentButton;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JLabel titleLabel;
    private JTextField searchTextField;
    private JButton buttonAdd;
    private JPanel mainContentPanel;
    private Object supplyListTable[][];
    private Logic logic = Logic.getInstance();
    private TableRowSorter<DefaultTableModel> filter;


    public SupplyList(JPanel mainContentPanel){
        this.mainContentPanel = mainContentPanel;
        components = new Components(mainContentPanel);
    }

    // Method for initializing content panel
    public JPanel initializeContentPanel() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1366, 590));

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1055, 25, 0));

        buttonAdd = components.createRoundedButton("Agregar", "#000000", "#2F1940", 30, 30);
        buttonAdd.setPreferredSize(new Dimension(150, 34));
        buttonAdd.addActionListener(this); // permited to go to the new supply window
        components.hoverButton(buttonAdd); // configure the button to change the background color when hovered

        contentButton.add(buttonAdd);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
        return contentPanel;
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
        String[] columnNames = {"Código", "Material", "Categoria", "Características", "Valor Unitario", "Cantidad", "Total", "", ""};

        // Table model
        DefaultTableModel model = new DefaultTableModel(getSuppliesList(), columnNames) {
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

        // configure the MouseListener for the table
        setupTableMouseListener(table);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            // change the content of the main panel instead of opening a new window
            mainContentPanel.removeAll();
            mainContentPanel.add(new NewSupply(mainContentPanel).initializeContentPanel());
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        }
    }

    // Method for configuring the MouseListener, view line 173
    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                String valor;
                if (column == 7) {
                    valor = table.getValueAt(row, 0).toString();
                    components.windowConfirmation("¿Está seguro de eliminar este insumo?", "Cancelar", "Eliminar", "Insumo eliminado con éxito", valor);
                } else if (column == 8) {
                    // change the content of the main panel instead of opening a new window
                    valor = table.getValueAt(row, 0).toString();
                    mainContentPanel.removeAll();
                    mainContentPanel.add(new UpdateSupplY(mainContentPanel).initializeContentPanel(valor));
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                }
            }
        });
    }

    // Method for getting the order list and its icons
    public Object[][] getSuppliesList() {

        ImageIcon icon2 = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image2 = icon2.getImage();
        ImageIcon pencilIcon = new ImageIcon(image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image3 = icon3.getImage();
        ImageIcon trashIcon = new ImageIcon(image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ArrayList<Supply> supplyList = new ArrayList<>(logic.getSupplyList().values());

        supplyListTable = new Object[supplyList.size()][9];
        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < supplyList.size(); i++) {
            supplyListTable[i][0] = supplyList.get(i).getId();
            supplyListTable[i][1] = supplyList.get(i).getMaterial();
            supplyListTable[i][2] = supplyList.get(i).getCategory();
            supplyListTable[i][3] = supplyList.get(i).getCharacteristics();
            supplyListTable[i][4] = supplyList.get(i).getUnitPrice();
            supplyListTable[i][5] = supplyList.get(i).getQuantity();
            supplyListTable[i][6] = supplyList.get(i).getTotalPrice();
            supplyListTable[i][7] = trashIcon;
            supplyListTable[i][8] = pencilIcon;
        }
        return supplyListTable;
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
