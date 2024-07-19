package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JFrame implements ActionListener {

    private Components components;
    private JPanel contentPanel;
    private JPanel contentTitle;
    private JPanel emailPanel;
    private JPanel passwordPanel;
    private JPanel datesPanel;
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel iconLabel;
    private JLabel iconLabel2;
    private JLabel newEmailLabel;
    private JLabel newPasswordLabel;
    private JLabel titlePassword;
    private JPasswordField passwordField;
    private JPasswordField newPasswordField;
    private JTextField emailTextField;
    private JButton saveEmailButton;
    private JButton savePasswordButton;
    private Logic logic = Logic.getInstance();
    private boolean isPasswordVisible = false;

    public Admin(JPanel mainContentPanel) {
        components = new Components(mainContentPanel);
    }

    // Method for initializing content panel
    public JPanel initializeContentPanel() {
        contentPanel = new JPanel(null);  // Usar null layout para posición absoluta
        contentPanel.setPreferredSize(new Dimension(1366, 670));
        //contentPanel.setBorder(BorderFactory.createEmptyBorder(75,40,0,0));
        contentPanel.setBackground(Color.WHITE);

        datesPanel = new JPanel(null);
        datesPanel.setBackground(Color.WHITE);
        datesPanel.setBounds(40, 100, 1366, 570);

        // Panel title
        initializeContentTitle();

        // Panel de materiales
        panelEmail();

        // Panel cantidad
        panelPassword();
        return contentPanel;
    }

    // Method for creating title and search field
    private void initializeContentTitle() {
        contentTitle = new JPanel();
        contentTitle.setLayout(null);
        contentTitle.setBackground(Color.WHITE);
        contentTitle.setForeground(Color.BLACK);
        contentTitle.setPreferredSize(new Dimension(1208, 354));

        // title label
        titleLabel = new JLabel("Administrador", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        titleLabel.setBounds(0, 0, 1286, 100);

        titleLabel.setFont(components.createFont(0, 40));

        contentTitle.add(titleLabel);

        contentTitle.setBounds(0, 0, 1286, 100);
        contentPanel.add(contentTitle, BorderLayout.NORTH);
    }

    // Método para el panel de materiales
    private void panelEmail() {
        emailPanel = new JPanel(null);
        emailPanel.setBackground(Color.decode("#D9D9D9"));
        emailPanel.setBounds(40, 75, 604, 354); // Establecer la posición y tamaño adecuados

        emailLabel = new JLabel("Actualizar Correo");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(components.createFont(0, 30));
        emailLabel.setBounds(150, 60, 300, 40);
        emailPanel.add(emailLabel);

        newEmailLabel = new JLabel("Nuevo Correo");
        newEmailLabel.setForeground(Color.BLACK);
        newEmailLabel.setFont(components.createFont(0, 20));
        newEmailLabel.setBounds(60, 120, 150, 34);
        emailPanel.add(newEmailLabel);

        emailTextField = components.createRoundedTextField(30,30);
        emailTextField.setBounds(200, 120, 250, 34);
        emailTextField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        emailPanel.add(emailTextField);

        saveEmailButton = components.createRoundedButton("Actualizar", "#000000", "#2F1940", 30, 30);
        saveEmailButton.setBounds(60, 200, 150, 34);
        emailPanel.add(saveEmailButton);

        saveEmailButton.addActionListener(this);

        datesPanel.add(emailPanel); // Agregar al contentPanel
    }

    // Método para el panel de cantidad
    private void panelPassword() {
        passwordPanel = new JPanel(null);
        passwordPanel.setBackground(Color.decode("#D9D9D9"));
        passwordPanel.setBounds(644, 75, 604, 354); // Establecer la posición y tamaño adecuados

        titlePassword = new JLabel("Actualizar Contraseña");
        titlePassword.setForeground(Color.BLACK);
        titlePassword.setFont(components.createFont(0, 30));
        titlePassword.setBounds(150, 60, 300, 40);
        passwordPanel.add(titlePassword);

        passwordLabel = new JLabel("Contraseña Actual");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(components.createFont(0, 20));
        passwordLabel.setBounds(60, 120, 200, 40);
        passwordPanel.add(passwordLabel);

        passwordField = components.createRoundedPasswordField(30,30);
        passwordField.setBounds(250, 120, 250, 34);
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        passwordPanel.add(passwordField);

        ImageIcon icon = new ImageIcon("src\\Utilities\\Images\\Eye.png");
        Image image = icon.getImage();
        ImageIcon eyeIcon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        iconLabel = new JLabel(eyeIcon);
        iconLabel.setBounds(525, 125, 20, 20);
        passwordPanel.add(iconLabel);

        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    passwordField.setEchoChar((char) 0); // allows you to view the password
                } else {
                    passwordField.setEchoChar('*'); // hide the password
                }
            }
        });

        newPasswordLabel = new JLabel("Nueva Contraseña");
        newPasswordLabel.setForeground(Color.BLACK);
        newPasswordLabel.setFont(components.createFont(0, 20));
        newPasswordLabel.setBounds(60, 180, 200, 40);
        passwordPanel.add(newPasswordLabel);

        newPasswordField = components.createRoundedPasswordField(30,30);
        newPasswordField.setBounds(250, 180, 250, 34);
        newPasswordField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        passwordPanel.add(newPasswordField);

        iconLabel2 = new JLabel(eyeIcon);
        iconLabel2.setBounds(525, 185, 20, 20);
        passwordPanel.add(iconLabel2);

        iconLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    newPasswordField.setEchoChar((char) 0); // allows you to view the password
                } else {
                    newPasswordField.setEchoChar('*'); // hide the password
                }
            }
        });

        savePasswordButton = components.createRoundedButton("Actualizar", "#000000", "#2F1940", 30, 30);
        savePasswordButton.setBounds(60, 260, 150, 34);
        passwordPanel.add(savePasswordButton);

        savePasswordButton.addActionListener(this);

        datesPanel.add(passwordPanel); // Agregar al contentPanel
        contentPanel.add(datesPanel, BorderLayout.CENTER); // Agregar al contentPanel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveEmailButton) {

            if (emailTextField.getText().isEmpty()) {
                components.messageConfirmation("El correo no puede estar vacío");
            }else {
                String message = logic.Email(emailTextField.getText());
                if (!message.equals("Creado")) {
                    components.messageConfirmation(message);
                }else {
                    emailTextField.setText("");
                    components.messageConfirmation("Correo actualizado con éxito");
                }
            }
            // Starts a timer to close the window after 1 seconds
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    components.getConfirmationFrame2().dispose();
                }
            });
            timer.setRepeats(false); // To make the timer only execute once
            timer.start();
        }
        else if (e.getSource() == savePasswordButton) {

            if (passwordField.getText().isEmpty() || newPasswordField.getText().isEmpty()) {
                components.messageConfirmation("El campo de la contraseña no puede estar vacío");
            }else if (!passwordField.getText().equals(logic.getPassword())) {
                passwordField.setText("");
                components.messageConfirmation("La contraseña actual no coincide con la contraseña actual");
            }else if (passwordField.getText().equals(newPasswordField.getText())) {
                newPasswordField.setText("");
                components.messageConfirmation("La nueva contraseña no puede ser igual a la actual");
            }else {
                String message = logic.Password(newPasswordField.getText());
                if (!message.equals("Creado")) {
                    newPasswordField.setText("");
                    components.messageConfirmation(message);
                }else {
                    passwordField.setText("");
                    newPasswordField.setText("");
                    components.messageConfirmation("Contraseña actualizada con éxito");
                }
            }
            // Starts a timer to close the window after 1 seconds
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    components.getConfirmationFrame2().dispose();
                }
            });
            timer.setRepeats(false); // To make the timer only execute once
            timer.start();
        }
    }
}
