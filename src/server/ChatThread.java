package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class ChatThread extends Thread {
	
	Socket socket;
 
	public ChatThread(Socket s) {
		this.socket = s;
	}
 
	public void out(String out) {
		try {
			socket.getOutputStream().write(out.getBytes("GB2312"));
		} catch (Exception e) {
		}
	}
 
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GB2312"));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				ChatThreadManager.getChatManager().publish(this, line);
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}