/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import dbAccess.ItemDBAccess;
import dbAccess.OrderDBAccess;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;
import reserver.ItemReserver;

/**
 *
 * @author linux
 */
public class OrderControllerImpl extends UnicastRemoteObject implements OrderController{
    
    private final OrderDBAccess dBAccess=new OrderDBAccess();
    private static final ItemReserver reserver=new ItemReserver();
    
    ItemControllerImpl add;

    public OrderControllerImpl() throws RemoteException{
        try {
            add=new ItemControllerImpl();
        } catch (RemoteException ex) {
            Logger.getLogger(OrderControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public boolean addOrder(Order order, ArrayList<OrderDetail> orderDetailList) throws RemoteException, ClassNotFoundException, SQLException {
        System.out.println("6666666");
        for(OrderDetail a:orderDetailList){
            reserver.reserveItem(a.getItemCode(), add);
        }
        System.out.println("2222222"+dBAccess);
        boolean addOrder = dBAccess.addOrder(order, orderDetailList);
        for(OrderDetail a:orderDetailList){
            reserver.releseItem(a.getItemCode(), add);
        }
        System.out.println("mmmmmm");
        return addOrder;
    }

    
    
}
