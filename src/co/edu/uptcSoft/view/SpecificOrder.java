package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SpecificOrder {
    private JFrame specificOrderWindow;
    private JPanel menu;
    private JPanel head;
    private JPanel allInformation;
    private JPanel allInfoPanel;
    private JPanel window;
    private JPanel dataSpecificOrder;
    private JTable table;

    public SpecificOrder() {
        specificOrderWindow = new JFrame("Orden Especifica");
        menu = new JPanel();
        head = new JPanel();
        allInformation = new JPanel();
        allInfoPanel = new JPanel();
        window = new JPanel(new BorderLayout());
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));
    }

    public  void createWindow(){
        specificOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        specificOrderWindow.setSize(1366, 670);

        addSpecificOrder();
        setWindow();
        specificOrderWindow.add(window);
        specificOrderWindow.setVisible(true);
    }

    public void addSpecificOrder(){
        JLabel title = new JLabel("Orden Especifica");

        allInformation.setPreferredSize(new Dimension(1366, 700));
        allInfoPanel.setPreferredSize(new Dimension(1366, 700));

        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(389, 47));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);



        allInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.add(Box.createVerticalStrut(30));
        allInfoPanel.add(title);
        allInfoPanel.add(Box.createVerticalStrut(35));
        setSpecificData();
        dataSpecificOrder.setPreferredSize(new Dimension(1186, 160));

        allInfoPanel.add(dataSpecificOrder);

        table();
        allInformation.add(allInfoPanel);
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

        JTextField productTxt = new JTextField();
        JTextField typeTxt = new JTextField();
        JTextField customerTxt = new JTextField();
        JTextField stateTxt = new JTextField();
        JTextField productionDateTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JTextField orderNumberTxt = new JTextField();
        JTextField deliveryDateTxt = new JTextField();
        JTextField documentTxt = new JTextField();

        productionDateTxt.setPreferredSize(new Dimension(300, 30));

        product.setPreferredSize(new Dimension(99, 30));
        type.setPreferredSize(new Dimension(99, 30));
        customer.setPreferredSize(new Dimension(99, 30));

        productTxt.setPreferredSize(new Dimension(300, 30));
        typeTxt.setPreferredSize(new Dimension(300, 30));
        customerTxt.setPreferredSize(new Dimension(300, 30));

        state.setPreferredSize(new Dimension(187, 30));
        productionDate.setPreferredSize(new Dimension(187, 30));
        phone.setPreferredSize(new Dimension(187, 30));
        orderNumber.setPreferredSize(new Dimension(187, 30));
        deliveryDate.setPreferredSize(new Dimension(187, 30));
        document.setPreferredSize(new Dimension(187, 30));

        stateTxt.setPreferredSize(new Dimension(180, 30));
        orderNumberTxt.setPreferredSize(new Dimension(180, 30));
        productionDateTxt.setPreferredSize(new Dimension(180, 30));
        deliveryDateTxt.setPreferredSize(new Dimension(180, 30));
        phoneTxt.setPreferredSize(new Dimension(180, 30));
        documentTxt.setPreferredSize(new Dimension(180, 30));

        dataSpecificOrder.add(product);
        dataSpecificOrder.add(productTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(15));

        dataSpecificOrder.add(state);
        dataSpecificOrder.add(stateTxt);
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

    public  void setWindow(){
        menu.setBackground(Color.decode("#2F1940"));
        menu.setPreferredSize(new Dimension(80, 670));
        head.setBackground(Color.decode("#2F1940"));
        head.setPreferredSize(new Dimension(1286, 80));

        window.add(menu, BorderLayout.WEST);
        window.add(head, BorderLayout.NORTH);
        window.add(allInformation, BorderLayout.CENTER);
    }

    public void table(){
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel materialsTitle = new JLabel("Materiales Requeridos");

        materialsTitle.setFont(new Font(materialsTitle.getFont().getName(), Font.PLAIN, 30));
        materialsTitle.setPreferredSize(new Dimension(380, 47));
        materialsTitle.setHorizontalTextPosition(JLabel.LEFT);

        // Table
        String[] columnNames = {"Codigo", "Material", "Cantidad", "Costo"};
        Object[][] data = {
                {"01", "Chenille", 5, 475000},
                {"02", "Cuero Sintético", 5, 450000},
                {"03", "Pana", 3, 300000}
        };

        table = new JTable(data, columnNames);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 216));
        tableScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));

        jPanel.add(materialsTitle, BorderLayout.NORTH);
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

        jPanel.setMaximumSize(new Dimension(1366, 328));
        allInfoPanel.add(jPanel);
    }
}
