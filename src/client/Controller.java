package client;

/*
 * 控制器类，用于控制各Frame界面及动作。
 */
public class Controller {
	public FrameLogin frameLogin;
	
	/* 构造方法 */
	public Controller() {
		
	}
	
	/* Controller 生命周期开始 */
	public void start() {
		frameLogin = new FrameLogin(this);
	}
}
