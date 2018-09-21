package model.train;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class TrainCarriage {
	private String ctype = "";
	private String capacity = "";
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
}
