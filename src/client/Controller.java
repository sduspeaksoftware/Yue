package client;

/*
 * �������࣬���ڿ��Ƹ�Frame���漰������
 */
public class Controller {
	public FrameLogin frameLogin;
	
	/* ���췽�� */
	public Controller() {
		
	}
	
	/* Controller �������ڿ�ʼ */
	public void start() {
		frameLogin = new FrameLogin(this);
	}
}
