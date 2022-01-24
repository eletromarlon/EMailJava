/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author book
 */
public class Utils {
    
    public static boolean sendMessage (Socket connection, String message) throws IOException{
        try {
            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            output.writeObject(message);
            return true;
        } catch (IOException e){
            System.err.println("[ERRO:SendMessage] -> " + e.getMessage());
        }
        return false;
    }
    
    public static String receiveMessage(Socket connection){
        String response = null;
        
        try {
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
            response = (String) input.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.err.println("[ERRO:receiveMessage] -> " + e.getMessage());
        }
        
        return response;
    }
}
