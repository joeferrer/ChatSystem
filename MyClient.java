/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
public class MyClient {
	public static void main(String args[]) {

		Socket socket;
		MyConnection conn;
		String ipaddr = null;
		try {

			ipaddr = (String)JOptionPane.showInputDialog(null,"Please Specify IP Address of Server","Connection Settings",JOptionPane.WARNING_MESSAGE);
			if(ipaddr == null) {
				JOptionPane.showMessageDialog(null,"Null IP. Connection Failed.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			socket = new Socket(ipaddr,8888);
			conn = new MyConnection(socket);
			
			CSender ts = new CSender(socket,conn);
			
			ChatGUI cg = new ChatGUI(ts);
			
			CReceiver tr = new CReceiver(socket,conn,cg);
			tr.start();
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Invalid IP. Connection Failed.","Error",JOptionPane.ERROR_MESSAGE);
		}	
	}
}
