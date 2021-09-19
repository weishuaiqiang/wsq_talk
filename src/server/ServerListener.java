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
			Socket socket=serversocket.accept();  //进程会阻塞在这句，直到有socket连接进来，就往下执行
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
