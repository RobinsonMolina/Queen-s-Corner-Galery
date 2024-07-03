package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class UpdateSupplie extends JFrame implements ActionListener {

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
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel titleLabel;

    public UpdateSupplie() {
        setTitle("Actualizar Insumo");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximice window on open
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Simulación de cabeceras personalizadas
        HeaderMenu headerMenu = new HeaderMenu();
        add(headerMenu.getHeaderPanel(), BorderLayout.NORTH);
        add(headerMenu.getMenuPanel(), BorderLayout.WEST);

        components = new Components();
        initializeContentPanel();
        setVisible(true);
    }

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

    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // title label
        titleLabel = new JLabel("Actualizar Insumo", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);
    }

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

    //panel sur
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
        comboBox.setMaximumRowCount(6);
        quantityPanel.add(comboBox);

        // Label measurement
        measurementLabel = new JLabel("Unidad");
        measurementLabel.setFont(components.createFont(0, 24));
        measurementLabel.setForeground(Color.BLACK);
        measurementLabel.setBounds(310, 40, 100, 25);
        comboBox.setMaximumRowCount(4);
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
        updateButton = components.createRoundedButton("Actualizar", "#000000", "#2F1940", 30, 30);
        updateButton.setFont(components.createFont(1, 20));
        updateButton.setPreferredSize(new Dimension(150, 40));
        updateButton.setBounds(810, 180, 150, 40);
        quantityPanel.add(updateButton);

        cancelButton = components.createRoundedButton("Cancelar", "#000000", "#2F1940", 30, 30);
        cancelButton.setFont(components.createFont(1, 20));
        cancelButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setBounds(650, 180, 150, 40);
        quantityPanel.add(cancelButton);

        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);

        contentPanel.add(contentTitle);
        contentPanel.add(materialPanel);
        contentPanel.add(quantityPanel);

        add(contentPanel);
    }

    // method to choose the actions of the cancel button and the update button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            dispose();
            Supplies supplies = new Supplies();
            components.messageConfirmation("Insumo actualizado con éxito");
            // Starts a timer to close the window after 1 seconds
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    components.getConfirmationFrame2().dispose();
                }
            });
            timer.setRepeats(false); // To make the timer only execute once
            timer.start();
        } else if (e.getSource() == cancelButton) {
            Supplies supplies = new Supplies();
        }
    }
}
