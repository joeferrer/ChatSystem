/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.io.*;
import java.net.*;
import java.util.*;
public class MyServer {
	public static void main(String args[]) {
		LinkedList <SExtension> sx_list = new LinkedList <SExtension>();
		LinkedList <ID> id_list = new LinkedList <ID>();
		int count = 0;
		String pack = "";
		ServerGUI sg = new ServerGUI();
		try {
			sg.field[0].append("Server: Starting Server...\n");
			ServerSocket ssocket = new ServerSocket(8888);
			sg.field[0].append("Server: Waiting for connections...\n");
			while(true) {
				ID id = new ID(ssocket.accept(),"Client" + count);
				sg.field[0].append("Server: " + id.s_inetAd + " connected!\n");
				id_list.add(id);
				sx_list.add(new SExtension(count,sg));
				for(int i=0;i<count+1;i++) {
					if(sx_list.get(i).id_num == -1) 
						id_list.get(i).activity = 0;
					if(sx_list.get(i).id_num > -1) {
						sx_list.get(i).id_list = id_list;
						id_list.get(i).conn.sendMessage("1^^^Server message: " + id_list.get(count).scrname + " has connected");
						pack += "###"+id_list.get(i).scrname+"-" + id_list.get(i).status;
					}
				}
				for(int i=0;i<count+1;i++) {
					if(id_list.get(i).activity ==1) {
						id_list.get(i).conn.sendMessage(pack);
					}
				}
				pack = "";
				new Thread(sx_list.get(count)).start();
				count++;
			}
			
		} catch (Exception e) {
			sg.field[0].append(e.getMessage());
		}
	}
}
