package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Order;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.util.Callback;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import static co.edu.uptcSoft.view.Components.createFont;

public class CustomerList  {

    private Components components;
    private BorderPane contentPanel;
    private javafx.scene.control.Label titleLabel;
    private javafx.scene.control.TextField searchTextField;
    private javafx.scene.control.Button buttonAdd;
    private Logic logic = Logic.getInstance();

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Customer> orderList2;

    private ObservableList<ObservableList<Object>> customerList;

    private HBox hBoxTitle;
    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public CustomerList() {
        searchTextField = new javafx.scene.control.TextField();
        contentPanel = new BorderPane();
        components = new Components(CustomerList.this);

        hBoxTitle = new HBox(300);
        hBoxTable = new HBox();
        hBoxTable.setPrefSize(1366, 400);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());


        titleLabel = new javafx.scene.control.Label("Lista De Clientes");
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
        hBoxTitle.setPadding(new javafx.geometry.Insets(55, 80, 0, 0));

        //titleLabel.setFont(components.createFont(0, 40));
        searchTextField = components.createRoundedTextField(30, 30);
        searchTextField.setPadding(new javafx.geometry.Insets(0, 0, 0, 10));
        searchTextField.setPrefWidth(200);
        searchTextField.setPrefHeight(45);
        //searchTextField.setFont(components.createFont(1, 20));

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
            filteredData.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                // Adjust the logic according to the columns you want to filter
                if (customer.get(0).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Document Number
                } else if (customer.get(1).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Name
                } else if (customer.get(2).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter by Email
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
        table.setPrefWidth(1150);

        // Create the columns
        TableColumn<ObservableList<Object>, Long> numDocumento = new TableColumn<>("N°. Documento");
        numDocumento.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(0)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        numDocumento.setPrefWidth(200); // adjust column width
        applyHeaderFont(numDocumento, 0, 20); // Apply the font to the header
        applyCellFont(numDocumento, 0,20); // Apply the font style

        TableColumn<ObservableList<Object>, String> nameCustomer = new TableColumn<>("Nombre Cliente");
        nameCustomer.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        nameCustomer.setPrefWidth(400);
        applyHeaderFont(nameCustomer, 0, 20); //Apply the font to the header
        applyCellFont(nameCustomer, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> email = new TableColumn<>("Correo");
        email.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        email.setPrefWidth(400);
        applyHeaderFont(email, 0, 20); // Apply the font to the header
        applyCellFont(email, 0,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, ImageView> eye = new TableColumn<>("");
        eye.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<ObservableList<Object>, ImageView> cellData) {
                ObservableList<Object> row = cellData.getValue();
                if (row != null && row.size() > 3 && row.get(3) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(3));
                } else {
                    return new SimpleObjectProperty<>(new ImageView());
                }
            }
        });
        eye.setPrefWidth(50);
        eye.setCellFactory(column -> createCellWithBackgroundColor("white"));
        eye.getStyleClass().add("column-header-eye"); /// Apply

        TableColumn<ObservableList<Object>, ImageView> edit = new TableColumn<>("");
        edit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<ObservableList<Object>, ImageView> cellData) {
                ObservableList<Object> row = cellData.getValue();
                if (row != null && row.size() > 4 && row.get(4) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(4));
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
                if (row != null && row.size() > 5 && row.get(5) instanceof ImageView) {
                    return new SimpleObjectProperty<>((ImageView) row.get(5));
                } else {
                    return new SimpleObjectProperty<>(new ImageView());
                }
            }
        });
        trash.setPrefWidth(50);
        trash.setCellFactory(column -> createCellWithBackgroundColor("white"));
        trash.getStyleClass().add("column-header-trash"); // Apply the CSS class for the 'eye' column

        table.getColumns().addAll(numDocumento, nameCustomer, email, eye, edit, trash);
        applyRowStyles();

        // Call the method to initialize the filter
        initializeFilter();

        filteredData = new FilteredList<>(getCustomerList(), p -> true);
        table.setItems(filteredData);

        // Add the table to the HBox and configure the HBox
        hBoxTable.getChildren().clear(); // Clear any previous content in hBoxTable
        initializeIconColumns();

        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);
        hBoxTable.setPadding(new javafx.geometry.Insets(20, 0, 200, 0));

        contentPanel.setCenter(hBoxTable);
    }

    // Method to get the customer list
    public ObservableList<ObservableList<Object>> getCustomerList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        // Load the images
        javafx.scene.image.Image eyeImage = new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Eye.png")).toExternalForm());
        javafx.scene.image.Image editImage = new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Edit.png")).toExternalForm());
        javafx.scene.image.Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        // get the customer list
        orderList2 = new ArrayList<>(logic.getCustomerList().values());

        for (Customer customer : orderList2) {
            // Create new instances of ImageView for each row
            ImageView eyeView = new ImageView(eyeImage);
            eyeView.setFitWidth(24);  // Width of the icon
            eyeView.setFitHeight(24); // Height of the icon
            eyeView.setPreserveRatio(true); // To preserve the aspect ratio

            ImageView editView = new ImageView(editImage);
            editView.setFitWidth(24);
            editView.setFitHeight(24);
            editView.setPreserveRatio(true);

            ImageView trashView = new ImageView(trashImage);
            trashView.setFitWidth(24);
            trashView.setFitHeight(24);
            trashView.setPreserveRatio(true);

            // Add the rows with the icons
            ObservableList<Object> row = FXCollections.observableArrayList(
                    customer.getDocumentNumber(),
                    customer.getName(),
                    customer.getEmail(),
                    eyeView,   //  View icon
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

    // Method to create button agregar
    private void initializeButtonAdd() {
        buttonAdd = new Button("Agregar");
        buttonAdd.setOnAction(event -> {
            NewCustomer newCustomer = new NewCustomer();
            // Clear current content
            contentPanel.getChildren().clear();
            // Add the new content
            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPanel.getChildren().add(newCustomer.screen());
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
        //
        filteredData = new FilteredList<>(getCustomerList(), p -> true);

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

    public void initializeIconColumns() {
        // Get all columns
        TableColumn<ObservableList<Object>, ImageView> eyeColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(3);
        TableColumn<ObservableList<Object>, ImageView> editColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(4);
        TableColumn<ObservableList<Object>, ImageView> trashColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(5);

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
                            long index = orderList2.get(rowIndex).getDocumentNumber();
                            ViewCustomer vCustomer = new ViewCustomer();
                            // Clear current content
                            contentPanel.getChildren().clear();
                            // Add the new content
                            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
                            //contentPanel.getChildren().add(vCustomer.screen());
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
                            // Clear current content
                            contentPanel.getChildren().clear();
                            //  Add the new content
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
                            long index = orderList2.get(rowIndex).getDocumentNumber();
                            String documentNumberStr = String.valueOf(index);

                            components.windowConfirmation(
                                    "¿Está seguro de eliminar este cliente?",
                                    "Cancelar", "Eliminar", "Cliente eliminado con éxito",
                                    documentNumberStr);
                        }
                    });
                }
            }
        });
    }

    // Method to refresh the table
    public void refreshTable() {
        table.setItems(getCustomerList());
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
