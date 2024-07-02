package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class NewSupplie extends JFrame {

    private Components components;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel materialPanel;
    private JPanel quantityPanel;
    private JLabel materialLabel;
    private JLabel categoryLabel;
    private JLabel characteristicLabel;
    private JLabel quantityLabel;
    private JLabel measurementLabel;
    private JLabel unitaryLabel;
    private JLabel totalLabel;
    private JTextField materialTextField;
    private JTextField categoryTextField;
    private JTextField characteristicTextField;
    private JTextField unitaryTextField;
    private JTextField totalTextField;
    private JComboBox<Integer> comboBox;
    private JComboBox<String> comboBox2;
    private JButton addButton;
    private JButton saveButton;
    private JLabel titleLabel;

    public NewSupplie() {
        setTitle("Nuevo Insumo");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximice window on open
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Menu
        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        components = new Components();
        initializeContentPanel();
        setVisible(true);
    }

    // Method for initializing content panel
    public void initializeContentPanel() {
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1366, 670));
        contentPanel.setBackground(Color.WHITE);

        // Panel title
        initializeContentTitle();

        // Panel de materiales
        panelCenter();

        // Panel cantidad
        panelSouth();
    }

    // Method for creating title and search field
    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // title label
        titleLabel = new JLabel("Nuevo Insumo", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);
    }

    // Method for panel center
    private void panelCenter(){
        // Panel materiales
        materialPanel = new JPanel(null);
        materialPanel.setBackground(Color.decode("#D9D9D9"));
        materialPanel.setBounds(143, 100, 1000, 240);

        // Label material
        materialLabel = new JLabel("Material");
        materialLabel.setFont(components.createFont(0, 24));
        materialLabel.setForeground(Color.BLACK);
        materialLabel.setBounds(60, 40, 400, 25);

        materialPanel.add(materialLabel);

        // Label category
        categoryLabel = new JLabel("Categoria");
        categoryLabel.setFont(components.createFont(0, 24));
        categoryLabel.setForeground(Color.BLACK);
        categoryLabel.setBounds(560, 32, 400, 35);

        materialPanel.add(categoryLabel);

        // Text field material
        materialTextField = components.createRoundedTextField(30,30);
        materialTextField.setBounds(60, 90, 400, 34);
        materialTextField.setFont(components.createFont(1, 20));
        materialTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        materialPanel.add(materialTextField);

        // Text field category
        categoryTextField = components.createRoundedTextField(30,30);
        categoryTextField.setBounds(560, 90, 400, 34);
        categoryTextField.setFont(components.createFont(1, 20));
        categoryTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        materialPanel.add(categoryTextField);

        // Label characteristic
        characteristicLabel = new JLabel("Características");
        characteristicLabel.setFont(components.createFont(0, 24));
        characteristicLabel.setForeground(Color.BLACK);
        characteristicLabel.setBounds(60, 140, 400, 25);
        materialPanel.add(characteristicLabel);

        // Text field characteristic
        characteristicTextField = components.createRoundedTextField(30,30);
        characteristicTextField.setBounds(60, 190, 400, 34);
        characteristicTextField.setFont(components.createFont(1, 20));
        characteristicTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        materialPanel.add(categoryTextField);
        materialPanel.add(characteristicTextField);
    }

    // Method for panel south
    private void panelSouth(){
        // Panel cantidad
        quantityPanel = new JPanel(null);
        quantityPanel.setBackground(Color.decode("#D9D9D9"));
        quantityPanel.setBounds(143, 350, 1000, 240);

        // Label quantity
        quantityLabel = new JLabel("Cantidad");
        quantityLabel.setFont(components.createFont(0, 24));
        quantityLabel.setForeground(Color.BLACK);
        quantityLabel.setBounds(60, 40, 200, 25);
        quantityPanel.add(quantityLabel);

        // Combo box quantity
        Integer options[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Combo box quantity
        comboBox = new JComboBox<>(options);
        comboBox.setFont(components.createFont(1, 20));
        comboBox.setBounds(60, 90, 85, 30);
        comboBox.setMaximumRowCount(4);
        quantityPanel.add(comboBox);

        // Label measurement
        measurementLabel = new JLabel("Unidad");
        measurementLabel.setFont(components.createFont(0, 24));
        measurementLabel.setForeground(Color.BLACK);
        measurementLabel.setBounds(310, 40, 100, 25);
        quantityPanel.add(measurementLabel);

        // Combo box measurement
        String options2[] = {"cm", "CM²", "M", "m²", "Kg", "Unidades"};

        // Combo box measurement
        comboBox2 = new JComboBox<>(options2);
        comboBox2.setFont(components.createFont(1, 20));
        comboBox2.setBounds(296, 90, 105, 30);
        comboBox2.setMaximumRowCount(4);
        quantityPanel.add(comboBox2);

        unitaryLabel = new JLabel("Valor Unitario");
        unitaryLabel.setFont(components.createFont(0, 24));
        unitaryLabel.setForeground(Color.BLACK);
        unitaryLabel.setBounds(560, 40, 400, 25);
        quantityPanel.add(unitaryLabel);

        // Text field unitary
        unitaryTextField = components.createRoundedTextField(30,30);
        unitaryTextField.setBounds(560, 90, 400, 34);
        unitaryTextField.setFont(components.createFont(1, 20));
        unitaryTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        quantityPanel.add(unitaryTextField);

        // JLabel total
        totalLabel = new JLabel("Total");
        totalLabel.setFont(components.createFont(0, 24));
        totalLabel.setForeground(Color.BLACK);
        totalLabel.setBounds(60, 140, 300, 25);
        quantityPanel.add(totalLabel);

        // Text field total
        totalTextField = components.createRoundedTextField(30,30);
        totalTextField.setBounds(60, 190, 300, 34);
        totalTextField.setFont(components.createFont(1, 20));
        totalTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        materialPanel.add(totalTextField);
        quantityPanel.add(totalTextField);

        //Buttons cancel and save
        addButton = components.createRoundedButton("Agregar");
        addButton.setFont(components.createFont(1, 20));
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setBounds(810, 180, 150, 40);
        quantityPanel.add(addButton);

        saveButton = components.createRoundedButton("Cancelar");
        saveButton.setFont(components.createFont(1, 20));
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.setBounds(650, 180, 150, 40);
        quantityPanel.add(saveButton);

        contentPanel.add(contentTitle);
        contentPanel.add(materialPanel);
        contentPanel.add(quantityPanel);

        add(contentPanel);
    }

    public static void main(String[] args) {
        NewSupplie newSupplies = new NewSupplie();
    }
}
