package client;

import java.awt.EventQueue;
 
public class StartClient {
	
	public static void main(String[] args) {
		//另外开启一个线程，以保证线程的安全性
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegisPage regis = new RegisPage();
					regis.setVisible(true);
				}
				catch (Exception e) {
				}
			}
		});
	}
}
