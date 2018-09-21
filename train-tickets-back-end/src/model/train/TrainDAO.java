package model.train;

import java.util.ArrayList;

// 火车相关方法接口
public interface TrainDAO {
	//查询所有通火车的城市
	public ArrayList<String> getAllCities();
	
	//根据选择的起止城市，获取火车ID
	public ArrayList<Train> getTrainByStop(String startCity, String endCity, String date);
	
	//根据选择的起止城市，获取可换乘的火车
	public ArrayList<TransferTrain> getTrainTransfer(String startCity, String endCity, String date);
	
	//根据火车ID获取火车信息
	public Train getTrainByTrainId(String trainId);
	
	//查询火车座位剩余
	public ArrayList<TrainSeats> getRemainingSeats(String tid, String date, String fromSid, String toSid);
}
