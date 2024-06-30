package co.edu.uptcSoft.view;

public class Main {

    public static void main(String[] args) {

        Login login = new Login();
        login.createWindow();

        Board board = new Board();

        SpecificOrder specificOrder = new SpecificOrder();
        specificOrder.createWindow();

        NewOrder newOrder = new NewOrder();
        newOrder.createWindow();

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.createWindow();

        CustomerList customerList = new CustomerList();
        customerList.contentPanel();

        NewSupplie newSupplies = new NewSupplie();
        //newSupplies.createWindow();

        OrderList orderList = new OrderList();


        NewCustomer newCustomer = new NewCustomer();
        newCustomer.createWindow();

        UpdateCustomer updateCustomer = new UpdateCustomer();
        updateCustomer.createWindow();

        ViewCustomer viewCustomer = new ViewCustomer();
        viewCustomer.createWindow();
    }
}