/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package okey101;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author adem
 */
public class dbconnection {
     public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/okey101","root","karakan")) {
                Statement stmt=con.createStatement();
                    System.out.println("connected to database okey101");
            }
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}
    }
    
}
    
