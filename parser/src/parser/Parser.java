/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jorbe
 */
public class Parser {

    private static String temporary = "";
    private static Orderdata ordener = new Orderdata();
    private static int indexer = 0;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Locaties voor de files
        List<String> filenames = new ArrayList();
        filenames.addAll(Arrays.asList("locations", "actors", "actresses", "ratings", "genres","movies"));
        String user = System.getProperty("user.name");
        String userURL = "/Users/" + user + "/Google Drive/Big Data Project/Files/";
        
        
        // File naar lijst zetten, elke waarde is 1 regel
        
            // Loop door de file namen en objecten heen om deze lijsten te koppelen aan het bijbehorende object.
            for (String file : filenames)
            {
                process(userURL + file + ".list", file); 
            }  
    }
    static void process(String file, String fileName) throws FileNotFoundException, IOException
    {
        // Scann de file
        Scanner s = new Scanner(new File(file), "UTF-8");
        // Split every line
        s.useDelimiter("[;\r\n]+");
        // Save in a list

        while(s.hasNext() && indexer < 100) {
            String temporary = s.next();
            ordener.orderMain(temporary, fileName, indexer);
            indexer++;

        }
        indexer = 0;
    }
    
}
