package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpecificOrder implements ActionListener {
    /*
    //private JFrame specificOrderWindow;
    private JPanel allInformation;
    private JPanel allInfoPanel;
    //private JPanel window;
    private JPanel dataSpecificOrder;
    private Components components;

    private JPanel buttons;
    private JButton delete;
    private JButton update;
    private JButton pdf;
    private JButton goBack;
    private int previousScreen;

    public SpecificOrder(JPanel mainContentPanel) {
        allInfoPanel = mainContentPanel;
        allInfoPanel = new JPanel();
        components = new Components(mainContentPanel);

        allInformation = new JPanel();
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));

        buttons = new JPanel(new FlowLayout());
        delete = new JButton("Eliminar");
        update = new JButton("Actualizar");
        pdf = new JButton("PDF");
        goBack = new JButton("Volver");
        previousScreen = 0;
    }*/
    /*public SpecificOrder() {
        specificOrderWindow = new JFrame("Orden Especifica");
        allInformation = new JPanel();
        allInfoPanel = new JPanel();
        window = new JPanel(new BorderLayout());
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));
        createWindow();
    }

    public  void createWindow(){
        specificOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        specificOrderWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        specificOrderWindow.setSize(1366, 670);

        addSpecificOrder();
        setWindow();
        specificOrderWindow.add(window);
        specificOrderWindow.setVisible(true);
    }

    public  void setWindow(){
        HeaderMenu headerMenu = new HeaderMenu();

        window.add(headerMenu.getMenuPanel(), BorderLayout.WEST);
        window.add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        window.add(allInformation, BorderLayout.CENTER);
    }*/

    /* _______________________________________________________________________________________________
    // Previous screen indicates if you are coming from Board (1) or List (2) (3)
    public JPanel addSpecificOrder(int previousScreen){
        JLabel title = new JLabel("Orden Especifica");
        this.previousScreen = previousScreen;

        allInfoPanel.setPreferredSize(new Dimension(1286, 590));

        title.setFont(components.createFont(0, 40));
        title.setPreferredSize(new Dimension(389, 47));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.add(Box.createVerticalStrut(30));
        allInfoPanel.add(title);
        allInfoPanel.add(Box.createVerticalStrut(35));
        setSpecificData();
        dataSpecificOrder.setPreferredSize(new Dimension(1286, 200));
        dataSpecificOrder.setBorder(new EmptyBorder(0, 55, 0, 55));

        allInfoPanel.add(dataSpecificOrder);

        table();
        allInfoPanel.setBackground(Color.WHITE);
        dataSpecificOrder.setBackground(Color.WHITE);

        allInformation.add(allInfoPanel);

        return allInfoPanel;
    }

    public void setSpecificData(){
        JLabel product = new JLabel("Producto");
        JLabel type = new JLabel("Tipo");
        JLabel customer = new JLabel("Cliente");
        JLabel state = new JLabel("Estado");
        JLabel productionDate = new JLabel("Fecha Producción");
        JLabel phone = new JLabel("Teléfono");
        JLabel orderNumber = new JLabel("Número de orden");
        JLabel deliveryDate = new JLabel("Fecha de Entrega");
        JLabel document = new JLabel("Documento");

        String[] options = {"Por Hacer", "En Progreso", "Entregado"};
        JTextField productTxt = new JTextField("Sofacama");
        JTextField typeTxt = new JTextField("Mueble");
        JTextField customerTxt = new JTextField("Juan David Pérez");
        JComboBox <String> stateCombo = new JComboBox<> (options);
        JTextField productionDateTxt = new JTextField("26/06/2024");
        JTextField phoneTxt = new JTextField("3133333333");
        JTextField orderNumberTxt = new JTextField("001");
        JTextField deliveryDateTxt = new JTextField("30/06/2024");
        JTextField documentTxt = new JTextField("10544444");

        product.setFont(components.createFont(0, 20));
        type.setFont(components.createFont(0, 20));
        customer.setFont(components.createFont(0, 20));
        state.setFont(components.createFont(0, 20));
        productionDate.setFont(components.createFont(0, 20));
        phone.setFont(components.createFont(0, 20));
        orderNumber.setFont(components.createFont(0, 20));
        deliveryDate.setFont(components.createFont(0, 20));
        document.setFont(components.createFont(0, 20));

        productTxt.setFont(components.createFont(1, 20));
        typeTxt.setFont(components.createFont(1, 20));
        customerTxt.setFont(components.createFont(1, 20));
        stateCombo.setFont(components.createFont(1, 20));
        productionDateTxt.setFont(components.createFont(1, 20));
        phoneTxt.setFont(components.createFont(1, 20));
        orderNumberTxt.setFont(components.createFont(1, 20));
        deliveryDateTxt.setFont(components.createFont(1, 20));
        documentTxt.setFont(components.createFont(1, 20));

        productTxt.setEditable(false);
        typeTxt.setEditable(false);
        customerTxt.setEditable(false);
        productionDateTxt.setEditable(false);
        phoneTxt.setEditable(false);
        orderNumberTxt.setEditable(false);
        deliveryDateTxt.setEditable(false);
        documentTxt.setEditable(false);

        // ComboBox
        stateCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(Color.decode("#D9D9D9"));
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        });
        stateCombo.setBackground(Color.WHITE);
        stateCombo.isPopupVisible();

        int borderRadius = 5;
        Color borderColor = Color.decode("#2F1940");

        productTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        typeTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        customerTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        stateCombo.setBorder(new RoundedBorder(borderRadius, borderColor));
        productionDateTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        phoneTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        orderNumberTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        deliveryDateTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        documentTxt.setBorder(new RoundedBorder(borderRadius, borderColor));

        product.setPreferredSize(new Dimension(99, 30));
        type.setPreferredSize(new Dimension(99, 30));
        customer.setPreferredSize(new Dimension(99, 30));

        productTxt.setPreferredSize(new Dimension(300, 35));
        typeTxt.setPreferredSize(new Dimension(300, 35));
        customerTxt.setPreferredSize(new Dimension(300, 35));

        state.setPreferredSize(new Dimension(187, 30));
        productionDate.setPreferredSize(new Dimension(187, 30));
        phone.setPreferredSize(new Dimension(187, 30));
        orderNumber.setPreferredSize(new Dimension(187, 30));
        deliveryDate.setPreferredSize(new Dimension(187, 30));
        document.setPreferredSize(new Dimension(187, 30));

        stateCombo.setPreferredSize(new Dimension(180, 35));
        orderNumberTxt.setPreferredSize(new Dimension(180, 35));
        productionDateTxt.setPreferredSize(new Dimension(180, 35));
        deliveryDateTxt.setPreferredSize(new Dimension(180, 35));
        phoneTxt.setPreferredSize(new Dimension(180, 35));
        documentTxt.setPreferredSize(new Dimension(180, 35));

        dataSpecificOrder.add(product);
        dataSpecificOrder.add(productTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(state);
        dataSpecificOrder.add(stateCombo);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));


        dataSpecificOrder.add(orderNumber);
        dataSpecificOrder.add(orderNumberTxt);

        dataSpecificOrder.add(type);
        dataSpecificOrder.add(typeTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(productionDate);
        dataSpecificOrder.add(productionDateTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(deliveryDate);
        dataSpecificOrder.add(deliveryDateTxt);

        dataSpecificOrder.add(customer);
        dataSpecificOrder.add(customerTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(phone);
        dataSpecificOrder.add(phoneTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(document);
        dataSpecificOrder.add(documentTxt);
    }


    public void table(){
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel materialsTitle = new JLabel("Materiales Requeridos");

        materialsTitle.setFont(components.createFont(0, 30));
        materialsTitle.setPreferredSize(new Dimension(380, 30));
        materialsTitle.setHorizontalTextPosition(JLabel.LEFT);

        ImageIcon icon = new ImageIcon("src/Utilities/Images/Trash.png");
        Image image = icon.getImage();
        ImageIcon defIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        Object[][] data = {
                {"01", "Chenille", 5, 475000, defIcon},
                {"02", "Cuero Sintético", 5, 450000, defIcon},
                {"03", "Pana", 3, 300000, defIcon},
        };

        String[] columnNames = {"Codigo", "Material", "Cantidad", "Costo", ""};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return Icon.class;
                }
                return super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(34);
        table.setShowGrid(false);
        table.setPreferredSize(new Dimension(1286, 156));
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        setupTableMouseListener(table);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Focuses the text
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setFont(components.createFont(0, 20));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                // Change column header color
                if (column == 4) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(header.getBackground());
                }

                return c;
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Focuses the text
                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setFont(components.createFont(1, 20));
                }

                // Change the color of the rows
                if (row % 2 == 0) {
                    cell.setBackground(Color.white);
                } else {
                    cell.setBackground(Color.decode("#D9D9D9"));
                }

                return cell;
            }
        });

        header.setPreferredSize(new Dimension(283, 34));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));
        tableScrollPane.setPreferredSize(new Dimension(1286, 155));

        jPanel.add(materialsTitle, BorderLayout.NORTH);
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.add(buttons(), BorderLayout.SOUTH);
        jPanel.setBorder(new EmptyBorder(10, 55, 0, 55));

        jPanel.setPreferredSize(new Dimension(1286, 155));
        jPanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);
        allInfoPanel.add(jPanel);
    }

    public class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;

        public RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(borderColor);
            if (borderColor == null) {
                g2d.setStroke(new BasicStroke(4));
            }
            g2d.drawRoundRect(x, y, width, height, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius, this.radius, this.radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius;
            return insets;
        }
    }

    public JPanel buttons() {
        buttons.add(Box.createHorizontalStrut(600));
        buttons.add(delete);
        buttons.add(update);
        buttons.add(pdf);
        buttons.add(goBack);

        delete.setBackground(Color.decode("#2F1940"));
        update.setBackground(Color.decode("#2F1940"));
        pdf.setBackground(Color.decode("#2F1940"));
        goBack.setBackground(Color.decode("#2F1940"));

        delete.setFont(components.createFont(1, 20));
        update.setFont(components.createFont(1, 20));
        pdf.setFont(components.createFont(1, 20));
        goBack.setFont(components.createFont(1, 20));

        delete.setForeground(Color.white);
        update.setForeground(Color.white);
        pdf.setForeground(Color.white);
        buttons.setBackground(Color.white);
        goBack.setForeground(Color.white);

        delete.setPreferredSize(new Dimension(127, 32));
        update.setPreferredSize(new Dimension(127, 32));
        pdf.setPreferredSize(new Dimension(127, 32));
        goBack.setPreferredSize(new Dimension(127, 32));

        delete.setBorder((new RoundedBorder(10, null)));
        update.setBorder((new RoundedBorder(10, null)));
        pdf.setBorder((new RoundedBorder(10, null)));
        goBack.setBorder((new RoundedBorder(10, null)));

        delete.addActionListener(this);
        update.addActionListener(this);
        pdf.addActionListener(this);
        goBack.addActionListener(this);

        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            allInfoPanel.removeAll();
            allInfoPanel.add(new UpdateOrder(allInfoPanel).addSpecificOrder(previousScreen));
            allInfoPanel.revalidate();
            allInfoPanel.repaint();

        } else if (e.getSource() == goBack) {
            allInfoPanel.removeAll();

            if (previousScreen == 1){
                // Board
                allInfoPanel.add(new Board(allInfoPanel).contentPanel());
            } else if (previousScreen == 2 || previousScreen == 3) {
                // List && List - Specific - Update
                allInfoPanel.add(new OrderList(allInfoPanel).initializeContentPanel());
            }

            allInfoPanel.revalidate();
            allInfoPanel.repaint();
        }
    }

    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());

                if (column == 4) {
                    String valor = String.valueOf(table.getValueAt(row, 0).toString());
                    components.windowConfirmation("¿Está seguro de eliminar este material?", "Cancelar", "Eliminar", "Material eliminado con éxito", valor);
                }
            }
        });
    }
    */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
