package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Supply;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSupplY extends JFrame implements ActionListener {

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
    private JTextField characteristicTextField;
    private JTextField unitaryTextField;
    private JTextField totalTextField;
    private JComboBox<String> comboBoxCategory;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox2;
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel titleLabel;
    private JPanel mainContentPanel;
    private Logic logic = Logic.getInstance();
    private String id;

    public UpdateSupplY(JPanel mainContentPanel) {
        this.mainContentPanel = mainContentPanel;
        components = new Components(mainContentPanel);
    }

    public JPanel initializeContentPanel(String id) {
        contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1366, 670));
        contentPanel.setBackground(Color.WHITE);
        this.id = id;

        // Panel title
        initializeContentTitle();

        // Panel de materiales
        panelCenter();

        // Panel cantidad
        panelSouth();
        getValues();

        return contentPanel;
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
        titleLabel.setBounds(0, 0, 1366, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        contentTitle.setBounds(0, 0, 1366, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);
    }

    private void panelCenter(){
        // Panel materiales
        materialPanel = new JPanel(null);
        materialPanel.setBackground(Color.decode("#D9D9D9"));
        materialPanel.setBounds(183, 100, 1000, 240);

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

        // Combo box category
        String options[] = {"Telas", "Muebles", "Decorativos", "Artesanias"};

        // Combo box quantity
        comboBoxCategory = new JComboBox<>(options);
        comboBoxCategory.setSelectedItem(1);
        comboBoxCategory.setFont(components.createFont(1, 20));
        comboBoxCategory.setBounds(560, 90, 150, 30);
        comboBoxCategory.setMaximumRowCount(4);
        materialPanel.add(comboBoxCategory);

        comboBoxCategory.addActionListener(this);

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
        materialPanel.add(characteristicTextField);
    }

    //panel sur
    private void panelSouth(){
        // Panel cantidad
        quantityPanel = new JPanel(null);
        quantityPanel.setBackground(Color.decode("#D9D9D9"));
        quantityPanel.setBounds(183, 350, 1000, 240);

        // Label quantity
        quantityLabel = new JLabel("Cantidad");
        quantityLabel.setFont(components.createFont(0, 24));
        quantityLabel.setForeground(Color.BLACK);
        quantityLabel.setBounds(60, 40, 200, 25);
        quantityPanel.add(quantityLabel);

        // Combo box quantity
        String options[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        // Combo box quantity
        comboBox = new JComboBox<>(options);
        comboBox.setFont(components.createFont(1, 20));
        comboBox.setBounds(60, 90, 85, 30);
        comboBox.setMaximumRowCount(6);
        quantityPanel.add(comboBox);

        // Add ActionListener to combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTotal();
            }
        });

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

        // Method for updating the total when the unitary text field changes
        unitaryTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotal();
            }
        });

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
            getMaterials();
            // Cambiar el contenido del panel principal en lugar de abrir una nueva ventana
            mainContentPanel.removeAll();
            mainContentPanel.add(new SupplyList(mainContentPanel).initializeContentPanel());
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
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
            // Cambiar el contenido del panel principal en lugar de abrir una nueva ventana
            mainContentPanel.removeAll();
            mainContentPanel.add(new SupplyList(mainContentPanel).initializeContentPanel());
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        }
    }

    public void getMaterials() {
        logic.updateSupply(id, materialTextField.getText(), comboBoxCategory.getSelectedItem().toString(), characteristicTextField.getText(), Integer.parseInt(comboBox.getSelectedItem().toString()), comboBox2.getSelectedItem().toString(), Long.parseLong(unitaryTextField.getText()), Long.parseLong(totalTextField.getText()));
    }


    private void updateTotal() {
        int quanty = Integer.parseInt(comboBox.getSelectedItem().toString());
        String unitPrice = unitaryTextField.getText();
        int price = unitPrice.isEmpty() ? 0 : Integer.parseInt(unitPrice);
        int result = quanty * price;
        totalTextField.setText(String.valueOf(result));
    }

    public void getValues() {
        Supply supply = logic.searchSupply(id);
        materialTextField.setText(supply.getMaterial());
        comboBoxCategory.setSelectedItem(supply.getCategory());
        characteristicTextField.setText(supply.getCharacteristics());
        comboBox.setSelectedItem(String.valueOf(supply.getQuantity()));
        comboBox2.setSelectedItem(supply.getUnit());
        unitaryTextField.setText(String.valueOf(supply.getUnitPrice()));
        totalTextField.setText(String.valueOf(supply.getTotalPrice()));
    }
}
