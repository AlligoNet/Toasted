/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted;

import static spark.Spark.*;
import co.alligo.toasted.routes.*;

/**
 *
 * @author Quinton
 */
public class Main {
    public static void main(String[] args){
        
        //Configuration config = new Configuration();
        
        Servers servers = new Servers(150000);
        
        port(9090);
        //get("/stats", (req, res) -> Stats.stats(req, res, servers));
        get("/list", (req, res) -> ServerList.serverList(req, res, servers));
        get("/announce", (req, res) -> Announce.announce(req, res, servers));
    }
}
