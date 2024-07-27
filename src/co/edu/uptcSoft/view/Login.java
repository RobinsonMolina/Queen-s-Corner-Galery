package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

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
    }
}
