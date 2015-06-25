/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.alligo.toasted;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quinton
 */
public class Configuration {
    
    String  redisServerAddr;
    int     redisServerPort;
    String  mainServerAddr;
    int     mainServerPort;
    int     serverTimeout;
    boolean isStats;
    
    public void Configuration(){
        try {
            loadPropValues();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadPropValues() throws IOException {
        String result = "";
        Properties prop = new Properties();
        String propFileName = "toasted.config";
        
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("Configuration file not found! toasted.config missing!");
        }
        
        mainServerPort  = Integer.parseInt(prop.getProperty("ServerPort"));
        mainServerAddr  = prop.getProperty("ServerAddress");
        isStats = Boolean.parseBoolean(prop.getProperty("isStatsEnabled"));
        serverTimeout = Integer.parseInt(prop.getProperty("ServerTimeout"));
        
    }
}
