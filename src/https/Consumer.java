/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package https;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author kpalmall
 */
public class Consumer {
    public static Optional<Map<String, String>> reniec_findPerson(String dni) throws MalformedURLException, IOException{
        URL URL = new URL(enums.Constans.apiReniec);
        URLConnection re = URL.openConnection();
        re.connect(); 
        
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream)re.getContent()));
        JsonObject jo = root.getAsJsonObject();
        
        Map<String, String> map = new HashMap<>();
        
        String lastname = jo.get("apellido_paterno").getAsString()
                .concat(jo.get("apellido_materno").getAsString());
        
        map.put("nombre", jo.get("nombres").getAsString());
        map.put("apellidos", lastname); 
        
        return Optional.of(map);
    }
}
