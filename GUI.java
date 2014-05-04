import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI {
	
	JFrame		frame;
	Container	frame_pane;
	GridBagConstraints gbc;
	JTextArea[] field = new JTextArea[3];
	JScrollPane[] scrolls = new JScrollPane[3];
	JPanel[] pane = new JPanel[3];

	public void setFSP(int i,int length, int width,boolean editor,String title) {
		field[i] = new JTextArea("",length,width);
		field[i].setLineWrap(true);
		field[i].setWrapStyleWord(true);
		field[i].setEditable(editor);
		
		scrolls[i] = new JScrollPane(field[i]);
		
		pane[i] = new JPanel(new BorderLayout());
		if(i!=2)
			pane[i].setBorder(BorderFactory.createTitledBorder(title));
		else 
			pane[2].setBorder(BorderFactory.createLoweredBevelBorder());
		
		pane[i].add(scrolls[i]);
		
		frame_pane.add(pane[i],gbc);
	}

	public void setGBC(double wx, int fill, int gx, int gy) {
		gbc.weightx  = wx;
		gbc.fill = fill;
		gbc.gridx = gx;
		gbc.gridy = gy;
	}

}