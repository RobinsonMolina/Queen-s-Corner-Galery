package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class Components {

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
    public JButton createRoundedButton(String text) {
        return new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
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
                setBackground(Color.decode("#2F1940"));
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
}
