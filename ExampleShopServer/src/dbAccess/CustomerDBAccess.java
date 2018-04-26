/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.util.concurrent.locks.ReentrantReadWriteLock;
import model.Customer;

/**
 *
 * @author student
 */
public class CustomerDBAccess {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public boolean addCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.writeLock().lock();
            Connection conn = DBConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement("Insert into Customer Values(?,?,?,?)");
            stm.setObject(1, customer.getId());
            stm.setObject(2, customer.getName());
            stm.setObject(3, customer.getAddress());
            stm.setObject(4, customer.getSalary());
            return stm.executeUpdate() > 0;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public boolean deleteCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.writeLock().lock();
            String sql = "Delete From Customer where id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, id);
            return stm.executeUpdate() > 0;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Customer searchCustomer(String id) throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.readLock().lock();
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            String sql = "Select * From Customer where id='" + id + "'";
            System.out.println(sql);
            ResultSet rstResultSet = stm.executeQuery(sql);
            Customer customer = null;
            if (rstResultSet.next()) {
                customer = new Customer(rstResultSet.getString("id"), rstResultSet.getString("name"), rstResultSet.getString("address"), rstResultSet.getDouble("salary"));
            }
            return customer;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public boolean updateCustomer(Customer customer) throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.writeLock().lock();
            String sql = "Update Customer set name=?,address=?,salary=? where id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(4, customer.getId());
            stm.setObject(1, customer.getName());
            stm.setObject(2, customer.getAddress());
            stm.setObject(3, customer.getSalary());
            return stm.executeUpdate() > 0;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public ArrayList<Customer> getAllCustomer() throws RemoteException, ClassNotFoundException, SQLException {
        try {
            rwLock.readLock().lock();
            Connection conn = DBConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery("Select * From Customer");
            ArrayList<Customer> customerList = new ArrayList<>();
            while (rst.next()) {
                Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"));
                customerList.add(customer);
            }
            return customerList;
        } finally {
            rwLock.readLock().unlock();
        }
    }

}
