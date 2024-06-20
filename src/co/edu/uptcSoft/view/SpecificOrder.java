package co.edu.uptcSoft.view;

import javax.swing.*;
import java.awt.*;

public class SpecificOrder {
    private JFrame specificOrderWindow;
    private JPanel menu;
    private JPanel head;
    private JPanel data;

    public SpecificOrder() {
        specificOrderWindow = new JFrame("Orden Especifica");
        menu = new JPanel();
        head = new JPanel();
        data = new JPanel(new GridLayout());
    }

    public  void createWindow(){
        specificOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        specificOrderWindow.setSize(1366, 670);

        specificOrderWindow.setVisible(true);
    }
}
