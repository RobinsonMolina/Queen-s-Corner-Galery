package co.edu.uptcSoft.view;

import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Materials;
import co.edu.uptcSoft.model.Order;
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
import java.util.ArrayList;
import static co.edu.uptcSoft.view.Components.createFont;

public class NewCustomer {

    private Pane root;
    private Components components;
    private VBox principal;
    private Label titleLabel;
    private VBox informationVBox;
    private HBox buttonsHBox;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    private ArrayList<Order> ordes;

    private TextField nameTxt;
    private TextField emailTxt;

    private TextField phoneTxt;
    private TextField documentTxt;
    private TextField adressTxt;

    public NewCustomer() {
        root = new Pane();
        components = new Components();
        principal = new VBox();
        titleLabel = new Label("Nuevo Cliente");
        informationVBox = new VBox();
        buttonsHBox = new HBox();

        nameTxt = new TextField();
        emailTxt = new TextField();
        adressTxt = new TextField();
        phoneTxt = new TextField();
        documentTxt = new TextField();
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
        Label title = new Label("Materiales Requeridos");

        VBox.setMargin(title, new Insets(35, 0, 35, 0));
        title.setFont(createFont(0, 30));
        title.setPrefHeight(50);

        // Table
        TableView <Order> tableView = new TableView<>();
        TableColumn<Order, String> codeColumn = new TableColumn<>("Número de Orden");
        //codeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order, String> materialColumn = new TableColumn<>("Producto");
        //materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));

        TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Fecha de Entrega");
        //quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Order, Void> iconColumn = new TableColumn<>("Icon");
        iconColumn.setCellFactory(column -> new TableCell<Order, Void>() {
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
        Button addButt = new Button("+ Order");
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

    public void confirmationData(){

        String name = nameTxt.getText();
        String email = emailTxt.getText();
        String phone = phoneTxt.getText();
        String document = documentTxt.getText();
        String address = adressTxt.getText();

        try {
            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !document.isEmpty()){

                if (numValLong(phone) != null && numValLong(document) != null){

                    // Se puede
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
}
