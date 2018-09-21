package model.train;

// 火车车座类
public class TrainSeats {
	private String seatId = "";
	private String carriage = "";
	private String seatType = "";
	private String seatLocation = "";
	
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
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
	public String getSeatLocation() {
		return seatLocation;
	}
	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
	}
	@Override
	public String toString() {
		return "TrainSeats [seat_id=" + seatId + ", carriage=" + carriage + ", seatType=" + seatType
				+ ", seatLocation=" + seatLocation + "]";
	}
}
