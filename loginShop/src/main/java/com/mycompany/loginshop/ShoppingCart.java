/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginshop;

/**
 *
 * @author sawaz
 */
import java.sql.Connection; // Represents a connection to the database
import java.sql.DriverManager; // Helps in obtaining a connection to the database
import java.sql.PreparedStatement; // Used for prepared statements
import java.sql.Statement; // Used for executing SQL statements
import java.sql.ResultSet; // Represents a table of data resulting from a query
import java.sql.SQLException; // Handles SQL exceptions
import java.util.Scanner;

public class ShoppingCart {
    
    static String url = "jdbc:mysql://localhost:3306/test69";
    static String user = "root";
    static String pass = "";
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
       try {
            
            AddtoCart("afzal","ikan");
            replaceExistingItem("afzal","ikan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    
    public static void addExistingItem(String username, String Item_code){
        
        try{
          Connection connect = DriverManager.getConnection(url,user,pass);
          Statement statement = connect.createStatement();
          String query ="SELECT * FROM shopping_cart where username=Username";
          ResultSet result= statement.executeQuery(query);
          while (result.next()) {
              
                if (result.getString(2).equals(username) && result.getString(3).equals(Item_code)) {
                    
                    System.out.print("Please enter the quantity that you wish to add into the existing product: ");
                    int x =scan.nextInt();
                    String insertQuery = "UPDATE shopping_cart SET Item_quantity = Item_quantity + ? WHERE Item_code = ?";
                    PreparedStatement preparedStatement = connect.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, x);
                    preparedStatement.setString(2,Item_code);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Quantity(s) updated to cart successfully.");
                    } else {
                        System.out.println("Failed to update to cart.");
                    }
                    
                    return;
                }
            }
       }catch(SQLException e){
           e.printStackTrace();
       }
        
    }
    
    public static void replaceExistingItem(String username, String Item_code){
        
        try{
          Connection connect = DriverManager.getConnection(url,user,pass);
          Statement statement = connect.createStatement();
          String query ="SELECT * FROM shopping_cart where username=Username";
          ResultSet result= statement.executeQuery(query);
          while (result.next()) {
              
                if (result.getString(2).equals(username) && result.getString(3).equals(Item_code)) {
                    
                    System.out.print("Please enter the quantity that you wish to replace into the existing product: ");
                    int x =scan.nextInt();
                    String insertQuery = "UPDATE shopping_cart SET Item_quantity = ? WHERE Item_code = ?";
                    PreparedStatement preparedStatement = connect.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, x);
                    preparedStatement.setString(2,Item_code);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Quantity(s) updated to cart successfully.");
                    } else {
                        System.out.println("Failed to update to cart.");
                    }
                    
                    return;
                }
            }
       }catch(SQLException e){
           e.printStackTrace();
       }
        
    }
    public static void DisplayCart(String username) throws SQLException{
        
        
       try{
          Connection connect = DriverManager.getConnection(url,user,pass);
          Statement statement = connect.createStatement();
          String query ="SELECT * FROM shopping_cart where username=Username";
          ResultSet result= statement.executeQuery(query);
          System.out.println("Your Shopping Cart: ");
          while(result.next()){
               String items="";
               for (int i = 4; i >= 3; i--) {
                  items+= result.getString(i)+" ";
              }
               
               System.out.println(items);
          }
       }catch(SQLException e){
           e.printStackTrace();
       }

    }
    
    public static void AddtoCart(String username ,String Item_code) throws SQLException{


        System.out.print(" Type The Quantity : ");
        
        int quantity= scan.nextInt();
        

        try (Connection connect = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connect.createStatement();
            String query ="SELECT * FROM shopping_cart where username=Username";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                if (result.getString(2).equals(username) && result.getString(3).equals(Item_code)){
                    System.out.println("The product that you want to add has already existed.");
                    addExistingItem(username, Item_code);
                    return;
                }
                
            }
            
            String insertQuery = "INSERT INTO shopping_cart (Username,Item_code,Item_quantity) VALUES ( ?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(insertQuery);

            // Set the values for the parameters in the prepared statement
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, Item_code);
            preparedStatement.setInt(3, quantity);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item(s) added to cart successfully.");
            } else {
                System.out.println("Failed to add to cart.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
