/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author book
 */
public class Home extends JFrame {
    
    private String connection_info;
    private Socket connection;
    private JLabel jl_title;
    private JButton jb_get_connected, jb_start_talk;
    private JList jlist;
    private JScrollPane scroll;
    
    /**
     *
     * @param connection_info
     */
    public Home(Socket connection, String connection_info){
        super ("Chat - Home");
        this.connection = connection;
        this.connection_info = connection_info;
        
        initComponents();
        configComponents();
        insertComponents();
        insertActions();
        start();
    }
    
    private void initComponents(){
       jl_title = new JLabel("< Usuário: " + connection_info.split(":")[0] + " > ", SwingConstants.CENTER); //Nome do usuário
       jb_get_connected = new JButton(" Atualizar Contatos ");
       jb_start_talk = new JButton(" Mandar e-mail ");
       jlist = new JList();
       scroll = new JScrollPane(jlist);
    }

    private void configComponents(){
        this.setLayout(null);
        this.setMinimumSize(new Dimension(600, 400));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        
        jl_title.setBounds(10, 10, 370, 40);
        jl_title.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        jb_get_connected.setBounds(400, 10, 180, 40);
        //jb_get_connected.setFocusable(false); TESTE
        
        jb_start_talk.setBounds(10, 400, 575, 40);
        //jb_start_talk.setFocusable(false); TESTE
        
        jlist.setBorder(BorderFactory.createTitledBorder("Contatos Da lista"));
        //jlist.setSelectionModel(ListSelectionModel.SINGLE_INTERVAL_SELECTION); APenas uma conversa por vez
        
        scroll.setBounds(10, 60, 575, 335);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
               
    }

    private void insertComponents(){
        this.add(jl_title);
        this.add(jb_get_connected);
        this.add(jb_start_talk);
        this.add(scroll);
        
    }

    private void insertActions(){

    }

    private void start(){
        this.pack();
        this.setVisible(true);
    }
    /*
    public static void main (String[] args){
        Home home = new Home("Dinho:127.0.0.1:3333");
    }*/
    
}
