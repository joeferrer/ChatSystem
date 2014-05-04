/*
CS 145 
MP1: Chat System
Author: Joseph Ferrer, 2010-24600
*/
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

public class ChatGUI extends GUI implements ActionListener {
	
	JButton send;
	CSender ts;
	
	String[] words;
	String msg;
	DefaultCaret caret;

	public ChatGUI(CSender x) {
		ts = x;
		ts.start();
		
		frame = new JFrame("ChatClient by Joe Ferrer v.Alpha");
		frame.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent we){
					ts.input = "/QUIT";
					System.exit(0);
				}
			}	
		);
		frame.setResizable(false);
		
		frame_pane = frame.getContentPane();
		frame_pane.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		setGBC(0.5,GridBagConstraints.NONE,0,0);
		setFSP(0,20,50,false,"Chat Window");
		caret = (DefaultCaret)field[0].getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		setGBC(0.5,GridBagConstraints.NONE,1,0);
		setFSP(1,20,15,false,"Online Clients");
		
		setGBC(0.5,GridBagConstraints.BOTH,0,2);
		setFSP(2,3,50,true,"Your Text");
		
		send = new JButton("Send");
		send.setActionCommand("send");
        send.addActionListener(this);
		send.setPreferredSize(new Dimension(160,23));
		setGBC(0.5,GridBagConstraints.NONE,1,2);
		frame_pane.add(send,gbc);
		
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("send")) {
			if(field[2].getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Please Enter Your Text First","No Message",JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(field[2].getLineCount() > 1) {
					words = field[2].getText().split("\n");
					msg = words[0];
					for(int i=1;i<words.length;i++) {
						msg += "$$$" + words[i];
					}
					ts.input = msg;
				}
				else {
					ts.input = field[2].getText();
				}	
				field[2].setText("");
			}	
		}
	}

	public void ForceQuit() {
		System.exit(0);
	}
}
