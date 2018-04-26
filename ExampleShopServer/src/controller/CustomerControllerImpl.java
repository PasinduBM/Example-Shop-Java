/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbAccess.CustomerDBAccess;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.SQLException;

import java.util.ArrayList;
import model.Customer;
import observer.CustomerObserver;
import observerble.CustomerObserverble;
import reserver.CustomerReserver;

/**
 *
 * @author student
 */
public class CustomerControllerImpl extends UnicastRemoteObject implements CustomerController {

    private final CustomerDBAccess dBAccess = new CustomerDBAccess();
    private static final CustomerReserver customerReserver = new CustomerReserver();
    private static final CustomerObserverble customerObserverble = new CustomerObserverble();

    public CustomerControllerImpl() throws RemoteException {

    }

    @Override
    public boolean addCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException {
        boolean isAdded = dBAccess.addCustomer(customer);
        if (isAdded) {
            customerObserverble.notifyCustomerObservers(customer.getId());
        }
        return isAdded;
    }

    @Override
    public boolean deleteCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException {
        if (customerReserver.releaseCustomer(id, this)) {
            boolean isDeleted=dBAccess.deleteCustomer(id);
            if(isDeleted){
                customerObserverble.notifyCustomerObservers(id);
            }
            return isDeleted;
        } else {
            return false;
        }
    }

    @Override
    public Customer searchCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException {
        System.out.println("aaaa");
        return dBAccess.searchCustomer(id);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException {
        if (customerReserver.reserveCustomer(customer.getId(), this)) {
            boolean isUpdate=dBAccess.updateCustomer(customer);
            if(isUpdate){
                customerObserverble.notifyCustomerObservers(customer.getId());
            }
            return isUpdate;
        } else {
            return false;
        }

    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws RemoteException, ClassNotFoundException, SQLException {
        return dBAccess.getAllCustomer();
    }

    @Override
    public boolean reserveCustomer(String id) throws RemoteException {
        return customerReserver.reserveCustomer(id, this);
    }

    @Override
    public boolean releaseCustomer(String id) throws RemoteException {
        return customerReserver.releaseCustomer(id, this);
    }

    @Override
    public void addCustomerObserver(CustomerObserver customerObserver) throws RemoteException {
        customerObserverble.addCustomerObserver(customerObserver);
    }

    @Override
    public void removeCustomerObserver(CustomerObserver customerObserver) throws RemoteException {
        customerObserverble.removeCustomerObserver(customerObserver);
    }
}
