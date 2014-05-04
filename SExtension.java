/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.io.*;
import java.net.*;
import java.util.*;
public class SExtension implements Runnable {
	String read,oldname,status,whispered,pack="";
	String[] words;
	LinkedList <ID> id_list;
	int id_num;
	ServerGUI sg;

	public SExtension(int c,ServerGUI x) {
		LinkedList <ID> id_list = new LinkedList <ID> ();
		id_num = c;
		sg = x;
	}
	public void run() {
		try {
			while(true) {
				read = id_list.get(id_num).conn.getMessage();
				if(read.charAt(0) == '/') {
					words = read.split(" ");
					if(read.toUpperCase().equals("/QUIT")) {
						for(int i=0;i<id_list.size();i++) {
							if(id_list.get(i).activity == 1) 
								id_list.get(i).conn.sendMessage("1^^^Server message: " + id_list.get(id_num).scrname + " has disconnected");
						}
						id_list.get(id_num).activity = 0;
						id_list.get(id_num).conn.sendMessage("GOODBYE");
						sendIDL();
						id_list.get(id_num).socket.close();
						sg.field[0].append("Server: " + id_list.get(id_num).s_inetAd + " disconnected!\n");
						id_num = -1;
						break;
					}
					else if(words[0].toUpperCase().equals("/CHANGENAME")) {
						if(words.length != 2) 
							id_list.get(id_num).conn.sendMessage("0^^^Server message: Invalid Name. Username should consist of 1 word [at least/at most]");
						else {
							for(int i=0;i<id_list.size();i++) {
								if(words[1].equals(id_list.get(i).scrname) && id_list.get(i).activity == 1) {
									if(i!=id_num) 
										id_list.get(id_num).conn.sendMessage("0^^^Server message: Invalid Name. Username must be unique");
									else 
										id_list.get(id_num).conn.sendMessage("0^^^Server message: Same Name. No changes were made");
									break;			
								}
								else {
									if(i == id_list.size() - 1) {
										oldname = id_list.get(id_num).scrname;
										id_list.get(id_num).scrname = words[1];
										for(int j=0;j<id_list.size();j++) {
											if(id_list.get(j).activity == 1) 
												id_list.get(j).conn.sendMessage("1^^^Server message: " + oldname + " has changed name to " + id_list.get(id_num).scrname);
										}
										sendIDL();
									}	
								}		
							}			
						}			
					}
					else if(words[0].toUpperCase().equals("/CHANGESTATUS")) {
						if(words.length == 1) {
							id_list.get(id_num).conn.sendMessage("0^^^Server message: Incomplete Command. Please type your 'status'.");
						}
						else {	
							status = words[1];
							for(int i=2;i<words.length;i++) {
								status += " " + words[i];
							}
							for(int i=0;i<id_list.size();i++) {
								if(id_list.get(i).activity == 1)
									id_list.get(i).conn.sendMessage("1^^^Server message: " + id_list.get(id_num).scrname + " has changed status to " + "'" + status + "'");
							}
							id_list.get(id_num).status = status;
							sendIDL();
						}	
					}
					else if(words[0].toUpperCase().equals("/WHISPER")) {
						if(words.length <3) {
							id_list.get(id_num).conn.sendMessage("0^^^Server message: Incomplete Command. Please state the User to 'Whisper' to and WHAT to whisper.");		
						}
						else {
							for(int i=0;i<id_list.size();i++) {
								if(id_list.get(i).scrname.equals(words[1]) && id_list.get(i).activity == 1) {
									whispered = words[2];
									for(int j=3;j<words.length;j++) {
										whispered += " " + words[j];
									}
									id_list.get(i).conn.sendMessage("0^^^[" + id_list.get(id_num).scrname + " whispers]: " + whispered);
									break;
								}	
								else  
									if(i==id_list.size()-1) id_list.get(id_num).conn.sendMessage("0^^^Server message: " + words[1] + " user was not found");
							}
						}			
					}
					else 
						id_list.get(id_num).conn.sendMessage("0^^^Server message: Invalid command " + read);
				}
				else {
					for(int i=0;i<id_list.size();i++) {	
						if(id_list.get(i).activity == 1)
							id_list.get(i).conn.sendMessage("0^^^" + id_list.get(id_num).scrname +": " + read);
					}
				}	
			}
		}
		catch(Exception e) {}
	}
	
	public void sendIDL() {
		for(int i=0;i<id_list.size();i++) {
			if(id_list.get(i).activity==1) {
				pack+="###"+id_list.get(i).scrname+"-"+id_list.get(i).status;
			}
		}
		for(int i=0;i<id_list.size();i++) {
			if(id_list.get(i).activity==1) {
				id_list.get(i).conn.sendMessage(pack);
			}
		}
		pack="";
	}
}
