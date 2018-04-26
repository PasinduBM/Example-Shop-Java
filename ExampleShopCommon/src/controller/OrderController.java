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
import model.Order;
import model.OrderDetail;

/**
 *
 * @author HELLO USER™
 */
public interface OrderController extends Remote{

    /**
     *
     * @param order
     * @param orderDetailList
     * @return
     * @throws java.rmi.RemoteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean addOrder(Order order, ArrayList<OrderDetail> orderDetailList) throws RemoteException, ClassNotFoundException, SQLException;
}
