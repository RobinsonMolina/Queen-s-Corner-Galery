package co.edu.uptcSoft.view;

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
import javafx.scene.text.Font;
import javafx.stage.Screen;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import static co.edu.uptcSoft.view.Components.createFont;

public class NewOrder {

    private Pane root;
    private Components components;
    private VBox principal;
    private Label titleLabel;
    private VBox informationVBox;
    private HBox buttonsHBox;

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
    }

    public Pane screen(){
        title();
        allInfo();
        buttons();

        principal.getChildren().addAll(titleLabel, informationVBox);
        principal.setAlignment(Pos.TOP_CENTER);

        informationVBox.setPrefWidth(screenWidth - 80);
        principal.setPrefSize(screenWidth - 80, screenHeight - 80);

        root.getChildren().add(principal);
        principal.setPrefSize(screenWidth - 80, screenHeight - 80);

        return root;
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
        TableView <Supply> tableView = new TableView<>();
        TableColumn<Supply, String> codeColumn = new TableColumn<>("Codigo");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Supply, String> materialColumn = new TableColumn<>("Material");
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));

        TableColumn<Supply, Integer> quantityColumn = new TableColumn<>("Cantidad");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Supply, Double> costColumn = new TableColumn<>("Costo");
        costColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TableColumn<Supply, Void> iconColumn = new TableColumn<>("Icon");
        iconColumn.setCellFactory(column -> new TableCell<Supply, Void>() {
            private final ImageView imageView = new ImageView(new Image("/styles/utilities/images/Trash.png"));

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    setGraphic(imageView);
                }
            }
        });

        // Add Columns
        tableView.getColumns().add(codeColumn);
        tableView.getColumns().add(materialColumn);
        tableView.getColumns().add(quantityColumn);
        tableView.getColumns().add(costColumn);
        tableView.getColumns().add(iconColumn);

        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setPrefWidth(screenWidth - 80);
        tableView.setPrefWidth(screenWidth - 80);
        dataVBox.setPrefWidth(screenWidth - 80);

        scrollPane.setPrefHeight(136);
        tableView.setPrefHeight(136);
        dataVBox.setPrefHeight(scrollPane.getPrefHeight() + title.getPrefHeight() + 70);

        dataVBox.setPadding(new Insets(0, 30, 0, 30));
        dataVBox.getChildren().addAll(title, scrollPane);

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
                System.out.println("Button clicked!");
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
                            // Se puede pasar a la siguiente pestaña
                        } else {
                            components.messageConfirmation("Ingrese una Fecha Correcta");
                        }
                    } else {
                        // Ingrese un número
                        components.messageConfirmation("Ingrese un Número de Orden Válido");
                    }
                } else{
                    // Ingrese un valor de 10 dígitos
                    components.messageConfirmation("Ingrese un Número de Teléfono o de Documento de Diez Digitos");
                }

            } else {
                components.messageConfirmation("Ingrese Todos los Datos");
                System.out.println("\n1: " + productTxt.getText() + "\n2: " + typeTxt.getText() + "\n3: " + customerTxt.getText() + "\n4: " + status + "\n5: " +
                        productionDateTxt.getValue() +"\n6: " + phoneTxt.getText() + "\n7: " + orderNumberTxt.getText() + "\n8: " + deliveryDateTxt.getValue() + "\n9: " + documentTxt.getText());
            }
        } catch (Exception e){
            components.messageConfirmation("Ingrese todos los datos");
            System.out.println("\n1: " + productTxt.getText() + "\n2: " + typeTxt.getText() + "\n3: " + customerTxt.getText() + "\n4: " + status + "\n5: " +
                    productionDateTxt.getValue() +"\n6: " + phoneTxt.getText() + "\n7: " + orderNumberTxt.getText() + "\n8: " + deliveryDateTxt.getValue() + "\n9: " + documentTxt.getText());
            e.printStackTrace();
        }
    }
}
