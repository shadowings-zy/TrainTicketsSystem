package model.train;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Train {
	private String tid = "";
	private String tname = "";
	private String type = "";
	
	//以下为非必需属性
	private String start = "";
	private String end = "";
	private String startTime = "";
	private String endTime = "";
	private int mileage = 0;
	private ArrayList<TrainStop> stopInfo = null;
	private ArrayList<TrainCarriage> carriageInfo = null;
	private ArrayList<TrainSeats> seatsInfo = null;
	private int startIndex = 0;
	private int endIndex = 0;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public ArrayList<TrainStop> getStopInfo() {
		return stopInfo;
	}
	public void setStopInfo(ArrayList<TrainStop> stopInfo) {
		this.stopInfo = stopInfo;
	}
	public ArrayList<TrainCarriage> getCarriageInfo() {
		return carriageInfo;
	}
	public void setCarriageInfo(ArrayList<TrainCarriage> carriageInfo) {
		this.carriageInfo = carriageInfo;
	}
	public ArrayList<TrainSeats> getSeatsInfo() {
		return seatsInfo;
	}
	public void setSeatsInfo(ArrayList<TrainSeats> seatsInfo) {
		this.seatsInfo = seatsInfo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
