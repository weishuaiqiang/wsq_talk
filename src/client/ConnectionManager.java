package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class ConnectionManager {
	
	private ConnectionManager() {
	}
	private static final ConnectionManager instance = new ConnectionManager();
	
	public static ConnectionManager getChatManager() {
		return instance;
	}
	
	// 为了能在界面上显示服务器发来的信息，就需要传一个MainWindow的引用进来
	MyClientWindow window;
	Socket socket;
	private String IP;
	BufferedReader bReader;
	PrintWriter pWriter;
 
	public void setWindow(MyClientWindow window) {
		this.window = window;
	}
 
	public void connect(String ip) {
		this.IP = ip;
		new Thread() {
			@Override
			public void run() {
				// 实现网络方法
				try {
					socket = new Socket(IP, 55533);
                    // 输出流 
					pWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					if(pWriter != null) {
						send(window.getRegis().getName() + "已进入房间。");
						window.Setstatus("已连接");
						
					}
					// 输入流
					bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String line = null;
					// 如果读取数据为空
					while ((line = bReader.readLine()) != null) {
						if("GET / HTTP/1.1/r/n".equals(line) == false)
						window.appendText(line);
					}
					// 读完数据之后要关闭
					pWriter.close();
					bReader.close();
					pWriter = null;
					bReader = null;
				} catch (ConnectException e) {
					window.Setstatus("连接失败，请联系管理员。。。");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
 
	public void send(String sendMsg) {
		if (pWriter != null) {
			pWriter.write(sendMsg + "\n");
			pWriter.flush();
		} else {
			window.appendText("请确认连接成功。。");
		}
	}
}
