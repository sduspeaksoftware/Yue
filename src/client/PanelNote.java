package client;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import http.SimpleHttpUtils;

@SuppressWarnings("serial")
public class PanelNote extends JPanel{
	FrameMain frameMain;
	String id;
	JTextArea textArea;
	
	public PanelNote(FrameMain frameMain) {
		super(new BorderLayout());
		this.frameMain = frameMain;
		
		this.textArea = new JTextArea();
		
		this.add(textArea);
		
	}
	
	public void set(String id) {
		this.id = id;

		// get note
		String ret = "";
		try {
			ret = SimpleHttpUtils.get(frameMain.controller.host + "getNote.php?id=" + id);
			System.out.println(ret);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String[] result = ret.split("<br>");
		String res = "";
		for(int i=0; i<result.length; i++) {
			res += result[i] + "\n";
		}
		this.textArea.setText(res);
	}
}
