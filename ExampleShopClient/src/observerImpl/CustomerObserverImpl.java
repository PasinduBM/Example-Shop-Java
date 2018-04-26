/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observerImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import observer.CustomerObserver;
import view.ViewCustomerList;

/**
 *
 * @author student
 */
public class CustomerObserverImpl extends UnicastRemoteObject implements CustomerObserver{
    private ViewCustomerList viewListForm;
    public  CustomerObserverImpl(ViewCustomerList viewList)throws RemoteException{
        this.viewListForm=viewList;
    }
    @Override
    public void update(String id) throws RemoteException{
        viewListForm.setMessage(id);
    }
    
}
