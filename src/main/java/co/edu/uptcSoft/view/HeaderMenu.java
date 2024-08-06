package co.edu.uptcSoft.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.util.Objects;
import static co.edu.uptcSoft.view.Components.createFont;

public class HeaderMenu {

    private Components components;
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private VBox menuBar, menuIcon;

    private ImageView boardImageV;
    private ImageView listImageV;
    private ImageView newOrderImageV;
    private ImageView customerImageV;
    private ImageView supplyImageV;
    private ImageView adminImageV;
    private ImageView exitImageV;

    private Image boardImage;
    private Image listImage;
    private Image newOrderImage;
    private Image customerImage;
    private Image supplyImage;
    private Image adminImage;
    private Image exitImage;

    private Label boardLabel;
    private Label listLabel;
    private Label newOrderLabel;
    private Label customerLabel;
    private Label supplyLabel;
    private Label adminLabel;
    private Label exitLabel;

    private HBox boardHBox;
    private HBox listHBox;
    private HBox newOrderHBox;
    private HBox customerHBox;
    private HBox supplyHBox;
    private HBox adminHBox;
    private HBox exitHBox;

    private VBox head;
    private StackPane menuContainer;
    private StackPane centerStack;
    private Pane center;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public HeaderMenu() {
        components = new Components();
        stage = new Stage();
        menuBar = new VBox();
        menuIcon = new VBox();
        root = new BorderPane();
        head = new VBox();

        boardHBox = new HBox();
        listHBox = new HBox();
        newOrderHBox = new HBox();
        customerHBox = new HBox();
        supplyHBox = new HBox();
        adminHBox = new HBox();
        exitHBox = new HBox();

        center = new Pane();
        menuContainer = new StackPane(menuIcon, menuBar);
        centerStack = new StackPane(center, menuContainer);
        loadIcons();
    }

    public void screen(){
        menu1();
        menu2();
        header();
        visibility();
        actions();

        center.setMaxSize(screenWidth - 80, screenHeight - head.getHeight());
        menuContainer.setMinSize(80, screenHeight - head.getHeight());

        StackPane.setAlignment(center, Pos.CENTER_RIGHT);
        StackPane.setAlignment(menuContainer, Pos.TOP_LEFT);

        root.setCenter(centerStack);
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src\\main\\resources\\styles\\principal.css").toURI().toString());
        stage.setTitle("Tablero de Ordenes");
        stage.setMaxHeight(screenHeight);
        stage.setMaxWidth(screenWidth);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    // Icon Menu
    public void menu1(){
        menuIcon.setPrefSize(80, screenHeight - head.getHeight());
        menuContainer.setMaxSize(80, screenHeight - head.getHeight());
        menuIcon.getStyleClass().add("custom-background");

        boardLabel = new Label("", boardImageV);
        listLabel = new Label("", listImageV);
        newOrderLabel = new Label("", newOrderImageV);
        customerLabel = new Label("", customerImageV);
        supplyLabel = new Label("", supplyImageV);
        adminLabel = new Label("", adminImageV);
        exitLabel = new Label("", exitImageV);

        VBox.setMargin(boardLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(listLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(newOrderLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(customerLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(supplyLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(adminLabel, new Insets(10, 0, 10, 0));
        VBox.setMargin(exitLabel, new Insets(10, 0, 10, 0));

        boardLabel.setCursor(Cursor.HAND);
        listLabel.setCursor(Cursor.HAND);
        newOrderLabel.setCursor(Cursor.HAND);
        customerLabel.setCursor(Cursor.HAND);
        supplyLabel.setCursor(Cursor.HAND);
        adminLabel.setCursor(Cursor.HAND);
        exitLabel.setCursor(Cursor.HAND);

        menuIcon.setAlignment(Pos.CENTER);
        menuIcon.getChildren().addAll(boardLabel, listLabel, newOrderLabel, customerLabel, supplyLabel, adminLabel, exitLabel);
        root.setCenter(centerStack);
    }

    // Complete Menu
    public void menu2(){
        menuBar.setPrefSize(235, screenHeight - head.getHeight());
        menuBar.getStyleClass().add("custom-background");
        loadIcons();

        Label boardLabel = new Label("Tablero");
        Label listLabel = new Label("Lista");
        Label newOrderLabel = new Label("Nueva Orden");
        Label customerLabel = new Label("Clientes");
        Label supplyLabel = new Label("Insumos");
        Label adminLabel = new Label("Administrador");
        Label exitLabel = new Label("Salir");

        boardLabel.setFont(createFont(1, 20));
        listLabel.setFont(createFont(1, 20));
        newOrderLabel.setFont(createFont(1, 20));
        customerLabel.setFont(createFont(1, 20));
        supplyLabel.setFont(createFont(1, 20));
        adminLabel.setFont(createFont(1, 20));
        exitLabel.setFont(createFont(1, 20));

        boardLabel.getStyleClass().add("custom-textWhite");
        listLabel.getStyleClass().add("custom-textWhite");
        newOrderLabel.getStyleClass().add("custom-textWhite");
        customerLabel.getStyleClass().add("custom-textWhite");
        supplyLabel.getStyleClass().add("custom-textWhite");
        adminLabel.getStyleClass().add("custom-textWhite");
        exitLabel.getStyleClass().add("custom-textWhite");

        boardHBox.setSpacing(15);
        listHBox.setSpacing(15);
        newOrderHBox.setSpacing(15);
        customerHBox.setSpacing(15);
        supplyHBox.setSpacing(15);
        adminHBox.setSpacing(15);
        exitHBox.setSpacing(15);

        boardHBox.getChildren().addAll(boardImageV, boardLabel);
        listHBox.getChildren().addAll(listImageV, listLabel);
        newOrderHBox.getChildren().addAll(newOrderImageV, newOrderLabel);
        customerHBox.getChildren().addAll(customerImageV, customerLabel);
        supplyHBox.getChildren().addAll(supplyImageV, supplyLabel);
        adminHBox.getChildren().addAll(adminImageV, adminLabel);
        exitHBox.getChildren().addAll(exitImageV, exitLabel);

        boardHBox.setPrefHeight(60);
        listHBox.setPrefHeight(60);
        newOrderHBox.setPrefHeight(60);
        customerHBox.setPrefHeight(60);
        supplyHBox.setPrefHeight(60);
        adminHBox.setPrefHeight(60);
        exitHBox.setPrefHeight(60);

        boardHBox.setCursor(Cursor.HAND);
        listHBox.setCursor(Cursor.HAND);
        newOrderHBox.setCursor(Cursor.HAND);
        customerHBox.setCursor(Cursor.HAND);
        supplyHBox.setCursor(Cursor.HAND);
        adminHBox.setCursor(Cursor.HAND);
        exitHBox.setCursor(Cursor.HAND);

        HBox.setMargin(boardImageV, new Insets(0,0,0,30));
        HBox.setMargin(listImageV, new Insets(0,0,0,30));
        HBox.setMargin(newOrderImageV, new Insets(0,0,0,30));
        HBox.setMargin(customerImageV, new Insets(0,0,0,30));
        HBox.setMargin(supplyImageV, new Insets(0,0,0,30));
        HBox.setMargin(adminImageV, new Insets(0,0,0,30));
        HBox.setMargin(exitImageV, new Insets(0,0,0,30));

        menuBar.setAlignment(Pos.CENTER);
        menuBar.getChildren().addAll(boardHBox, listHBox, newOrderHBox, customerHBox, supplyHBox, adminHBox, exitHBox);
        menuBar.setVisible(false);
    }

    public void loadIcons(){
        boardImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Board.png")).toExternalForm());
        listImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/OrderList.png")).toExternalForm());
        newOrderImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/NewOrder.png")).toExternalForm());
        customerImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Customers.png")).toExternalForm());
        supplyImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Supplies.png")).toExternalForm());
        adminImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Admin.png")).toExternalForm());
        exitImage = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/LogOut.png")).toExternalForm());

        boardImageV = new ImageView(boardImage);
        listImageV = new ImageView(listImage);
        newOrderImageV = new ImageView(newOrderImage);
        customerImageV = new ImageView(customerImage);
        supplyImageV = new ImageView(supplyImage);
        adminImageV = new ImageView(adminImage);
        exitImageV = new ImageView(exitImage);

        boardImageV.setFitHeight(40);
        listImageV.setFitHeight(40);
        newOrderImageV.setFitHeight(40);
        customerImageV.setFitHeight(40);
        supplyImageV.setFitHeight(40);
        adminImageV.setFitHeight(40);
        exitImageV.setFitHeight(40);

        boardImageV.setFitWidth(40);
        listImageV.setFitWidth(40);
        newOrderImageV.setFitWidth(40);
        customerImageV.setFitWidth(40);
        supplyImageV.setFitWidth(40);
        adminImageV.setFitWidth(40);
        exitImageV.setFitWidth(40);
    }

    public void header(){
        head.getStyleClass().add("custom-background");

        Label title = new Label("Queen's Corner Gallery");
        Font fontTitle = Font.loadFont(getClass().getResourceAsStream("/styles/utilities/fonts/CinzelDecorative-Regular.ttf"), 45); // No deja cuadrar el tamaño del head. Head depende del tamaño de la letra

        title.getStyleClass().add("custom-title");
        title.setFont(fontTitle);
        title.setAlignment(Pos.TOP_CENTER);
        head.setAlignment(Pos.TOP_CENTER);

        head.getChildren().add(title);
        //head.setMaxSize(1286, 80);
        //title.setPrefHeight(2);
        root.setTop(head);
    }

    public void actions(){

        boardHBox.setOnMouseClicked(event -> {
            Board board = new Board();
            center = board.screen();
            center.setMaxWidth(screenWidth - 80);
            StackPane.setAlignment(center, Pos.CENTER_RIGHT);
            root.setCenter(centerStack);
        });

        newOrderHBox.setOnMouseClicked(event -> {
            NewOrder order = new NewOrder();
            center = order.screen();
            center.setMaxWidth(screenWidth - 80);
            StackPane.setAlignment(center, Pos.CENTER_RIGHT);
            root.setCenter(centerStack);
        });

        listHBox.setOnMouseClicked(event -> {
            OrderList orderList = new OrderList();
            center = orderList.screen();
            center.setMaxWidth(screenWidth - 80);
            StackPane.setAlignment(center, Pos.CENTER_RIGHT);
            root.setCenter(centerStack);
        });

        customerHBox.setOnMouseClicked(event -> {
            CustomerList customerList = new CustomerList();
            center = customerList.screen();
            center.setMaxWidth(screenWidth - 80);
            StackPane.setAlignment(center, Pos.CENTER_RIGHT);
            root.setCenter(centerStack);
        });

        supplyHBox.setOnMouseClicked(event -> {
            SupplyList supplyList = new SupplyList();
            center = supplyList.screen();
            center.setMaxWidth(screenWidth - 80);
            StackPane.setAlignment(center, Pos.CENTER_RIGHT);
            root.setCenter(centerStack);
        });
    }

    // Menu Visibility
    public void visibility(){
        menuIcon.setOnMouseEntered(event -> {
            menuIcon.setVisible(false);
            menuBar.setVisible(true);
            menuContainer.setMaxWidth(235);
            root.setCenter(centerStack);
        });

        menuBar.setOnMouseExited(event -> {
            menuBar.setVisible(false);
            menuIcon.setVisible(true);
            menuContainer.setMaxWidth(80);
            menuContainer.setAlignment(Pos.TOP_LEFT);
            centerStack = new StackPane(center, menuContainer); // Pone el pane con la info sobre el menu
            root.setCenter(centerStack);
        });
    }

    public void setCenter(Pane center) {
        this.center = center;
    }
}