/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import commom.Utils;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import server.Server;
/**
 *
 * @author book
 */
public class Login extends JFrame {

    private JButton jb_login;
    private JLabel jl_user, jl_port, jl_title;
    private JTextField jt_user, jt_port;
    
    public Login(){
        super("Login");
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
        start();
    }
    
    private void initComponents(){
        jb_login = new JButton("Entrar");
        jl_user = new JLabel("Apelido", SwingConstants.CENTER);
        jl_port = new JLabel("Porta", SwingConstants.CENTER);
        jl_title = new JLabel();
        jt_user = new JTextField();
        jt_port = new JTextField();
    }

    private void configComponents(){
        this.setLayout(null);
        this.setMinimumSize(new Dimension(400,300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        jl_title.setBounds(10,10,375,100);
        ImageIcon icon = new ImageIcon("src/logo.png");
        jl_title.setIcon(new ImageIcon(icon.getImage().getScaledInstance(375, 100, Image.SCALE_SMOOTH)));

        jb_login.setBounds(10,220,375,40);

        jl_user.setBounds(10,120,100,40);
        jl_user.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jl_port.setBounds(10,170,100,40);
        jl_port.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jt_user.setBounds(120,120,265,40);
        jt_port.setBounds(120,120,265,40);
    }

    private void insertComponents(){
        this.add(jb_login);
        this.add(jl_user);
        this.add(jl_port);
        this.add(jl_title);
        this.add(jt_user);
        this.add(jt_port);

    }

    private void insertActions(){
        jb_login.addActionListener(event -> {
            try {
            String nickname = jt_user.getText();
            jt_user.setText("");
            int port = Integer.parseInt(jt_port.getText());
            jt_port.setText("");
            Socket connection = new Socket(Server.HOST, Server.PORT);
            String connection_info = (nickname + ":" + connection.getLocalAddress().getHostAddress() + ":" + port);
            Utils.sendMessage(connection, connection_info);
            if (Utils.receiveMessage(connection).equals("SUCESS")){
                new Home(connection, connection_info);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Alguém usando porta ou apelido");
            }
            } catch (IOException e){
                JOptionPane.showMessageDialog(null, "Erro ao conectar. Verifique se o servidor esta em execução!");
            }
        });
    }

    private void start(){
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Login login = new Login();
    }
}

