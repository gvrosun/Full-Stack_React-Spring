package com.kaiburr.api.api;

import java.util.ArrayList;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServerController {

	MongoConnection servers = new MongoConnection();
    @GetMapping("/getAllServers")
    public ArrayList<String> getAllServers(HttpServletResponse response){
      
    	response.setHeader("Content-Type", "application/json");
      
    	response.setHeader("Access-Control-Allow-Origin", "*");
      
    	ArrayList<String> serversList = servers.getAllServers();
    	System.out.println(serversList);
    	return serversList;
    }
    
    @GetMapping("/addNewServer")
    public Server addNewServer(HttpServletResponse response, @RequestParam(value = "name", defaultValue = "Unknown") String name, @RequestParam(value = "language", defaultValue = "Unknown") String language, @RequestParam(value = "framework", defaultValue = "Unknown") String framework) {
    	response.setHeader("Content-Type", "application/json");
        
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	long result_id = servers.insertNewServer(name, language, framework);
    	return new Server(result_id, name, language, framework);
    }
    
    @GetMapping("/deleteOneServer")
    public boolean deleteOneServer(HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") String id) {
    	response.setHeader("Content-Type", "application/json");
        
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	return servers.deleteOneServer(Integer.parseInt(id));
    }
    
    @GetMapping("/deleteAllServer")
    public boolean deleteAllServer(HttpServletResponse response) {
    	response.setHeader("Content-Type", "application/json");
        
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	return servers.deleteAllServer();
    }
    
    @GetMapping("/updateServer")
    public boolean updateServer(HttpServletResponse response, @RequestParam(value = "id", defaultValue = "0") String id, @RequestParam(value = "name", defaultValue = "Unknown") String name, @RequestParam(value = "language", defaultValue = "Unknown") String language, @RequestParam(value = "framework", defaultValue = "Unknown") String framework) {
    	response.setHeader("Content-Type", "application/json");
        
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	return servers.updateServer(Integer.parseInt(id), name, language, framework);
    }
}