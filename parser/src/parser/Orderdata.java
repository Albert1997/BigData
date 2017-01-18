/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Jorben
public class Orderdata {
            
            // Alle data word hierin opgeslagen
            List <String> values = new ArrayList();
    
            // Opslaan gegevens  
            String newWord;
            String nameSave = "";
            
            // Algemeen
            int whitespace = 0;
            boolean stop;
            boolean repeat;
            int charIndex = 0;
            int yearNumber = 0;
            String wordSave = "";
            
            // Gemaakt voor ratings
            int listIndex = 1;
            
            // Bernard zijn variables
            int step = 1;
            int index = 0;
            int space = 0;
            Boolean stepfilled = false;
            Boolean release = true;
            Boolean good = true;
            
            List <String> title = new ArrayList();
            List <String> year = new ArrayList();
            List <String> genre = new ArrayList();
    
    void orderMain(String myString, String file, int indexer) throws IOException
    {

                // Char number reset
                newWord = "";
                stop = false;
                charIndex = 0;
                yearNumber = 0;
                repeat = false;
                good = true;
                step = 1;
                space = 0;
                Boolean release = true;
                stepfilled = false;
                
                
                    // Kijk daarna per zin char voor char wat er staat. 
                    for(char ch: myString.toCharArray())
                    {
                        /*if (file == "actors" || file == "actresses")
                        {
                        orderActors(ch);
                        charIndex ++;
                        }
                        
                        if (file == "movies")
                        {
                            orderMovies(ch);
                            charIndex ++;
                        }
                        if (file == "ratings")
                        {
                            orderRatings(ch);
                            charIndex++;
                        }
                        if (file == "genres")
                        {
                            orderGenres(ch);
                            charIndex++;
                        }*/
                        if (file == "locations")
                        {
                            orderLocations(ch);
                            charIndex++;
                        }
                    }
            //Voeg de laatste nog even toe
            if (file == "genres" || file == "locations")
            {
                    values.add(newWord);
                    newWord = "";
            }
        if (values.size() > 1 && values.size() < 5)
        {   
            
       
            
            //MakeCSV.MakeCSV(values, file);
            
            
            System.out.print("index: ");
            System.out.print(indexer);
            System.out.print(". File: ");
            System.out.print(file);
            for (int i =0; i< values.size(); i++)
            {
            System.out.print("[");
            System.out.print(values.get(i));
            System.out.print("] ");
            }
            System.out.println(" ");
            DB.Database(values, file, indexer);
            
        }
        // Reset values
        values.clear();
               
    }
            
    // Bernard zou deze doen maar is ziek.
    void orderLocations(char ch)
    {
        if ( release ) {
                    
                    if ( step == 1 )                        
                        if ( ch == '(' ) 
                        {
                           if (!(wordSave).equals(newWord)){
                            step = 2;
                            values.add(newWord);
                            //System.out.println(wordSave + "@");
                           // System.out.println(newWord + "@");
                            wordSave = newWord;
                            newWord = "";
                            }
                           
                           else {
                               newWord ="";
                               release = false;
                           }
                        } else if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '(') || (ch == ')') || (ch == '!') || (ch == '?') || (ch == '"') || (ch >= '0' && ch <= '9') || (ch >= ' ') ) {
                            newWord += ch;
                        }
                    
                    
                    if ( step == 2 ) {
                        if ( ch == ')' ) {
                            step = 3;
                            values.add(newWord);
                            newWord = "";
                        } else if ( (ch >= '0' && ch <= '9') ) {
                            newWord += ch;
                        }
                    }
                    
                    if ( step == 3 ) {
                        if ( ch == '{' ) {
                            
                            release = false;
                        } else {
                            step = 4;
                        }
                       
                    }
                    
                    if ( step == 4 ) {
                        if ( ch == '{' ) {
                            release = false;
                        }
                        
                        if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '!') || (ch == '?') || (ch == '"') || (ch >= '0' && ch <= '9') || (ch >= ' ') && ch != ',' && ch != ')' && ch != '{' ) {
                            newWord += ch;
                        }
                    }
                    
        } else {
                    if ( ch == '}' ) {
                        release = true;
                        step = 4;
                    }
                }
    }
            
            
    void orderActors(char ch)
    {
          if (stop == false){
                        if (ch == '\t' && charIndex < 1)
                        {
                            repeat = true;
                            values.add(nameSave);
                        }

                        if (values.size() < 2)
                        {
                                if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == ',')
                                {
                                    if (ch == ',' && charIndex > 5)
                                    {
                                    values.add(newWord);
                                    whitespace = 0;
                                    nameSave = newWord;
                                    newWord = "";
                                    }
                                    // Na meer dan 2 spaties staat de film
                                    else if (ch != ',')
                                        {
                                            if (whitespace > 1 && repeat == false)
                                            {
                                            values.add(newWord);
                                            whitespace = 0;
                                            nameSave = newWord;
                                            newWord = "";
                                            newWord += ch;
                                            }
                                            else if (whitespace == 1 && newWord.length() > 1)
                                            {
                                            newWord += ' ';
                                            newWord += ch;
                                            whitespace = 0;
                                            }
                                            else
                                            {
                                            newWord += ch;
                                            whitespace = 0;
                                            }
                                        }
                                }
                                else if (ch == ' ' || ch == '\t')
                                {
                                    whitespace ++;
                                }
                                // Bij ( geeft die elke keer het jaartal aan, dit hebben we niet nodig alleen de titel.
                                if (ch == '(')
                                {
                                   values.add(newWord);

                                   newWord = "";

                                }
                        }
                        // When it is a 
                        else if (values.size() == 2)
                        {
                            
                            if ((ch >= '0' && ch <= '9'))
                            {
                                newWord += ch;
                            }
                            if (ch == ')')
                            {
                                values.add(newWord);
                                stop = true;

                            }
                        }
                    }
    }
    
    void orderRatings(char ch)
    {
        
        if ( release ) {
            
            if ( step == 1 ) {
              
                if ( ((ch >= 0 && ch <= 9) || ch == '.' || ch == ' ') && space < 17 ) {
                    space++;
                }
                
                if ( space >= 17 ) {
                    step = 2;
                }
                
            }
            
            if ( step == 2 ) {
                if ( ch != ' ' ) {
                    System.out.print(ch);
                    stepfilled = true;
                }
                if ( ch == ' ' && stepfilled ) {
                    step = 3;
                }
            }
            
            if ( step == 3 ) {
                if ( (ch >= 0 && ch <= 9) ) {
                    //  System.out.print(ch);
                }
            }
            
        }
        
        
        
        
        
        
//       if(stop == false){
//           // Kijkt of char spatie is
//           if (ch == ' ')
//           {
//           whitespace++;
//           }    
//                // Sla de eerste 17 over 
//                if (charIndex > 17)
//                {
//                
//                    if (listIndex == 3 && ch == ' ')
//                    {
//                        newWord += ch;
//                        whitespace = 0;
//                    }
//                    if (listIndex != 3 && (ch >= '0' && ch <= '9') || ch == '.')
//                    {
//                        newWord += ch;
//                        whitespace = 0; 
//                    }
//                    if ( ch != ',' && ch != '\t' && ch != '(' )
//                    {
//                        if (listIndex == 3)
//                        {
//                        newWord += ch;
//                        whitespace = 0;
//                        }
//                    }
//                // Wanneer newWord een waarde heeft en de volgende char een spatie is of een volgende regel is, voeg dit woord toe aan de lijst waar de lijstIndex op dat moment is
//
//                    if (listIndex == 1 && newWord.length() > 0 && ch == ' ')
//                    {
//                        
//                         values.add(newWord);
//                         
//                         listIndex ++;
//                    
//                         newWord = "";
//                    }
//                    if (listIndex == 2 && newWord.length() > 0 && ch == ' ')
//                    {
//                        values.add(newWord);
//                       
//                        listIndex ++;
//                    
//                        newWord = "";
//                    }
//                    
//                    if (listIndex == 3 && ch == '(')
//                    {
//                        values.add(newWord);
//                        //System.out.print("2@" + newWord);
//                        listIndex++; 
//                        newWord = "";
//                        
//                    }
//                    
//                    if (listIndex == 4 && ch == ')' || ch == '/')
//                    {
//                        values.add(newWord);
//                        newWord = "";
//                        stop = true;
//                        listIndex = 1;
//                    }
//
//                    
//                } // Check if newword een value heeft en het eerstvolgende char een whitespace is. Dan list.add
//                
//             } 
                              
    }
    // Bernard zou deze doen maar is ziek, 
    void orderGenres(char ch)
    {
        if ( release ) {
                    
                    if ( step == 1 ) {
                        if ( charIndex > 0 && ch == '(' ) {
                            step = 2; 
                            values.add(newWord);
                            newWord = "";
                        } else if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '(') || (ch == ')') || (ch == '!') || (ch == '?') || (ch == '"') || (ch >= '0' && ch <= '9') ) {
                            newWord += ch;
                        }
                    }

                    if ( step == 2 ) {
                        if ( ch == ')' ) {
                            step = 3;
                            values.add(newWord);
                            newWord = "";
                        } else if ( (ch >= '0' && ch <= '9') ) {
                            newWord += ch;
                        }
                    }

                    if ( step == 3 ) {
                        if ( ch == '{' ) {
                            release = false;
                        } else if ( ch >= 'A' && ch <= 'Z' ) {
                            step = 4;
                        }
                    }
                    
                    if ( step == 4 ) {
                        if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '-') ) {
                            newWord += ch;
                            
                        }
                    }
                    
                } else {
                    if ( ch == '}' ) {
                        release = true;
                        step = 4;
                    }
                }
    }
    void orderMovies(char ch)
    {                                  
         if ( release ) {
                    
                    if ( step == 1 ) {
                        
                        if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '!') || (ch == '?') || (ch == '"') || (ch >= '0' && ch <= '9') || (ch >= ' ') || ch == '#' ) {
                            
                            if ( ch == '(' ) {
                                step = 2;
                                values.add(newWord);
                                newWord = "";
                            } else {
                                newWord += ch;
                            }
                        
                        }
                        
                    }
                    
                    if ( step == 2 ) {
                        
                        if ( (ch >= '0' && ch <= '9') && ch != '(' && ch != ')' ) {
                            newWord += ch;
                        }
                        if ( ch == ')' ) {
                            step = 3;
                            values.add(newWord);
                            newWord = "";
                        }
                    
                    }
                    
                    
                } 
    }    
}
