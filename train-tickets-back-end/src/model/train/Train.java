package model.train;

import java.util.ArrayList;

// 火车类
public class Train {
	private String trainId = "";
	private String trainName = "";
	private String trainType = "";
	
	//以下为非必需属性
	private String startIndex = "";
	private String endIndex = "";
	private String start = "";
	private String end = "";
	private String startTime = "";
	private String endTime = "";
	private String mileage = "";
	private ArrayList<TrainStop> stopInfo = null;
	private ArrayList<TrainSeats> seatsInfo = null;
	
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
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
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public ArrayList<TrainStop> getStopInfo() {
		return stopInfo;
	}
	public void setStopInfo(ArrayList<TrainStop> stopInfo) {
		this.stopInfo = stopInfo;
	}
	public ArrayList<TrainSeats> getSeatsInfo() {
		return seatsInfo;
	}
	public void setSeatsInfo(ArrayList<TrainSeats> seatsInfo) {
		this.seatsInfo = seatsInfo;
	}
	@Override
	public String toString() {
		return "Train [trainId=" + trainId + ", trainName=" + trainName + ", trainType=" + trainType + ", startIndex="
				+ startIndex + ", endIndex=" + endIndex + ", start=" + start + ", end=" + end + ", startTime="
				+ startTime + ", endTime=" + endTime + ", mileage=" + mileage + ", stopInfo=" + stopInfo
				+ ", seatsInfo=" + seatsInfo + "]";
	}
}
