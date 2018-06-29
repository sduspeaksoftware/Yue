package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelSelect extends JPanel{
	public FrameMain frameMain;
	
	JButton readBtn;
	JButton writeBtn;
	
	public PanelSelect(FrameMain frameMain) {
		super(new BorderLayout());
		this.frameMain = frameMain;
		
		// initialize
		this.readBtn = new JButton("找别人的约");
		this.writeBtn = new JButton("我要约");
		
		// panel collect
		JPanel jp11 = new JPanel(new GridLayout(4, 1));
		jp11.add(this.readBtn);
		jp11.add(this.writeBtn);
		
		this.add(jp11);
		
		// listener
		this.readBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMain.noteList.getPage();
				frameMain.displayNoteList();
			}
		});
		this.writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMain.displaySubmit();
			}
		});
	}
}
