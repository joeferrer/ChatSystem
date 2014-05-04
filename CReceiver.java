/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.io.*;
import java.net.*;
import java.util.*;

public class CReceiver extends Thread {
	Socket socket;
	String received;
	MyConnection conn;
	ChatGUI cg;
	String[] words;
	String msg;
	
	public CReceiver(Socket x, MyConnection y,ChatGUI c) {
		socket = x;
		conn = y;
		cg = c;
	}
	public void run() {
		while(true) {
			try {
				received = conn.getMessage();
				words = received.split("\\^\\^\\^");
				if(!received.equals(null)) {
					if(words[0].equals("1")) {
						cg.field[0].append(words[1] + "\n");
						cg.field[1].setText("");
						received = conn.getMessage();
						if(received.toUpperCase().equals("GOODBYE")) {
							cg.field[0].append("Program Terminating...\n");
							cg.send.setEnabled(false);
							Thread.sleep(3000);
							cg.ForceQuit();
							break;
						}	
						words = received.split("\\#\\#\\#");
						for(int i=1;i<words.length;i++) {
							//System.out.println("here @1's: " + words[i]);
							cg.field[1].append(words[i] + "\n");
						}
						//System.out.println("here @1's");
					}
					else {
						msg = words[1];
						words = msg.split("\\$\\$\\$");
						msg = words[0];
						for(int i=1;i<words.length;i++) {
							msg += "\n" + words[i];
						}
						cg.field[0].append(msg + "\n");
						//System.out.println("here @0's "); //+ words[0] + " " + words[1]);
					}	
				}
				else break;
			}catch(Exception e) {
				break;
			}
		}	
	}
}
