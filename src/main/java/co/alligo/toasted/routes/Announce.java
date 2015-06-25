/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted.routes;

import co.alligo.toasted.Servers;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;


/**
 *
 * @author Quinton
 */
public class Announce {
    public static String announce(Request req, Response res, Servers servers) throws IOException {
        JSONObject json = new JSONObject();
        JSONObject result = new JSONObject();
        String ip = req.ip();
        String port;
        
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
                
            } else {
                
            }
        }
        
        return "500 Internal Server Error";
    }
    
    public boolean checkGameServer(String ip, String port) throws MalformedURLException, IOException {
        boolean result = false;
        InputStream inputStream = new URL("http://" + ip + ":" + port + "/").openStream();
        
        return result;
    }
}
