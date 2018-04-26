/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.CustomerControllerImpl;
import controller.ItemControllerImpl;
import controller.RemoteFactoryImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class StartMain {
    public static void main(String[] args) {
        try {
            Registry thogaKadeRegistry=LocateRegistry.createRegistry(6060);
            thogaKadeRegistry.rebind("ExampleShopServer", new RemoteFactoryImpl());
            System.out.println("Server is started..");
        } catch (RemoteException ex) {
            Logger.getLogger(StartMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
