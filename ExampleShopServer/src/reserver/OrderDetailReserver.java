/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserver;

import controller.CustomerController;
import controller.OrderDetailController;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author student
 */
public class OrderDetailReserver {

    Map<String, OrderDetailController> reserveData = new HashMap<>();

    public boolean reserveCustomer(String id, OrderDetailController customerController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == customerController) {
                return true;
            } else {
                return false;
            }
        } else {
            reserveData.put(id, customerController);
            return true;
        }
    }

    public boolean releaseCustomer(String id, CustomerController customerController) {
        if (reserveData.get(id) == customerController) {
            reserveData.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
