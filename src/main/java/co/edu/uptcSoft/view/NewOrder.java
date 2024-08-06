package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Supply;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Callback;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static co.edu.uptcSoft.view.Components.createFont;

public class NewOrder {

    private Logic logic = Logic.getInstance();
    private Pane root;
    private Components components;
    private VBox principal;
    private Label titleLabel;
    private VBox informationVBox;
    private HBox buttonsHBox;

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Supply> orderList2;
    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    private String productName;
    private String status;
    private int orderNumber;
    private String type;
    private Date productionDate;
    private Date deliveryDate;
    private Customer customer;
    private ArrayList<Materials> materials;

    private TextField productTxt;
    private TextField typeTxt;
    private TextField customerTxt;

    private ComboBox<String> stateComboB;
    private DatePicker productionDateTxt;
    private TextField phoneTxt;

    private TextField orderNumberTxt;
    private DatePicker deliveryDateTxt;
    private TextField documentTxt;

    private Long documentId;

    public NewOrder() {
        root = new Pane();
        components = new Components();
        principal = new VBox();
        titleLabel = new Label("Nueva Orden");
        informationVBox = new VBox();
        buttonsHBox = new HBox();

        productTxt = new TextField();
        typeTxt = new TextField();
        customerTxt = new TextField();
        stateComboB = new ComboBox<>();
        productionDateTxt = new DatePicker();
        phoneTxt = new TextField();
        orderNumberTxt = new TextField();
        deliveryDateTxt = new DatePicker();
        documentTxt = new TextField();

        status = "Por Hacer";

        hBoxTable = new HBox();
        hBoxTable.setPrefSize(screenWidth - 80, 136);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());

        orderList2 = new ArrayList<>();
        customer = new Customer();
    }

    public Pane screen(){
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

        customerTxt = new TextField(customer.getName());
        phoneTxt = new TextField(String.valueOf(customer.getPhoneNumber()));
        documentTxt = new TextField(String.valueOf(customer.getDocumentNumber()));
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
        VBox info5VB = new VBox();
        VBox info6VB = new VBox();

        // Space between nodes
        info1VB.setSpacing(20);
        info2VB.setSpacing(20);
        info3VB.setSpacing(20);
        info4VB.setSpacing(20);
        info5VB.setSpacing(20);
        info6VB.setSpacing(20);
        data.setSpacing(15);

        Label product = new Label("Producto");
        Label type = new Label("Tipo");
        Label customer = new Label("Cliente");
        info1VB.getChildren().addAll(product, type, customer);

        Label state = new Label("Estado");
        Label productionDateLabel = new Label("Fecha Producción");
        Label phone = new Label("Teléfono");
        info3VB.getChildren().addAll(state, productionDateLabel, phone);

        Label orderNumber = new Label("Número de orden");
        Label deliveryDateLabel = new Label("Fecha de Entrega");
        Label document = new Label("Documento");
        info5VB.getChildren().addAll(orderNumber, deliveryDateLabel, document);

        info2VB.getChildren().addAll(productTxt, typeTxt, customerTxt);
        info4VB.getChildren().addAll(stateComboB, productionDateTxt, phoneTxt);
        info6VB.getChildren().addAll(orderNumberTxt, deliveryDateTxt, documentTxt);

        product.setPrefSize(80, 30);
        type.setPrefSize(80, 30);
        customer.setPrefSize(80, 30);

        productTxt.setPrefSize(300, 30);
        typeTxt.setPrefSize(300, 30);
        customerTxt.setPrefSize(300, 30);

        state.setPrefSize(154, 30);
        productionDateLabel.setPrefSize(154, 30);
        phone.setPrefSize(154, 30);

        stateComboB.setPrefSize(180, 30);
        productionDateTxt.setPrefSize(180, 30);
        phoneTxt.setPrefSize(180, 30);

        orderNumber.setPrefSize(167, 30);
        deliveryDateLabel.setPrefSize(167, 30);
        document.setPrefSize(167, 30);

        orderNumberTxt.setPrefSize(180, 30);
        deliveryDateTxt.setPrefSize(180, 30);
        documentTxt.setPrefSize(180, 30);

        // Font Labels
        product.setFont(createFont(1, 20));
        type.setFont(createFont(1, 20));
        customer.setFont(createFont(1, 20));
        state.setFont(createFont(1, 20));
        productionDateLabel.setFont(createFont(1, 20));
        phone.setFont(createFont(1, 20));
        orderNumber.setFont(createFont(1, 20));
        deliveryDateLabel.setFont(createFont(1, 20));
        document.setFont(createFont(1, 20));

        // Font Fields
        Font customFont = createFont(1, 16);
        String style = "-fx-font-family: '" + customFont.getName() + "'; -fx-font-size: " + (int) customFont.getSize() + "px;";

        productTxt.setFont(createFont(1, 18));
        typeTxt.setFont(createFont(1, 18));
        customerTxt.setFont(createFont(1, 18));

        stateComboB.setStyle(style);
        productionDateTxt.setStyle(style);
        phoneTxt.setFont(createFont(1, 18));

        orderNumberTxt.setFont(createFont(1, 18));
        deliveryDateTxt.setStyle(style);
        documentTxt.setFont(createFont(1, 18));

        // Size
        product.setMinHeight(35);
        type.setMinHeight(35);
        customer.setMinHeight(35);

        productTxt.setMinHeight(35);
        typeTxt.setMinHeight(35);
        customerTxt.setMinHeight(35);

        state.setMinHeight(35);
        productionDateLabel.setMinHeight(35);
        phone.setMinHeight(35);

        stateComboB.setMinHeight(35);
        productionDateTxt.setMinHeight(35);
        phoneTxt.setMinHeight(35);

        orderNumber.setMinHeight(35);
        deliveryDateLabel.setMinHeight(35);
        document.setMinHeight(35);

        orderNumberTxt.setMinHeight(35);
        deliveryDateTxt.setMinHeight(35);
        documentTxt.setMinHeight(35);

        data.setAlignment(Pos.CENTER);
        //informationVBox.setAlignment(Pos.CENTER);
        data.getChildren().addAll(info1VB, info2VB, info3VB, info4VB, info5VB, info6VB);
        informationVBox.getChildren().add(data);

        // Border Color
        //createRoundedTextField
        productTxt.getStyleClass().add("rounded-textfield");
        typeTxt.getStyleClass().add("rounded-textfield");
        customerTxt.getStyleClass().add("rounded-textfield");

        stateComboB.getStyleClass().add("combo-box");
        productionDateTxt.getStyleClass().add("rounded-datepicker");
        phoneTxt.getStyleClass().add("rounded-textfield");

        orderNumberTxt.getStyleClass().add("rounded-textfield");
        deliveryDateTxt.getStyleClass().add("rounded-datepicker");
        documentTxt.getStyleClass().add("rounded-textfield");

        // Combo Box
        ObservableList<String> options = FXCollections.observableArrayList(
                "Por Hacer",
                "En Progreso",
                "Entregado"
        );
        stateComboB.setItems(options);
        stateComboB.getSelectionModel().select(0);

        stateComboB.setOnAction(event -> {
            status = stateComboB.getSelectionModel().getSelectedItem();
            System.out.println("Statutss:   " + status);
        });
    }

    // VBox Data (title, table)
    public void data2(){
        VBox dataVBox = new VBox();
        Label title = new Label("Materiales Requeridos");

        VBox.setMargin(title, new Insets(35, 0, 35, 0));
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
        Button addButt = new Button("+ Material");
        Button cancelButt = new Button("Cancelar");
        Button newButt = new Button("Agregar");

        addButt.setPrefSize(107, 12);
        cancelButt.setPrefSize(107, 12);
        newButt.setPrefSize(107, 12);

        buttonsHBox.setSpacing(25);
        VBox.setMargin(buttonsHBox, new Insets(20, 30, 20, 0));
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
        buttonsHBox.getChildren().addAll(addButt, newButt, cancelButt);
        informationVBox.getChildren().add(buttonsHBox);

        addButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddSupply list = new AddSupply();
                root.getChildren().clear();
                root.setMinSize(screenWidth - 80, screenHeight - 80);
                root.getChildren().add(list.screen());
            }
        });

        cancelButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lleva al board o a la página anterior
            }
        });

        newButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                confirmationData();
                // Manda a la página siguiente
            }
        });

        addButt.getStyleClass().add("rounded-button");
        cancelButt.getStyleClass().add("rounded-button");
        newButt.getStyleClass().add("rounded-button");
    }

    // Validations
    public Long numValLong(String num){
        try {
            return num.length() == 10 ? Long.parseLong(num) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public int numValInt(String numStr){
        try {
            return Integer.parseInt(numStr);
        } catch (Exception e) {
            return -1;
        }
    }

    public void confirmationData(){

        LocalDate localDate1 =  LocalDate.of(1000, 1, 1);
        LocalDate localDate2 = LocalDate.of(1000, 1, 1);;

        try {
            localDate1 = productionDateTxt.getValue();
            productionDate = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());

            localDate2 = deliveryDateTxt.getValue();
            productionDate = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e){

        }

        String product = productTxt.getText();
        String type = typeTxt.getText();
        String customer = customerTxt.getText();
        String phone = phoneTxt.getText();
        String orderNumber = orderNumberTxt.getText();
        String document = documentTxt.getText();

        try {
            if (!product.isEmpty() && !type.isEmpty() && !customer.isEmpty() && !status.isEmpty() && !productionDateTxt.getValue().toString().isEmpty() && !phone.isEmpty() && !orderNumber.isEmpty() && !deliveryDateTxt.getValue().toString().isEmpty() && !document.isEmpty()){

                if (numValLong(phone) != null && numValLong(document) != null){

                    if (numValInt(orderNumber) != -1){

                        if (!localDate1.toString().isEmpty() && !localDate2.toString().isEmpty()){
                            if (!orderList2.isEmpty()){
                                // Se puede
                            } else {
                                components.messageConfirmation("Debe de Tener al Menos un Insumo");
                            }
                        } else {
                            components.messageConfirmation("Ingrese una Fecha Correcta");
                        }
                    } else {
                        // Ingrese un número
                        components.messageConfirmation("Ingrese un Número de Orden Válido");
                    }
                } else{
                    // Ingrese un valor de 10 dígitos
                    components.messageConfirmation("Ingrese un Número de Diez Digitos");
                }

            } else {
                components.messageConfirmation("Ingrese Todos los Datos");
            }
        } catch (Exception e){
            components.messageConfirmation("Ingrese todos los datos");
            e.printStackTrace();
        }
    }

    // Table __________________
    public void contentTable() {
        table.setMaxHeight(136);
        table.setMaxWidth(screenWidth - 80);
        hBoxTable.setPrefHeight(136);
        //informationVBox.getStyleClass().add("custom-background");

        // Create the columns
        TableColumn<ObservableList<Object>, String> id = new TableColumn<>("Código");
        id.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof String)
                    ? new SimpleStringProperty((String) row.get(0))
                    : new SimpleStringProperty("");
        });
        id.setPrefWidth(((screenWidth - 80) - 100)/4); // Adjust the width of the column
        applyHeaderFont(id, 0, 20); // Apply the font to the header
        applyCellFont(id, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, String> material = new TableColumn<>("Material");
        material.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        material.setPrefWidth(((screenWidth - 80) - 100)/4);
        applyHeaderFont(material, 0, 20); // Apply the font to the header
        applyCellFont(material, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Integer> quantity = new TableColumn<>("Cantidad");
        quantity.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(2)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        quantity.setPrefWidth(((screenWidth - 80) - 100)/4); // Adjust the width of the column
        applyHeaderFont(quantity, 0, 20); // Apply the font to the header
        applyCellFont(quantity, 1,20); // Apply the font to the cell

        TableColumn<ObservableList<Object>, Long> total = new TableColumn<>("Total");
        total.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 3 && row.get(3) instanceof Long)
                    ? new SimpleLongProperty((Long) row.get(3)).asObject()
                    : new SimpleLongProperty(0).asObject();
        });
        total.setPrefWidth(((screenWidth - 80) - 100)/4);
        applyHeaderFont(total, 0, 20); // Apply the font to the header
        applyCellFont(total, 1,20); // Apply the font to the cell

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

        table.getColumns().addAll(id, material, quantity, total, trash);
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

        Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        //orderList2 = new ArrayList<>(logic.getSupplyList().values());

        for (Supply supply : orderList2) {
            ImageView trashView = new ImageView(trashImage);
            trashView.setFitWidth(24);
            trashView.setFitHeight(24);
            trashView.setPreserveRatio(true);

            // Agregar las filas con los iconos
            ObservableList<Object> row = FXCollections.observableArrayList(
                    supply.getId(),
                    supply.getMaterial(),
                    supply.getQuantity(),
                    supply.getTotalPrice(),
                    trashView  // Delete icon
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
        TableColumn<ObservableList<Object>, ImageView> trashColumn = (TableColumn<ObservableList<Object>, ImageView>) table.getColumns().get(4);

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
                            String index = orderList2.get(rowIndex).getId();

                            components.windowConfirmation(
                                    "¿Está seguro de eliminar esta orden?",
                                    "Cancelar", "Eliminar", "Orden eliminada con éxito",
                                    index);
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