package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Order;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class ViewCustomer {

    private Logic logic = Logic.getInstance();
    private Pane root;
    private Components components;
    private VBox principal;
    private Label titleLabel;
    private VBox informationVBox;
    private HBox buttonsHBox;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Order> orderList2;
    private HBox hBoxTable;
    private ArrayList<Order> ordes;

    private TextField nameTxt;
    private TextField emailTxt;

    private TextField phoneTxt;
    private TextField documentTxt;
    private TextField adressTxt;

    private Customer customer;
    private Long documentId;

    public ViewCustomer() {
        root = new Pane();
        components = new Components();
        principal = new VBox();
        titleLabel = new Label("Cliente");
        informationVBox = new VBox();
        buttonsHBox = new HBox();

        nameTxt = new TextField();
        emailTxt = new TextField();
        adressTxt = new TextField();
        phoneTxt = new TextField();
        documentTxt = new TextField();

        hBoxTable = new HBox();
        hBoxTable.setPrefSize(screenWidth - 80, 136);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());
        orderList2 = new ArrayList<>();

        customer = new Customer();
    }

    public Pane screen(){
        loadCustomer();
        title();
        allInfo();
        contentTable();
        buttons();

        principal.getChildren().addAll(titleLabel, informationVBox);
        principal.setAlignment(Pos.TOP_CENTER);

        informationVBox.setPrefWidth(screenWidth - 80);
        principal.setPrefSize(screenWidth - 80, screenHeight - 80);

        root.getChildren().add(principal);
        principal.setPrefSize(screenWidth - 80, screenHeight - 80);

        return root;
    }

    public void loadCustomer(){
        customer = logic.searchCustomer(documentId);
        orderList2 = new ArrayList<>(customer.getOrders());

        nameTxt = new TextField(customer.getName());
        emailTxt = new TextField(customer.getEmail());
        adressTxt = new TextField(customer.getAddress());
        phoneTxt = new TextField(String.valueOf(customer.getPhoneNumber()));
        documentTxt = new TextField(String.valueOf(customer.getDocumentNumber()));

        nameTxt.setEditable(false);
        emailTxt.setEditable(false);
        adressTxt.setEditable(false);
        phoneTxt.setEditable(false);
        documentTxt.setEditable(false);
    }

    public void title(){
        VBox.setMargin(titleLabel, new Insets(30, 0, 0, 0));
        titleLabel.setFont(createFont(0, 40));
        titleLabel.setPrefHeight(112);
        titleLabel.setAlignment(Pos.TOP_CENTER);
    }

    // All Center Info (VBox)
    public void allInfo(){
        data();
        data2();
    }

    // Order Info (HB)
    public void data(){
        HBox data = new HBox();
        VBox info1VB = new VBox();
        VBox info2VB = new VBox();
        VBox info3VB = new VBox();
        VBox info4VB = new VBox();

        // Space between nodes
        info1VB.setSpacing(20);
        info2VB.setSpacing(20);
        info3VB.setSpacing(20);
        info4VB.setSpacing(20);
        data.setSpacing(15);

        Label name = new Label("Nombre");
        Label email = new Label("Email");
        Label phone = new Label("Celular");
        Label document = new Label("Documento");
        Label address = new Label("Dirección");

        info1VB.getChildren().addAll(name, email, phone);
        info3VB.getChildren().addAll(document, address);
        info2VB.getChildren().addAll(nameTxt, emailTxt, phoneTxt);
        info4VB.getChildren().addAll(documentTxt, adressTxt);

        name.setPrefSize(80, 30);
        email.setPrefSize(80, 30);
        phone.setPrefSize(80, 30);
        document.setPrefSize(154, 30);
        address.setPrefSize(154, 30);

        nameTxt.setPrefSize(300, 30);
        emailTxt.setPrefSize(300, 30);
        phoneTxt.setPrefSize(180, 30);
        documentTxt.setPrefSize(180, 30);
        adressTxt.setPrefSize(300, 30);

        // Font Labels
        name.setFont(createFont(1, 20));
        email.setFont(createFont(1, 20));
        phone.setFont(createFont(1, 20));
        document.setFont(createFont(1, 20));
        address.setFont(createFont(1, 20));

        nameTxt.setFont(createFont(1, 18));
        emailTxt.setFont(createFont(1, 18));
        phoneTxt.setFont(createFont(1, 18));
        documentTxt.setFont(createFont(1, 18));
        adressTxt.setFont(createFont(1, 18));

        // Size
        name.setMinHeight(35);
        email.setMinHeight(35);
        phone.setMinHeight(35);
        document.setMinHeight(35);
        address.setMinHeight(35);

        nameTxt.setMinHeight(35);
        emailTxt.setMinHeight(35);
        phoneTxt.setMinHeight(35);
        documentTxt.setMinHeight(35);
        adressTxt.setMinHeight(35);

        data.setAlignment(Pos.CENTER);
        data.getChildren().addAll(info1VB, info2VB, info3VB, info4VB);
        informationVBox.getChildren().add(data);

        // Border Color
        nameTxt.getStyleClass().add("rounded-textfield");
        emailTxt.getStyleClass().add("rounded-textfield");
        adressTxt.getStyleClass().add("rounded-textfield");
        phoneTxt.getStyleClass().add("rounded-textfield");
        documentTxt.getStyleClass().add("rounded-textfield");
    }

    // VBox Data (title, table)
    public void data2(){
        VBox dataVBox = new VBox();
        Label title = new Label("Productos Ordenados");

        VBox.setMargin(title, new Insets(35, 0, 35, 100));
        title.setFont(createFont(0, 30));
        title.setPrefHeight(50);

        // Table
        dataVBox.setMaxHeight(hBoxTable.getPrefHeight() + title.getPrefHeight() + 70);

        dataVBox.setPadding(new Insets(0, 30, 0, 30));
        dataVBox.getChildren().addAll(title, hBoxTable);

        informationVBox.getChildren().add(dataVBox);
    }

    //Buttons
    public void buttons(){
        Button cancelButt = new Button("Regresar");

        cancelButt.setPrefSize(107, 12);

        buttonsHBox.setSpacing(25);
        VBox.setMargin(buttonsHBox, new Insets(20, 30, 20, 0));
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
        buttonsHBox.getChildren().add(cancelButt);
        informationVBox.getChildren().add(buttonsHBox);

        cancelButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CustomerList list = new CustomerList();
                root.getChildren().clear();
                root.setMinSize(screenWidth - 80, screenHeight - 80);
                root.getChildren().add(list.screen());
            }
        });

        cancelButt.getStyleClass().add("rounded-button");
    }

    // Table __________________
    public void contentTable() {
        table.setMaxHeight(136);
        table.setMaxWidth(screenWidth - 80);
        hBoxTable.setPrefHeight(136);
        //informationVBox.getStyleClass().add("custom-background");

        TableColumn<ObservableList<Object>, Integer> numOrder = new TableColumn<>("N°. Orden");
        numOrder.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(0)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        numOrder.setPrefWidth(((screenWidth - 80) - 100)/4); // Ajusta el ancho de la columna
        applyHeaderFont(numOrder, 0, 20); // Apply the font to the header
        applyCellFont(numOrder, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> nameProduct = new TableColumn<>("Producto");
        nameProduct.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        nameProduct.setPrefWidth(((screenWidth - 80) - 100)/4);
        applyHeaderFont(nameProduct, 0, 20); // Apply the font to the header
        applyCellFont(nameProduct, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> deliveryDate = new TableColumn<>("Fecha De Entrega");
        deliveryDate.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        deliveryDate.setPrefWidth(((screenWidth - 80) - 100)/4);
        applyHeaderFont(deliveryDate, 0, 20); // Apply the font to the header
        applyCellFont(deliveryDate, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, ImageView> edit = new TableColumn<>("");
        edit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
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
        edit.setPrefWidth(50);
        edit.setCellFactory(column -> createCellWithBackgroundColor("white"));
        edit.getStyleClass().add("column-header-edit"); // Aplicar la clase CSS para la columna 'eye'

        TableColumn<ObservableList<Object>, ImageView> trash = new TableColumn<>("");
        trash.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<Object>, ImageView>, ObservableValue<ImageView>>() {
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
        trash.setPrefWidth(50);
        trash.setCellFactory(column -> createCellWithBackgroundColor("white"));
        trash.getStyleClass().add("column-header-trash"); // Aplicar la clase CSS para la columna 'eye'

        table.getColumns().addAll(numOrder, nameProduct, deliveryDate, edit, trash);
        applyRowStyles();

        filteredData = new FilteredList<>(getOrderList(), p -> true);
        table.setItems(filteredData);

        // Añadir la tabla al HBox y configurar el HBox
        hBoxTable.getChildren().clear(); // Limpiar cualquier contenido anterior en hBoxTable
        initializeIconColumns();

        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);

        informationVBox.getChildren().add(hBoxTable);
    }

    public ObservableList<ObservableList<Object>> getOrderList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        Image editImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Edit.png")).toExternalForm());
        Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        //orderList2 = new ArrayList<>(logic.getOrderList().values());

        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Order order : orderList2) {
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
                    formato.format(order.getDeliveryDate()),
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

    public void initializeIconColumns() {
        // Obtener todas las columnas
        TableColumn<ObservableList<Object>, ImageView> editColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(3);
        TableColumn<ObservableList<Object>, ImageView> trashColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(4);

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
                            root.getChildren().clear();
                            // Agregar el nuevo contenido
                            root.setMinSize(screenWidth - 80, screenHeight - 80);
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

    // Method to apply font to the header
    private <T> void applyHeaderFont(TableColumn<ObservableList<Object>, T> column, int style, int size) {
        javafx.scene.control.Label label = new javafx.scene.control.Label(column.getText());
        label.setFont(createFont(style, size));
        column.setText(""); // Remove the default header text
        column.setGraphic(label);
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }
}
