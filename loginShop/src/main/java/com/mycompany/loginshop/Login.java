/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginshop;

/**
 *
 * @author sawaz
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {
    public void sqlConnectorLogin(String email, String password){
        String url = "jdbc:mysql://localhost:3306/test69";
        String user = "root";
        String pass = "";
        email = email.trim();
        
        password = password.trim();
        try (
            Connection connect = DriverManager.getConnection(url, user, pass);
            Statement statement = connect.createStatement()) { // connecting to the database

            // checking the database whether email or username inserted by user has existed
            // or not
            boolean emailExist = false;
            boolean passwordExist = false;
            
            String username = null;
            ResultSet check = statement.executeQuery("select * from users");
            while (check.next()) {
                if (check.getString(1).equals(email) ) {
                    emailExist = true;
                    if(check.getString(3).equals(password)){
                            passwordExist = true;
                            username = check.getString(2);
                    }
                }
            }

            if(emailExist){
                if(passwordExist){
                    System.out.println("Welcome back "+username.toUpperCase()+"!");  
                }else{
                    System.out.println("Incorrect password, please try again!");  
                }
            }else{
                System.out.println("No email found, please register an account if you don't have an account!");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            // System.out.println("Hello world");
        }
    }
}
