package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import http.SimpleHttpUtils;
import subString.SubString;

/* 该Panel从属于FrameMain */
@SuppressWarnings("serial")
public class PanelNoteList extends JPanel{
	public int currentPage = 1;
	public FrameMain frameMain;
	
	public PanelNoteList(FrameMain frameMain) {
		super(new GridLayout(11, 1));
		this.frameMain = frameMain;
		this.setBackground(Color.WHITE);
		this.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		this.getPage();
	}
	
	public void getPage() {
		this.removeAll();
		String ret = "";
		try {
			ret = SimpleHttpUtils.get(this.frameMain.controller.host + "getNoteList.php?page=" + this.currentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] noteList = ret.split("<br>");
		for(int i=0; i<10; i++) {
			if(i<noteList.length) {
				this.add(new ListElement(this, noteList[i]));
			}else {
				this.add(new ListElement(this, null));
			}
		}
		this.add(new ListPage(this, currentPage != 1, noteList.length == 10));
	}
}

/* 单个Note在List中的显示，聚合ListPanel */
@SuppressWarnings("serial")
class ListElement extends JPanel{
	PanelNoteList listPanel;
	ListElement element;
	String id, title, publisherUsername, publishTime, noteFinished;
	
	public ListElement(PanelNoteList listPanel, String noteDetail) {
		super(new BorderLayout());
		this.listPanel = listPanel;
		this.element = this;
		this.setBackground(Color.WHITE);
		this.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		if(noteDetail != null) {
			id = SubString.getSubString(noteDetail, "<Id>", "</Id>");
			title = SubString.getSubString(noteDetail, "<Title>", "</Title>");
			publisherUsername = SubString.getSubString(noteDetail, "<PublisherUsername>", "</PublisherUsername>");
			publishTime = SubString.getSubString(noteDetail, "<PublishTime>", "</PublishTime>");
			noteFinished = SubString.getSubString(noteDetail, "<NoteFinished>", "</NoteFinished>");
			
			this.add(new JLabel(title, SwingConstants.LEFT), BorderLayout.CENTER);
			this.add(new JLabel(publisherUsername), BorderLayout.EAST);
			
			// 当前帖子被点击打开
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					listPanel.frameMain.note.set(id);
					listPanel.frameMain.displayNote(id);
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {
					element.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				public void mouseExited(MouseEvent e) {
					element.setCursor(new Cursor(Cursor.MOVE_CURSOR));
				}
			});
		}else {
			this.add(new JLabel(" ", SwingConstants.LEFT), BorderLayout.CENTER);
		}
	}
}

@SuppressWarnings("serial")
/* 上一页、下一页显示类 */
class ListPage extends JPanel{
	PanelNoteList listPanel;
	JButton lastBtn;
	JButton nextBtn;
	
	/* last表示是否显示上一页，next表示是否显示下一页 */
	public ListPage(PanelNoteList listPanel, boolean lastFlag, boolean nextFlag) {
		super(new BorderLayout());
		this.listPanel = listPanel;
		
		this.lastBtn = new JButton("上一页");
		this.nextBtn = new JButton("下一页");
		
		this.add(lastBtn, BorderLayout.WEST);
		this.add(nextBtn, BorderLayout.EAST);
		this.add(new JLabel("  "), BorderLayout.CENTER);
		
		this.lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listPanel.currentPage--;
				listPanel.getPage();
			}
		});
		this.nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listPanel.currentPage++;
				listPanel.getPage();
			}
		});
		
		this.lastBtn.setEnabled(lastFlag);
		this.nextBtn.setEnabled(nextFlag);
	}
}