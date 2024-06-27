package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;

public class UpdateSupplie extends JFrame {

    JPanel contentPanel;
    JPanel contentTitle;
    JPanel materialPanel;
    JPanel quantityPanel;
    JTextField searchField;
    JPanel contentButton;
    JLabel titleLabel;

    public UpdateSupplie() {
        setTitle("Actualizar Insumo");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Simulación de cabeceras personalizadas
        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        contentPanel();
        setVisible(true);
    }

    public void contentPanel() {
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1366, 670));
        contentPanel.setBackground(Color.WHITE);

        // Panel de título
        contentTitle = new JPanel(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setBounds(0, 0, 1366, 100);

        JLabel titleLabel = new JLabel("Actualizar Insumo");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 1366, 100);

        contentTitle.add(titleLabel);

        // Panel de materiales
        materialPanel = new JPanel(null);
        materialPanel.setBackground(Color.decode("#D9D9D9"));
        materialPanel.setBounds(143, 100, 1000, 240);

        JLabel materialLabel = new JLabel("Material");
        materialLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        materialLabel.setForeground(Color.BLACK);
        materialLabel.setBounds(60, 50, 400, 25);

        materialPanel.add(materialLabel);

        JLabel categoryLabel = new JLabel("Categoria");
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        categoryLabel.setForeground(Color.BLACK);
        categoryLabel.setBounds(560, 50, 400, 25);

        materialPanel.add(categoryLabel);

        JTextField materialTextField = new JTextField();
        materialTextField.setBounds(60, 100, 400, 25);
        materialPanel.add(materialTextField);

        JTextField categoryTextField = new JTextField();
        categoryTextField.setBounds(560, 100, 400, 25);
        materialPanel.add(categoryTextField);

        JLabel characteristicLabel = new JLabel("Características");
        characteristicLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        characteristicLabel.setForeground(Color.BLACK);
        characteristicLabel.setBounds(60, 150, 400, 25);

        materialPanel.add(characteristicLabel);

        JTextField characteristicTextField = new JTextField();
        characteristicTextField.setBounds(60, 200, 400, 25);
        materialPanel.add(characteristicTextField);

        // Panel de cantidad
        quantityPanel = new JPanel(null);
        quantityPanel.setBackground(Color.decode("#D9D9D9"));
        quantityPanel.setBounds(143, 350, 1000, 240);

        JLabel quantityLabel = new JLabel("Cantidad");
        quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        quantityLabel.setForeground(Color.BLACK);
        quantityLabel.setBounds(60, 50, 200, 25);

        quantityPanel.add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(60, 100, 50, 25);
        quantityPanel.add(quantityTextField);
        JTextField quantityTextField2 = new JTextField();
        quantityTextField2.setBounds(110, 100, 50, 25);
        quantityPanel.add(quantityTextField2);

        JLabel measurementLabel = new JLabel("Unidad");
        measurementLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        measurementLabel.setForeground(Color.BLACK);
        measurementLabel.setBounds(310, 50, 100, 25);

        quantityPanel.add(measurementLabel);

        JTextField measurementTextField = new JTextField();
        measurementTextField.setBounds(300, 100, 50, 25);
        quantityPanel.add(measurementTextField);
        JTextField measurementTextField2 = new JTextField();
        measurementTextField2.setBounds(350, 100, 50, 25);
        quantityPanel.add(measurementTextField2);

        JLabel unitaryLabel = new JLabel("Valor Unitario");
        unitaryLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        unitaryLabel.setForeground(Color.BLACK);
        unitaryLabel.setBounds(560, 50, 400, 25);

        quantityPanel.add(unitaryLabel);

        JTextField unitaryTextField = new JTextField();
        unitaryTextField.setBounds(560, 100, 400, 25);
        quantityPanel.add(unitaryTextField);

        JLabel totalLabel = new JLabel("Total");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        totalLabel.setForeground(Color.BLACK);
        totalLabel.setBounds(60, 150, 300, 25);

        quantityPanel.add(totalLabel);

        JTextField totalTextField = new JTextField();
        totalTextField.setBounds(60, 200, 300, 25);
        quantityPanel.add(totalTextField);

        //Buttons cancel and save
        RoundedButton cancelButton = new RoundedButton("Agregar");
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setBounds(720, 190, 150, 40);
        quantityPanel.add(cancelButton);

        RoundedButton saveButton = new RoundedButton("Cancalar");
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.setBounds(560, 190, 150, 40);
        quantityPanel.add(saveButton);

        contentPanel.add(contentTitle);
        contentPanel.add(materialPanel);
        contentPanel.add(quantityPanel);

        add(contentPanel);
    }
    // Clase para el botón redondeado
    public class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setFont(new Font("Serif", Font.BOLD, 20));
            setForeground(Color.WHITE);
            setBackground(Color.decode("#2F1940"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        UpdateSupplie updateSupplies = new UpdateSupplie();
    }
}
