/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.OrderDetail;

/**
 *
 * @author linux
 */
public class OrderDetailDBAccess {
    
    public boolean addOrderDetails(ArrayList<OrderDetail> orderDetailist) throws ClassNotFoundException, SQLException {
        int res = 0;
        String query = "insert into OrderDetail values(?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        for (OrderDetail orderDetail : orderDetailist) {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, orderDetail.getOrderId());
            prepareStatement.setObject(2, orderDetail.getItemCode());
            prepareStatement.setObject(3, orderDetail.getQty());
            prepareStatement.setObject(4, orderDetail.getUnitPrice());
            res += prepareStatement.executeUpdate();
        }
        return res == orderDetailist.size();
    }
}
