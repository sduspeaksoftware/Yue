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
		res += "标题：\t" + result[1] + "\n";
		res += "发帖人：\t" + result[2] + "\n";
		res += "时间：\t" + result[3] + "\n";
		res += "目的地：\t" + result[4] + "\n";
		res += "内容：\t\n\n" + result[5] +"\n\n";
		if(result[6].equals("0"))
			res += "未结帖。\n";
		else
			res += "已结帖。\n";
		this.textArea.setText(res);
	}
}
