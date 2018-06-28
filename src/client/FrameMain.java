package client;

import java.awt.GridLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameMain extends JFrame{
	Controller controller;
	
	PanelNoteList noteList;
	
	public FrameMain() {
		
	}
	
	public FrameMain(Controller controller) {
		super("Ô¼  Yue");
		this.controller = controller;
		
		// layout settings
		this.setLayout(new GridLayout());
		
		this.noteList = new PanelNoteList(this);
		
		this.add(noteList);
		
		// necessary settings
		this.setSize(1024, 768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/* Òþ²ØËùÓÐpanel */
	public void hideAll() {
		noteList.setVisible(false);
	}
}
