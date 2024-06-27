package co.edu.uptcSoft.view;

public class Main {

    public static void main(String[] args) {

        Login login = new Login();
        //login.createWindow();

        //Board board = new Board();

        SpecificOrder specificOrder = new SpecificOrder();
        //specificOrder.createWindow();

        NewOrder newOrder = new NewOrder();
        //newOrder.createWindow();

        UpdateOrder updateOrder = new UpdateOrder();
        //updateOrder.createWindow();

        NewCustomer newCustomer = new NewCustomer();
        newCustomer.createWindow();
    }
}