/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author student
 */
public class RemoteFactoryImpl extends UnicastRemoteObject implements RemoteFactory {

    public RemoteFactoryImpl() throws RemoteException {
    }

    @Override
    public CustomerController getCustomerController() throws RemoteException {
        return new CustomerControllerImpl();
    }

    @Override
    public ItemController getItemController() throws RemoteException {
        return new ItemControllerImpl();
    }

    @Override
    public OrderController getOrderController() throws RemoteException {
        return new OrderControllerImpl();
    }

}
