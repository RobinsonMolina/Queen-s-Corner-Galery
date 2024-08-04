package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.util.Objects;

public class Login extends Application {

    private Stage stage;
    private Scene scene;
    private HBox root;
    private VBox infoVBox;
    private Image image;
    private ImageView imageView;
    private Label titleLabel;
    private Label emailLabel;
    private Label passwordLabel;
    private TextField emailTxt;
    private TextField passwordTxt;
    private Button logInButton;

    Font font = Font.loadFont(getClass().getResourceAsStream("/styles/utilities/fonts/Buenard-Bold.ttf"), 50);
    Font font2 = Font.loadFont(getClass().getResourceAsStream("/styles/utilities/fonts/Buenard-Bold.ttf"), 20);
    double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

    private Components components;

    public Login() {
        stage = new Stage();
        root = new HBox();
        infoVBox = new VBox();
        image = new Image(Objects.requireNonNull(getClass().getResource("/styles/utilities/images/Login.jpg")).toExternalForm());
        imageView = new ImageView(image);
        titleLabel = new Label("Inicia Sesión");
        emailLabel = new Label("Correo");
        passwordLabel = new Label("Contraseña");
        emailTxt = new TextField("tuemail@email.com");
        emailTxt = new TextField("nuevo@gmail.com");

        passwordTxt = new TextField("Ingresa tu Contraseña");
        passwordTxt = new TextField("Nueva1234");

        logInButton = new Button("Continuar");

        components = new Components();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src\\main\\resources\\styles\\principal.css").toURI().toString());

        imageView.setFitHeight(screenHeight);
        imageView.setFitWidth(820);

        infoVBox.setMinHeight(670);
        infoVBox.setMinWidth(554);

        info();

        root.getChildren().addAll(imageView, infoVBox);
        root.setAlignment(Pos.CENTER);
        root.setPrefHeight(600);
        root.setPrefWidth(450);

        stage.setMaxHeight(screenHeight);
        stage.setMaxWidth(screenWidth);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void info(){
        HBox emailHBox = new HBox();
        emailHBox.getChildren().add(emailLabel);
        HBox passwordHBox = new HBox();
        passwordHBox.getChildren().add(passwordLabel);


        infoVBox.getChildren().addAll(titleLabel, emailHBox, emailTxt, passwordHBox, passwordTxt, logInButton);
        infoVBox.setAlignment(Pos.CENTER);
        infoVBox.getStyleClass().add("custom-background");

        titleLabel.setFont(font);
        emailLabel.setFont(font2);
        passwordLabel.setFont(font2);
        emailTxt.setFont(font2);
        passwordTxt.setFont(font2);
        logInButton.setFont(font2);

        titleLabel.getStyleClass().add("custom-textWhite");
        emailLabel.getStyleClass().add("custom-textWhite");
        passwordLabel.getStyleClass().add("custom-textWhite");

        emailTxt.getStyleClass().add("custom-textPurple");
        passwordTxt.getStyleClass().add("custom-textPurple");
        logInButton.getStyleClass().add("custom-textPurple");

        // V: Width, V1: height
        titleLabel.setPrefHeight(65);
        emailLabel.setMinHeight(46);
        passwordLabel.setMinHeight(46);
        emailTxt.setMaxSize(350, 40);
        passwordTxt.setMaxSize(350, 40);
        logInButton.setMaxSize(350, 40);

        // V: up, V1: right, V2: down, V3: left
        VBox.setMargin(emailHBox, new Insets(0, 0, 0, 102));
        VBox.setMargin(passwordHBox, new Insets(0, 0, 0, 102));

        VBox.setMargin(titleLabel, new Insets(0, 0, 73, 0));
        VBox.setMargin(emailTxt, new Insets(0, 0, 18, 0));
        VBox.setMargin(passwordTxt, new Insets(0, 0, 62, 0));

        logInButton.setCursor(Cursor.HAND);
        action();
    }

    public void action(){
        logInButton.setOnAction(e -> buttonAction());
    }

    public void buttonAction(){
        if (!emailTxt.getText().equals(Logic.getInstance().getEmail())) {
            components.messageConfirmation("Correo incorrecto");
        }else if (!passwordTxt.getText().equals(Logic.getInstance().getPassword())){
            components.messageConfirmation("Contraseña incorrecta");
        }else{
            HeaderMenu headerMenu = new HeaderMenu();
            headerMenu.screen();
            stage.close();
            //headerMenu.paneles(new Board(headerMenu.getContentPanel()).contentPanel());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }


    /*
    private JFrame loginWindow;
    private ImageIcon icon;
    private Image image;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JLabel imagePanel;
    private JButton loginButton;
    private Components components;
    private JTextField emailText;
    private JTextField passwordText;
    private Logic logic = Logic.getInstance();

    public Login() {
        loginWindow = new JFrame("Login");
        mainPanel = new JPanel(new BorderLayout());
        icon = new ImageIcon("src\\Utilities\\Images\\Login.jpg");
        image = icon.getImage().getScaledInstance(812, 670, Image.SCALE_DEFAULT);
        imagePanel = new JLabel(new ImageIcon(image));
        infoPanel = new JPanel();
        components = new Components();
    }

    public void createWindow(){
        loginWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(1366, 670);

        createInformation();
        mainPanel.add(imagePanel, BorderLayout.WEST);

        loginWindow.add(mainPanel);
        loginWindow.setVisible(true);
    }

    public void createInformation(){
        JLabel title = new JLabel("Iniciar Sesión");
        JLabel email = new JLabel("Correo");
        JLabel password = new JLabel("Contraseña");
        emailText = new JTextField("");
        passwordText = new JTextField("");
        loginButton = new JButton("Continuar");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setFont(components.createFont(0, 50));
        email.setFont(components.createFont(0, 20));
        password.setFont(components.createFont(0, 20));
        emailText.setFont(components.createFont(1, 20));
        passwordText.setFont(components.createFont(1, 20));
        loginButton.setFont(components.createFont(0, 20));

        title.setForeground(Color.white);
        email.setForeground(Color.white);
        password.setForeground(Color.white);
        loginButton.setBackground(Color.WHITE);


        title.setPreferredSize(new Dimension(350, 85));
        email.setMaximumSize(new Dimension(350, 40));
        password.setMaximumSize(new Dimension(350, 40));
        emailText.setMaximumSize(new Dimension(350,40));
        passwordText.setMaximumSize(new Dimension(350,40));
        loginButton.setMaximumSize(new Dimension(350,40));

        emailText.setBorder(new RoundedBorder(5, null));
        passwordText.setBorder(new RoundedBorder(5, null));
        loginButton.setBorder(new RoundedBorder(5, null));

        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(Box.createVerticalStrut(98));
        infoPanel.add(title);
        infoPanel.add(Box.createVerticalStrut(63));
        infoPanel.add(email);
        infoPanel.add(emailText);
        infoPanel.add(Box.createVerticalStrut(18));
        infoPanel.add(password);
        infoPanel.add(passwordText);
        infoPanel.add(Box.createVerticalStrut(62));
        infoPanel.add(loginButton);

        // Add action listener to the button
        loginButton.addActionListener(this);

        infoPanel.setBackground(Color.decode("#2F1940"));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(554, 670));
        mainPanel.add(infoPanel, BorderLayout.EAST);
    }

    public class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color borderColor;

        public RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(borderColor);
            if (borderColor == null) {
                g2d.setStroke(new BasicStroke(4));
            }
            g2d.drawRoundRect(x, y, width, height, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius, this.radius, this.radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius;
            return insets;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (!emailText.getText().equals(Logic.getInstance().getEmail())) {
                components.messageConfirmation("Correo incorrecto");
            }else if (!passwordText.getText().equals(Logic.getInstance().getPassword())){
                components.messageConfirmation("Contraseña incorrecta");
            }else{
                loginWindow.dispose();
                HeaderMenu headerMenu = HeaderMenu.getInstance();
                headerMenu.paneles(new Board(headerMenu.getContentPanel()).contentPanel());
            }
        }
    }*/
}
