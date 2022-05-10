package org.example;
import lombok.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Data
public class Server {
    private ServerSocket ss;
    private int numberOfPlayers;
    private final int maxPlayers;
    private Socket player1;
    private Socket player2;

    public Server(){
        numberOfPlayers = 0;
        maxPlayers = 2;
        try{
            ss = new ServerSocket(45371);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem while creating Server");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for players...");
            while (numberOfPlayers < maxPlayers){
                DataInputStream in;
                DataOutputStream out;
                if(numberOfPlayers == 0){
                    player1 = ss.accept();
                    in  = new DataInputStream(player1.getInputStream());
                    out  = new DataOutputStream(player1.getOutputStream());
                }
                else{
                    player2 = ss.accept();
                    in  = new DataInputStream(player2.getInputStream());
                    out  = new DataOutputStream(player2.getOutputStream());
                }
                numberOfPlayers++;
                out.writeInt(numberOfPlayers);
                System.out.println("Player number: " + numberOfPlayers + " has connected!");
                if(numberOfPlayers == 2){
                    out = new DataOutputStream(player1.getOutputStream());
                    out.writeBoolean(true);
                    out = new DataOutputStream(player2.getOutputStream());
                    out.writeBoolean(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem while accepting client");
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.acceptConnections();
    }
}
