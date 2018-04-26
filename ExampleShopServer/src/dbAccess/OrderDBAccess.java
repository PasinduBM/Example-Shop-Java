/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import controller.*;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author linux
 */
public class OrderDBAccess {

    public boolean addOrder(Order order, ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
        String query = "insert into Orders values(?,?,?)";
        Connection connection = DBConnection.getConnection();
        //connection.setAutoCommit(false);
        try {
            
            System.out.println("1234");
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, order.getId());
            prepareStatement.setObject(2, order.getDate());
            prepareStatement.setObject(3, order.getCustomerId());
            int res = prepareStatement.executeUpdate();
            OrderDetailDBAccess orderDetailDBAccess = new OrderDetailDBAccess();
            ItemDBAccess orderDBAccess = new ItemDBAccess();
            System.out.println("lmaas");
            if (res > 0) {
                System.out.println("321");
                boolean addOrderDetails = orderDetailDBAccess.addOrderDetails(orderDetailList);
                System.out.println("6321");
                if (addOrderDetails) {
                    System.out.println("lkjhg");
                    boolean updateItemQty = orderDBAccess.updateItemQty(orderDetailList);
                    if (updateItemQty) {
                        //connection.commit();
                        return true;
                    }
                    //connection.rollback();
                }
                //connection.rollback();
            }
            
            return false;
        } finally {
            //connection.setAutoCommit(true);
        }
    }
}
