package model.train;

// 火车换乘组合类
public class TransferTrain {
	private String firstTid = "";
	private String secondTid = "";
	private String firstTname = "";
	private String secondTname = "";
	private String transferStation = "";
	
	public String getFirstTid() {
		return firstTid;
	}
	public void setFirstTid(String firstTid) {
		this.firstTid = firstTid;
	}
	public String getSecondTid() {
		return secondTid;
	}
	public void setSecondTid(String secondTid) {
		this.secondTid = secondTid;
	}
	public String getFirstTname() {
		return firstTname;
	}
	public void setFirstTname(String firstTname) {
		this.firstTname = firstTname;
	}
	public String getSecondTname() {
		return secondTname;
	}
	public void setSecondTname(String secondTname) {
		this.secondTname = secondTname;
	}
	public String getTransferStation() {
		return transferStation;
	}
	public void setTransferStation(String transferStation) {
		this.transferStation = transferStation;
	}
	
}
