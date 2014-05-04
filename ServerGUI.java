import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

public class ServerGUI extends GUI  {
	
	DefaultCaret caret;

	public ServerGUI() {
		frame = new JFrame("ChatServer by Joe Ferrer v.Alpha");
		frame.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent we){
					System.exit(0);
				}
			}	
		);
		frame.setResizable(false);

		frame_pane = frame.getContentPane();
		frame_pane.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		setGBC(0.5,GridBagConstraints.NONE,0,0);
		setFSP(0,5,25,false,"Server Notifications");
		caret = (DefaultCaret)field[0].getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		frame.pack();
		frame.setVisible(true);
	}
}


