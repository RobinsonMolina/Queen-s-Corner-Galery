package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static co.edu.uptcSoft.view.Components.createFont;

public class Board {
    private Logic logic = Logic.getInstance();
    private BorderPane contentPanel;
    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public Board() {
        contentPanel = new BorderPane();
        createContentPanel();
        contentPanel.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString()); // Carga el CSS
    }

    public BorderPane screen() {
        contentPanel = new BorderPane();
        createContentPanel();
        contentPanel.getStylesheets().add(new File("src/main/resources/styles/principal.css").toURI().toString()); // Carga el CSS
        return contentPanel;
    }

    private void createContentPanel() {
        contentPanel.getStyleClass().add("cards-panel");
        contentPanel.setPrefSize(1366, 670);

        // Título
        HBox titleHBox = new HBox();
        Label titleLabel = new Label("Tablero");
        titleLabel.setFont(createFont(0, 60)); // Cambiar fuente del título principal
        titleLabel.setTextFill(Color.BLACK);
        titleHBox.setPadding(new Insets(20, 0, 0, 30));
        titleHBox.getChildren().add(titleLabel);
        titleHBox.setAlignment(Pos.CENTER);
        contentPanel.setTop(titleHBox);

        // Panel de tarjetas
        HBox cardsPanel = new HBox();
        cardsPanel.getStyleClass().add("cards-panel");
        cardsPanel.setSpacing(20);
        addCard(cardsPanel, "Por Hacer", logic.getOrdersDo());
        addCard(cardsPanel, "En Progreso", logic.getOrdersProgress());
        addCard(cardsPanel, "Entregado", logic.getOrdersDelivered());

        // Agregar directamente el HBox de tarjetas al centro del BorderPane
        contentPanel.setCenter(cardsPanel);
    }

    private void addCard(HBox cardsPanel, String title, ArrayList<String> orders) {
        VBox card = new VBox();
        card.setPrefWidth(380); // Ajusta el ancho de la tarjeta
        card.setPrefHeight(240); // Ajusta la altura de la tarjeta
        HBox cardHeader = new HBox();

        // Título de la tarjeta
        Label cardTitle = new Label(title);
        cardTitle.setFont(createFont(0, 20)); // Cambiar fuente del título de la tarjeta
        cardTitle.setTextFill(Color.BLACK);
        cardHeader.getChildren().add(cardTitle);
        cardHeader.setAlignment(Pos.CENTER);
        cardHeader.setPadding(new Insets(-20, 0, 0, 20));
        card.getChildren().add(cardHeader);

        VBox orderPanel = new VBox();
        orderPanel.setSpacing(8);

        // Botones de órdenes
        for (String order : orders) {
            Button orderButton = new Button(order);
            orderButton.setPrefSize(350, 30);
            orderButton.getStyleClass().add("order-button");
            orderButton.setFont(createFont(1, 20)); // Cambiar fuente de los botones
            changeWindow(orderButton, order);
            orderPanel.getChildren().add(orderButton);
        }

        ScrollPane cardScrollPane = new ScrollPane(orderPanel);
        cardScrollPane.setPrefHeight(360); // Ajusta la altura del ScrollPane
        cardScrollPane.setFitToWidth(true);
        cardScrollPane.setPadding(new Insets(-10, 0, 0, 0));
        card.getChildren().add(cardScrollPane);
        card.getStyleClass().add("card");
        cardTitle.getStyleClass().add("card-title");
        orderPanel.getStyleClass().add("order-panel");
        cardsPanel.getChildren().add(card);
        cardsPanel.setPadding(new Insets(20, 0, 120, 60));
    }

    public void changeWindow(Button orderButton, String order) {
        orderButton.setOnAction(event -> {
            String number = extractNumber(order);// Extrae el número de la orden
            SpecificOrder sOrder = new SpecificOrder();
            assert number != null;
            sOrder.setOrder(logic.searchOrder(Integer.parseInt(number)));
            sOrder.loadOrder();
            contentPanel.getChildren().clear();
            contentPanel.setMinSize(screenWidth - 80, screenHeight - 80);
            contentPanel.getChildren().add(sOrder.screen());
        });
    }

    private String extractNumber(String input) {
        // Expresión regular para encontrar números
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        // Verificar si hay coincidencias
        if (matcher.find()) {
            return matcher.group(); // Retorna el primer número encontrado
        }

        return null; // Si no se encuentra ningún número
    }
}
