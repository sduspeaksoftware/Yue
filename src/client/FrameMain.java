package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameMain extends JFrame{
	Controller controller;
	
	JPanel cardPanel;
	CardLayout card;
	PanelSelect select;
	PanelNoteList noteList;
	PanelNote note;
	PanelSubmit submit;
	
	public FrameMain() {
		
	}
	
	public FrameMain(Controller controller) {
		super("‘º  Yue");
		this.controller = controller;
		
		// layout settings
		this.setLayout(new BorderLayout());
		
		this.card = new CardLayout(10, 10);
		this.cardPanel = new JPanel(card);
		
		this.select = new PanelSelect(this);
		this.noteList = new PanelNoteList(this);
		this.note = new PanelNote(this);
		this.submit = new PanelSubmit(this);

		// card
		this.cardPanel.add("noteList", noteList);
		this.cardPanel.add("note", note);
		this.cardPanel.add("submit", submit);

		// add
		this.add(select, BorderLayout.WEST);
		this.add(cardPanel, BorderLayout.CENTER);
		
		// necessary settings
		this.setSize(1024, 768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/* œ‘ æPanel */
	public void displayNoteList() {
		this.card.show(cardPanel, "noteList");
	}
	public void displayNote(String id) {
		this.card.show(cardPanel, "note");
	}
	public void displaySubmit() {
		this.card.show(cardPanel, "submit");
	}
}
