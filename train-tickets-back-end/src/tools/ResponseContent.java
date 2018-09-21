package tools;

public class ResponseContent {
	private String statusCode = "";
	private String content = "";
	private String discription = "";
	
	public ResponseContent(String statusCode, String content, String discription) {
		super();
		this.statusCode = statusCode;
		this.content = content;
		this.discription = discription;
	}

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", content=" + content + ", discription=" + discription + "]";
	}
}
