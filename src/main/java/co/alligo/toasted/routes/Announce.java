/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted.routes;

import co.alligo.toasted.Servers;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;


/**
 *
 * @author Quinton
 */
public class Announce {
    public static String announce(Request req, Response res, Servers servers) {
        JSONObject json = new JSONObject();
        JSONObject result = new JSONObject();
        String ip = req.ip();
        String port;
        
        res.header("Content-Type", "application/json");
        
        if (!(req.headers("x-forwarded-for") == null)) {
            ip = req.headers("x-forwarded-for");
        }
        
        if (req.attribute("port") == null) {
            result.put("code", 1);
            result.put("msg", "Invalid parameters, valid parameters are 'port' (int) and 'shutdown' (bool)");
            json.put("result", result);
            return json.toJSONString();
        } else {
            port = req.attribute("port").toString();
        }
        
        if ("true".equals(req.attribute("shutdown").toString())) {
            servers.delServer(ip, port);
        } else {
            if (checkGameServer(ip, port)) {
                servers.addServer(ip, port);
                result.put("code", 0);
                result.put("msg", "Added server to list.");
                System.out.println("Added server to list"+ ip + ":" + port);
                json.put("result", result);
                return json.toJSONString();
                
            } else {
                result.put("code", 0);
                result.put("msg", "Failed to contact game server, are the ports open and forwarded correctly?");
                json.put("result", result);
                return json.toJSONString();
            }
        }
        
        return "500 Internal Server Error";
    }
    
    public static boolean checkGameServer(String ip, String port) {
        boolean result;
        URL             url;
        URLConnection   urlConnection;
        DataInputStream dis;
        
        
        
        try {
            url = new URL("http://" + ip + ":" + port +"/");
            
            urlConnection = url.openConnection();
            urlConnection.connect();
            
            result = true;
        } catch (IOException ioe) {
            result = false;
        }
        return result;
    }
}
