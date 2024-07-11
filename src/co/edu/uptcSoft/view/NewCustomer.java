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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class NewCustomer {
    private JFrame specificOrderWindow;
    private JPanel allInformation;
    private JPanel allInfoPanel;
    private JPanel window;
    private JPanel dataSpecificOrder;
    private Components components;

    public NewCustomer() {
        specificOrderWindow = new JFrame("Crear Cliente");
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
    }

    public JPanel addSpecificOrder(){
        JLabel title = new JLabel("Crear Cliente");

        allInformation.setPreferredSize(new Dimension(1366, 700));
        allInfoPanel.setPreferredSize(new Dimension(1366, 700));

        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 40));
        title.setPreferredSize(new Dimension(389, 47));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        allInfoPanel.add(Box.createVerticalStrut(30));
        allInfoPanel.add(title);
        allInfoPanel.add(Box.createVerticalStrut(30));
        setSpecificData();
        dataSpecificOrder.setPreferredSize(new Dimension(886, 170));
        dataSpecificOrder.setBorder(new EmptyBorder(0, 145, 0, 145));

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

        JTextField nameTxt = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JTextField documentTxt = new JTextField();
        JTextField addressTxt = new JTextField();

        int borderRadius = 5;
        Color borderColor = Color.decode("#2F1940");

        nameTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        emailTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        addressTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        phoneTxt.setBorder(new RoundedBorder(borderRadius, borderColor));
        documentTxt.setBorder(new RoundedBorder(borderRadius, borderColor));

        name.setPreferredSize(new Dimension(143, 30));
        email.setPreferredSize(new Dimension(143, 30));
        address.setPreferredSize(new Dimension(143, 30));

        nameTxt.setPreferredSize(new Dimension(288, 30));
        emailTxt.setPreferredSize(new Dimension(288, 30));
        addressTxt.setPreferredSize(new Dimension(288, 30));

        phone.setPreferredSize(new Dimension(143, 30));
        document.setPreferredSize(new Dimension(143, 30));

        phoneTxt.setPreferredSize(new Dimension(288, 30));
        documentTxt.setPreferredSize(new Dimension(288, 30));

        dataSpecificOrder.add(name);
        dataSpecificOrder.add(nameTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(20));

        dataSpecificOrder.add(document);
        dataSpecificOrder.add(documentTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(20));

        dataSpecificOrder.add(email);
        dataSpecificOrder.add(emailTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(20));

        dataSpecificOrder.add(address);
        dataSpecificOrder.add(addressTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(20));

        dataSpecificOrder.add(phone);
        dataSpecificOrder.add(phoneTxt);
        dataSpecificOrder.add(Box.createHorizontalStrut(20));

        dataSpecificOrder.add(Box.createHorizontalStrut(451));
    }


    public void table(){
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel materialsTitle = new JLabel("Productos Ordenados");

        materialsTitle.setFont(new Font(materialsTitle.getFont().getName(), Font.PLAIN, 30));
        materialsTitle.setPreferredSize(new Dimension(380, 30));
        materialsTitle.setHorizontalTextPosition(JLabel.LEFT);

        ImageIcon icon = new ImageIcon("src/Utilities/Images/Trash.png");
        Image image = icon.getImage();
        ImageIcon defIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        Object[][] data = {

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
        table.setPreferredSize(new Dimension(1134, 156));

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
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(30, 0, 0, 0));
        //table.setPreferredSize(new Dimension(1134, 34));

        jPanel.add(materialsTitle, BorderLayout.NORTH);
        jPanel.add(tableScrollPane, BorderLayout.CENTER);
        jPanel.add(buttons(), BorderLayout.SOUTH);
        jPanel.setBorder(new EmptyBorder(10, 20, 0, 20));

        jPanel.setPreferredSize(new Dimension(1366, 136));
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
        JButton add = new JButton("+ Orden");
        JButton save = new JButton("Aceptar");
        JButton cancel = new JButton("Cancelar");

        buttons.add(Box.createHorizontalStrut(700));
        buttons.add(add);
        buttons.add(save);
        buttons.add(cancel);

        add.setBackground(Color.decode("#2F1940"));
        save.setBackground(Color.decode("#2F1940"));
        cancel.setBackground(Color.decode("#2F1940"));

        add.setFont(new Font(add.getFont().getName(), Font.PLAIN, 20));
        save.setFont(new Font(add.getFont().getName(), Font.PLAIN, 20));
        cancel.setFont(new Font(add.getFont().getName(), Font.PLAIN, 20));

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
                    allInfoPanel.add(new UpdateOrder().addSpecificOrder());
                    allInfoPanel.revalidate();
                    allInfoPanel.repaint();
                } else if (column == 4) {
                    long valor = Long.parseLong(table.getValueAt(row, 0).toString());
                    components.windowConfirmation("¿Está seguro de eliminar esta orden?", "Cancelar", "Eliminar", "Orden eliminada con éxito", valor);

                }
            }
        });
    }
}
