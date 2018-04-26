/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserver;

import controller.ItemController;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HELLO USER™
 */
public class ItemReserver {
    
    Map<String,ItemController> map=new HashMap<>();
    
    public boolean reserveItem(String id,ItemController address){
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
    
    public boolean releseItem(String id,ItemController address){
        if(map.get(id)==address){
            map.remove(id);
            return true;
        }else{
            return false;
        }
        
    }
    
}
