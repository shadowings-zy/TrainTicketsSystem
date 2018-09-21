package model.train;

// 火车车站类
public class TrainStop {
	private String stopId = "";
	private String cityName = "";
	private String stationName = "";
	private String arriveTime = "";
	private String stopTime = "";
	private String mileage = "";
	
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	@Override
	public String toString() {
		return "TrainStop [stopId=" + stopId + ", cityName=" + cityName + ", stationName=" + stationName
				+ ", arriveTime=" + arriveTime + ", stopTime=" + stopTime + ", mileage=" + mileage + "]";
	}
}
