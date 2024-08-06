package co.edu.uptcSoft.view;



import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Supply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import static co.edu.uptcSoft.view.Components.createFont;

public class NewSupply {

    private Pane contentPane;
    private BorderPane contentPane2;
    private Label titleLabel;
    private HBox titleBox;
    private HBox pane1;
    private VBox materialBox;
    private HBox quantityBox;
    private HBox quantityComboBox;
    private HBox totalBox;
    private HBox totalFieldBox;
    private HBox buttonsBox;
    private HBox totalButtonsBox;
    private VBox pane2;
    private ComboBox<Integer> comboBoxQuantity;
    private ComboBox<String> comboBoxUnit;
    private TextField unitaryTextField;
    private TextField totalTextField;
    private Button cancelButton;
    private Button addButton;
    private Label quantityLabel;
    private Label unitLabel;
    private Label priceLabel;
    private Label totalLabel;

    private Label materialLabel;
    private TextField materialTextField;
    private Label characteristicLabel;
    private TextField characteristicTextField;
    private VBox categoryBox;
    private Label categoryLabel;
    private ComboBox<String> comboBoxCategory;

    private Logic logic = Logic.getInstance();
    private Components components;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public NewSupply() {
        components = new Components();
        contentPane = new Pane();
        contentPane2 = new BorderPane();
        contentPane2.setStyle("-fx-background-color: #FFFFFF;");
        titleBox = new HBox();
    }

    public Pane screen() {
        contentPane2.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString()); // Carga el CSS
        title();
        panelCenter();
        panelSouth();
        initialiceButtonCancel();
        initializeButtonAdd();

        contentPane.getChildren().add(contentPane2);

        return contentPane;
    }

    public void title() {

        titleBox.setPrefSize(1366, 40);
        titleLabel = new Label("Nuevo Insumo");
        titleLabel.setFont(createFont(0, 40));
        titleBox.getChildren().add(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 100, 0, 100));

        contentPane2.setTop(titleBox);
    }

    public void panelCenter() {

        pane1 = new HBox(200);
        pane1.setPadding(new Insets(0, 50, 20, 200));  // Margen en todos los lados
        //pane1.setPrefSize(400, 200); // Tamaño preferido para que parezca una tarjeta

        materialBox = new VBox(10);
        materialBox.setPadding(new Insets(30));

        materialLabel = new Label("Material");
        materialLabel.setFont(createFont(0, 20));
        materialLabel.setTextFill(Color.BLACK);

        materialTextField = new TextField();
        materialTextField.setPrefWidth(400);
        materialTextField.setFont(createFont(1, 20));
        materialTextField.getStyleClass().add("rounded-textfield");

        characteristicLabel = new Label("Características");
        characteristicLabel.setFont(createFont(0, 20));
        characteristicLabel.setTextFill(Color.BLACK);

        characteristicTextField = new TextField();
        characteristicTextField.setPrefWidth(400);
        characteristicTextField.setFont(createFont(1, 20));
        characteristicTextField.getStyleClass().add("rounded-textfield");

        materialBox.getChildren().addAll(materialLabel, materialTextField, characteristicLabel, characteristicTextField);

        categoryBox = new VBox(10);
        categoryBox.setPrefWidth(150);
        categoryBox.setPrefHeight(30);
        categoryBox.setPadding(new Insets(30));

        categoryLabel = new Label("Categoria");
        categoryLabel.setFont(createFont(0, 20));
        categoryLabel.setTextFill(Color.BLACK);

        comboBoxCategory = new ComboBox<>();
        comboBoxCategory.getItems().addAll("Telas", "Muebles", "Decorativos", "Artesanias");
        comboBoxCategory.setValue("Telas");

        categoryBox.getChildren().addAll(categoryLabel, comboBoxCategory);

        pane1.getChildren().addAll(materialBox, categoryBox);
        pane1.getStyleClass().add("panel-center");

        // Envolver pane1 en un VBox para evitar que se extienda
        VBox centerWrapper = new VBox(pane1);
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.setPadding(new Insets(20, 50, 20, 50));

        contentPane2.setCenter(centerWrapper);
    }

    public void panelSouth() {

        pane2 = new VBox(20);
        pane2.setPadding(new Insets(10, 50, 50, 0));  // Margen en todos los lados
        pane2.setPrefSize(400, 200); // Tamaño preferido para que parezca una tarjeta

        quantityLabel = new Label("Cantidad");
        quantityLabel.setFont(createFont(0, 20));
        quantityLabel.setTextFill(Color.BLACK);

        unitLabel = new Label("Unidad");
        unitLabel.setFont(createFont(0, 20));
        unitLabel.setTextFill(Color.BLACK);

        priceLabel = new Label("Valor Unitario");
        priceLabel.setFont(createFont(0, 20));
        priceLabel.setTextFill(Color.BLACK);
        priceLabel.setPadding(new Insets(0, 0, 0, 150));

        quantityBox = new HBox(200);
        quantityBox.getChildren().addAll(quantityLabel, unitLabel, priceLabel);
        quantityBox.setAlignment(Pos.CENTER_LEFT);
        quantityBox.setPadding(new Insets(0, 0, 0, 200));

        comboBoxQuantity = new ComboBox<>();
        comboBoxQuantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        comboBoxQuantity.setValue(1);

        comboBoxUnit = new ComboBox<>();
        comboBoxUnit.getItems().addAll("Cm", "Cm²", "M", "M²", "Kg", "Unidades");
        comboBoxUnit.setValue("Cm");

        TextField priceTextField = new TextField();
        priceTextField.setPrefWidth(400);
        priceTextField.setFont(createFont(1, 20));
        priceTextField.getStyleClass().add("rounded-textfield");

        quantityComboBox = new HBox(200);
        quantityComboBox.getChildren().addAll(comboBoxQuantity, comboBoxUnit, priceTextField);
        quantityComboBox.setAlignment(Pos.CENTER_LEFT);
        quantityComboBox.setPadding(new Insets(0, 0, 0, 200));

        totalLabel = new Label("Total");
        totalLabel.setFont(createFont(0, 20));
        totalLabel.setTextFill(Color.BLACK);

        totalBox = new HBox(0);
        totalBox.getChildren().addAll(totalLabel);
        totalBox.setAlignment(Pos.CENTER_LEFT);
        totalBox.setPadding(new Insets(0, 0, 0, 370));

        unitaryTextField = new TextField();
        unitaryTextField.setPrefWidth(400);
        unitaryTextField.setFont(createFont(1, 20));
        unitaryTextField.getStyleClass().add("rounded-textfield");

        totalFieldBox = new HBox(0);
        totalFieldBox.getChildren().addAll(unitaryTextField);
        totalFieldBox.setAlignment(Pos.CENTER_LEFT);
        totalFieldBox.setPadding(new Insets(0, 0, 0, 0));

        cancelButton = new Button("Cancelar");
        cancelButton.setFont(createFont(1, 20));
        cancelButton.getStyleClass().add("rounded-button");
        cancelButton.setOnAction(event -> initialiceButtonCancel());

        addButton = new Button("Agregar");
        addButton.setFont(createFont(1, 20));
        addButton.getStyleClass().add("rounded-button");

        buttonsBox = new HBox(50);
        buttonsBox.getChildren().addAll(cancelButton, addButton);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);
        buttonsBox.setPadding(new Insets(0, 0, 0, 0));

        totalButtonsBox = new HBox(300);
        totalButtonsBox.getChildren().addAll(totalFieldBox, buttonsBox);
        totalButtonsBox.setAlignment(Pos.CENTER_LEFT);
        totalButtonsBox.setPadding(new Insets(0, 0, 0, 200));

        pane2.getChildren().addAll(quantityBox, quantityComboBox, totalBox, totalButtonsBox);
        pane2.setAlignment(Pos.CENTER_LEFT);
        pane2.getStyleClass().add("panel-center");

        // Envolver pane2 en un VBox para evitar que se extienda
        VBox southWrapper = new VBox(pane2);
        southWrapper.setAlignment(Pos.CENTER);
        southWrapper.setPadding(new Insets(20, 50, 20, 50));

        contentPane2.setBottom(southWrapper);
    }

    public void initialiceButtonCancel(){
        cancelButton.setOnAction(event -> {
            SupplyList supplyList = new SupplyList();
            // Clear current content
            contentPane.getChildren().clear();
            // Add the new content
            contentPane.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPane.getChildren().add(supplyList.screen());
        });
    }

    public void initializeButtonAdd(){
        addButton.setOnAction(event -> {
            if(validateFields()) {
                getMaterials();
                SupplyList supplyList = new SupplyList();
                // Clear current content
                contentPane.getChildren().clear();
                // Add the new content
                contentPane.setMinSize(screenWidth - 80, screenHeight - 80);
                contentPane.getChildren().add(supplyList.screen());
                components.messageConfirmation("Insumo agregado con éxito");
            }
        });
    }

    public void getMaterials(){
        logic.addSupply(materialTextField.getText(), comboBoxCategory.getValue(),
                characteristicTextField.getText(), comboBoxQuantity.getValue(),
                comboBoxUnit.getValue(), Integer.parseInt(unitaryTextField.getText()),
                Integer.parseInt(totalTextField.getText()));
    }

    private void updateTotal() {
        int quantity = comboBoxQuantity.getValue();
        String unitPrice = unitaryTextField.getText();
        int price = unitPrice.isEmpty() ? 0 : Integer.parseInt(unitPrice);
        int result = quantity * price;
        totalTextField.setText(String.valueOf(result));
    }

    private boolean validateFields() {
        if (materialTextField.getText().isEmpty()) {
            components.messageConfirmation("El material no puede estar vacío");
            return false;
        }
        if (characteristicTextField.getText().isEmpty()) {
            components.messageConfirmation("La característica no puede estar vacía");
            return false;
        }
        if (unitaryTextField.getText().isEmpty()) {
            components.messageConfirmation("El valor unitario no puede ser 0");
            return false;
        }
        return true;
    }
}
