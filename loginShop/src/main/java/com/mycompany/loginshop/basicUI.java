/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginshop;

/**
 *
 * @author sawaz
 */
import java.util.Scanner;

public class basicUI {
    Scanner scan = new Scanner(System.in);
    public int ui(){
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to the Price Catcher!");
        
        System.out.println("1.Register an account");
        System.out.println("2.Login to your account");
        System.out.println("3.Exit");
        System.out.print("Please select on what you want to do:");
        int userInput = scan.nextInt();
        
        return userInput;
    }
}