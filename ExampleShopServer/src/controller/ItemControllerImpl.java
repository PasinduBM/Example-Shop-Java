/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import dbAccess.ItemDBAccess;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Item;
import model.OrderDetail;
import reserver.ItemReserver;

/**
 *
 * @author linux
 */
public class ItemControllerImpl extends UnicastRemoteObject implements ItemController{
    private final ItemDBAccess dBAccess=new ItemDBAccess();
    private static final ItemReserver reserver=new ItemReserver();
            
    public ItemControllerImpl()throws RemoteException{
        
    }

    @Override
    public boolean addItem(Item item) throws RemoteException, ClassNotFoundException, SQLException {
        return dBAccess.addItem(item);
    }

    @Override
    public boolean updateItem(Item item) throws RemoteException, ClassNotFoundException, SQLException {
        if (reserver.reserveItem(item.getCode(), this)) {
            dBAccess.updateItem(item);
            return true;
        } else {
            return false;
        }
    }

    
    
    @Override
    public Item searchItem(String code) throws RemoteException, ClassNotFoundException, SQLException {
        return dBAccess.searchItem(code);
    }

    @Override
    public boolean deleteItem(String code) throws RemoteException, ClassNotFoundException, SQLException {
        if(reserver.reserveItem(code, this)){
            return dBAccess.deleteCustomer(code);
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Item> getAllItem() throws RemoteException, ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reserveItem(String id) throws RemoteException {
        return reserver.reserveItem(id, this);
    }

    @Override
    public boolean releaseItem(String id) throws RemoteException {
        return reserver.releseItem(id, this);
    }

    

}
