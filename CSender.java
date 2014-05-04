/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import javax.swing.*;
import java.io.*;
import java.net.*;
public class CSender extends Thread {
	Socket socket;
	String input;
	MyConnection conn;
	
	public CSender(Socket x, MyConnection y) {
		socket = x;
		conn = y;
		input = "";
	}
	public void run() {
		while(true) {
			try {
				while(input.equals(""));
				conn.sendMessage(input);
				if(input.toUpperCase().equals("/QUIT")) break;
				input = "";
			}catch (Exception e) {
				break;
			}
		}	
	}	
}
