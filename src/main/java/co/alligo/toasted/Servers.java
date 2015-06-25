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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;

public class Servers {
    private Map<String, String> servers = new HashMap<>();
    private Map<String, Long> serverExpire = new HashMap<>();
    private int expirationTime;
    
    public Servers(int expireTime) {
        addServer("test1", "9987");
        addServer("test2", "9987");
        expirationTime = expireTime;
    }
    
    public void addServer(String addr, String port) {
        Date date = new Date();
        servers.put(addr, port);
        serverExpire.put(addr, date.getTime());
    }
    
    public void delServer(String addr) {
        servers.remove(addr);
        serverExpire.remove(addr);
    }
    
    public JSONArray getServers() {
        Set<String> Serveraddrset = servers.keySet();
        System.out.println(Serveraddrset);
        String[] Serveraddrs = Serveraddrset.toArray(new String[Serveraddrset.size()]);
        String[] Serverports = new String[Serveraddrs.length];
        String[] returnString= new String[Serveraddrs.length];
        Date currentTime = new Date();
        Date tempDate;
        long currentMS = currentTime.getTime();
        long tempMS;
        
        for (int x = 0; x > Serveraddrs.length; x++) {
            Serverports[x] = servers.get(Serveraddrs[x]);
        }
        
        System.out.println("Serv " + Arrays.toString(servers.keySet().toArray()));
        System.out.println("Servers " + Arrays.toString(Serveraddrs));
        System.out.println("Ports:  " + Arrays.toString(Serverports)); 
        
        for (int x = 0; x > Serveraddrs.length; x++) {
            returnString[x] = Serveraddrs[x] + ":" + Serverports[x];
        }
        
        System.out.println("Retrun: " + Arrays.toString(returnString));
        
        List<String> addrPortList = new ArrayList<>(Arrays.asList(returnString));
        
        for (int x = 0; x > returnString.length; x++) {
            if ((serverExpire.get(Serveraddrs[x]) - currentMS <= 150000)) {
                addrPortList.removeAll(Arrays.asList(returnString[x]));
                servers.remove(Serveraddrs[x]);
                serverExpire.remove(Serveraddrs[x]);
                System.out.println("Removed: " + Serveraddrs[x]);
            }
        }
        
        returnString = addrPortList.toArray(new String[0]);
        
        System.out.println("Return2: " + Arrays.toString(returnString));
        
        JSONArray jsonarray = new JSONArray();
        
        
        for (int x = 0; x < returnString.length; x++)
            jsonarray.add(returnString[x]);
        
        System.out.println("JSON: " + jsonarray.toJSONString());
        
        return jsonarray;
    }
}