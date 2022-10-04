/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamchat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class UDPServer{
    public static void main(String args[]) throws Exception{
        DatagramSocket serverSocket = new DatagramSocket (9876);
        ArrayList<InetAddress> IPAddresses = new ArrayList<InetAddress>();
        while(true){
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPA = receivePacket.getAddress();
            if(!IPAddresses.contains(IPA)){
                IPAddresses.add(IPA);
            }
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            for(InetAddress i:IPAddresses){
                DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, i, port);
                serverSocket.send(sendPacket);
            }
        }
    }
}
