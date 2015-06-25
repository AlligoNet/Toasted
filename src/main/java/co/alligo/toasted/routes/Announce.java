/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted.routes;

import co.alligo.toasted.Servers;
import spark.Request;
import spark.Response;

/**
 *
 * @author Quinton
 */
public class Announce {
    public static String announce(Request req, Response res, Servers servers) {
        
        return "{\n" +
"    \"result\": {\n" +
"      \"code\": 0,\n" +
"      \"msg\": \"OK\"\n" +
"    }\n" +
"  }";
    }
}
