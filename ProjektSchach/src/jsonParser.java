
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robin
 */
public class jsonParser {
    File file;
    Scanner reader;
    ArrayList<String> data;
    
    public jsonParser(){
        try {
            data = new ArrayList<>();
            file = new File("settings.json");
            reader = new Scanner(file);
            
            while(reader.hasNextLine()){
                data.add(reader.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(jsonParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String parseDefaultIp(){
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).contains("defaul-IP")){
                res = data.get(i);
                res = res.substring(res.indexOf(":")+2, res.indexOf(",")-1);
            }
        }
        return res;
    }
    public String parseIp(){
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).contains("IP")){
                res = data.get(i);
                res = res.substring(res.indexOf(":")+2, res.indexOf(",")-1);
            }
        }
        return res;
    }
    public String parseFarbe(){
        String res = "";
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).contains("farbe")){
                res = data.get(i);
                res = res.substring(res.indexOf(":")+2, res.indexOf(",")-1);
            }
        }
        return res;
    }
}
