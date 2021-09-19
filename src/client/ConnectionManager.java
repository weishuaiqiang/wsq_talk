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
	
	// Ϊ�����ڽ�������ʾ��������������Ϣ������Ҫ��һ��MainWindow�����ý���
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
				// ʵ�����緽��
				try {
					socket = new Socket(IP, 55533);
                    // ����� 
					pWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					if(pWriter != null) {
						send(window.getRegis().getName() + "�ѽ��뷿�䡣");
						window.Setstatus("������");
						
					}
					// ������
					bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String line = null;
					// �����ȡ����Ϊ��
					while ((line = bReader.readLine()) != null) {
						if("GET / HTTP/1.1/r/n".equals(line) == false)
						window.appendText(line);
					}
					// ��������֮��Ҫ�ر�
					pWriter.close();
					bReader.close();
					pWriter = null;
					bReader = null;
				} catch (ConnectException e) {
					window.Setstatus("����ʧ�ܣ�����ϵ����Ա������");
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
			window.appendText("��ȷ�����ӳɹ�����");
		}
	}
}
