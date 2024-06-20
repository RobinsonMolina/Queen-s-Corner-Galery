package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;

public class Login {

    private JFrame loginWindow;
    private ImageIcon icon;
    private Image image;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JLabel imagePanel;


    public Login() {
        loginWindow = new JFrame("Login");
        mainPanel = new JPanel(new BorderLayout());
        icon = new ImageIcon(".\\src\\co\\edu\\uptcSoft\\resources\\Login.jpg");
        image = icon.getImage().getScaledInstance(812, 670, Image.SCALE_DEFAULT);
        imagePanel = new JLabel(new ImageIcon(image));
        infoPanel = new JPanel();
    }

    public void createWindow(){

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
        JTextField emailText = new JTextField("Ingrese el correo");
        JTextField passwordText = new JTextField("Ingrese la contraseña");
        JButton loginButton = new JButton("Continuar");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        title.setPreferredSize(new Dimension(350, 80));
        email.setMaximumSize(new Dimension(350, 40));
        password.setMaximumSize(new Dimension(350, 40));
        emailText.setMaximumSize(new Dimension(350,40));
        passwordText.setMaximumSize(new Dimension(350,40));

        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(title);
        infoPanel.add(email);
        infoPanel.add(emailText);
        infoPanel.add(password);
        infoPanel.add(passwordText);
        infoPanel.add(loginButton);

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(554, 670));
        mainPanel.add(infoPanel, BorderLayout.EAST);
    }
}
