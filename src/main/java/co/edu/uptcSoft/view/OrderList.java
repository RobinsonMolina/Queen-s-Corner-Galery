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

    private ObservableList<ObservableList<Object>> orderList;

    private HBox hBoxTitle;
    private HBox hBoxTable;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public OrderList() {
        components = new Components();
        contentPanel = new BorderPane();

        hBoxTitle = new HBox(200);
        hBoxTable = new HBox();
        hBoxTable.setPrefSize(1366, 400);
        table = new TableView<>();
        applyRowStyles();
        table.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString());


        titleLabel = new Label("Lista De Ordenes");
    }

    public BorderPane screen( ) {
        //this.principal = root;
        //principal.setPrefSize(1366, 670);
        title();
        contentTable();
        initializeButtonAdd();
        //principal.setPrefSize(screenWidth - 80, screenHeight - 80);
        //principal.setCenter(contentPanel);
        //principal.setBottom(buttonAdd); // Agregar el botón en la parte inferior
        return contentPanel;
    }

    public void title() {
        hBoxTitle.setAlignment(Pos.CENTER);
        hBoxTitle.setPadding(new Insets(55, 0, 0, 0));

        titleLabel.setFont(components.createFont(0, 40));
        searchTextField = components.createRoundedTextField(30, 30);
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

        table.setItems(getOrderList());
        hBoxTable.getChildren().add(table);
        hBoxTable.setAlignment(Pos.CENTER);
        hBoxTable.setPadding(new Insets(20, 0, 200, -120));


        contentPanel.setCenter(hBoxTable);
    }

    public ObservableList<ObservableList<Object>> getOrderList() {
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();

        // Cargar las imágenes
        Image eyeImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Eye.png")).toExternalForm());
        Image editImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Edit.png")).toExternalForm());
        Image trashImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Trash.png")).toExternalForm());

        // Supongamos que orderList es tu ArrayList de órdenes
        ArrayList<Order> orderList = new ArrayList<>(logic.getOrderList().values());

        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Order order : orderList) {
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

    private void initializeButtonBottom() {
        buttonAdd = new Button("Agregar Orden");
        buttonAdd.setOnAction(event -> {
            // Lógica para agregar una orden
            System.out.println("Botón 'Agregar Orden' presionado");
        });
        // Establecer el estilo del botón si es necesario
        buttonAdd.setStyle("-fx-pref-width: 200; -fx-pref-height: 50; -fx-font-size: 16px;");
        HBox hBoxButton = new HBox(buttonAdd);
        hBoxButton.setAlignment(Pos.CENTER);
        hBoxButton.setPadding(new Insets(-400,0,0,0)); // Espaciado alrededor del botón
        contentPanel.setBottom(hBoxButton); // Establecer el HBox que contiene el botón en la parte inferior
    }

    // Method for creating button agregar
    private void initializeButtonAdd() {
        buttonAdd = new Button("Agregar");
        buttonAdd.setOnAction(event -> {
            NewOrder order = new NewOrder();
            // Limpiar el contenido actual
            contentPanel.getChildren().clear();
            // Agregar el nuevo contenido
            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPanel.getChildren().add(order.screen());
        });

        buttonAdd.setPrefSize(150, 34);

        buttonAdd.getStyleClass().add("rounded-button");
        buttonAdd.getStyleClass().add("rounded-button:hover");
        buttonAdd.getStyleClass().add("rounded-button:pressed");
        HBox buttonAddBox = new HBox(buttonAdd);
        buttonAddBox.setAlignment(Pos.CENTER_RIGHT);
        buttonAddBox.setPadding(new Insets(-300, 60, 0, 0));
        contentPanel.setBottom(buttonAddBox);
    }

    private void initializeFilter() {
        // Inicializa la lista filtrada
        filteredData = new FilteredList<>(getOrderList(), p -> true);

        // Configura el filtro del TextField
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(row -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Muestra todas las filas si el campo de búsqueda está vacío
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Filtra según el texto en cualquier columna
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


    /*
    private Components components;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel contentButton;
    private JLabel titleLabel;
    private JTextField searchTextField;
    private JButton buttonAdd;
    private JTable table;
    private DefaultTableModel model;
    private Object orderListTable[][];
    private Logic logic = Logic.getInstance();
    private JPanel mainContentPanel;
    private TableRowSorter<DefaultTableModel> filter;

    public OrderList(JPanel mainContentPanel) {
        this.mainContentPanel = mainContentPanel;
        components = new Components(mainContentPanel);
        contentPanel = new JPanel(new BorderLayout());
    }

    // Method for initializing content panel
    public JPanel initializeContentPanel() {
        contentPanel.setPreferredSize(new Dimension(1286, 590));

        initializeContentTitle();
        initializeTable();

        // Rounded button panel
        contentButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentButton.setBackground(Color.WHITE);
        contentButton.setBorder(new EmptyBorder(23, 1058, 25, 0));

        // Button add
        buttonAdd = components.createRoundedButton("Agregar", "#000000", "#2F1940", 30, 30);
        buttonAdd.setPreferredSize(new Dimension(150, 34));
        contentButton.add(buttonAdd);

        buttonAdd.addActionListener(this);

        contentPanel.add(contentButton, BorderLayout.SOUTH);

        add(contentPanel);
        return contentPanel;
    }

    // Method for creating title
    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1286, 100));

        // title label
        titleLabel = new JLabel("Lista De Ordenes", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        // Rounded search field
        searchTextField = components.createRoundedTextField(30,30);
        searchTextField.setBounds(888, 45, 200, 45);
        searchTextField.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 15));
        searchTextField.setFont(components.createFont(1, 20));

        // Add icon to the right of the search field
        ImageIcon searchIcon = new ImageIcon("src\\Utilities\\Images\\Glass.png");
        Image scaledSearchIcon = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel searchLabel = new JLabel(new ImageIcon(scaledSearchIcon));
        searchLabel.setBounds(5, 12, 24, 24);
        searchTextField.add(searchLabel);

        titleLabel.add(searchTextField);
        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);

        // Add DocumentListener to the JTextField to filter the table
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });
    }

    // Method for initializing table
    public void initializeTable() {
        // Data of the table
        String[] columnNames = {"Nº. Orden", "Producto", "Nombre Cliente", "Telefono", "Fecha De Entrega", "", "", ""};

        // Table model
        model = new DefaultTableModel(getOrderList(), columnNames) {
            // Method for setting the column class
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 5 || column == 6 || column == 7) ? Icon.class : super.getColumnClass(column);
            }

            // Method for checking if the cell is editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Table
        table = new JTable(model);
        table.setFont(components.createFont(1, 20));
        table.setForeground(Color.decode("#2F2F2F"));
        table.setRowHeight(34);
        table.setShowGrid(false);
        setColumnWidths(table);

        // TableRowSorter for filtering
        filter = new TableRowSorter<>(model);
        table.setRowSorter(filter);

        // Table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#D9D9D9"));
        header.setPreferredSize(new Dimension(283, 34));
        header.setFont(components.createFont(0, 20));

        // Move configuration of renderer after setting font
        header.setDefaultRenderer(createHeaderRenderer(header.getFont()));
        table.setDefaultRenderer(Object.class, createTableRowRenderer());

        // Configurar el MouseListener para la tabla
        setupTableMouseListener(table);

        // Scroll pane of table
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1134, 136));
        tableScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        tableScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        tableScrollPane.getViewport().setBackground(Color.WHITE);// change background of content scroll pane
        tableScrollPane.setBackground(Color.WHITE); // change background of scroll pane

        // Panel of table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 80, 0, 60));
        tablePanel.setPreferredSize(new Dimension(1366, 136));
        tablePanel.setBackground(Color.WHITE);

        contentPanel.add(tablePanel, BorderLayout.CENTER);
    }

    // Method for setting column widths
    private void setColumnWidths(JTable table) {
        table.getColumnModel().getColumn(0).setPreferredWidth(150);// Esto ajusta el ancho máximo de la columna 0
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
    }

    // Method for creating header renderer
    private DefaultTableCellRenderer createHeaderRenderer(Font headerFont) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Focuses the text
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setFont(headerFont);// set font
                    label.setForeground(Color.BLACK); // set color of text
                }

                // Change column header color
                if (column == 5 || column == 6 || column == 7) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(table.getTableHeader().getBackground());
                }
                return c;
            }
        };
    }

    // Method for creating table row renderer
    private DefaultTableCellRenderer createTableRowRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Focuses the text
                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                }

                // Change the color of the rows
                if (row % 2 == 0) {
                    cell.setBackground(Color.WHITE);
                } else {
                    cell.setBackground(Color.decode("#D9D9D9"));
                }
                return cell;
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            // change the content of the main panel instead of opening a new window
            mainContentPanel.removeAll();
            mainContentPanel.add(new NewOrder(mainContentPanel).addSpecificOrder(2));
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        }
    }

    // Method for configuring the MouseListener, view line 175
    private void setupTableMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (column == 5) {
                    // change the content of the main panel instead of opening a new window
                    mainContentPanel.removeAll();
                    mainContentPanel.add(new SpecificOrder(mainContentPanel).addSpecificOrder(3));
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                } else if (column == 6) {
                    // change the content of the main panel instead of opening a new window
                    mainContentPanel.removeAll();
                    mainContentPanel.add(new UpdateOrder(mainContentPanel).addSpecificOrder(2));
                    mainContentPanel.revalidate();
                    mainContentPanel.repaint();
                } else if (column == 7) {
                    String valor = table.getValueAt(row, 0).toString();
                    components.windowConfirmation("¿Está seguro de eliminar esta orden?", "Cancelar", "Eliminar", "Orden eliminada con éxito", valor);

                }
            }
        });
    }

    // Method for getting the order list and its icons
    public Object[][] getOrderList() {

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Eye.png");
        Image image = icon.getImage();
        ImageIcon eyeIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon2 = new ImageIcon("src\\Utilities\\Images\\Edit.png");
        Image image2 = icon2.getImage();
        ImageIcon pencilIcon = new ImageIcon(image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon("src\\Utilities\\Images\\Trash.png");
        Image image3 = icon3.getImage();
        ImageIcon trashIcon = new ImageIcon(image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        ArrayList<Order> orderList = new ArrayList<>(logic.getOrderList().values());

        orderListTable = new Object[orderList.size()][8];
        // change the format desired
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < orderList.size(); i++) {
            orderListTable[i][0] = orderList.get(i).getOrderNumber();
            orderListTable[i][1] = orderList.get(i).getProductName();
            orderListTable[i][2] = orderList.get(i).getCustomer().getName();
            orderListTable[i][3] = orderList.get(i).getCustomer().getPhoneNumber();
            orderListTable[i][4] = formato.format(orderList.get(i).getDeliveryDate());
            orderListTable[i][5] = eyeIcon;
            orderListTable[i][6] = pencilIcon;
            orderListTable[i][7] = trashIcon;
        }
        return orderListTable;
    }

    // Method for filtering table
    private void filterTable() {
        String text = searchTextField.getText();
        if (text.trim().length() == 0) {
            filter.setRowFilter(null);
        } else {
            filter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
    */
}
