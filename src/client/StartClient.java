package client;

import java.awt.EventQueue;
 
public class StartClient {
	
	public static void main(String[] args) {
		//���⿪��һ���̣߳��Ա�֤�̵߳İ�ȫ��
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
