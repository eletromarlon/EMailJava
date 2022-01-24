/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import commom.Utils;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author book
 */
public class ClientListener implements Runnable {
    private String connection_info;
    private Socket connection;
    private Server server;
    private boolean running;
    
    
    public ClientListener (String connection_info, Socket connection, Server server) {
        this.connection_info = connection_info;
        this.connection = connection;
        this.server = server;
        this.running = false;
        
    }
    
    public boolean isRunning(){
        return running;
    }
    
    public void setRunnig(boolean running){
        this.running = running;
    }

    @Override
    public void run() {
        running = true;
        String message;
        while (running){
            message = Utils.receiveMessage(connection);
            if (message.equals("quit")){
                running = false;
            } else if (message.equals("GET_CONNECTED_USERS")){
                System.out.println("Solicitação de atualizar lista de contatos... ");
                String response = "";
                for (Map.Entry<String, ClientListener> pair : server.getClients().entrySet()){
                    response += (pair.getKey() + ";");
                }
                Utils.send(connection, response);
            } else {
                System.out.println("Recebido : " + message);
            }
        }
    }
}
