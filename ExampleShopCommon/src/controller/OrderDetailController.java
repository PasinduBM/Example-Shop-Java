/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.OrderDetail;

/**
 *
 * @author HELLO USER™
 */
public interface OrderDetailController extends Remote{

    /**
     *
     * @param orderDetailist
     * @return
     * @throws java.rmi.RemoteException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean addOrderDetails(ArrayList<OrderDetail> orderDetailist) throws RemoteException, ClassNotFoundException, SQLException ;
}
