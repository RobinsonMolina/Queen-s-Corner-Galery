package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Supply;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.text.Font.loadFont;


public class Components {

    private Button buttonYes;
    private Button buttonNo;
    private Stage stage2;
    //private Frame confirmationFrame;
    //private Frame confirmationFrame2;
    private String message;
    //private Panel confirmationPanel;
    private Logic logic = logic = Logic.getInstance(); // Get the single instance of Logic
    private String row;
    //private Panel mainContentPanel;
    private Customer currentCustomer = new Customer();
    private Supply currentSupply = new Supply();
    //private ArrayList<Supply> supplyList;
    private NewOrder order;
    private OrderList orderList; // Agrega una referencia a OrderList
    private CustomerList customerList;
    private SupplyList supplyList;

    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    public Components() {
    }

    public Components(OrderList orderList) {
        this.orderList = orderList;
    }

    public Components(CustomerList customerList) {
        this.customerList = customerList;
    }

    public Components(SupplyList supplyList) {
        this.supplyList = supplyList;
    }

    // Method for creating fonts
    public static Font createFont(int style, int size) {
        String fontFilePath = (style == 0) ? "/styles/utilities/fonts/Buenard-Bold.ttf" : "/styles/utilities/fonts/Buenard-Regular.ttf";

        return loadFont(Components.class.getResourceAsStream(fontFilePath), size);
    }

    /*public Button createRoundedButton(String text, String borderColor, String fillColor, double arcWidth, double arcHeight) {
        Button button = new Button(text);

        // Establecer el fondo del botón
        button.setStyle("-fx-background-color: " + fillColor + "; -fx-text-fill: green; -fx-font-size: 20px;");

        // Crear un efecto de sombra
        /*DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(0);
        shadow.setOffsetY(0);
        shadow.setColor(Color.GRAY);
        button.setEffect(shadow);*/

        // Configurar el tamaño del botón
        //button.setPrefSize(200, 50); // Ajusta el tamaño según sea necesario
        //button.setShape(new Rectangle(200, 50, arcWidth, arcHeight)); // Establecer forma redondeada
        //button.setClip(new Rectangle(200, 50, arcWidth, arcHeight)); // Recortar el botón a la forma redondeada

        //return button;
   // }

    public Button createRoundedButton(String text, String borderColor, String fillColor, double arcWidth, double arcHeight) {
        Button button = new Button(text);

        // Establecer el fondo y el color del texto del botón
        button.setStyle("-fx-background-color: " + fillColor + "; -fx-text-fill: white; -fx-font-size: 16px;");

        // Configurar el tamaño del botón
        button.setPrefSize(200, 50);

        // Establecer la forma redondeada del botón
        button.setShape(new Rectangle(button.getPrefWidth(), button.getPrefHeight(), arcWidth, arcHeight));

        // Configurar el clip para que el contenido se ajuste a la forma redondeada
        button.setClip(new Rectangle(button.getPrefWidth(), button.getPrefHeight(), arcWidth, arcHeight));

        // Aplicar borde redondeado
        button.setStyle(button.getStyle() + " -fx-border-color: " + borderColor + "; -fx-border-width: 2px;");

        return button;
    }
    /*
    *//*
    public void hoverButton(JButton buttonAdd){
        buttonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonAdd.setBackground(Color.decode("#411365"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonAdd.setBackground(Color.decode("#2F1940"));
            }
        });
    }
    *//*
    public void hoverButtonBoard(JButton buttonAdd){
        buttonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonAdd.setBackground(Color.decode("#2F1940"));
                buttonAdd.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonAdd.setBackground(Color.WHITE);
                buttonAdd.setForeground(Color.decode("#2F1940"));
            }
        });
    }
    *//*

    */
    // Method for rounded text field
    /*public TextField createRoundedTextField(double arcWidth, double arcHeight) {
        TextField textField = new TextField();
        textField.getStyleClass().add("rounded-text-field");

        // Create a pane to wrap the text field and apply the border
        Pane pane = new Pane(textField) {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                // Apply rounded corners to the text field
                Rectangle clip = new Rectangle(textField.getWidth(), textField.getHeight());
                clip.setArcWidth(arcWidth);
                clip.setArcHeight(arcHeight);
                textField.setClip(clip);
            }
        };

        return textField;
    }*/

    public TextField createRoundedTextField(double arcw, double arch) {
        TextField textField = new TextField();
        textField.getStyleClass().add("rounded-text-field");

        // Create a rounded rectangle as the clip
        Rectangle rect = new Rectangle();
        rect.setArcWidth(arcw);
        rect.setArcHeight(arch);
        rect.widthProperty().bind(textField.widthProperty());
        rect.heightProperty().bind(textField.heightProperty());

        textField.setClip(rect);

        return textField;
    }

    /*
    *//*
    // Method for rounded text field
    public JPasswordField createRoundedPasswordField(int arcw, int arch) {
        return new JPasswordField() {
            @Override
            // Set the size of the field
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcw, arch));
                setOpaque(false);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            // Draw the border of the field
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcw, arch));
                setOpaque(false);
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcw, arch);
                return shape.contains(x, y);
            }
        };
    }
    *//*
    // Method for creating a limit of characters introduced in the text field
    public void limitTextField(JTextField textField, int limit) {
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textField.getText().length() >= 10)// if the length of the text is greater than 10
                {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    *//*
    // window method to confirm
    public void windowConfirmation(String title, String button1, String button2, String message) {

        this.message = message;
        confirmationFrame = new JFrame();
        confirmationFrame.setTitle("Confirmación");
        confirmationFrame.setSize(500, 130);
        confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmationFrame.setLocationRelativeTo(null);

        confirmationPanel = new JPanel();
        confirmationPanel.setBackground(Color.WHITE);
        JLabel confirmationLabel = new JLabel(title);
        confirmationLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmationLabel.setFont(createFont(0, 20));
        confirmationLabel.setForeground(Color.BLACK);
        confirmationPanel.add(confirmationLabel, BorderLayout.NORTH);

        // Rounded button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Button yes
        buttonNo = createRoundedButton(button1, "#000000", "#2F1940", 30, 30);
        buttonNo.setPreferredSize(new Dimension(150, 34));
        buttonPanel.add(buttonNo);

        buttonNo.addActionListener(this);

        // Button no
        buttonYes = createRoundedButton(button2, "#000000", "#2F1940", 30, 30);
        buttonYes.setPreferredSize(new Dimension(150, 34));
        buttonPanel.add(buttonYes);


        buttonYes.addActionListener(this);
        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);

        confirmationFrame.add(confirmationPanel);
        confirmationFrame.setVisible(true);
    }
    */
    //method overloaded to confirm the message after clicking on the yes button
    // window method to confirm
    /*public void windowConfirmation(String title, String button1, String button2, String message, String row) {

        this.message = message;
        this.row = row;
        confirmationFrame = new JFrame();
        confirmationFrame.setTitle("Confirmación");
        confirmationFrame.setSize(500, 130);
        confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmationFrame.setLocationRelativeTo(null);

        confirmationPanel = new JPanel();
        confirmationPanel.setBackground(Color.WHITE);
        JLabel confirmationLabel = new JLabel(title);
        confirmationLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmationLabel.setFont(createFont(0, 20));
        confirmationLabel.setForeground(Color.BLACK);
        confirmationPanel.add(confirmationLabel, BorderLayout.NORTH);

        // Rounded button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Button yes
        buttonNo = createRoundedButton(button1, "#000000", "#2F1940", 30, 30);
        buttonNo.setPreferredSize(new Dimension(150, 34));
        buttonPanel.add(buttonNo);

        buttonNo.addActionListener(this);

        // Button no
        buttonYes = createRoundedButton(button2, "#000000", "#2F1940", 30, 30);
        buttonYes.setPreferredSize(new Dimension(150, 34));
        buttonPanel.add(buttonYes);


        buttonYes.addActionListener(this);
        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);

        confirmationFrame.add(confirmationPanel);
        confirmationFrame.setVisible(true);
    }*/


    // es este----------------------------------------------------->>>>>>>>>>>>>>>>>>
    public void windowConfirmation(String title, String button1, String button2, String message, String row) {
        this.message = message;
        this.row = row;

        stage2 = new Stage();
        VBox root = new VBox(10);
        HBox messageHBox = new HBox();
        HBox buttonPanel = new HBox(50);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/principal.css").toExternalForm());
        Label messageLabel = new Label(title);

        messageLabel.setFont(createFont(0, 20));

        messageHBox.setAlignment(Pos.CENTER);
        messageHBox.getChildren().add(messageLabel);

        Button buttonNo = new Button(button1);
        buttonNo.getStyleClass().add("rounded-button");
        buttonNo.getStyleClass().add("rounded-button:hover");
        buttonNo.getStyleClass().add("rounded-button:pressed");
        buttonNo.setPrefSize(150, 34);
        buttonNo.setOnAction(event -> handleButtonNoAction());
        buttonPanel.getChildren().add(buttonNo);

        Button buttonYes = new Button(button2);
        buttonYes.getStyleClass().add("rounded-button");
        buttonYes.getStyleClass().add("rounded-button:hover");
        buttonYes.getStyleClass().add("rounded-button:pressed");
        buttonYes.setPrefSize(150, 34);
        buttonYes.setOnAction(event -> handleButtonYesAction());
        buttonPanel.getChildren().add(buttonYes);

        buttonPanel.setAlignment(Pos.CENTER);

        root.getChildren().addAll(messageHBox, buttonPanel);


        stage2.setTitle("Mensaje de confirmación");
        stage2.setHeight(130);
        stage2.setWidth(500);
        stage2.setScene(scene);
        stage2.show();
    }

    // Method for confirming the message after clicking on the yes button
public void messageConfirmation(String message) {
        Stage stage = new Stage();
        HBox root = new HBox();
        Scene scene = new Scene(root);
        Label messageLabel = new Label(message);

        messageLabel.setFont(createFont(0, 20));

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(messageLabel);

        stage.setTitle("Mensaje de confirmación");
        stage.setHeight(130);
        stage.setWidth(500);
        stage.setScene(scene);
        stage.show();

        // 2 seg
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e ->stage.close()));
        timeline.setCycleCount(1); // Ejecutar solo una vez
        timeline.play();
    }

    private void handleButtonNoAction() {
        stage2.close();
    }

    private void handleButtonYesAction() {
        // change the content of the main panel instead of opening a new window
        if (message.contains("Orden")) {
            long orderNumber = Long.parseLong(row);
            logic.deleteOrder(orderNumber); // Eliminar la orden

            // Actualizar la tabla después de eliminar la orden
            if (orderList != null) {
                orderList.refreshTable(); // Llamar al método para refrescar la tabla
            }

            messageConfirmation(message);
        } else if (message.contains("Insumo eliminado")) {
            String id = row;
            logic.deleteSupply(id); // Elimina el cliente

            // Actualizar la tabla después de eliminar el cliente
            if (supplyList != null) {
                supplyList.refreshTable(); // Llamar al método para refrescar la tabla
            }

            messageConfirmation(message);
        } else if (message.contains("Cliente añadido")) {
            //Logica para agregar cliente
        } else if (message.contains("Cliente eliminado")) {
            long numDocument = Long.parseLong(row);
            logic.deleteCustomer(numDocument); // Elimina el cliente

            // Actualizar la tabla después de eliminar el cliente
            if (customerList != null) {
                customerList.refreshTable(); // Llamar al método para refrescar la tabla
            }

            messageConfirmation(message);
        } else if (message.contains("Insumo agregado")) {
            //mainContentPanel.add(new SupplyList(mainContentPanel).initializeContentPanel());
        } else if (message.contains("Insumo añadido")) {
            /*order.setSupplyList(getSupplyList());
            order.setCurrentCustomer(getCurrentCustomer());
            mainContentPanel.add(order.addSpecificOrder(0));*/
        }
        stage2.close();
    }

    /*
    // Error message
    public void windowConfirmation(String title, String button1, String message) {

        this.message = message;
        confirmationFrame = new JFrame();
        confirmationFrame.setTitle("Error");
        confirmationFrame.setSize(500, 130);
        confirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmationFrame.setLocationRelativeTo(null);

        confirmationPanel = new JPanel();
        confirmationPanel.setBackground(Color.WHITE);
        JLabel confirmationLabel = new JLabel(title);
        confirmationLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmationLabel.setFont(createFont(0, 20));
        confirmationLabel.setForeground(Color.BLACK);
        confirmationPanel.add(confirmationLabel, BorderLayout.NORTH);

        // Rounded button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(500, 130));

        // Button yes
        buttonNo = createRoundedButton(button1, "#000000", "#2F1940", 30, 30);
        buttonNo.setPreferredSize(new Dimension(150, 34));
        buttonPanel.add(buttonNo);

        buttonNo.addActionListener(this);
        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);

        confirmationFrame.add(confirmationPanel);
        confirmationFrame.setVisible(true);
    }
    *//*
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonYes) {

            // change the content of the main panel instead of opening a new window
                mainContentPanel.removeAll();
            if (message.contains("Orden")) {
                logic.deleteOrder(Long.parseLong(row));
                mainContentPanel.add(new OrderList(mainContentPanel).initializeContentPanel());
            } else if (message.contains("Insumo eliminado")) {
                logic.deleteSupply(row);
                mainContentPanel.add(new SupplyList(mainContentPanel).initializeContentPanel());
            } else if (message.contains("Cliente añadido")) {
                new NewCustomer(mainContentPanel).addCustomerLogic(currentCustomer);
                mainContentPanel.add(new CustomerList(mainContentPanel).initializeContentPanel());
            } else if (message.contains("Cliente eliminado")) {
                logic.deleteCustomer(Long.parseLong(row));
                mainContentPanel.add(new CustomerList(mainContentPanel).initializeContentPanel());
            } else if (message.contains("Insumo agregado")) {
                mainContentPanel.add(new SupplyList(mainContentPanel).initializeContentPanel());
            } else if (message.contains("Insumo añadido")) {
                order.setSupplyList(getSupplyList());
                order.setCurrentCustomer(getCurrentCustomer());
                mainContentPanel.add(order.addSpecificOrder(0));
            }

            mainContentPanel.revalidate();
            mainContentPanel.repaint();
            confirmationFrame.dispose();
            messageConfirmation(message);
            // Starts a timer to close the window after 1 seconds
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    confirmationFrame2.dispose();
                }
            });
            timer.setRepeats(false); // To make the timer only execute once
            timer.start();
        } else if (e.getSource() == buttonNo) {
            confirmationFrame.dispose();
        }
    }
    *//*
    public JFrame getConfirmationFrame2() {
        return confirmationFrame2;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Supply getCurrentSupply() {
        return currentSupply;
    }

    public void setCurrentSupply(Supply currentSupply) {
        this.currentSupply = currentSupply;
    }

    public NewOrder getOrder() {
        return order;
    }

    public void setOrder(NewOrder order) {
        this.order = order;
    }

    public ArrayList<Supply> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(ArrayList<Supply> supplyList) {
        this.supplyList = supplyList;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }*/
}
