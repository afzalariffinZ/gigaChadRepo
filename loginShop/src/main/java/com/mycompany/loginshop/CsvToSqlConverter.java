/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginshop;

/**
 *
 * @author sawaz
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvToSqlConverter {

    public static void main(String[] args) {
        String csvFilePath = "D:\\lookup_item.csv"; // Replace with your CSV file path

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath))) {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            String url = "jdbc:mysql://localhost:3306/test69";
            String username = "root";
            String password = "";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String insertQuery = "INSERT INTO lookup_item (item_code, item, unit,item_group,item_category) VALUES (?, ?, ?,?,?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // Assuming your CSV file has three columns (adjust accordingly)
                            String[] columns = line.split(",");
                            try {
                            int x = Integer.parseInt(columns[0]);

                            // Set values for the prepared statement
                            preparedStatement.setInt(1, x);
                            preparedStatement.setString(2, columns[1]);
                            preparedStatement.setString(3, columns[2]);
                            preparedStatement.setString(4, columns[3]);
                            preparedStatement.setString(5, columns[4]);

                        } catch (NumberFormatException e) {
                            // Handle the case where the first column is not a valid integer
                            System.err.println("Error parsing integer: " + e.getMessage());
                            // You might want to log the error, skip the current line, or take other actions
                        }
                        

                        // Execute the insert statement
                        preparedStatement.executeUpdate();
                    }
                }
                System.out.println("CSV data successfully inserted into the MySQL database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

