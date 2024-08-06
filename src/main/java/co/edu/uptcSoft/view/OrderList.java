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
    private Logic logic = Logic.getInstance();

    private TableView<ObservableList<Object>> table;
    private FilteredList<ObservableList<Object>> filteredData;
    private ArrayList<Order> orderList2;

    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public OrderList() {
        contentPanel = new BorderPane();
        components = new Components(OrderList.this);

        hBoxTable = new HBox();
        hBoxTable.setPrefSize(screenWidth - 80, 136);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());
    }

    public BorderPane screen() {
        contentTable();
        return contentPanel;
    }

    public void contentTable() {

        table.setMaxHeight(136);
        table.setMaxWidth(screenWidth - 80);

        TableColumn<ObservableList<Object>, Integer> numOrder = new TableColumn<>("N°. Orden");
        numOrder.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 0 && row.get(0) instanceof Integer)
                    ? new SimpleIntegerProperty((Integer) row.get(0)).asObject()
                    : new SimpleIntegerProperty(0).asObject();
        });
        numOrder.setPrefWidth(((screenWidth - 80) - 48)/4); // Ajusta el ancho de la columna

        TableColumn<ObservableList<Object>, String> nameProduct = new TableColumn<>("Producto");
        nameProduct.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 1 && row.get(1) instanceof String)
                    ? new SimpleStringProperty((String) row.get(1))
                    : new SimpleStringProperty("");
        });
        nameProduct.setPrefWidth(((screenWidth - 80) - 48)/4);

        TableColumn<ObservableList<Object>, String> deliveryDate = new TableColumn<>("Fecha De Entrega");
        deliveryDate.setCellValueFactory(cellData -> {
            ObservableList<Object> row = cellData.getValue();
            return (row != null && row.size() > 2 && row.get(2) instanceof String)
                    ? new SimpleStringProperty((String) row.get(2))
                    : new SimpleStringProperty("");
        });
        deliveryDate.setPrefWidth(((screenWidth - 80) - 48)/4);

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