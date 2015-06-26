/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted.routes;

import spark.Request;
import spark.Response;
import co.alligo.toasted.Servers;

import org.json.simple.JSONObject;


/**
 *
 * @author Quinton
 */
public class ServerList {
    public static String serverList(Request req, Response res, Servers servers) {
        
        JSONObject response = new JSONObject();
        JSONObject result = new JSONObject();
        
        res.header("Content-Type", "application/json");
        
        response.put("listVersion", 1);
        
        result.put("code", 0);
        result.put("msg", "OK");
        result.put("servers", servers.getServers());
        
        response.put("result", result);
        
        return (String)response.toJSONString();
    }
}