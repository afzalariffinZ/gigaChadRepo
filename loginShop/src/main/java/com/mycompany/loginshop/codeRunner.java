/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginshop;

/**
 *
 * @author sawaz
 */
public class codeRunner {



    //This is where codes are being run
    public static void main(String[] args) {
        //creating instance of Prompt, Database and basicUI class
        Prompt newPrompt = new Prompt(); 
        Register checker = new Register(); 
        Login Login = new Login();

        while(true){
            basicUI base = new basicUI();
            //calling the UI
            int userChoice = base.ui();

            switch (userChoice) {
                case 1:
                    {
                        //register
                        String[] newData = new String[3]; //creating an array to fill user's email, username and password
                        newPrompt.promptRegister(newData); // calling the prompter method from Prompt.java using newPrompt instance to ask user for their infos
                        /*
                        * newData[0] = email
                        * newData[1] = username
                        * newData[2] = password
                        */
                        checker.sqlConnectorRegister(newData[0], newData[1], newData[2]); //checking and inserting user info into the SQL Database
                        break;
                    }
                case 2:
                    {
                        //login
                        String[] newData = new String[3];
                        newPrompt.promptLogin(newData);
                        Login.sqlConnectorLogin(newData[0], newData[2]);
                        break;
                    }
                case 3:
                    //exiting
                    System.exit(0);
                default:
                    System.out.println("Please insert the input properly!");
                    break;
            }

        }
        

    }
}