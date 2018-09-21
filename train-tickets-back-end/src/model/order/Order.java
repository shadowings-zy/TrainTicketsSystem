package model.order;

// 订单类
public class Order {
	private String orderId = "";
	private String userId = "";
	private String passengerId = "";
	private String userName = "";
	private String trainId = "";
	private String trainName = "";
	private String carriage = "";
	private String seatType = "";
	private String seatId = "";
	private String seatLocation = "";
	private String startTime = "";
	private String startStopId = "";
	private String startStationName = "";
	private String endStopId = "";
	private String endStationName = "";
	private String date = "";
	private String createAt = "";
	private String status = "未付款";

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getSeatLocation() {
		return seatLocation;
	}

	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartStopId() {
		return startStopId;
	}

	public void setStartStopId(String startStopId) {
		this.startStopId = startStopId;
	}

	public String getStartStationName() {
		return startStationName;
	}

	public void setStartStationName(String startStationName) {
		this.startStationName = startStationName;
	}

	public String getEndStopId() {
		return endStopId;
	}

	public void setEndStopId(String endStopId) {
		this.endStopId = endStopId;
	}

	public String getEndStationName() {
		return endStationName;
	}

	public void setEndStationName(String endStationName) {
		this.endStationName = endStationName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String creatAt) {
		this.createAt = creatAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", passengerId=" + passengerId + ", userName="
				+ userName + ", trainId=" + trainId + ", trainName=" + trainName + ", carriage=" + carriage
				+ ", seatType=" + seatType + ", seatId=" + seatId + ", seatLocation=" + seatLocation + ", startTime="
				+ startTime + ", startStopId=" + startStopId + ", startStationName=" + startStationName + ", endStopId="
				+ endStopId + ", endStationName=" + endStationName + ", date=" + date + ", creatAt=" + createAt
				+ ", status=" + status + "]";
	}
}
