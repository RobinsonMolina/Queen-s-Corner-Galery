package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.model.Supply;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewOrder implements ActionListener {
    //private JFrame specificOrderWindow;
    private JPanel allInformation;
    private JPanel allInfoPanel;
    //private JPanel window;
    private JPanel dataSpecificOrder;
    private Components components;
    private int previousScreen;
    private JButton add;
    private JButton save;
    private JButton cancel;

    private Customer currentCustomer;
    private JTextField customerTxt;
    private JTextField phoneTxt;
    private JTextField documentTxt;
    private JTextField productTxt;
    private JTextField typeTxt;
    private JTextField productionDateTxt;
    private JTextField orderNumberTxt;
    private JTextField deliveryDateTxt;
    private String statusOp;

    private Order order;
    private String productName;
    private String status;
    private int orderNumber;
    private String type;
    private Date productionDate;
    private Date deliveryDate;
    private Customer customer;
    private ArrayList<Materials> materials;
    private JTable table;

    private Logic logic = Logic.getInstance();
    private Object supplyListTable[][];
    private ArrayList<Supply> supplyList;
    private AddSupply addSupply;

    public NewOrder(JPanel mainContentPanel) {
        allInfoPanel = mainContentPanel;
        allInfoPanel = new JPanel();
        allInformation = new JPanel();

        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        components = new Components();
        previousScreen = 0;
        add = new JButton("+ Material");
        save = new JButton("Aceptar");
        cancel = new JButton("Cancelar");

        customerTxt = new JTextField();
        phoneTxt = new JTextField();
        documentTxt = new JTextField();

        order = new Order();
        productTxt = new JTextField();
        typeTxt = new JTextField();
        productionDateTxt = new JTextField();
        orderNumberTxt = new JTextField();
        deliveryDateTxt = new JTextField();
        statusOp = "Por Hacer";

        table = new JTable();
        supplyList = new ArrayList<>();
    }
    /*public NewOrder() {
        specificOrderWindow = new JFrame("Nueva Orden");
        allInformation = new JPanel();
        allInfoPanel = new JPanel();
        window = new JPanel(new BorderLayout());
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));
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

    // 1. Menu, 2. OrderList, 3. NewCustomer
    public JPanel addSpecificOrder(int previousScreen){
        JLabel title = new JLabel("Nueva Orden");
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
        JComboBox <String> stateCombo = new JComboBox<> (options);

        stateCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusOp = (String) stateCombo.getSelectedItem();
            }
        });

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
        newCustomer();
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

        String[] columnNames = {"Codigo", "Material", "Cantidad", "Costo", ""};

        DefaultTableModel model = new DefaultTableModel(getSuppliesList(), columnNames) {
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

        table = new JTable(model);
        table.setRowHeight(34);
        table.setShowGrid(false);
        table.setPreferredSize(new Dimension(1286, 156));
        table.getColumnModel().getColumn(4).setMaxWidth(50);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));

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
                    label.setFont(components.createFont(1, 20));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
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

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1286, 155));
        tableScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));

        jPanel.add(materialsTitle, BorderLayout.NORTH);
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.add(buttons(), BorderLayout.SOUTH);
        jPanel.setBorder(new EmptyBorder(10, 55, 0, 55));

        jPanel.setPreferredSize(new Dimension(1286, 155));
        jPanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);

        allInfoPanel.add(jPanel);
    }

    public Object[][] getSuppliesList() {
        supplyListTable = new Object[supplyList.size()][4];

        for (int i = 0; i < supplyList.size(); i++) {
            supplyListTable[i][0] = supplyList.get(i).getId();
            supplyListTable[i][1] = supplyList.get(i).getMaterial();
            supplyListTable[i][2] = supplyList.get(i).getQuantity();
            supplyListTable[i][3] = supplyList.get(i).getTotalPrice();
        }
        return supplyListTable;
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
        JPanel buttons = new JPanel(new FlowLayout());

        buttons.add(Box.createHorizontalStrut(700));
        buttons.add(add);
        buttons.add(save);
        buttons.add(cancel);

        add.setBackground(Color.decode("#2F1940"));
        save.setBackground(Color.decode("#2F1940"));
        cancel.setBackground(Color.decode("#2F1940"));

        add.setFont(components.createFont(1, 20));
        save.setFont(components.createFont(1, 20));
        cancel.setFont(components.createFont(1, 20));

        add.setForeground(Color.white);
        save.setForeground(Color.white);
        cancel.setForeground(Color.white);

        add.setPreferredSize(new Dimension(127, 32));
        save.setPreferredSize(new Dimension(127, 32));
        cancel.setPreferredSize(new Dimension(127, 32));

        add.setBorder((new RoundedBorder(10, null)));
        save.setBorder((new RoundedBorder(10, null)));
        cancel.setBorder((new RoundedBorder(10, null)));

        buttons.setBackground(Color.white);

        add.addActionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);

        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            allInfoPanel.removeAll();

            if (previousScreen == 1){
                // Menu
                allInfoPanel.add(new Board(allInfoPanel).contentPanel());
            } else if (previousScreen == 2) {
                // OrderList
                allInfoPanel.add(new OrderList(allInfoPanel).initializeContentPanel());
            } else if (previousScreen == 3) {
                // NewCustomer
                updateCustomer();
                NewCustomer newCustomer = new NewCustomer(allInfoPanel);
                newCustomer.addCompleteCustomer(getCurrentCustomer());
                allInfoPanel.add(newCustomer.addSpecificOrder());
            }

            allInfoPanel.revalidate();
            allInfoPanel.repaint();
        } if (e.getSource() == save){
            addCurrentOrder();

            if (previousScreen == 3){
                //NewCustomer
                //allInfoPanel.add(new NewCustomer(allInfoPanel).addSpecificOrder());
                //allInfoPanel.removeAll();
                new NewCustomer(allInfoPanel).addCompleteCustomer(currentCustomer);
                //allInfoPanel.add(new CustomerList(allInfoPanel).initializeContentPanel());

                //no se actualiza bien la página
            } else if (previousScreen == 4) {

            }

            allInfoPanel.revalidate();
            allInfoPanel.repaint();
        } else if (e.getSource() == add) {
            allInfoPanel.removeAll();

            addSupply = new AddSupply(allInfoPanel);
            addSupply.setSupplyList(getSupplyList());
            addSupply.setCurrentCustomer(getCurrentCustomer());

            allInfoPanel.add(addSupply.initializeContentPanel());
            allInfoPanel.revalidate();
            allInfoPanel.repaint();
        }
    }

    public void newCustomer(){
        try {
            if (getCurrentCustomer() != null && getCurrentCustomer().getPhoneNumber() != 0 && getCurrentCustomer().getDocumentNumber() != 0){
                customerTxt.setText(getCurrentCustomer().getName());
                phoneTxt.setText(String.valueOf(getCurrentCustomer().getPhoneNumber()));
                documentTxt.setText(String.valueOf(getCurrentCustomer().getDocumentNumber()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCurrentOrder(){
        try {
            productName = productTxt.getText();
            status = statusOp;
            orderNumber = Integer.parseInt(orderNumberTxt.getText());
            type = typeTxt.getText();
            productionDate = new Date(productionDateTxt.getText());
            deliveryDate = new Date(deliveryDateTxt.getText());
            customer = currentCustomer;
        } catch (Exception e){

        }

        if (productName.isEmpty() || status.isEmpty() || orderNumber == 0 || type.isEmpty() || productionDate.toString().isEmpty() || deliveryDate.toString().isEmpty()) {
            components.windowConfirmation("Ingrese todos los datos de la orden", "Aceptar", "Datos de la Orden");
        } else if (table.getRowCount() == 0) {
            components.windowConfirmation("Debe de tener al menos una Material Requerido", "Aceptar", "Material Requerido");
        } else {

            components.windowConfirmation("¿Está seguro de añadir esta orden?", "Cancelar", "Añadir", "Orden añadida con éxito");
            components.setCurrentCustomer(getCurrentCustomer());

            setOrder(new Order(productName, status, orderNumber, type, productionDate, deliveryDate, customer, materials));

            logic.addOrder(productName, status, orderNumber, type, productionDate, deliveryDate, customer, materials);
            allInformation.removeAll();
            allInformation.add(new OrderList(allInformation).initializeContentPanel());
            allInformation.revalidate();
            allInformation.repaint();
        }
    }

    public void updateCustomer(){
        Customer updateCus;
        updateCus = getCurrentCustomer();
        updateCus.setName(customerTxt.getText());
        updateCus.setPhoneNumber(Long.parseLong(phoneTxt.getText()));
        updateCus.setDocumentNumber(Long.parseLong(documentTxt.getText()));
        setCurrentCustomer(updateCus);
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setSupplyList(ArrayList<Supply> supplyList) {
        this.supplyList = supplyList;
    }

    public ArrayList<Supply> getSupplyList() {
        return supplyList;
    }
}
