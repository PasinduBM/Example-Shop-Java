/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import dbAccess.ItemDBAccess;
import dbAccess.OrderDetailDBAccess;
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
public class OrderDetailControllerImpl extends UnicastRemoteObject implements OrderController{
    
    private final OrderDetailDBAccess dbAccess=new OrderDetailDBAccess();
    private static final ItemReserver reserver=new ItemReserver();
    ItemControllerImpl add;

    public OrderDetailControllerImpl() throws RemoteException{
        try {
            add=new ItemControllerImpl();
        } catch (RemoteException ex) {
            Logger.getLogger(OrderDetailControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @Override
    public boolean addOrder(Order order, ArrayList<OrderDetail> orderDetailList) throws RemoteException, ClassNotFoundException, SQLException {
        System.out.println("8888888");
        for(OrderDetail b:orderDetailList){
            reserver.reserveItem(b.getItemCode(), add);
        }
        System.out.println("00000000");
        boolean addOrderDetails = dbAccess.addOrderDetails(orderDetailList);
        System.out.println("777777");
        for(OrderDetail b:orderDetailList){
            reserver.releseItem(b.getItemCode(), add);
        }
        System.out.println("3333333");
        return addOrderDetails;
    }
    
    
}
