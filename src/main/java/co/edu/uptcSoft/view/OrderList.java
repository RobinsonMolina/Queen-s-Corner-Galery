package co.edu.uptcSoft.view;

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

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(order -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                // Aquí ajusta la lógica según las columnas que deseas filtrar
                if (order.get(0).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filtro por N°. Orden
                } else if (order.get(1).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filtro por Producto
                } else if (order.get(2).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filtro por Nombre Cliente
                } else if (order.get(3) != null && order.get(3).toString().contains(lowerCaseFilter)) {
                    return true; // Filtro por Telefono
                } else if (order.get(4).toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filtro por Fecha De Entrega
                }
                return false; // No coincide
            });
        });

        hBoxTitle.getChildren().addAll(titleLabel, searchStackPane);
        contentPanel.setTop(hBoxTitle);
    }

    public void contentTable() {

        //table.setPrefSize(1050, 400); // width and height of the table
        table.setPrefHeight(400);
        table.setPrefWidth(1125);

        TableColumn<ObservableList<Object>, Integer> numOrder = new TableColumn<>("N°. Orden");
        numOrder.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(0)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        numOrder.setPrefWidth(125); // Ajusta el ancho de la columna

        TableColumn<ObservableList<Object>, String> nameProduct = new TableColumn<>("Producto");
        nameProduct.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        nameProduct.setPrefWidth(185);

        TableColumn<ObservableList<Object>, String> nameCustomer = new TableColumn<>("Nombre Cliente");
        nameCustomer.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        nameCustomer.setPrefWidth(275);

        TableColumn<ObservableList<Object>, Long> phone = new TableColumn<>("Telefono");
        phone.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 3 && row.get(3) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(3)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        phone.setPrefWidth(180);

        TableColumn<ObservableList<Object>, String> deliveryDate = new TableColumn<>("Fecha De Entrega");
        deliveryDate.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 4 && row.get(4) instanceof String)
                    ? new SimpleStringProperty((String) row.get(4))
                    : new SimpleStringProperty("");
        });
        deliveryDate.setPrefWidth(210);

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
        eye.getStyleClass().add("column-header-eye"); // Aplicar la clase CSS para la columna 'eye'

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
        edit.getStyleClass().add("column-header-edit"); // Aplicar la clase CSS para la columna 'eye'

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
        trash.getStyleClass().add("column-header-trash"); // Aplicar la clase CSS para la columna 'eye'

        table.getColumns().addAll(numOrder, nameProduct, nameCustomer, phone, deliveryDate, eye, edit, trash);
        applyRowStyles();

        // Llama al método para inicializar el filtrado
        initializeFilter();

        filteredData = new FilteredList<>(getOrderList(), p -> true);
        table.setItems(filteredData);

        // Añadir la tabla al HBox y configurar el HBox
        hBoxTable.getChildren().clear(); // Limpiar cualquier contenido anterior en hBoxTable
        initializeIconColumns();

        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);
        hBoxTable.setPadding(new Insets(20, 0, 200, 0));


        contentPanel.setCenter(hBoxTable);
    }

    public ObservableList<ObservableList<Object>> getOrderList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        // Cargar las imágenes
        Image eyeImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Eye.png")).toExternalForm());
        Image editImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Edit.png")).toExternalForm());
        Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        // Supongamos que orderList es tu ArrayList de órdenes
        orderList2 = new ArrayList<>(logic.getOrderList().values());

        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Order order : orderList2) {
            // Crear nuevas instancias de ImageView para cada fila
            ImageView eyeView = new ImageView(eyeImage);
            eyeView.setFitWidth(24);  // Ancho del icono
            eyeView.setFitHeight(24); // Alto del icono
            eyeView.setPreserveRatio(true); // Para preservar la proporción

            ImageView editView = new ImageView(editImage);
            editView.setFitWidth(24);
            editView.setFitHeight(24);
            editView.setPreserveRatio(true);

            ImageView trashView = new ImageView(trashImage);
            trashView.setFitWidth(24);
            trashView.setFitHeight(24);
            trashView.setPreserveRatio(true);

            // Agregar las filas con los iconos
            ObservableList<Object> row = FXCollections.observableArrayList(
                    order.getOrderNumber(),
                    order.getProductName(),
                    order.getCustomer().getName(),
                    order.getCustomer().getPhoneNumber(),
                    formato.format(order.getDeliveryDate()),
                    eyeView,   // Ver icono
                    editView,  // Editar icono
                    trashView  // Eliminar icono
            );
            data.add(row);
        }


        return data;
    }

    // Metodo para aplicar estilos a las filas
    private void applyRowStyles() {
        // Configurar fábrica de filas para aplicar estilos alternados
        table.setRowFactory(new Callback<TableView<ObservableList<Object>>, TableRow<ObservableList<Object>>>() {
            @Override
            public TableRow<ObservableList<Object>> call(TableView<ObservableList<Object>> tableView) {
                return new TableRow<>() {
                    @Override
                    protected void updateItem(ObservableList<Object> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setStyle(""); // Limpiar estilo para filas vacías
                        } else {
                            // Aplicar colores alternados a las filas
                            if (getIndex() % 2 == 0) {
                                setStyle("-fx-background-color: white;"); // blanco para filas pares
                            } else {
                                setStyle("-fx-background-color: #D9D9D9;"); // gris claro para filas impares
                            }
                        }
                    }
                };
            }
        });
    }

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
            // Limpiar el contenido actual
            contentPanel.getChildren().clear();
            // Agregar el nuevo contenido
            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPanel.getChildren().add(newOrder.screen());
        });

        buttonAdd.setPrefSize(150, 34);

        buttonAdd.getStyleClass().add("rounded-button");
        buttonAdd.getStyleClass().add("rounded-button:hover");
        buttonAdd.getStyleClass().add("rounded-button:pressed");
        HBox buttonAddBox = new HBox(buttonAdd);
        buttonAddBox.setAlignment(Pos.CENTER_RIGHT);
        buttonAddBox.setPadding(new Insets(-300, 80, 0, 0));
        contentPanel.setBottom(buttonAddBox);
    }

    private void initializeFilter() {
        // Inicializa la lista filtrada con los datos originales
        filteredData = new FilteredList<>(getOrderList(), p -> true);

        // Configura el filtro del TextField
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(row -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Muestra todas las filas si el campo de búsqueda está vacío
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Filtra según el texto en cualquier columna que sea de tipo String
                return row.stream().anyMatch(data -> {
                    if (data instanceof String) {
                        return ((String) data).toLowerCase().contains(lowerCaseFilter);
                    }
                    return false;
                });
            });
        });

        // Establece el `FilteredList` en la tabla
        table.setItems(filteredData);
    }

    public void initializeIconColumns() {
        // Obtener todas las columnas
        TableColumn<ObservableList<Object>, ImageView> eyeColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(5);
        TableColumn<ObservableList<Object>, ImageView> editColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(6);
        TableColumn<ObservableList<Object>, ImageView> trashColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(7);

        // Añadir EventHandler a la columna de "ver"
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
                            // Limpiar el contenido actual
                            contentPanel.getChildren().clear();
                            // Agregar el nuevo contenido
                            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
                            //contentPanel.getChildren().add(oSpecific.screen());
                        }
                    });
                }
            }
        });

        // Añadir EventHandler a la columna de "editar"
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
                            // Limpiar el contenido actual
                            contentPanel.getChildren().clear();
                            // Agregar el nuevo contenido
                            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
                            //contentPanel.getChildren().add(uOrder.screen());
                        }
                    });
                }
            }
        });

        // Añadir EventHandler a la columna de "borrar"
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

    // Agrega un método para actualizar la tabla
    public void refreshTable() {
        table.setItems(getOrderList());
        filteredData.setPredicate(filteredData.getPredicate());
    }
}