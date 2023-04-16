package poly.message;

public class Message {
	private String type;
	private String content;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
