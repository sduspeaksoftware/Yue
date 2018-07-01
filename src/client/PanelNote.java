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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		String[] result = ret.split("<br>");
		String res = "";
		res += "���⣺\t" + result[1] + "\n";
		res += "�����ˣ�\t" + result[2] + "\n";
		res += "ʱ�䣺\t" + result[3] + "\n";
		res += "Ŀ�ĵأ�\t" + result[4] + "\n";
		res += "���ݣ�\t\n\n" + result[5] +"\n\n";
		if(result[6].equals("0"))
			res += "δ������\n";
		else
			res += "�ѽ�����\n";
		this.textArea.setText(res);
	}
}
