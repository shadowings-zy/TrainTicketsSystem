package model.user;

// 用户类
public class User {
	private String userId = "";
	private String userName = "";
	private String password = "";
	private String idcard = "";
	private String status = "0";
	private String telephone = "";
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String uid) {
		this.userId = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String tel) {
		this.telephone = tel;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", idcard=" + idcard
				+ ", status=" + status + ", telephone=" + telephone + "]";
	}
}
