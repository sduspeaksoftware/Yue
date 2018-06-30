package client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import http.SimpleHttpUtils;

@SuppressWarnings("serial")
public class PanelSubmit extends JPanel{
	FrameMain frameMain;
	JTextArea text;
	JButton btn;
	
	public PanelSubmit(FrameMain frameMain) {
		super();
		this.frameMain = frameMain;
		
		text = new JTextArea("title=&publishUsername=" + frameMain.controller.user.username + "&noteDestination=&content=");
		btn = new JButton("提交");
		
		this.add(text);
		this.add(btn);
		
		this.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ret = "";
				try {
					ret = SimpleHttpUtils.post(frameMain.controller.host + "submitNote.php", text.getText().getBytes("utf-8"));
					System.out.println(ret);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, ret, ret, JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
	}
}
