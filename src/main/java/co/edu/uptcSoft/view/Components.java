package co.edu.uptcSoft.view;

import co.edu.uptcSoft.logic.Logic;
import co.edu.uptcSoft.model.Customer;
import co.edu.uptcSoft.model.Supply;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Components implements ActionListener {

    private JButton buttonYes;
    private JButton buttonNo;
    private JFrame confirmationFrame;
    private JFrame confirmationFrame2;
    private String message;
    private JPanel confirmationPanel;
    private Logic logic = logic = Logic.getInstance(); // Get the single instance of Logic
    private String row;
    private JPanel mainContentPanel;
    private Customer currentCustomer = new Customer();
    private Supply currentSupply = new Supply();
    private ArrayList<Supply> supplyList;
    private NewOrder order;

    public Components(JPanel mainContentPanel) {
        this.mainContentPanel = mainContentPanel;
        supplyList = new ArrayList<>();
        order = new NewOrder(mainContentPanel);
    }

    public Components() {
    }
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

    // Method for rounded text field
    public JTextField createRoundedTextField(int arcw, int arch) {
        return new JTextField() {
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

    //method overloaded to confirm the message after clicking on the yes button
    // window method to confirm
    public void windowConfirmation(String title, String button1, String button2, String message, String row) {

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
    }

    // Method for confirming the message after clicking on the yes button
    public void messageConfirmation(String message) {
        confirmationFrame2 = new JFrame();
        confirmationFrame2.setTitle("Confirmación");
        confirmationFrame2.setSize(500, 130);
        confirmationFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Starts a timer to close the window after 2 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                confirmationFrame2.dispose();
            }
        });
        timer.setRepeats(false); // To make the timer only execute once
        timer.start();
    }

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
    }
}
