package client;

/*
 * �������࣬���ڿ��Ƹ�Frame���漰������
 */
public class Controller {
	public String host;
	
	public FrameLogin frameLogin;
	
	/* ���췽�� */
	public Controller() {
		host = "http://yue.rayiooo.top/";
	}
	
	/* Controller �������ڿ�ʼ */
	public void start() {
		frameLogin = new FrameLogin(this);
	}
}
