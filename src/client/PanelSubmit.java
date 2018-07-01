package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import http.SimpleHttpUtils;

@SuppressWarnings("serial")
public class PanelSubmit extends JPanel{
	FrameMain frameMain;
	JTextArea titleText;
	JTextArea destinationText;
	JTextArea contentText;
	JButton submitBtn;
	
	public PanelSubmit(FrameMain frameMain) {
		super(new BorderLayout());
		this.frameMain = frameMain;
		
		// ���
		this.titleText = new JTextArea();
		this.destinationText = new JTextArea();
		this.contentText = new JTextArea();
		this.submitBtn = new JButton("�ύ");
		
		JPanel jp11 = new JPanel(new BorderLayout());
		jp11.add(new JLabel("���⣺"), BorderLayout.WEST);
		jp11.add(this.titleText, BorderLayout.CENTER);
		
		JPanel jp12 = new JPanel(new BorderLayout());
		jp12.add(new JLabel("Ŀ�ĵأ�"), BorderLayout.WEST);
		jp12.add(this.destinationText, BorderLayout.CENTER);
		
		JPanel jp21 = new JPanel(new GridLayout(2, 1));
		jp21.add(jp11);
		jp21.add(jp12);
		
		JPanel jp22 = new JPanel(new BorderLayout());
		jp22.add(new JLabel("���ݣ�"), BorderLayout.WEST);
		jp22.add(this.contentText, BorderLayout.CENTER);
		
		this.add(jp21, BorderLayout.NORTH);
		this.add(jp22, BorderLayout.CENTER);
		this.add(submitBtn, BorderLayout.SOUTH);
		
		// listener
		this.submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ret = "";
				if(titleText.getText().equals("") || destinationText.getText().equals("") || contentText.getText().equals("")) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "���ݲ���Ϊ�գ�", "���ݲ���Ϊ�գ�", JOptionPane.PLAIN_MESSAGE);
				}
				else
				try {
					String data = "";
					data += "title=" + titleText.getText();
					data += "&publishUsername=" + frameMain.controller.user.username;
					data += "&noteDestination=" + destinationText.getText();
					data += "&content=" + contentText.getText();
					ret = SimpleHttpUtils.post(frameMain.controller.host + "submitNote.php", data.getBytes("utf-8"));
					System.out.println(ret);
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, ret, ret, JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
	}
}
