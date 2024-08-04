package co.edu.uptcSoft.view;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class UpdateCustomer implements ActionListener {
    //private JFrame specificOrderWindow;
    private JPanel allInformation;
    private JPanel allInfoPanel;
    //private JPanel window;
    private JPanel dataSpecificOrder;
    private Components components;
    private JButton add;
    private JButton save;
    private JButton cancel;

    public UpdateCustomer(JPanel mainContentPanel) {
        allInfoPanel = mainContentPanel;
        allInfoPanel = new JPanel();
        components = new Components(mainContentPanel);
        allInformation = new JPanel();
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));

        add = new JButton("+ Orden");
        save = new JButton("Aceptar");
        cancel = new JButton("Cancelar");
    }
    /*
    public UpdateCustomer() {
        specificOrderWindow = new JFrame("Acutualizar Cliente");
        allInformation = new JPanel();
        allInfoPanel = new JPanel();
        window = new JPanel(new BorderLayout());
        allInformation.setLayout(new BoxLayout(allInformation, BoxLayout.Y_AXIS));
        allInfoPanel.setLayout(new BoxLayout(allInfoPanel, BoxLayout.Y_AXIS));
        dataSpecificOrder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 24));
        components = new Components();
    }

    public  void createWindow(){
        specificOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        specificOrderWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        specificOrderWindow.setSize(1286, 670);

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
    }
    */
    public JPanel addSpecificCustomer(){
        JLabel title = new JLabel("Acutualizar Cliente");

        allInformation.setPreferredSize(new Dimension(1286, 590));
        allInfoPanel.setPreferredSize(new Dimension(1286, 590));

        title.setFont(components.createFont(0, 40));
        title.setPreferredSize(new Dimension(389, 47));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.add(Box.createVerticalStrut(30));
        allInfoPanel.add(title);
        allInfoPanel.add(Box.createVerticalStrut(30));
        setSpecificData();
        dataSpecificOrder.setPreferredSize(new Dimension(886, 200));
        dataSpecificOrder.setBorder(new EmptyBorder(0, 200, 0, 200));

        allInfoPanel.add(dataSpecificOrder);

        table();
        allInfoPanel.setBackground(Color.WHITE);
        dataSpecificOrder.setBackground(Color.WHITE);

        allInformation.add(allInfoPanel);
        return allInfoPanel;
    }

    public void setSpecificData(){
        JLabel name = new JLabel("Nombre");
        JLabel email = new JLabel("Email");
        JLabel phone = new JLabel("Teléfono");
        JLabel document = new JLabel("Documento");
        JLabel address = new JLabel("Dirección");

        JTextField nameTxt = new JTextField("Juan Davis Pérez");
        JTextField emailTxt = new JTextField("dPerez@gmail.com");
        JTextField phoneTxt = new JTextField("3133333333");
        JTextField documentTxt = new JTextField("10533333");
        JTextField addressTxt = new JTextField("Calle 10 #23-45, Sogamoso");

        name.setFont(components.createFont(0, 20));
        email.setFont(components.createFont(0, 20));
        phone.setFont(components.createFont(0, 20));
        document.setFont(components.createFont(0, 20));
        address.setFont(components.createFont(0, 20));

        nameTxt.setFont(components.createFont(1, 20));
        emailTxt.setFont(components.createFont(1, 20));
        phoneTxt.setFont(components.createFont(1, 20));
        documentTxt.setFont(components.createFont(1, 20));
        addressTxt.setFont(components.createFont(1, 20));

        int borderRadius = 5;
        Color borderColor = Color.decode("#2F1940");

        nameTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        emailTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        addressTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        phoneTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        documentTxt.setBorder(new RoundedBorder(borderRadius, borderColor));

        name.setPreferredSize(new Dimension(116, 30));
        email.setPreferredSize(new Dimension(116, 30));
        address.setPreferredSize(new Dimension(116, 30));

        nameTxt.setPreferredSize(new Dimension(300, 35));
        emailTxt.setPreferredSize(new Dimension(300, 35));
        addressTxt.setPreferredSize(new Dimension(300, 35));

        phone.setPreferredSize(new Dimension(116, 30));
        document.setPreferredSize(new Dimension(116, 30));

        phoneTxt.setPreferredSize(new Dimension(300, 35));
        documentTxt.setPreferredSize(new Dimension(300, 35));

        dataSpecificOrder.add(name);
        dataSpecificOrder.add(nameTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(54));

        dataSpecificOrder.add(document);
        dataSpecificOrder.add(documentTxt);

        dataSpecificOrder.add(email);
        dataSpecificOrder.add(emailTxt);

        dataSpecificOrder.add(Box.createHorizontalStrut(54));

        dataSpecificOrder.add(address);
        dataSpecificOrder.add(addressTxt);

        dataSpecificOrder.add(phone);
        dataSpecificOrder.add(phoneTxt);

        dataSpecificOrder.add(Box.createHorizontalStrut(470));
    }


    public void table(){
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel materialsTitle = new JLabel("Productos Ordenados");

        materialsTitle.setFont(components.createFont(0, 30));
        materialsTitle.setPreferredSize(new Dimension(380, 30));
        materialsTitle.setHorizontalTextPosition(JLabel.LEFT);

        ImageIcon iconTrash = new ImageIcon("src/Utilities/Images/Trash.png");
        ImageIcon iconEdit = new ImageIcon("src/Utilities/Images/Edit.png");
        Image imageTrash = iconTrash.getImage();
        Image imageEdit = iconEdit.getImage();
        ImageIcon defIconTrash = new ImageIcon(imageTrash.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon defIconEdit = new ImageIcon(imageEdit.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        Object[][] data = {
                {"001", "Sofacama", "01/07/2024", defIconEdit, defIconTrash},
                {"002", "Silla", "01/07/2024", defIconEdit, defIconTrash},
                {"003", "Sofacama", "01/07/2024", defIconEdit, defIconTrash},
        };

        String[] columnNames = {"Numero de Orden", "Producto", "Fecha de Entrega", "", ""};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3 || column == 4) {
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
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.setPreferredSize(new Dimension(1286, 156));

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
                if (column == 3 || column == 4) {
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

        setupTableMouseListener(table);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1286, 155));
        tableScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));
        //table.setPreferredSize(new Dimension(1286, 34));

        jPanel.add(materialsTitle, BorderLayout.NORTH);
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.add(buttons(), BorderLayout.SOUTH);
        jPanel.setBorder(new EmptyBorder(10, 55, 0, 55));

        jPanel.setPreferredSize(new Dimension(1286, 155));
        jPanel.setBackground(Color.white);
        tableScrollPane.setBackground(Color.white);

        allInfoPanel.add(Box.createVerticalStrut(35));
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

    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());

                if (column == 3) {
                    // change the content of the main panel instead of opening a new window
                    allInfoPanel.removeAll();
                    allInfoPanel.add(new UpdateOrder(allInfoPanel).addSpecificOrder(5));
                    allInfoPanel.revalidate();
                    allInfoPanel.repaint();
                } else if (column == 4) {
                    String valor = String.valueOf(table.getValueAt(row, 0).toString());
                    components.windowConfirmation("¿Está seguro de eliminar este cliente?", "Cancelar", "Eliminar", "Cliente eliminado con éxito", valor);

                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {

        } else if (e.getSource() == save) {

        } else if (e.getSource() == cancel) {
            allInfoPanel.removeAll();
            allInfoPanel.add(new CustomerList(allInfoPanel).initializeContentPanel());
            allInfoPanel.revalidate();
            allInfoPanel.repaint();
        }
    }
}
