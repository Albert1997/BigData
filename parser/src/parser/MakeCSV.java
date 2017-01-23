/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;


//import static com.sun.org.apache.regexp.internal.RETest.test;
//import static com.sun.org.apache.regexp.internal.RETest.test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import static jdk.nashorn.internal.objects.NativeRegExp.test;


// Bouke werkt wel, is alleen nog niet samen gevoegd met de rest. Vanwege een omslag in het programma.
public class MakeCSV {
    
    static void MakeCSV(List<String> values, String file) throws IOException
    {
        String user = System.getProperty("user.name");
        String CSVurl = "/Users/" + user + "/Google Drive/Big Data Project/CSV/CSV Jorben/" + file + ".csv";
        FileWriter Writer = new FileWriter(CSVurl, true);
        String line = "";
        for (int i =0; i < values.size(); i++)
        {
            if (values.size() == i +1)
            {
            line += "" + values.get(i);
            }
            else
            {
            line += values.get(i) + ",";
            }
        }
        Writer.write(line+"\r\n");
        Writer.close();
    }
}
