package server;

import java.util.Vector;

public class ChatThreadManager {
 
	private ChatThreadManager() {
 
	}
 
	private static final ChatThreadManager Cm = new ChatThreadManager();
 
	public static ChatThreadManager getChatManager() {
		return Cm;
	}
 
	Vector<ChatThread> ChatSocket_vector = new Vector<ChatThread>();
 
	public void add(ChatThread cs) {
		ChatSocket_vector.add(cs);
	}
 
	public void publish(ChatThread cs, String msg) {
		for (int i = 0; i < ChatSocket_vector.size(); i++) {
			ChatThread csTemp = ChatSocket_vector.get(i);
			if (!cs.equals(csTemp)) {
				csTemp.out(msg + "\n");
			}
		}
	}
}

