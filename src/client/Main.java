package client;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main{
	public static void main(String[] args){
		//GUI优化
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
							// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				break;
			}
		}
		
		new Controller().start();
	}
}