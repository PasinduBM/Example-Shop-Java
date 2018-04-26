/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observerble;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.CustomerObserver;

/**
 *
 * @author student
 */
public class CustomerObserverble {
    //private String id;
    private ArrayList<CustomerObserver> observerList=new ArrayList<>();

    public void addCustomerObserver(CustomerObserver customerObserver){
        observerList.add(customerObserver);
    }
    public void removeCustomerObserver(CustomerObserver customerObserver){
        observerList.remove(customerObserver);
    }    
    public void notifyCustomerObservers(String id) throws RemoteException{
        for (CustomerObserver observer : observerList) {
            new Thread(){
                public void run(){
                    try {
                        observer.update(id);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CustomerObserverble.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
    }
}
