package client;

/*
 * �������࣬���ڿ��Ƹ�Frame���漰������
 */
public class Controller {
	public String host;
	
	public FrameLogin frameLogin;
	public FrameMain frameMain;
	
	/* ���췽�� */
	public Controller() {
		host = "http://yue.rayiooo.top/";
	}
	
	/* Controller �������ڿ�ʼ */
	public void start() {
		this.newFrameLogin();
	}
	
	/* ��½����� */
	public void newFrameLogin() {
		frameLogin = new FrameLogin(this);
	}
	
	/* ������� */
	public void newFrameMain() {
		frameMain = new FrameMain(this);
	}
}
