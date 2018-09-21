package model.train;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class TrainSeats {
	private String ctype = "";
	private int seatsCount = 0;
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public int getSeatsCount() {
		return seatsCount;
	}
	public void setSeatsCount(int seatsCount) {
		this.seatsCount = seatsCount;
	}
	
}
