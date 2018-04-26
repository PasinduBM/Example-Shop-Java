/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import observer.CustomerObserver;

/**
 *
 * @author student
 */
public interface CustomerController extends Remote {

    public boolean addCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean deleteCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException;

    public Customer searchCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException;

    public boolean updateCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException;

    public ArrayList<Customer> getAllCustomer() throws RemoteException, ClassNotFoundException, SQLException;
    
    public boolean reserveCustomer(String id)throws RemoteException;
    
    public boolean releaseCustomer(String id)throws RemoteException;
    
    public void addCustomerObserver(CustomerObserver customerObserver)throws RemoteException;
    
    public void removeCustomerObserver(CustomerObserver customerObserver)throws RemoteException;
    
    
}
