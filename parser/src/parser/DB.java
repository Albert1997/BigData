/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


// Jorben


public class DB {
    
    void Version()
    {
    Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String url = "jdbc:postgresql://localhost/BigData";
        String user = "postgres";
        String password = "Informatica123";
        
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MakeCSV.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(MakeCSV.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
    } 
    static void Database(List <String> values, String file , int indexer)
    {
        Connection con = null;
        PreparedStatement pst = null;

        String url = "jdbc:postgresql://localhost/BigData";
        String user = "postgres";
        String password = "Informatica123";
        //System.out.print("Current file: "); System.out.println(file);
        String stm = "";
        System.out.print(file);
        System.out.println(" is de file waar we in de database zitten.");
                    try{
                        con = DriverManager.getConnection(url, user, password);
                        if (null != file)
                        switch (file) {
                            
                case "actors":
                    stm = "INSERT INTO actors(id, name, movie, year) VALUES(?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                        pst.setInt(1, indexer);
                        pst.setString(2, values.get(0));
                        pst.setString(3, values.get(1));
                        pst.setInt(4, Integer.parseInt(values.get(2)));
                    break;
                    
                case "actresses":
                    stm = "INSERT INTO actresses(id, name, movie, year) VALUES(?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                        pst.setInt(1, indexer);
                        pst.setString(2, values.get(0));
                        pst.setString(3, values.get(1));
                        pst.setInt(4, Integer.parseInt(values.get(2)));
                    break;
                    
                case "movies":
                    stm = "INSERT INTO movies(id, movie, year) VALUES(?, ?, ?)";
                    pst = con.prepareStatement(stm);
                    pst.setInt(1, indexer);
                    pst.setString(2, values.get(1));
                    pst.setInt(3, Integer.parseInt(values.get(2)));
                    break;
                    
                case "ratings":
                    stm = "INSERT INTO ratings(id, votes, rating, movie, year) VALUES(?, ?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                    pst.setInt(1, indexer);
                    pst.setInt(2, Integer.parseInt(values.get(0)));
                    pst.setFloat(3, Float.parseFloat(values.get(1)));
                    pst.setString(4, values.get(2));
                    pst.setInt(5, Integer.parseInt(values.get(3)));
                    break;
                    
                case "locations":
                    stm = "INSERT INTO locations(id, movie, year, location) VALUES(?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                    pst.setInt(1, indexer);
                    pst.setString(2, values.get(0));
                    pst.setInt(3, Integer.parseInt(values.get(1)));
                    pst.setString(4, values.get(2));
                    break;
                    
                case "genres":
                    stm = "INSERT INTO genres(id, movie, year, genre) VALUES(?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                    pst.setInt(1, indexer);
                    pst.setString(2, values.get(0));
                    pst.setInt(3, Integer.parseInt(values.get(1)));
                    pst.setString(4, values.get(2));
                    break;
                case "soundtracks":
                    stm = "INSERT INTO soundtracks1(soundtrack_id, movie, year, soundtrack) VALUES(?, ?, ?, ?)";
                    pst = con.prepareStatement(stm);
                    pst.setInt(1, indexer);
                    pst.setString(2, values.get(0));
                    pst.setInt(3, Integer.parseInt(values.get(1)));
                    pst.setString(4, values.get(2));
                    
                default:
                    break;
            }
                        pst.executeUpdate();
                        System.out.print("Succes waarde in de database: ");
                        System.out.print(indexer);
                        System.out.print(" ");

                    } catch (SQLException ex) {
                        Logger lgr = Logger.getLogger(DB.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);

                    } finally {

                        try {
                            if (pst != null) {
                                pst.close();
                            }
                            if (con != null) {
                                con.close();
                            }

                        } catch (SQLException ex) {
                            Logger lgr = Logger.getLogger(DB.class.getName());
                            lgr.log(Level.SEVERE, ex.getMessage(), ex);
                        }
                    }
                
    }
}
