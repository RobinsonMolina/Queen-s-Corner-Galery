package co.edu.uptcSoft.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class Components implements ActionListener {

    private JButton buttonYes;
    private JButton buttonNo;
    private JFrame confirmationFrame;
    private JFrame confirmationFrame2;
    private String message;
    private JPanel confirmationPanel;

    // Method for creating fonts
    public Font createFont(int style, int size) {
        try {
            return (style == 0) ? Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\Buenard-Bold.ttf")).deriveFont(Font.PLAIN, size) : Font.createFont(Font.TRUETYPE_FONT, new File("src\\Utilities\\Fonts\\Buenard-Regular.ttf")).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method for rounded button
    public JButton createRoundedButton(String text, String borderColor, String fillColor, int arcw, int arch) {
        return new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcw, arch);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            public void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode(borderColor));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcw, arch));
                setOpaque(false);
                g2.dispose();
            }

            @Override
            public void updateUI() {
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorderPainted(false);
                setOpaque(false);
                setFont(createFont(1, 20));
                setForeground(Color.WHITE);
                setBackground(Color.decode(fillColor));
                super.updateUI();
            }
        };
    }

    // Method for rounded text field
    public JTextField createRoundedTextField(int arcw, int arch) {
        return new JTextField() {
            @Override
            // Establecemos el tamaño del campo
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
            // Dibujamos el borde del campo
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

    // Method for confirming the message after clicking on the yes button
    public void messageConfirmation(String message) {
        confirmationFrame2 = new JFrame();
        confirmationFrame2.setTitle("Confirmación");
        confirmationFrame2.setSize(500, 130);
        confirmationFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmationFrame2.setLocationRelativeTo(null);

        JPanel confirmationPanel2 = new JPanel();
        confirmationPanel2.setBackground(Color.WHITE);
        confirmationPanel2.setBorder(new EmptyBorder(25, 0, 0, 0));
        confirmationPanel2.setPreferredSize(new Dimension(500, 130));
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(createFont(0, 20));
        messageLabel.setForeground(Color.BLACK);
        confirmationPanel2.add(messageLabel, BorderLayout.CENTER);

        confirmationFrame2.add(confirmationPanel2);
        confirmationFrame2.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonYes) {
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

    public JFrame getConfirmationFrame2() {
        return confirmationFrame2;
    }
}