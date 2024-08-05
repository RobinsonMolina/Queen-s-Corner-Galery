package co.edu.uptcSoft.view;

import javafx.application.Application;
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

        menuContainer = new StackPane(menuIcon, menuBar);
        loadIcons();
    }

    public void screen(){
        menu1();
        menu2();
        header();
        visibility();
        actions();

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
        menuIcon.setPrefSize(80, screenHeight);
        menuContainer.setMaxSize(80, screenHeight);
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
        root.setLeft(menuContainer);
    }

    // Complete Menu
    public void menu2(){
        menuBar.setPrefSize(235, screenHeight);
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

    // Menu Visibility
    public void visibility(){
        menuIcon.setOnMouseEntered(event -> {
            menuIcon.setVisible(false);
            menuBar.setVisible(true);
            menuContainer.setMinSize(235, screenHeight);
            root.setLeft(menuContainer);
        });

        menuBar.setOnMouseExited(event -> {
            menuBar.setVisible(false);
            menuIcon.setVisible(true);
            menuIcon.setMaxSize(80, screenHeight);
            menuContainer.setMaxSize(80, screenHeight);
            menuContainer.setAlignment(Pos.CENTER_LEFT);
            root.setLeft(menuContainer);
        });
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
        newOrderHBox.setOnMouseClicked(event -> {
            NewOrder order = new NewOrder();
            root.setCenter(order.screen());
        });
    }

    /*
    private JPanel menuPanel;
    private JPanel menuPanel2;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private static HeaderMenu instance; // Single instance of HeaderMenu

    public HeaderMenu() {
        setTitle("Board");
        setSize(1366, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximizar la ventana al abrirla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        add(getHeaderPanel(), BorderLayout.NORTH);
        add(getMenuPanel(), BorderLayout.WEST);
        contentPanel = new JPanel();
        setVisible(true);
    }

    // Static method to get the single instance of HeaderMenu
    public static HeaderMenu getInstance() {
        if (instance == null) {
            instance = new HeaderMenu();
        }
        return instance;
    }

    public JPanel getHeaderPanel() {
        if (headerPanel == null) {
            headerPanel = new JPanel();
            headerPanel.setPreferredSize(new Dimension(1366, 80));
            headerPanel.setBackground(new Color(47, 25, 64));

            // Title
            JLabel headerLabel = new JLabel("QUEEN'S CORNER GALLERY");
            try {
                headerLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\CinzelDecorative-Regular.ttf")).deriveFont(Font.PLAIN, 60));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            headerLabel.setForeground(Color.decode("#E1B958"));
            headerLabel.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
            headerPanel.add(headerLabel);
        }
        return headerPanel;
    }

    public JPanel getMenuPanel() {
        if (menuPanel == null) {
            menuPanel = new JPanel();
            menuPanel.setPreferredSize(new Dimension(80, 590));
            menuPanel.setBackground(new Color(47, 25, 64));
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

            // Rutas de las imágenes
            String[] imagePaths = {
                    "src\\Utilities\\Images\\Board.png",
                    "src\\Utilities\\Images\\OrderList.png",
                    "src\\Utilities\\Images\\NewOrder.png",
                    "src\\Utilities\\Images\\Customers.png",
                    "src\\Utilities\\Images\\Supplies.png",
                    "src\\Utilities\\Images\\Admin.png",
                    "src\\Utilities\\Images\\LogOut.png"
            };

            // Crear y agregar JLabels con iconos redimensionados
            for (String imagePath : imagePaths) {
                Image image = new ImageIcon(imagePath).getImage();
                Image resizedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

                JLabel iconLabel = new JLabel(new ImageIcon(resizedImage));
                iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                iconLabel.setPreferredSize(new Dimension(40, 40));

                // Agregar un espacio vertical de 10 píxeles entre los íconos
                menuPanel.add(Box.createVerticalStrut(30));
                menuPanel.add(iconLabel);
            }

            // Añadir evento para mostrar menuPanel2 al pasar el mouse
            menuPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLayeredPane layeredPane = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, menuPanel);
                    layeredPane.add(getMenuPanel2(), JLayeredPane.PALETTE_LAYER);
                    getMenuPanel2().setBounds(0, 0, 235, 750);
                    layeredPane.revalidate();
                    layeredPane.repaint();
                }
            });
        }
        return menuPanel;
    }

    public JPanel getMenuPanel2() {
        if (menuPanel2 == null) {
            menuPanel2 = new JPanel();
            menuPanel2.setPreferredSize(new Dimension(235, 750));
            menuPanel2.setLayout(new BoxLayout(menuPanel2, BoxLayout.Y_AXIS));
            menuPanel2.setBackground(new Color(47, 25, 64));

            // Rutas de las imágenes y nombres de las opciones
            String[][] menuItems = {
                    {"src\\Utilities\\Images\\Board.png", "Tablero"},
                    {"src\\Utilities\\Images\\OrderList.png", "Lista"},
                    {"src\\Utilities\\Images\\NewOrder.png", "Nueva Orden"},
                    {"src\\Utilities\\Images\\Customers.png", "Clientes"},
                    {"src\\Utilities\\Images\\Supplies.png", "Insumos"},
                    {"src\\Utilities\\Images\\Admin.png", "Administrador"},
                    {"src\\Utilities\\Images\\LogOut.png", "Salir"}
            };

            JPanel itemPanel = new JPanel();
            itemPanel.setBackground(new Color(47, 25, 64));
            itemPanel.add(Box.createVerticalStrut(80));

            // Crear y agregar botones con iconos redimensionados y nombres
            for (String[] item : menuItems) {
                Image image = new ImageIcon(item[0]).getImage();
                Image resizedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

                JPanel panel = new JPanel(new GridLayout(1, 2, 0, 0));
                panel.setBackground(new Color(47, 25, 64));
                JLabel iconLabel = new JLabel(new ImageIcon(resizedImage));
                JLabel textLabel = new JLabel(item[1]);
                textLabel.setForeground(Color.WHITE);
                panel.add(iconLabel);
                panel.add(textLabel);

                JButton button = new JButton();
                button.add(panel);
                button.setHorizontalTextPosition(SwingConstants.RIGHT);
                button.setPreferredSize(new Dimension(210, 66));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Serif", Font.BOLD, 20));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setBackground(new Color(47, 25, 64));
                button.setBorderPainted(false);
                button.setFocusPainted(true);

                itemPanel.add(button, BorderLayout.WEST);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuPanel2);
                        //frame.dispose(); // Cerrar la ventana actual
                        if (item[1].equals("Tablero")) {
                            paneles(new Board(contentPanel).contentPanel());
                        }else if (item[1].equals("Lista")) {
                            paneles(new OrderList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Nueva Orden")) {
                            paneles(new NewOrder(contentPanel).addSpecificOrder(1));
                        }else if (item[1].equals("Clientes")) {
                            paneles(new CustomerList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Insumos")) {
                            paneles(new SupplyList(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Administrador")) {
                            paneles(new Admin(contentPanel).initializeContentPanel());
                        }else if (item[1].equals("Salir")) {
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menuPanel2);
                            frame.dispose(); // Cerrar la ventana actual
                            Login login = new Login();
                            //login.createWindow();
                        }
                    }
                });
            }

            menuPanel2.add(Box.createVerticalStrut(80));
            menuPanel2.add(itemPanel, BorderLayout.NORTH);

            // Añadir evento para volver a mostrar menuPanel al salir el mouse de menuPanel2
            menuPanel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    Point p = e.getPoint();
                    if (p.x < 0 || p.y < 0 || p.x >= menuPanel2.getWidth() || p.y >= menuPanel2.getHeight()) {
                        JLayeredPane layeredPane = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, menuPanel2);
                        layeredPane.remove(menuPanel2);
                        layeredPane.revalidate();
                        layeredPane.repaint();
                    }
                }
            });
        }
        return menuPanel2;
    }

    public JPanel getContentPanel() {
        contentPanel = new JPanel();
        return contentPanel;
    }

    public void paneles(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(panel);
        add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

     */
}