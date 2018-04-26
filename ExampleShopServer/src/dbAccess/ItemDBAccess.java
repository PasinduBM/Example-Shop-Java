/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import db.DBConnection;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import model.Customer;
import model.Item;
import model.OrderDetail;

/**
 *
 * @author cmjd
 */
public class ItemDBAccess {
    
    private static ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
    //private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    public boolean addItem(Item item) throws RemoteException, ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement stm = conn.prepareStatement("Insert into Item values(?,?,?,?)");
        Object[] itemData = {item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()};
        for (int i = 0; i < itemData.length; i++) {
            stm.setObject(i + 1, itemData[i]);
        }
        return stm.executeUpdate() > 0;
    }

    public Item searchItem(String id) throws RemoteException, ClassNotFoundException, SQLException {
        
        try {
            rwLock.readLock().lock();
            String sql = "select * from item where code='" + id + "'";
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                return new Item(rst.getString("code"), rst.getString("description"), rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
            } else {
                return null;
            }
        } finally{
            rwLock.readLock().unlock();
        }
        
    }

    public boolean updateItem(Item item) throws RemoteException, ClassNotFoundException, SQLException {
        System.out.println("patan");
        try {
            System.out.println("athule");
            rwLock.writeLock().lock();
            System.out.println("lock");
            String sql = "update item set description='" + item.getDescription() + "',unitPrice='" + Double.toString(item.getUnitPrice()) + "',qtyOnHand='" + Integer.toString(item.getQtyOnHand()) + "' where code='"+item.getCode()+"'";
            System.out.println(sql);
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            return 0 < stm.executeUpdate(sql);
        } finally {
            rwLock.writeLock().unlock();
        }
        
    }

    public boolean deleteCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.writeLock().lock();
            String sql = "delete from item where code='" + id + "'";
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            return 0 < stm.executeUpdate(sql);
        } finally{
            rwLock.writeLock().unlock();
        }
        
        
    }

    public List<Customer> getAllCustomer() throws RemoteException, ClassNotFoundException, SQLException {
        return null;
    }
    
    public boolean updateItemQty(ArrayList<OrderDetail> itemList) throws ClassNotFoundException, SQLException {
        int res = 0;
        String query =  "update Item set qtyOnHand = qtyOnHand - ? where code = ?";
        Connection connection = DBConnection.getConnection();
        for (OrderDetail orderDetail : itemList) {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, orderDetail.getQty());
            prepareStatement.setObject(2, orderDetail.getItemCode());
            res += prepareStatement.executeUpdate();
        }
        return res == itemList.size();
    }
    
    
    
}
