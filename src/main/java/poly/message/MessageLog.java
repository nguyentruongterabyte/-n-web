package poly.message;

public class MessageLog {
	public static void showLog(Message message) {
		System.out.println(message.getType().toUpperCase() + ": " + message.getContent());
	}
}
