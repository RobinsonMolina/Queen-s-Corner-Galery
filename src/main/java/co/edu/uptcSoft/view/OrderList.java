package co.edu.uptcSoft.view;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Order;
import co.edu.uptcSoft.logic.Logic;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.util.Callback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import static co.edu.uptcSoft.view.Components.createFont;

public class OrderList {

    private Components components;
    private BorderPane contentPanel;
    private Label titleLabel;
    private TextField searchTextField;
    private Button buttonAdd;
    private Logic logic = Logic.getInstance();

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Order> orderList2;

    private ObservableList<ObservableList<Object>> orderList;

    private HBox hBoxTitle;
    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public OrderList() {
        searchTextField = new TextField();
        contentPanel = new BorderPane();
        components = new Components(OrderList.this);

        hBoxTitle = new HBox(300);
        hBoxTable = new HBox();
        hBoxTable.setPrefSize(1366, 400);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());


        titleLabel = new Label("Lista De Ordenes");
        titleLabel.setFont(createFont(0, 40));
    }

    public BorderPane screen() {
        title();
        contentTable();
        initializeButtonAdd();
        return contentPanel;
    }

    public void title() {
        hBoxTitle.setAlignment(Pos.CENTER_RIGHT);
        hBoxTitle.setPadding(new Insets(55, 80, 0, 0));

        titleLabel.setFont(components.createFont(0, 40));
        searchTextField = components.createRoundedTextField(30, 30);
        searchTextField.setPadding(new Insets(0, 0, 0, 10));
        searchTextField.setPrefWidth(200);
        searchTextField.setPrefHeight(45);
        searchTextField.setFont(components.createFont(1, 20));

        // Create the search icon
        Image searchIcon = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Glass.png")).toExternalForm());
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
            filteredData.setPredicate(order -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Adjust the logic according to the columns you want to filter
                if (order.get(0).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Order Number
                } else if (order.get(1).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Product
                } else if (order.get(2).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Customer Name
                } else if (order.get(3) != null && order.get(3).toString().contains(lowerCaseFilter)) {
                    return true; // Filter by Phone
                } else if (order.get(4).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Delivery Date
                }
                return false; // No match
            });
        });

        hBoxTitle.getChildren().addAll(titleLabel, searchStackPane);
        contentPanel.setTop(hBoxTitle);
    }

    public void contentTable() {

        table.setPrefHeight(400);
        table.setPrefWidth(1125);

        // Create the columns
        TableColumn<ObservableList<Object>, Integer> numOrder = new TableColumn<>("N°. Orden");
        numOrder.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(0)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        numOrder.setPrefWidth(125); // Adjust the width of the column
        applyHeaderFont(numOrder, 0, 20); // Apply the font to the header
        applyCellFont(numOrder, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> nameProduct = new TableColumn<>("Producto");
        nameProduct.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        nameProduct.setPrefWidth(185);
        applyHeaderFont(nameProduct, 0, 20); // Apply the font to the header
        applyCellFont(nameProduct, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> nameCustomer = new TableColumn<>("Nombre Cliente");
        nameCustomer.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        nameCustomer.setPrefWidth(275);
        applyHeaderFont(nameCustomer, 0, 20); // Apply the font to the header
        applyCellFont(nameCustomer, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Long> phone = new TableColumn<>("Telefono");
        phone.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 3 && row.get(3) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(3)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        phone.setPrefWidth(180);
        applyHeaderFont(phone, 0, 20); // Apply the font to the header
        applyCellFont(phone, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> deliveryDate = new TableColumn<>("Fecha De Entrega");
        deliveryDate.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 4 && row.get(4) instanceof String)
                    ? new SimpleStringProperty((String) row.get(4))
                    : new SimpleStringProperty("");
        });
        deliveryDate.setPrefWidth(210);
        applyHeaderFont(deliveryDate, 0, 20); // Apply the font to the header
        applyCellFont(deliveryDate, 0,20); //  Apply the font style to the cells

        TableColumn<ObservableList<Object>, ImageView> eye = new TableColumn<>("");
        eye.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<ObservableList<Object>, ImageView> cellData) {
                ObservableList<Object> row = cellData.getValue();
                if (row != null && row.size() > 5 && row.get(5) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(5));
                } else {
                    return new SimpleObjectProperty<>(new ImageView());
                }
            }
        });
        eye.setPrefWidth(50);
        eye.setCellFactory(column -> createCellWithBackgroundColor("white"));
        eye.getStyleClass().add("column-header-eye"); // Apply the CSS class for the 'eye' column

        TableColumn<ObservableList<Object>, ImageView> edit = new TableColumn<>("");
        edit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<ObservableList<Object>, ImageView> cellData) {
                ObservableList<Object> row = cellData.getValue();
                if (row != null && row.size() > 6 && row.get(6) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(6));
                } else {
                    return new SimpleObjectProperty<>(new ImageView());
                }
            }
        });
        edit.setPrefWidth(50);
        edit.setCellFactory(column -> createCellWithBackgroundColor("white"));
        edit.getStyleClass().add("column-header-edit"); // Apply the CSS class for the 'eye' column

        TableColumn<ObservableList<Object>, ImageView> trash = new TableColumn<>("");
        trash.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<ObservableList<Object>, ImageView> cellData) {
                ObservableList<Object> row = cellData.getValue();
                if (row != null && row.size() > 7 && row.get(7) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(7));
                } else {
                    return new SimpleObjectProperty<>(new ImageView());
                }
            }
        });
        //trash.setPrefWidth(50);
        trash.setPrefWidth(table.getPrefWidth() - (numOrder.getPrefWidth() + nameProduct.getPrefWidth() + nameCustomer.getPrefWidth() +
                phone.getPrefWidth() + deliveryDate.getPrefWidth() + eye.getPrefWidth() + edit.getPrefWidth()));

        trash.setCellFactory(column -> createCellWithBackgroundColor("white"));
        trash.getStyleClass().add("column-header-trash"); // Apply the CSS class for the 'eye' column

        table.getColumns().addAll(numOrder, nameProduct, nameCustomer, phone, deliveryDate, eye, edit, trash);
        applyRowStyles();

        // Call the method to initialize the filtering
        initializeFilter();

        filteredData = new FilteredList<>(getOrderList(), p -> true);
        table.setItems(filteredData);

        // Add the table to hBoxTable and configure hBoxTable
        hBoxTable.getChildren().clear(); // Clear any previous content in hBoxTable
        initializeIconColumns();

        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);
        hBoxTable.setPadding(new Insets(20, 0, 200, 0));


        contentPanel.setCenter(hBoxTable);
    }

    // Method to get the order list
    public ObservableList<ObservableList<Object>> getOrderList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        // Load the images
        Image eyeImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Eye.png")).toExternalForm());
        Image editImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Edit.png")).toExternalForm());
        Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        // get the order list
        orderList2 = new ArrayList<>(logic.getOrderList().values());

        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Order order : orderList2) {
            // Create new instances of ImageView for each row
            ImageView eyeView = new ImageView(eyeImage);
            eyeView.setFitWidth(24);  // Width of the icon
            eyeView.setFitHeight(24); // Height of the icon
            eyeView.setPreserveRatio(true);  // To preserve the aspect ratio

            ImageView editView = new ImageView(editImage);
            editView.setFitWidth(24);
            editView.setFitHeight(24);
            editView.setPreserveRatio(true);

            ImageView trashView = new ImageView(trashImage);
            trashView.setFitWidth(24);
            trashView.setFitHeight(24);
            trashView.setPreserveRatio(true);

            Customer customer = new Customer();
            try {

                Logic logic1 = new Logic();
                customer = logic1.searchCustomer(order.getCustomer());
            } catch (Exception e){

            }

            // Add the rows with the icons
            ObservableList<Object> row = FXCollections.observableArrayList(

                    order.getOrderNumber(),
                    order.getProductName(),
                    customer.getName(),
                    customer.getPhoneNumber(),
                    formato.format(order.getDeliveryDate()),
                    eyeView,   // View icon
                    editView,  // Edit icon
                    trashView  // Delete icon
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
                            //  Apply alternate row colors
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

    // Method for creating button agregar
    private void initializeButtonAdd() {
        buttonAdd = new Button("Agregar");
        buttonAdd.setOnAction(event -> {
            NewOrder newOrder = new NewOrder();
            // Clear current content
            contentPanel.getChildren().clear();
            // Add the new content
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
        buttonAddBox.setPadding(new Insets(-300, 80, 0, 0));
        contentPanel.setBottom(buttonAddBox);
    }

    // Method to initialize the filter
    private void initializeFilter() {
        // Initialize the filtered list with the original data
        filteredData = new FilteredList<>(getOrderList(), p -> true);

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

    // Method to initialize the icon columns
    public void initializeIconColumns() {
        // Get all columns
        TableColumn<ObservableList<Object>, ImageView> eyeColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(5);
        TableColumn<ObservableList<Object>, ImageView> editColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(6);
        TableColumn<ObservableList<Object>, ImageView> trashColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(7);

        // Add EventHandler to the column 'ver'
        eyeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1 && !isEmpty()) {
                            int rowIndex = getIndex();
                            int index = orderList2.get(rowIndex).getOrderNumber();
                            SpecificOrder oSpecific = new SpecificOrder();
                            oSpecific.setOrder(logic.searchOrder(index));
                            oSpecific.loadOrder();
                            contentPanel.getChildren().clear();
                            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
                            contentPanel.getChildren().add(oSpecific.screen());
                        }
                    });
                }
            }
        });

        // Add EventHandler to the column 'editar'
        editColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1 && !isEmpty()) {
                            int rowIndex = getIndex();
                            UpdateOrder uOrder = new UpdateOrder();
                            //  Clear current content
                            contentPanel.getChildren().clear();
                            // Add the new content
                            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
                            //contentPanel.getChildren().add(uOrder.screen());
                        }
                    });
                }
            }
        });

        // Add EventHandler to the column 'borrar'
        trashColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1 && !isEmpty()) {
                            int rowIndex = getIndex();
                            int index = orderList2.get(rowIndex).getOrderNumber();
                            String orderNumberStr = String.valueOf(index);

                            components.windowConfirmation(
                                    "¿Está seguro de eliminar esta orden?",
                                    "Cancelar", "Eliminar", "Orden eliminada con éxito",
                                    orderNumberStr);
                        }
                    });
                }
            }
        });
    }

    // Method to refresh the table
    public void refreshTable() {
        table.setItems(getOrderList());
        filteredData.setPredicate(filteredData.getPredicate());
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
                            setFont(components.createFont(0, 20));
                        }
                    }
                };
            }
        });
    }
}