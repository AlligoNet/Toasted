/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted;

/**
 *
 * @author Quinton Marchi
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;

public class Servers {
    private Map<String, Long> servers = new HashMap<>();
    private int expirationTime;
    
    public Servers(int expireTime) {
        expirationTime = expireTime;
    }
    
    public void addServer(String addr, String port) {
        Date date = new Date();
        servers.put(addr + ":" + port, date.getTime());
    } 
    
    public void delServer(String addr, String port) {
        servers.remove(addr + ":" + port);
    }
    
    public void refreshServer(String addr, String port) {
        String uri = addr + ":" + port;
        Date date = new Date();
        servers.put(uri, date.getTime());
    }
    
    public JSONArray getServers() {
        String[] serveruris = servers.keySet().toArray(new String[servers.keySet().size()]);
        JSONArray jsonarray = new JSONArray();
        
        for (int x = 0; x < serveruris.length; x++) {
            if ((servers.get(serveruris[x]) >= expirationTime)) {
                servers.remove(serveruris[x]);
            }
        }
        
        for (int x = 0; x < serveruris.length; x++)
            jsonarray.add(serveruris[x]);
        
        return jsonarray;
    }
}