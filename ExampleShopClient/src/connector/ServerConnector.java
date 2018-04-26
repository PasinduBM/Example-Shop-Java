/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import controller.CustomerController;
import controller.ItemController;
import controller.OrderController;
import controller.RemoteFactory;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author student
 */
public class ServerConnector {

    private final CustomerController customerController;
    private final OrderController orderController;
    private final ItemController itemController;
    private final RemoteFactory remoteFactory;
    private static ServerConnector serverConnector;

    private ServerConnector() throws NotBoundException, MalformedURLException, RemoteException {
        remoteFactory=(RemoteFactory) Naming.lookup("rmi://localhost:6060/ExampleShopServer");
        customerController=remoteFactory.getCustomerController();
        itemController=remoteFactory.getItemController();
        orderController=remoteFactory.getOrderController();
    }

    public static ServerConnector getServerConnector() throws NotBoundException, MalformedURLException, RemoteException {
        if (serverConnector == null) {
            serverConnector = new ServerConnector();
        }
        return serverConnector;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public ItemController getItemController() {
        return itemController;
    }
    
    public OrderController getOrderController() {
        return orderController;
    }
}
