package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 
 
public class ServerListener extends Thread{
   
	public void run()
	{
		try {
			ServerSocket serversocket= new ServerSocket(55533);
			while(true) {
			Socket socket=serversocket.accept();  //���̻���������䣬ֱ����socket���ӽ�����������ִ��
	        System.out.println("one client has connected");
			ChatThread cs=new ChatThread(socket);
			cs.start();
			ChatThreadManager.getChatManager().add(cs);
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
