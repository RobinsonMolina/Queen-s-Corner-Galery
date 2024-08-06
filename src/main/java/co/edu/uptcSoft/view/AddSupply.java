package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Supply;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.util.Callback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import static co.edu.uptcSoft.view.Components.createFont;

public class AddSupply {

    private Components components;
    private BorderPane contentPanel;
    private Label titleLabel;
    private TextField searchTextField;
    private Button buttonAdd;
    private Logic logic = Logic.getInstance();

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Supply> orderList2;

    private ObservableList<ObservableList<Object>> orderList;

    private HBox hBoxTitle;
    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    private String idSupply;

    public AddSupply() {
        searchTextField = new TextField();
        contentPanel = new BorderPane();
        components = new Components(); // Agrega una referencia a Components

        hBoxTitle = new HBox(300);
        hBoxTable = new HBox();
        hBoxTable.setPrefSize(1366, 400);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());

        titleLabel = new Label("Insumos");
        titleLabel.setFont(createFont(0, 40));
    }

    public BorderPane screen() {
        title();
        contentTable();
        initializeButtonAdd();

        contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
        contentPanel.setMaxWidth(screenWidth - 80);
        return contentPanel;
    }

    public void title() {
        hBoxTitle.setAlignment(Pos.CENTER_RIGHT);
        hBoxTitle.setPadding(new Insets(55, 120, 0, 0));

        searchTextField = components.createRoundedTextField(30, 30);
        searchTextField.setPadding(new Insets(0, 10, 0, 10));
        searchTextField.setPrefWidth(200);
        searchTextField.setPrefHeight(45);
        searchTextField.setFont(createFont(1, 20));

        // Create the search icon
        javafx.scene.image.Image searchIcon = new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Glass.png")).toExternalForm());
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitWidth(24);  // Width of the icon
        searchIconView.setFitHeight(24); // Height of the icon
        searchIconView.setPreserveRatio(true); // To preserve the aspect ratio

        // Create a StackPane to superpose the icon over the text field
        StackPane searchStackPane = new StackPane();
        searchStackPane.getChildren().addAll(searchTextField, searchIconView);

        // Align the icon to the right of the text field
        StackPane.setAlignment(searchIconView, Pos.CENTER_LEFT);
        searchIconView.setTranslateX(5); // Adjust the position of the icon

        // Create a listener to filter the table
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supply -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                // Adjust the logic according to the columns you want to filter
                if (supply.get(0).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by id
                } else if (supply.get(1).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Material
                } else if (supply.get(2).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Category
                } else if (supply.get(3).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Characteristics
                } else if (supply.get(4) != null && supply.get(4).toString().contains(lowerCaseFilter)) {
                    return true; // Filter by Unit Price
                }else if (supply.get(5) != null && supply.get(5).toString().contains(lowerCaseFilter)) {
                    return true; // Filter by Quantity
                }else if (supply.get(6) != null && supply.get(6).toString().contains(lowerCaseFilter)) {
                    return true; // Filter by Total
                }
                return false; // No match
            });
        });

        hBoxTitle.getChildren().addAll(titleLabel, searchStackPane);
        contentPanel.setTop(hBoxTitle);
    }

    public void contentTable() {

        //table.setPrefSize(1050, 400); // width and height of the table
        table.setPrefHeight(400);
        table.setPrefWidth(1250);

        // Create the columns
        TableColumn<ObservableList<Object>, String> id = new TableColumn<>("Código");
        id.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof String)
                    ? new SimpleStringProperty((String) row.get(0))
                    : new SimpleStringProperty("");
        });
        id.setPrefWidth(120); // Adjust the width of the column
        applyHeaderFont(id, 0, 20); // Apply the font to the header
        applyCellFont(id, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> material = new TableColumn<>("Material");
        material.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        material.setPrefWidth(150);
        applyHeaderFont(material, 0, 20); // Apply the font to the header
        applyCellFont(material, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> category = new TableColumn<>("Categoria");
        category.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        category.setPrefWidth(150);
        applyHeaderFont(category, 0, 20); // Apply the font to the header
        applyCellFont(category, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> characteristics = new TableColumn<>("Características");
        characteristics.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 3 && row.get(3) instanceof String)
                    ? new SimpleStringProperty((String) row.get(3))
                    : new SimpleStringProperty("");
        });
        characteristics.setPrefWidth(310);
        applyHeaderFont(characteristics, 0, 20); // Apply the font to the header
        applyCellFont(characteristics, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Long> unitPrice = new TableColumn<>("Valor Unitario");
        unitPrice.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 4 && row.get(4) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(4)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        unitPrice.setPrefWidth(150);
        applyHeaderFont(unitPrice, 0, 20); // Apply the font to the header
        applyCellFont(unitPrice, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Integer> quantity = new TableColumn<>("Cantidad");
        quantity.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 5 && row.get(5) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(5)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        quantity.setPrefWidth(150); // Adjust the width of the column
        applyHeaderFont(quantity, 0, 20); // Apply the font to the header
        applyCellFont(quantity, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Long> total = new TableColumn<>("Total");
        total.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 6 && row.get(6) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(6)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        total.setPrefWidth(120);
        applyHeaderFont(total, 0, 20); // Apply the font to the header
        applyCellFont(total, 0,20); // Apply the font to the cell

        table.getColumns().addAll(id, material, category, characteristics, unitPrice, quantity, total);
        applyRowStyles();

        // Call the method to initialize the filtering
        initializeFilter();

        filteredData = new FilteredList<>(getSupplyList(), p -> true);
        table.setItems(filteredData);

        // Add the table to hBoxTable and configure hBoxTable
        hBoxTable.getChildren().clear(); // Clear any previous content in hBoxTable

        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);
        hBoxTable.setPadding(new javafx.geometry.Insets(20, 0, 200, 0));


        contentPanel.setCenter(hBoxTable);
    }

    // Method to get the supply list
    public ObservableList<ObservableList<Object>> getSupplyList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        // get the supply list
        orderList2 = new ArrayList<>(logic.getSupplyList().values());

        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Supply supply : orderList2) {
            ObservableList<Object> row = FXCollections.observableArrayList(
                    supply.getId(),
                    supply.getMaterial(),
                    supply.getCategory(),
                    supply.getCharacteristics(),
                    supply.getUnitPrice(),
                    supply.getQuantity(),
                    supply.getTotalPrice()
            );
            data.add(row);
        }
        return data;
    }

    // Method to apply font to the header
    private <T> void applyHeaderFont(TableColumn<ObservableList<Object>, T> column, int style, int size) {
        javafx.scene.control.Label label = new javafx.scene.control.Label(column.getText());
        label.setFont(createFont(style, size));
        column.setText(""); // Remove the default header text
        column.setGraphic(label);
    }

    // Method to apply font to the cells
    private void applyRowStyles() {
        // Configure the factory for the rows to apply alternate row styles
        table.setRowFactory(new Callback<TableView<ObservableList<Object>>, TableRow<ObservableList<Object>>>() {
            @Override
            public TableRow<ObservableList<Object>> call(TableView<ObservableList<Object>> tableView) {
                return new TableRow<>() {
                    @Override
                    protected void updateItem(ObservableList<Object> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setStyle(""); // clear style for empty rows
                        } else {
                            // Apply alternate row colors
                            if (getIndex() % 2 == 0) {
                                setStyle("-fx-background-color: white;"); // white for even rows
                            } else {
                                setStyle("-fx-background-color: #D9D9D9;"); // grey for odd rows
                            }
                        }
                    }
                };
            }
        });
    }

    // Method to create a cell with a white background for the column 'ver', 'editar' and 'borrar'
    private <T> TableCell<ObservableList<Object>, T> createCellWithBackgroundColor(String color) {
        return new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    setText(null);
                    setGraphic((Node) item);
                    setStyle("-fx-background-color: " + color + ";");
                }
            }
        };
    }

    // Method to create button agregar
    private void initializeButtonAdd() {
        buttonAdd = new Button();
        buttonAdd.setText("Seleccionar");

        buttonAdd.setOnAction(event -> {
            NewOrder newOrder = new NewOrder();

            contentPanel.getChildren().clear();
            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPanel.getChildren().add(newOrder.screen());
        });

        buttonAdd.setPrefSize(150, 34);

        buttonAdd.getStyleClass().add("rounded-button");
        buttonAdd.getStyleClass().add("rounded-button:hover");
        buttonAdd.getStyleClass().add("rounded-button:pressed");
        buttonAdd.setFont(createFont(0, 20));
        HBox buttonAddBox = new HBox(buttonAdd);
        buttonAddBox.setAlignment(Pos.CENTER_RIGHT);
        buttonAddBox.setPadding(new Insets(-300, 120, 0, 0));
        contentPanel.setBottom(buttonAddBox);
    }

    //  Method to initialize the filter
    private void initializeFilter() {
        // Initialize the filtered list with the original data
        filteredData = new FilteredList<>(getSupplyList(), p -> true);

        // Configure the filter for the TextField
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(row -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all rows if the search field is empty
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter by any String column
                return row.stream().anyMatch(data -> {
                    if (data instanceof String) {
                        return ((String) data).toLowerCase().contains(lowerCaseFilter);
                    }
                    return false;
                });
            });
        });

        // Set the `FilteredList` in the table
        table.setItems(filteredData);
    }

    // Method to apply font to the cells
    private <T> void applyCellFont(TableColumn<ObservableList<Object>, T> column, int style, int size) {
        column.setCellFactory(new Callback<TableColumn<ObservableList<Object>, T>, TableCell<ObservableList<Object>, T>>() {
            @Override
            public TableCell<ObservableList<Object>, T> call(TableColumn<ObservableList<Object>, T> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                            setFont(components.createFont(1, 20));
                        }
                    }
                };
            }
        });
    }
}
