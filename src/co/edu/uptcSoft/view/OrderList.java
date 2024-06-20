package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderList extends JFrame {

    JPanel contentPanel;
    public OrderList() {
        setTitle("Order List");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        contentPanel();
        setVisible(true);
    }

    public void contentPanel() {
        JPanel mainPanel = new JPanel(null);
        JPanel icons = new JPanel(new GridLayout(11,3));

        // Panel para el título (usando BorderLayout para centrar el título)
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBounds(0, 0, 1100, 100);

        JLabel titleLabel = new JLabel("Lista De Ordenes", SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(titlePanel);


        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega"};
        Object[][] data = {
                {"001", "SofaCama", "Juan David Pérez", "3001234567", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"003", "Cojines", "Carlos Andrés Gómez", "323456789", "15/06/2024"},
                {"004", "Lampara", "Luisa Fernanda Fernández", "3109876543", "17/06/2024"},
                {"005", "Sofa", "Andrés Felipe López", "316879801", "17/06/2024"},
                {"006", "SofaCama", "Ana María Torres", "318678902", "20/06/2024"},
                {"007", "SofaCama", "Diego Alejandro Martínez", "317890123", "20/06/2024"},
                {"008", "SofaCama", "Laura Patricia Ramírez", "3198901234", "20/06/2024"},
                {"009", "Lampara", "Pedro José Jiménez", "3109021345", "25/06/2024"},
                {"010", "Mesa De Centro", "Sofía Margarita Morales", "3190173456", "25/06/2024"},
                {"011", "Cojines", "Jorge Luis Ríos", "3111234567", "25/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"},
                {"002", "Mesa De Centro", "María Alejandra Rodríguez", "339876543", "15/06/2024"}
        };

        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(34);
        TableColumn column = null;

        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150); // Primera columna
            } else if (i == 1) {
                column.setPreferredWidth(200);  // Segunda columna
            } else if (i == 2) {
                column.setPreferredWidth(300); // Tercera columna
            }else if (i == 3) {
                column.setPreferredWidth(200); // Cuarta columna
            } else
            {
                column.setPreferredWidth(250); // Quinta columna
            }
        }


        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(65, 112, 1103, 408);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));


        mainPanel.add(tableScrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Botón Agregar
        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");
            }
        });

        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel);
        add(mainPanel);
    }
}
