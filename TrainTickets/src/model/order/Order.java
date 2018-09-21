package model.order;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Order {
	private String oid = "";
	private String uid = "";
	private String passengerId = "";
	private String passengerName = "";
	private String tid = "";
	private String cid = "";
	private String location = "";
	private String startSid = "";
	private String endSid = "";
	private String date = "";
	private String creatAt = "";
	private String status = "未付款";
	
	//以下为非必须属性
	private String tname = "";
	private String fromCity = "";
	private String toCity = "";
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String ctype) {
		this.cid = ctype;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartSid() {
		return startSid;
	}
	public void setStartSid(String startSid) {
		this.startSid = startSid;
	}
	public String getEndSid() {
		return endSid;
	}
	public void setEndSid(String endSid) {
		this.endSid = endSid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatAt() {
		return creatAt;
	}
	public void setCreatAt(String creatAt) {
		this.creatAt = creatAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
}
