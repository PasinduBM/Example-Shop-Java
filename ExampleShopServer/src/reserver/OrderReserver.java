/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserver;

import controller.ItemController;
import controller.OrderController;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HELLO USER™
 */
public class OrderReserver {
    
    Map<String,OrderController> map=new HashMap<>();
    
    public boolean reserveItem(String id,OrderController address){
        if(map.containsKey(id)){
            if(map.get(id)==address){
                return true;
            }else{
                return false;
            }
            
        }else{
            map.put(id, address);
            return true;
        }
    }
    
    public boolean releseItem(String id,OrderController address){
        if(map.get(id)==address){
            map.remove(id);
            return true;
        }else{
            return false;
        }
        
    }
    
}
