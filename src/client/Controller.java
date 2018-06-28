package client;

/*
 * 控制器类，用于控制各Frame界面及动作。
 */
public class Controller {
	public String host;
	
	public FrameLogin frameLogin;
	public FrameMain frameMain;
	
	/* 构造方法 */
	public Controller() {
		host = "http://yue.rayiooo.top/";
	}
	
	/* Controller 生命周期开始 */
	public void start() {
		this.newFrameLogin();
	}
	
	/* 登陆界面打开 */
	public void newFrameLogin() {
		frameLogin = new FrameLogin(this);
	}
	
	/* 主界面打开 */
	public void newFrameMain() {
		frameMain = new FrameMain(this);
	}
}
