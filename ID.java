/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.io.*;
import java.net.*;
import java.util.*;
public class ID {
	String scrname;
	String status = "Available";
	String s_inetAd;
	Socket socket;
	MyConnection conn;
	int activity;
	public ID(Socket x,String d) {
		socket = x;	
		s_inetAd = socket.getInetAddress().toString();
		//System.out.println("Server: " + s_inetAd + " connected!");
		conn = new MyConnection(socket);
		scrname = d;
		activity = 1;
	}
}