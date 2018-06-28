package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FrameLogin extends JFrame{
	Controller controller;	// ��Controller�����/�ۺ�
	
	protected JTextField usernameText;
	protected JPasswordField passwordText;
	protected JButton loginBtn;
	protected JButton registerBtn;
	
	public FrameLogin(){
		
	}

	public FrameLogin(Controller controller) {
		super("Լ - ��½");
		this.controller = controller;

		// layout settings
		this.setLayout(new BorderLayout());
		
		JLabel imgLabel = new JLabel();
		try {
			imgLabel.setIcon(new ImageIcon(ImageIO.read(new FileInputStream("img\\yue.png")).getScaledInstance(480, 150, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		JLabel userLabel = new JLabel("�û���");
		this.usernameText = new JTextField(20);
		JLabel pwdLabel = new JLabel("���룺");
		this.passwordText = new JPasswordField(20);
		this.loginBtn = new JButton("��½");
		this.registerBtn = new JButton("ע��");
		
		// layout add
		JPanel jp11 = new JPanel(new BorderLayout());
		jp11.add(userLabel, BorderLayout.WEST);
		jp11.add(usernameText, BorderLayout.CENTER);
		JPanel jp12 = new JPanel(new BorderLayout());
		jp12.add(pwdLabel, BorderLayout.WEST);
		jp12.add(passwordText, BorderLayout.CENTER);
		
		JPanel jp21 = new JPanel(new BorderLayout());
		jp21.add(jp11, BorderLayout.NORTH);
		jp21.add(jp12, BorderLayout.SOUTH);
		JPanel jp22 = new JPanel(new BorderLayout());
		jp22.add(loginBtn, BorderLayout.NORTH);
		jp22.add(registerBtn, BorderLayout.SOUTH);
		
		JPanel jp31 = new JPanel(new BorderLayout());
		jp31.add(jp21, BorderLayout.NORTH);	// �˺�����
		jp31.add(jp22, BorderLayout.SOUTH);	// ��½ע�ᰴť
		
		JPanel jp41 = new JPanel(new BorderLayout());
		jp41.add(new JLabel("                        "), BorderLayout.WEST);
		jp41.add(new JLabel("                        "), BorderLayout.EAST);
		jp41.add(new JLabel(" "), BorderLayout.NORTH);
		jp41.add(new JLabel(" "), BorderLayout.SOUTH);
		jp41.add(jp31, BorderLayout.CENTER);
		
		JPanel jp51 = new JPanel(new BorderLayout());
		jp51.add(imgLabel, BorderLayout.NORTH);
		jp51.add(jp41, BorderLayout.SOUTH);
		
		this.add(jp51);
		
		// necessary settings
		this.setSize(480, 360);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
//		System.out.println("FrameLogin()");
	}
}